package com.etiya.salesservice.service.mappings;

import com.etiya.common.responses.IndividualCustomerResponse;
import com.etiya.salesservice.domain.OrderCustomer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderCustomerMapper {

    OrderCustomerMapper INSTANCE = Mappers.getMapper(OrderCustomerMapper.class);

    OrderCustomer orderCustomerFromIndividualCustomer(IndividualCustomerResponse individualCustomerResponse);
}
