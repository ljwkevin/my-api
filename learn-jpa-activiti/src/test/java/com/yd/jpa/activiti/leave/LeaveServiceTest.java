package com.yd.jpa.activiti.leave;

import com.yd.jpa.BaseTest;
import com.yd.jpa.dao.oa.LeaveDao;
import com.yd.jpa.entity.oa.Leave;
import com.yd.jpa.service.oa.leave.LeaveService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Date;

/**
 * @author Yd on  2018-03-02
 * @description
 **/
public class LeaveServiceTest extends BaseTest {
    @Autowired
    private LeaveService leaveService;
    @Autowired
    private LeaveDao leaveDao;

    @Autowired
    DataSource dataSource;

    @Test
    public void testSaveLave(){//多次插入同一个实体，目前只操作插入一次，hql也一次 todo
        Leave leave = new Leave();
        leave.setApplyTime(new Date());
        leave.setStartTime(new jodd.datetime.JDateTime("2018-03-01").convertToSqlDate());
        leave.setEndTime(new jodd.datetime.JDateTime("2012-03-02").convertToSqlDate());
        leave.setLeaveType("公休");
        leave.setUserId("Yd123");
        leave.setReason("no reason");
        leaveService.save(leave);
        System.out.println("Leave id:"+leave.getId()+"\n------------------------");

//        leaveService.saveLeave(leave);
        System.out.println("Leave id:"+leave.getId()+"\n------------------------");
        Leave leave1 = leave;
        leave1.setUserId("Yd");
        leaveDao.save(leave1);
        System.out.println("Leave id:"+leave1.getId()+"\n------------------------");
    }

    @Test
    public void testQryLeave(){
        Leave leave = leaveService.getLeave(2L);
        System.out.println("------------------\n"+leave);
    }

    @Test
    public void testDelete(){
        leaveService.delete(1L);
    }

    @Test
    public void testInsertBySql() throws SQLException {
        String insertSql = "insert into oa_leave(user_id) VALUES(123)";
        dataSource.getConnection().prepareStatement(insertSql).execute();
    }
}
