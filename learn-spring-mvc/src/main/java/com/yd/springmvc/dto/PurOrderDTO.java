package com.yd.springmvc.dto;

import com.yd.annotation.ColumnDesc;
import lombok.Data;

/**
 * 请假单DTO
 *
 * @author Yd on  2018-03-06
 * @description
 **/
@Data
public class PurOrderDTO {

    @ColumnDesc(columnDesc = "用户Id")
    private String userId;

    @ColumnDesc(columnDesc = "流程实例Id")
    private String processInstanceId;

    @ColumnDesc(columnDesc = "机器执行Id")
    private String executionId;

    @ColumnDesc(columnDesc = "任务Id")
    private String taskId;

    @ColumnDesc(columnDesc = "执行信息")
    private String msg;

    @ColumnDesc(columnDesc = "请假天数")
    private Integer day;

    @ColumnDesc(columnDesc = "处理留言")
    private String comment;

    @ColumnDesc(columnDesc = "部署流程文件")
    private String deployFileName;
}
