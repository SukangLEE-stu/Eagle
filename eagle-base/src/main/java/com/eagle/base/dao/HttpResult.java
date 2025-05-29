package com.eagle.base.dao;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HttpResult {
    private int code;
    private String message;
    private Object data;
    private Object extra;
}
