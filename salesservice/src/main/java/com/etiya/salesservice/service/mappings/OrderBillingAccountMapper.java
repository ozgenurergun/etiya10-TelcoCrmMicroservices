package com.etiya.salesservice.service.mappings;

import com.etiya.common.responses.BillingAccountResponse;
import com.etiya.common.responses.IndividualCustomerResponse;
import com.etiya.salesservice.domain.OrderBillingAccount;
import com.etiya.salesservice.domain.OrderCustomer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderBillingAccountMapper {

    OrderBillingAccountMapper INSTANCE = Mappers.getMapper(OrderBillingAccountMapper.class);

    OrderBillingAccount orderBillingAccountFromBillingAccount(BillingAccountResponse billingAccountResponse);
}
