package com.etiya.catalogservice.service.mappings;

import com.etiya.catalogservice.domain.entities.GENELTYPE;
import com.etiya.catalogservice.service.dtos.requests.GENELTYPE.CreateGenelTypeRequest;
import com.etiya.catalogservice.service.dtos.requests.GENELTYPE.UpdateGenelTypeRequest;
import com.etiya.catalogservice.service.dtos.responses.GENELTYPE.CreatedGenelTypeResponse;
import com.etiya.catalogservice.service.dtos.responses.GENELTYPE.GetListGenelTypeResponse;
import com.etiya.catalogservice.service.dtos.responses.GENELTYPE.UpdatedGenelTypeResponse;
import com.etiya.customerservice.domain.entities.City;
import com.etiya.customerservice.service.responses.city.GetListCityResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface GenelTypeMapper {

    GenelTypeMapper INSTANCE = Mappers.getMapper(GenelTypeMapper.class);

    //create
    GENELTYPE genelTypeFromGenelTypeRequest(CreateGenelTypeRequest createGenelTypeRequest);
    CreatedGenelTypeResponse createdGenelTypeResponseFromGenelType(GENELTYPE genelType);

    //update
    GENELTYPE genelTypeFromUpdateGenelTypeRequest(UpdateGenelTypeRequest request);
    GENELTYPE genelTypeFromUpdateGenelTypeRequest(UpdateGenelTypeRequest request, @MappingTarget GENELTYPE genelType);
    UpdatedGenelTypeResponse updatedGenelTypeResponseFromGenelType(GENELTYPE genelType);

    //getlist
    List<GetListGenelTypeResponse> getListGenelTypeResponseFromGenelType(List<GENELTYPE> genelTypes);

}
