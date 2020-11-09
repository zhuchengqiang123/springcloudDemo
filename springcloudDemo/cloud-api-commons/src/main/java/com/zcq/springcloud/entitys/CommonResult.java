package com.zcq.springcloud.entities;

import lombok.Data;

/**
 * 传给前端的类
 *
 * @param <T>
 */
@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class CommonResult<T> {
    //404 not found
    private Integer code;
    private String message;
    private T data;

    public CommonResult() {
    }
    public CommonResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public CommonResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
