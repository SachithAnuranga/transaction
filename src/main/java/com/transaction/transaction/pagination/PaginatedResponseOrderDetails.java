package com.transaction.transaction.pagination;

import com.transaction.transaction.dto.response.ResponseOrderDetailsDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaginatedResponseOrderDetails {
    private List<ResponseOrderDetailsDto> responseOrderDetailsDtoList;
    private long dataCount;
}
