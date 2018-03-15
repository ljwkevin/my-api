package com.yd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Yd on  2018-01-16
 * @Description：
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable{
    private static final long serialVersionUID = -1L;
    private Integer id;
    private String name;
    private String addr;
}
