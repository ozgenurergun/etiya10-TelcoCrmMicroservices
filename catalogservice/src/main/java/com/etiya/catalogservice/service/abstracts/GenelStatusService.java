package com.etiya.catalogservice.service.abstracts;

import com.etiya.catalogservice.service.dtos.requests.GENELSTATUS.CreateGenelStatusRequest;
import com.etiya.catalogservice.service.dtos.requests.GENELSTATUS.UpdateGenelStatusRequest;
import com.etiya.catalogservice.service.dtos.requests.GENELTYPE.CreateGenelTypeRequest;
import com.etiya.catalogservice.service.dtos.requests.GENELTYPE.UpdateGenelTypeRequest;
import com.etiya.catalogservice.service.dtos.responses.GENELSTATUS.CreatedGenelStatusResponse;
import com.etiya.catalogservice.service.dtos.responses.GENELSTATUS.GetListGenelStatusResponse;
import com.etiya.catalogservice.service.dtos.responses.GENELSTATUS.UpdatedGenelStatusResponse;
import com.etiya.catalogservice.service.dtos.responses.GENELTYPE.CreatedGenelTypeResponse;
import com.etiya.catalogservice.service.dtos.responses.GENELTYPE.GetListGenelTypeResponse;
import com.etiya.catalogservice.service.dtos.responses.GENELTYPE.UpdatedGenelTypeResponse;

import java.util.List;

public interface GenelStatusService {

    CreatedGenelStatusResponse add(CreateGenelStatusRequest createGenelStatusRequest);

    UpdatedGenelStatusResponse update(UpdateGenelStatusRequest request);

    List<GetListGenelStatusResponse> getAll();

    void deleteById(int id);
}
