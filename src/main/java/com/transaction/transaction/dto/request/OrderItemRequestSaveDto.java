package com.transaction.transaction.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemRequestSaveDto {

    private String itemName;

    private int quantity;

    private double amount;

    // Reference Order by ID (avoid passing entity)
    private Long orderId;

    // Reference Item by ID
    private Long itemId;
}
