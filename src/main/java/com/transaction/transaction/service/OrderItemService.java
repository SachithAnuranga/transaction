package com.transaction.transaction.service;

import com.transaction.transaction.entity.OrderItem;

import java.util.List;

public interface OrderItemService {
    OrderItem saveOrderItem(OrderItem orderItem);

    OrderItem getOrderItemById(Long id);

    List<OrderItem> getAllOrderItems();

    void deleteOrderItem(Long id);

    List<OrderItem> saveAllOrderItem(List<OrderItem> orderItemList);
}
