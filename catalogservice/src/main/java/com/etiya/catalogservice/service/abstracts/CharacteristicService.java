package com.etiya.catalogservice.service.abstracts;

import com.etiya.catalogservice.domain.entities.Characteristic;
import com.etiya.catalogservice.service.dtos.requests.Characteristic.CreateCharacteristicRequest;
import com.etiya.catalogservice.service.dtos.requests.Characteristic.UpdateCharacteristicRequest;
import com.etiya.catalogservice.service.dtos.responses.Characteristic.*;
import com.etiya.common.responses.GetListCharacteristicWithoutCharValResponse;

import java.util.List;

public interface CharacteristicService {

    CreatedCharacteristicResponse add(CreateCharacteristicRequest request);

    UpdatedCharacteristicResponse update(UpdateCharacteristicRequest request);

    List<GetListCharacteristicResponse> getAll();

    void deleteById(int id);

    Characteristic findById(int id);

    List<GetListCharacteristicWithCharValResponse> getAllByProdSpecId(int prodSpecId);

    List<GetListCharacteristicWithoutCharValResponse> getAllCharacteristicByProdSpecId(int prodSpecId);
}
