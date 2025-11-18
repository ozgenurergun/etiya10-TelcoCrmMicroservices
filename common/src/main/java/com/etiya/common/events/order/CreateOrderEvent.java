package com.etiya.common.events.order;

public record CreateOrderEvent(String orderId, int billingAccountId) {
}
