package com.etiya.catalogservice.service.concretes;

import com.etiya.catalogservice.domain.entities.CharValue;
import com.etiya.catalogservice.domain.entities.Characteristic;
import com.etiya.catalogservice.domain.entities.GENELTYPE;
import com.etiya.catalogservice.domain.entities.ProductSpecCharacteristic;
import com.etiya.catalogservice.repository.CharacteristicRepository;
import com.etiya.catalogservice.service.abstracts.CharacteristicService;
import com.etiya.catalogservice.service.abstracts.GenelTypeService;
import com.etiya.catalogservice.service.dtos.requests.Characteristic.CreateCharacteristicRequest;
import com.etiya.catalogservice.service.dtos.requests.Characteristic.UpdateCharacteristicRequest;
import com.etiya.catalogservice.service.dtos.responses.Characteristic.*;
import com.etiya.catalogservice.service.mappings.CharValueMapper;
import com.etiya.catalogservice.service.mappings.CharacteristicMapper;
import com.etiya.common.responses.CharValueForCharResponse;
import com.etiya.common.responses.GetListCharacteristicWithoutCharValResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CharacteristicServiceImpl implements CharacteristicService {

    private final CharacteristicRepository characteristicRepository;
    private final GenelTypeService genelTypeService;
    private final ProductSpecCharacteristicLookupService productSpecCharacteristicLookupService;
    private final CharValueLookupService charValueLookupService;

    public CharacteristicServiceImpl(CharacteristicRepository characteristicRepository, GenelTypeService genelTypeService, ProductSpecCharacteristicLookupService productSpecCharacteristicLookupService, CharValueLookupService charValueLookupService) {
        this.characteristicRepository = characteristicRepository;
        this.genelTypeService = genelTypeService;
        this.productSpecCharacteristicLookupService = productSpecCharacteristicLookupService;
        this.charValueLookupService = charValueLookupService;
    }

    @Override
    public CreatedCharacteristicResponse add(CreateCharacteristicRequest request) {
        Characteristic characteristic = CharacteristicMapper.INSTANCE.characteristicFromCreateCharacteristicRequest(request);

        GENELTYPE genelType = genelTypeService.findById(request.getGenelTypeId());

        characteristic.setGenelType(genelType);

        characteristicRepository.save(characteristic);

        CreatedCharacteristicResponse response = CharacteristicMapper.INSTANCE.createdCharacteristicResponseFromCharacteristic(characteristic);
        return response;
    }

    @Override
    public UpdatedCharacteristicResponse update(UpdateCharacteristicRequest request) {
        Characteristic oldCharacteristic = characteristicRepository.findById(request.getId()).orElseThrow(() -> new RuntimeException("Characteristic not found"));

        GENELTYPE genelType = genelTypeService.findById(request.getGenelTypeId());

        Characteristic updatedCharacteristic = CharacteristicMapper.INSTANCE.characteristicFromUpdateCharacteristicRequest(request, oldCharacteristic);

        updatedCharacteristic.setGenelType(genelType);
        characteristicRepository.save(updatedCharacteristic);

        UpdatedCharacteristicResponse response = CharacteristicMapper.INSTANCE.updatedCharacteristicResponseFromCharacteristic(updatedCharacteristic);
        return response;
    }

    @Override
    public List<GetListCharacteristicResponse> getAll() {
        List<Characteristic> characteristics = characteristicRepository.findAll();
        List<GetListCharacteristicResponse> responses = CharacteristicMapper.INSTANCE.getListCharacteristicResponseFromCharacteristic(characteristics);
        return responses;
    }

    @Override
    public void deleteById(int id) {
        Characteristic characteristic = characteristicRepository.findById(id).orElseThrow(() -> new RuntimeException("Characteristic not found"));

        characteristic.setDeletedDate(LocalDateTime.now());
        characteristic.setIsActive(0);
        characteristicRepository.save(characteristic);
    }

    @Override
    public Characteristic findById(int id) {
        return characteristicRepository.findById(id).orElseThrow(() -> new RuntimeException("Characteristic not found"));
    }

    @Override
    public List<GetListCharacteristicWithCharValResponse> getAllByProdSpecId(int prodSpecId) {
        List<ProductSpecCharacteristic> prodSpecs = productSpecCharacteristicLookupService.getByProdSpecId(prodSpecId);
        List<Characteristic> characteristics = new ArrayList<>();
        List<GetListCharacteristicWithCharValResponse> responses = new ArrayList<>();
        for (ProductSpecCharacteristic prodSpec : prodSpecs) {
            GetListCharacteristicWithCharValResponse response = CharacteristicMapper.INSTANCE.
                    getListCharacteristicWithCharValResponseFromCharacteristic(prodSpec.getCharacteristic());
                response.setRequired(prodSpec.getRequired());
            if (prodSpec.getCharacteristic().getUnitOfMeasure().equals("Metin")) {
                List<CharValueForCharResponse> charValResponses = new ArrayList<>();
                response.setCharValues(charValResponses);
                responses.add(response);
            } else {
                List<CharValue> charValues = charValueLookupService.getCharValListByCharId(prodSpec.getCharacteristic().getId());
                List<CharValueForCharResponse> charValResponses = CharValueMapper.INSTANCE.getListCharValueForCharResponseFromCharValue(charValues);
                response.setCharValues(charValResponses);
                responses.add(response);
            }
        }
        return responses;

    }

    @Override
    public List<GetListCharacteristicWithoutCharValResponse> getAllCharacteristicByProdSpecId(int prodSpecId) {
        List<ProductSpecCharacteristic> prodSpecs = productSpecCharacteristicLookupService.getByProdSpecId(prodSpecId);
        List<Characteristic> characteristics = new ArrayList<>();
        List<GetListCharacteristicWithoutCharValResponse> responses = new ArrayList<>();
        for (ProductSpecCharacteristic prodSpec : prodSpecs) {
            GetListCharacteristicWithoutCharValResponse response = CharacteristicMapper.INSTANCE.
                    getListCharacteristicWithoutCharValResponseFromCharacteristic(prodSpec.getCharacteristic());
            response.setRequired(prodSpec.getRequired());
            CharValueForCharResponse charValResponse = new CharValueForCharResponse();
            response.setCharValue(charValResponse);
            responses.add(response);
        }
        return responses;
    }
}
