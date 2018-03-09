package com.yd.jpa.dao.oa;

import com.yd.jpa.entity.oa.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

/**
 * @author Yd on  2018-03-01
 * @description 请假实体Dao接口
 **/
@Component
public interface LeaveDao extends JpaRepository<Leave, Long> {

    @Modifying
    @Query("delete from Leave where entity_id = ?1")
    void deleteById(Long entityId);
}
