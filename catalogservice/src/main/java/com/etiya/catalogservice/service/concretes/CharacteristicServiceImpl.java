package com.etiya.catalogservice.service.concretes;

import com.etiya.catalogservice.domain.entities.Characteristic;
import com.etiya.catalogservice.domain.entities.GENELTYPE;
import com.etiya.catalogservice.repository.CharacteristicRepository;
import com.etiya.catalogservice.service.abstracts.CharacteristicService;
import com.etiya.catalogservice.service.abstracts.GenelTypeService;
import com.etiya.catalogservice.service.dtos.requests.Characteristic.CreateCharacteristicRequest;
import com.etiya.catalogservice.service.dtos.requests.Characteristic.UpdateCharacteristicRequest;
import com.etiya.catalogservice.service.dtos.responses.Characteristic.CreatedCharacteristicResponse;
import com.etiya.catalogservice.service.dtos.responses.Characteristic.GetListCharacteristicResponse;
import com.etiya.catalogservice.service.dtos.responses.Characteristic.UpdatedCharacteristicResponse;
import com.etiya.catalogservice.service.mappings.CharacteristicMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CharacteristicServiceImpl implements CharacteristicService {

    private final CharacteristicRepository characteristicRepository;
    private final GenelTypeService genelTypeService;

    public CharacteristicServiceImpl(CharacteristicRepository characteristicRepository, GenelTypeService genelTypeService) {
        this.characteristicRepository = characteristicRepository;
        this.genelTypeService = genelTypeService;
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
}
