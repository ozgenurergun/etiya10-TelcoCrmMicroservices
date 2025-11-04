package com.etiya.catalogservice.service.mappings;

import com.etiya.catalogservice.domain.entities.CharValue;
import com.etiya.catalogservice.domain.entities.Characteristic;
import com.etiya.catalogservice.service.dtos.requests.CharValue.CreateCharValueRequest;
import com.etiya.catalogservice.service.dtos.requests.CharValue.UpdateCharValueRequest;
import com.etiya.catalogservice.service.dtos.requests.Characteristic.CreateCharacteristicRequest;
import com.etiya.catalogservice.service.dtos.requests.Characteristic.UpdateCharacteristicRequest;
import com.etiya.catalogservice.service.dtos.responses.CharValue.CreatedCharValueResponse;
import com.etiya.catalogservice.service.dtos.responses.CharValue.GetListCharValueResponse;
import com.etiya.catalogservice.service.dtos.responses.CharValue.UpdatedCharValueResponse;
import com.etiya.catalogservice.service.dtos.responses.Characteristic.CreatedCharacteristicResponse;
import com.etiya.catalogservice.service.dtos.responses.Characteristic.GetListCharacteristicResponse;
import com.etiya.catalogservice.service.dtos.responses.Characteristic.UpdatedCharacteristicResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CharValueMapper {

    CharValueMapper INSTANCE = Mappers.getMapper(CharValueMapper.class);

    //create
    @Mapping(target = "characteristic.id", source = "characteristicId") //“Request içindeki GENELTYPE_ID alanını al, Characteristic içindeki GENELTYPE nesnesinin id alanına koy.”
    CharValue charValueFromCreateCharValueRequest(CreateCharValueRequest request);

    @Mapping(target = "characteristicId", source = "characteristic.id")
    CreatedCharValueResponse createdCharValueResponseFromCharValue(CharValue charValue);

    //update
    @Mapping(target = "characteristic.id", source = "characteristicId")
    CharValue charValueFromUpdateCharValueRequest(UpdateCharValueRequest request);

    @Mapping(target = "characteristic.id", ignore = true)
    CharValue charValueFromUpdateCharValueRequest(UpdateCharValueRequest request, @MappingTarget CharValue charValue);

    @Mapping(target = "characteristicId", source = "characteristic.id") //hedef responsa yapmak -- kaynak char.. entitysi
    UpdatedCharValueResponse updatedCharValueResponseFromCharValue(CharValue charValue);

    //getlist
    List<GetListCharValueResponse> getListCharValueResponseFromCharvalue(List<CharValue> charValues);
}
