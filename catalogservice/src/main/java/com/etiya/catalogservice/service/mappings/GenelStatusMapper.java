package com.etiya.catalogservice.service.mappings;

import com.etiya.catalogservice.domain.entities.GENELSTATUS;
import com.etiya.catalogservice.domain.entities.GENELTYPE;
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
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface GenelStatusMapper {

    GenelStatusMapper INSTANCE = Mappers.getMapper(GenelStatusMapper.class);

    //create
    GENELSTATUS genelStatusFromGenelStatusRequest(CreateGenelStatusRequest request);
    CreatedGenelStatusResponse createdGenelStatusResponseFromGenelStatus(GENELSTATUS genelStatus);

    //update
    GENELSTATUS genelStatusFromUpdateGenelStatusRequest(UpdateGenelStatusRequest request);
    GENELSTATUS genelStatusFromUpdateGenelStatusRequest(UpdateGenelStatusRequest request, @MappingTarget GENELSTATUS genelStatus);
    UpdatedGenelStatusResponse updatedGenelStatusResponseFromGenelStatus(GENELSTATUS genelStatus);

    //getlist
    List<GetListGenelStatusResponse> getListGenelStatusResponseFromGenelStatus(List<GENELSTATUS> genelstatuses);
}
