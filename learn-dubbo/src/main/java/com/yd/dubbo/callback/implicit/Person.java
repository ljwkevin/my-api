package com.yd.dubbo.callback.implicit;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Yd on  2018-06-02
 * @description
 **/
@Data
@AllArgsConstructor
public class Person implements Serializable{
    Integer id;
    String name;
    int age;
}
