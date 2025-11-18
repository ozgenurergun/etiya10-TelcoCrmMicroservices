package com.etiya.customerservice.service.concretes;

import com.etiya.common.responses.BillingAccountResponse;
import com.etiya.customerservice.domain.entities.Address;
import com.etiya.customerservice.domain.entities.BillingAccount;
import com.etiya.customerservice.domain.enums.BillingAccountStatus;
import com.etiya.customerservice.repository.BillingAccountRepository;
import com.etiya.customerservice.service.abstracts.AddressService;
import com.etiya.customerservice.service.abstracts.BillingAccountService;
import com.etiya.customerservice.service.mappings.BillingAccountMapper;
import com.etiya.customerservice.service.requests.billingAccount.CreateBillingAccountRequest;
import com.etiya.customerservice.service.requests.billingAccount.UpdateBillingAccountRequest;
import com.etiya.customerservice.service.responses.billingAccount.CreatedBillingAccountResponse;
import com.etiya.customerservice.service.responses.billingAccount.GetListBillingAccountResponse;
import com.etiya.customerservice.service.responses.billingAccount.UpdatedBillingAccountResponse;
import com.etiya.customerservice.service.rules.BillingAccountBusinessRules;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BillingAccountServiceImpl implements BillingAccountService {

    private final BillingAccountRepository billingAccountRepository;
    private final BillingAccountBusinessRules billingAccountBusinessRules;
    private final AddressService addressService;

    public BillingAccountServiceImpl(BillingAccountRepository billingAccountRepository, BillingAccountBusinessRules billingAccountBusinessRules, AddressService addressService) {
        this.billingAccountRepository = billingAccountRepository;
        this.billingAccountBusinessRules = billingAccountBusinessRules;
        this.addressService = addressService;
    }

    @Override
    public BillingAccountResponse getById(int id) {
        BillingAccount billingAccount = billingAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Billing Account not found with id: " + id));
        // Feign Client'ın beklediği "common" response tipine map'le
        BillingAccountResponse response = BillingAccountMapper.INSTANCE.billingAccountResponseFromBillingAccount(billingAccount);
        return response;
    }

    @Override
    public List<GetListBillingAccountResponse> getByCustomerId(String customerId) {
        List<GetListBillingAccountResponse> responses = new ArrayList<>();
        List<BillingAccount> all = billingAccountRepository.findAll();
        for (BillingAccount billingAccount : all) {
            if(billingAccount.getCustomer().getId().toString().equals(customerId)){
                GetListBillingAccountResponse response = BillingAccountMapper.INSTANCE.getListBillingAccountResponseFromBillingAccount(billingAccount);
                responses.add(response);
            }
        }
        return responses;
    }


    @Override
    public CreatedBillingAccountResponse add(CreateBillingAccountRequest request) {
        billingAccountBusinessRules.checkIfCustomerHasAddress(request.getCustomerId());
        billingAccountBusinessRules.checkIfAddressBelongsToCustomer(request.getAddressId(), request.getCustomerId());
        billingAccountBusinessRules.checkIfCustomerTypeMatchesAccountType(
                request.getCustomerId(),
                request.getType()
        );

        BillingAccount billingAccount = BillingAccountMapper.INSTANCE
                .billingAccountFromCreateBillingAccountRequest(request);

        billingAccount.setStatus(BillingAccountStatus.ACTIVE);

        BillingAccount created = billingAccountRepository.save(billingAccount);

        return BillingAccountMapper.INSTANCE
                .createdBillingAccountResponseFromBillingAccount(created);
    }

    @Override
    public UpdatedBillingAccountResponse update(UpdateBillingAccountRequest request) {
        BillingAccount billingAccount = billingAccountRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("Billing Account not found"));

        billingAccountBusinessRules.checkIfTypeCanBeChanged(request.getId(), request.getType());

        boolean addressChanged = billingAccount.getAddress() == null
                || billingAccount.getAddress().getId() != (request.getAddressId());
        if (addressChanged) {
            billingAccountBusinessRules.checkIfAddressBelongsToCustomer(
                    request.getAddressId(),
                    request.getCustomerId()
            );
        }

        if (request.getStatus() != null && request.getStatus() != billingAccount.getStatus()) {
            billingAccountBusinessRules.checkIfStatusTransitionIsValid(
                    request.getId(),
                    request.getStatus()
            );
        }

        // mapper sadece diğer alanları günceller
        BillingAccountMapper.INSTANCE.updateBillingAccountFromRequest(request, billingAccount);

        // 🔥 Adres değiştiyse, servisten Address entity'sini al
        if (addressChanged) {
            Address newAddress = addressService.findById(request.getAddressId());
            billingAccount.setAddress(newAddress);
        }

        BillingAccount updated = billingAccountRepository.save(billingAccount);

        return BillingAccountMapper.INSTANCE
                .updatedBillingAccountResponseFromBillingAccount(updated);
    }

    @Override
    public List<GetListBillingAccountResponse> getList() {
        return billingAccountRepository.findAll().stream().map( billingAccount -> {
            GetListBillingAccountResponse response = new GetListBillingAccountResponse();
            response.setId(billingAccount.getId());
            response.setAccountName(billingAccount.getAccountName());
            response.setCustomerId(billingAccount.getCustomer().getId());
            response.setAddressId(billingAccount.getAddress().getId());
            response.setAccountNumber(billingAccount.getAccountNumber());
            response.setStatus(billingAccount.getStatus());
            response.setType(billingAccount.getType());
            return response;
        }).toList();
    }

    @Override
    public void delete(int id) {
        billingAccountBusinessRules.checkIfBillingAccountCanBeDeleted(id);
        BillingAccount billingAccount = billingAccountRepository.findById(id).orElseThrow(() -> new RuntimeException("Billing Account not found"));
        billingAccountRepository.delete(billingAccount);
    }

    @Override
    public void softDelete(int id) {
        BillingAccount billingAccount = billingAccountRepository.findById(id).orElseThrow(() -> new RuntimeException("Billing Account not found"));
        billingAccount.setDeletedDate(LocalDateTime.now());
        billingAccount.setIsActive(0);
        billingAccountRepository.save(billingAccount);
    }
}
