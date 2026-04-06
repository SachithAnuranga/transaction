package com.transaction.transaction.response;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StandardResponse {
    private int code;
    private Object data;
    private String msg;
}
