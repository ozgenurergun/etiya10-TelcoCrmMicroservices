package com.etiya.catalogservice.service.abstracts;

import com.etiya.catalogservice.domain.entities.CharValue;
import com.etiya.catalogservice.service.dtos.requests.CharValue.CreateCharValueRequest;
import com.etiya.catalogservice.service.dtos.requests.CharValue.UpdateCharValueRequest;
import com.etiya.catalogservice.service.dtos.responses.CharValue.CreatedCharValueResponse;
import com.etiya.catalogservice.service.dtos.responses.CharValue.GetListCharValueResponse;
import com.etiya.catalogservice.service.dtos.responses.CharValue.UpdatedCharValueResponse;

import java.util.List;

public interface CharValueService {

    CreatedCharValueResponse add(CreateCharValueRequest request);

    UpdatedCharValueResponse update(UpdateCharValueRequest request);

    List<GetListCharValueResponse> getAll();

    void deleteById(int id);

    CharValue findById(int id);

}
