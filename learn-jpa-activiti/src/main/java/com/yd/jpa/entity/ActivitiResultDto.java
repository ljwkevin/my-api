package com.yd.jpa.entity;

import lombok.Data;

/**
 * @author Yd on  2018-03-01
 * @description
 **/
@Data
public class ActivitiResultDto {

    private  String deployFile;
    private  String deployKey;
    private  String person;
    private  String taskId;
    private  String taskName;
    private  String msg;

}
