package com.yd.jpa.service.oa.leave;


import com.yd.jpa.entity.oa.Leave;

/**
 * @author Yd on  2018-03-01
 * @description 请假服务
 **/
public interface LeaveService {
    Leave getLeave(Long id);

    void saveLeave(Leave entity);

    void save(Leave entity);

    void delete(Long id);
}
