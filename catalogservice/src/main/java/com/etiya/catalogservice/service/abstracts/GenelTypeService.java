package com.etiya.catalogservice.service.abstracts;

import com.etiya.catalogservice.domain.entities.GENELTYPE;
import com.etiya.catalogservice.service.dtos.requests.GENELTYPE.CreateGenelTypeRequest;
import com.etiya.catalogservice.service.dtos.requests.GENELTYPE.UpdateGenelTypeRequest;
import com.etiya.catalogservice.service.dtos.responses.GENELTYPE.CreatedGenelTypeResponse;
import com.etiya.catalogservice.service.dtos.responses.GENELTYPE.GetListGenelTypeResponse;
import com.etiya.catalogservice.service.dtos.responses.GENELTYPE.UpdatedGenelTypeResponse;
import com.etiya.customerservice.service.responses.city.GetListCityResponse;

import java.util.List;

public interface GenelTypeService {

    CreatedGenelTypeResponse add(CreateGenelTypeRequest createGenelTypeRequest);

    UpdatedGenelTypeResponse update(UpdateGenelTypeRequest request);

    List<GetListGenelTypeResponse> getAll();

    void deleteById(int id);
}
