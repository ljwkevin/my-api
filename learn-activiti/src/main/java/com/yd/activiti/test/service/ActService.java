package com.yd.activiti.test.service;

import com.yd.activiti.test.entity.ActivitiResultDto;

import java.util.List;

/**
 * @author Yd on  2018-03-01
 * @description
 **/
public interface ActService {
    //01部署
    void deploy(String param);
    //02执行
    void start(String param);
    //03查询
    List<ActivitiResultDto> queryByPerson(String param);
    //04操作完成
    void action(String param);
}
