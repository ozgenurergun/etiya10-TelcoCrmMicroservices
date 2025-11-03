package com.etiya.catalogservice.service.concretes;

import com.etiya.catalogservice.domain.entities.CharValue;
import com.etiya.catalogservice.domain.entities.Characteristic;
import com.etiya.catalogservice.repository.CharacteristicRepository;
import com.etiya.catalogservice.service.abstracts.CharacteristicService;
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

    public CharacteristicServiceImpl(CharacteristicRepository characteristicRepository) {
        this.characteristicRepository = characteristicRepository;
    }

    @Override
    public CreatedCharacteristicResponse add(CreateCharacteristicRequest request) {
        Characteristic characteristic = CharacteristicMapper.INSTANCE.characteristicFromCreateCharacteristicRequest(request);
        Characteristic createdCharacteristic = characteristicRepository.save(characteristic);
        CreatedCharacteristicResponse response = CharacteristicMapper.INSTANCE.createdCharacteristicResponseFromCharacteristic(createdCharacteristic);
        return response;
    }

    @Override
    public UpdatedCharacteristicResponse update(UpdateCharacteristicRequest request) {
        Characteristic oldCharacteristic = characteristicRepository.findById(request.getId()).orElseThrow(() -> new RuntimeException("Characteristic not found"));

        Characteristic characteristic = CharacteristicMapper.INSTANCE.characteristicFromUpdateCharacteristicRequest(request, oldCharacteristic);
        Characteristic updateCharacteristic = characteristicRepository.save(characteristic);

        UpdatedCharacteristicResponse response = CharacteristicMapper.INSTANCE.updatedCharacteristicResponseFromCharacteristic(updateCharacteristic);
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
}
