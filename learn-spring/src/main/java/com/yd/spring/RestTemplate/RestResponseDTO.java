package com.yd.spring.RestTemplate;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Yd on  2018-07-20
 * @description
 **/
@Data
@Accessors(chain = true)
public class RestResponseDTO<T> {
    private T data;
    private String message;
    private int statusCode;
}
