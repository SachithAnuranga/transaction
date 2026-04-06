package com.transaction.transaction.controller;

import com.transaction.transaction.dto.request.RequestOrderSaveDto;
import com.transaction.transaction.pagination.PaginatedResponseOrderDetails;
import com.transaction.transaction.response.StandardResponse;
import com.transaction.transaction.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.transaction.transaction.enums.ActiveState.ACTIVE;
import static com.transaction.transaction.enums.ActiveState.INACTIVE;


@RestController
@RequestMapping("api/orders")
public class OrderController {


    @Autowired
    private OrdersService ordersService;

    @PostMapping("/save")
    public ResponseEntity<StandardResponse> saveOrder(
            @RequestBody RequestOrderSaveDto dto) {

        String result = ordersService.saveOrder(dto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new StandardResponse(201, result, "Order saved successfully"));
    }

    @GetMapping(params = {"stateType", "page", "size"}, path = {"/get-order-details"})
    public ResponseEntity<StandardResponse> getAllOrderDetails(
            @RequestParam String stateType,
            @RequestParam int page,
            @RequestParam int size) {

        if (!stateType.equalsIgnoreCase(String.valueOf(ACTIVE)) &&
                !stateType.equalsIgnoreCase(String.valueOf(INACTIVE))) {

            return ResponseEntity.badRequest().body(
                    new StandardResponse(400, null,
                            "Invalid stateType. Use active or inactive")
            );
        }

        boolean status = stateType.equalsIgnoreCase(String.valueOf(ACTIVE));
        PaginatedResponseOrderDetails data =
                ordersService.getAllOrdersDetails(status, page, size);

        return ResponseEntity.ok(
                new StandardResponse(200, data, "Success")
        );
    }
}
