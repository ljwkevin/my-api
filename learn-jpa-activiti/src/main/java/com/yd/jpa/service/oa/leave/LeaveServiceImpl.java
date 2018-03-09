package com.yd.jpa.service.oa.leave;

import com.yd.jpa.dao.oa.LeaveDao;
import com.yd.jpa.entity.oa.Leave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

/**
 * @author Yd on  2018-03-01
 * @description 请假服务
 **/
@Component
@Transactional
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    private LeaveDao leaveDao;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Leave getLeave(Long id) {
        return leaveDao.findOne(id);
    }

    @Override
    @Transactional
    public void saveLeave(Leave entity) {
        if (entity.getId() == null) {
            entity.setApplyTime(new Date());
        }
        entityManager.persist(entity);
        System.out.println("entity:" + entity);
    }

    @Override
    public void save(Leave entity) {
        if (entity.getId() == null) {
            entity.setApplyTime(new Date());
        }
        leaveDao.save(entity);
        System.out.println("entity:" + entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        leaveDao.delete(id);
    }
}
