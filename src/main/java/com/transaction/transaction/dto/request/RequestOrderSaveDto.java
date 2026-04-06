package com.transaction.transaction.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestOrderSaveDto {

    private String product;

    private double total;

    private LocalDateTime orderDate;

    // Reference customer by ID (best practice)
    private Long customerId;

    private boolean activeState;

    // Order items included in this order
    private List<OrderItemRequestSaveDto> items;
}
