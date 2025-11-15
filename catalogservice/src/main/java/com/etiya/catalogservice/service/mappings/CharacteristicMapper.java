package com.etiya.catalogservice.service.mappings;

import com.etiya.catalogservice.domain.entities.Characteristic;
import com.etiya.catalogservice.domain.entities.GENELTYPE;
import com.etiya.catalogservice.service.dtos.requests.Characteristic.CreateCharacteristicRequest;
import com.etiya.catalogservice.service.dtos.requests.Characteristic.UpdateCharacteristicRequest;
import com.etiya.catalogservice.service.dtos.responses.Characteristic.CreatedCharacteristicResponse;
import com.etiya.catalogservice.service.dtos.responses.Characteristic.GetListCharacteristicResponse;
import com.etiya.catalogservice.service.dtos.responses.Characteristic.GetListCharacteristicWithCharValResponse;
import com.etiya.catalogservice.service.dtos.responses.Characteristic.UpdatedCharacteristicResponse;
import com.etiya.customerservice.domain.entities.District;
import com.etiya.customerservice.service.requests.district.CreateDistrictRequest;
import com.etiya.customerservice.service.responses.district.CreatedDistrictResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CharacteristicMapper {

    //source --> requestteki alan target --> entityde

    CharacteristicMapper INSTANCE = Mappers.getMapper(CharacteristicMapper.class);

    //create
    @Mapping(target = "genelType.id", source = "genelTypeId") //“Request içindeki GENELTYPE_ID alanını al, Characteristic içindeki GENELTYPE nesnesinin id alanına koy.”
    Characteristic characteristicFromCreateCharacteristicRequest(CreateCharacteristicRequest request);

    @Mapping(target = "genelTypeId", source = "genelType.id")
    CreatedCharacteristicResponse  createdCharacteristicResponseFromCharacteristic(Characteristic characteristic);

    //update
    @Mapping(target = "genelType.id", source = "genelTypeId")
    Characteristic characteristicFromUpdateCharacteristicRequest(UpdateCharacteristicRequest request);

    @Mapping(target = "genelType.id", ignore = true)
    Characteristic characteristicFromUpdateCharacteristicRequest(UpdateCharacteristicRequest request, @MappingTarget Characteristic characteristic);

    @Mapping(target = "genelTypeId", source = "genelType.id") //hedef responsa yapmak -- kaynak char.. entitysi
    UpdatedCharacteristicResponse updatedCharacteristicResponseFromCharacteristic(Characteristic characteristic);

    //getlist
    List<GetListCharacteristicResponse> getListCharacteristicResponseFromCharacteristic(List<Characteristic> characteristics);

    GetListCharacteristicWithCharValResponse getListCharacteristicWithCharValResponseFromCharacteristic(Characteristic characteristics);

    List<GetListCharacteristicWithCharValResponse> getListCharacteristicWithCharValResponseFromCharacteristic(List<Characteristic> characteristics);


}
