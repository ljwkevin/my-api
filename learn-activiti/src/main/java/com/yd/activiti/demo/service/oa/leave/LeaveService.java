package com.yd.activiti.demo.service.oa.leave;

import com.yd.activiti.demo.entity.oa.Leave;

/**
 * @author Yd on  2018-03-01
 * @description 请假服务
 **/
public interface LeaveService {
    Leave getLeave(Long id);

    void saveLeave(Leave entity);
}
