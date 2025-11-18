package com.etiya.salesservice.service.mappings;

import com.etiya.common.responses.AddressResponse;
import com.etiya.common.responses.IndividualCustomerResponse;
import com.etiya.salesservice.domain.OrderAddress;
import com.etiya.salesservice.domain.OrderCustomer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderAddressMapper {

    OrderAddressMapper INSTANCE = Mappers.getMapper(OrderAddressMapper.class);

    OrderAddress orderAddressFromAddress(AddressResponse addressResponse);
}
