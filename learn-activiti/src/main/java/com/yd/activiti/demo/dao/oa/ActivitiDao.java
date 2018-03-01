package com.yd.activiti.demo.dao.oa;

import org.springframework.stereotype.Component;

/**
 * Activiti相关DAO操作
 * @author Yd on  2018-03-01
 * @description
 **/
public interface ActivitiDao {
    int deleteFormPropertyByProcessInstanceId(String processInstanceId);
}
