package com.yd.activiti.demo.service.oa.leave;

import com.yd.activiti.demo.dao.oa.LeaveDao;
import com.yd.activiti.demo.entity.oa.Leave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author Yd on  2018-03-01
 * @description 请假服务
 **/
@Component
@Transactional(readOnly = true)
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    private LeaveDao leaveDao;

    @Override
    public Leave getLeave(Long id) {
        return leaveDao.findOne(id);
    }

    @Override
    @Transactional(readOnly = false)
    public void saveLeave(Leave entity) {
        if (entity.getId() == null) {
            entity.setApplyTime(new Date());
        }
        leaveDao.save(entity);
    }
}
