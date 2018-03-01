package com.yd.activiti.demo.dao.oa;

import com.yd.activiti.demo.entity.oa.Leave;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

/**
 * @author Yd on  2018-03-01
 * @description 请假实体Dao接口
 **/
@Component
public interface LeaveDao extends CrudRepository<Leave, Long> {

}
