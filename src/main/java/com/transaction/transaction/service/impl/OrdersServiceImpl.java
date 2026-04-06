package com.transaction.transaction.service.impl;

import com.transaction.transaction.QueryInterface.QueryDetailInterface;
import com.transaction.transaction.dto.request.OrderItemRequestSaveDto;
import com.transaction.transaction.dto.request.RequestOrderSaveDto;
import com.transaction.transaction.dto.response.ResponseOrderDetailsDto;
import com.transaction.transaction.entity.Customer;
import com.transaction.transaction.entity.Items;
import com.transaction.transaction.entity.OrderItem;
import com.transaction.transaction.exception.OrderSaveException;
import com.transaction.transaction.pagination.PaginatedResponseOrderDetails;
import com.transaction.transaction.repo.OrdersRepository;
import com.transaction.transaction.service.CustomerService;
import com.transaction.transaction.service.ItemsService;
import com.transaction.transaction.service.OrderItemService;
import com.transaction.transaction.service.OrdersService;
import com.transaction.transaction.entity.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;


    @Autowired
    private CustomerService customerService;

    @Autowired
    private ItemsService itemsService;

    @Autowired
    private OrderItemService orderItemService;

    @Override
    @Transactional
    public String saveOrder(RequestOrderSaveDto requestOrderSaveDto) {

        // 1️⃣ Fetch customer (throws exception if not found)
        Customer customer = customerService
                .getCustomerById(requestOrderSaveDto.getCustomerId());

        // 2️⃣ Create & save order
        Orders order = Orders.builder()
                .product(requestOrderSaveDto.getProduct())
                .total(requestOrderSaveDto.getTotal())
                .orderDate(requestOrderSaveDto.getOrderDate())
                .customer(customer)
                .build();

        Orders savedOrder = ordersRepository.save(order);

        // Safety check (optional but clean)
        if (savedOrder.getId() == null) {
            throw new OrderSaveException("Order save failed");
        }

        // 3️⃣ Prepare OrderItems
        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemRequestSaveDto dto : requestOrderSaveDto.getItems()) {

            Items item = itemsService.getItemById(dto.getItemId());

            OrderItem orderItem = OrderItem.builder()
                    .itemName(dto.getItemName())
                    .quantity(dto.getQuantity())
                    .amount(dto.getAmount())
                    .orders(savedOrder)
                    .items(item)
                    .build();

            orderItems.add(orderItem);
        }

        // 4️⃣ Save OrderItems (if fails → rollback)
        List<OrderItem> savedItems =
                orderItemService.saveAllOrderItem(orderItems);

        if (savedItems == null || savedItems.isEmpty()) {
            throw new OrderSaveException("Order items save failed");
        }


        // 5️⃣ Attach items
        savedOrder.setOrderItems(savedItems);

        return "SAVED";
    }

    @Override
    public Orders getOrderById(Long id) {
        return ordersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id " + id));
    }

    @Override
    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }

    @Override
    public void deleteOrder(Long id) {
        ordersRepository.deleteById(id);
    }

    @Override
    public PaginatedResponseOrderDetails getAllOrdersDetails(boolean status, int page, int size) {
       List<QueryDetailInterface> queryDetailInterfaces = ordersRepository.getAllOrderDetails(status, PageRequest.of(page, size));
       List<ResponseOrderDetailsDto> responseOrderDetailsDtoList = new ArrayList<>();
       for (QueryDetailInterface queryDetail : queryDetailInterfaces){
           ResponseOrderDetailsDto responseOrderDetailsDto = new ResponseOrderDetailsDto(queryDetail.getName(),
                   queryDetail.getEmail(), queryDetail.getOrderDate(), queryDetail.getTotal());
           responseOrderDetailsDtoList.add(responseOrderDetailsDto);
       }

        return new PaginatedResponseOrderDetails(responseOrderDetailsDtoList,
                ordersRepository.countAllOrderDetails(status));
    }

}
