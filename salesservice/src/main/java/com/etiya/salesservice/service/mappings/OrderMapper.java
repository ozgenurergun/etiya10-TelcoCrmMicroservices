package com.etiya.salesservice.service.mappings;

import com.etiya.salesservice.domain.Order;
import com.etiya.salesservice.service.dtos.responses.GetOrderResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(source = "billingAccountSnapshot", target = "billingAccount")
    @Mapping(source = "addressSnapshot", target = "address")
    @Mapping(source = "items", target = "items")
    GetOrderResponse orderToGetOrderResponse(Order order);

    List<GetOrderResponse> ordersToGetOrderResponses(List<Order> orders);
}
