package com.transaction.transaction.service;

import com.transaction.transaction.dto.request.RequestOrderSaveDto;
import com.transaction.transaction.entity.Orders;
import com.transaction.transaction.pagination.PaginatedResponseOrderDetails;

import java.util.List;

public interface OrdersService {

    public String saveOrder(RequestOrderSaveDto requestOrderSaveDto) ;

    Orders getOrderById(Long id);

    List<Orders> getAllOrders();

    void deleteOrder(Long id);

    PaginatedResponseOrderDetails getAllOrdersDetails(boolean status, int page, int size);
}
