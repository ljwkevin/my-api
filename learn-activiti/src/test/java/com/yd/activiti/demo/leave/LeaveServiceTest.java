package com.yd.activiti.demo.leave;

import com.yd.activiti.BaseTest;
import com.yd.activiti.demo.dao.oa.LeaveDao;
import com.yd.activiti.demo.entity.oa.Leave;
import com.yd.activiti.demo.service.oa.leave.LeaveService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Date;

/**
 * @author Yd on  2018-03-02
 * @description
 **/
public class LeaveServiceTest extends BaseTest{
    @Autowired
    private LeaveService leaveService;
    @Autowired
    private LeaveDao leaveDao;

    @Autowired
    DataSource dataSource;

    @Test
    public void testSaveLave(){
        Leave leave = new Leave();
        leave.setApplyTime(new Date());
        leave.setStartTime(new jodd.datetime.JDateTime("2018-03-01").convertToSqlDate());
        leave.setEndTime(new jodd.datetime.JDateTime("2012-03-02").convertToSqlDate());
        leave.setLeaveType("公休");
        leave.setUserId("Yd");
        leave.setReason("no reason");
        leaveService.saveLeave(leave);
        System.out.println("Leave id:"+leave.getId()+"\n------------------------");

        leaveDao.save(leave);
        System.out.println("Leave id:"+leave.getId()+"\n------------------------");
    }

    @Test
    public void testQeyLeave(){
        leaveService.getLeave(1L);
    }

    @Test
    public void testInsertBySql() throws SQLException {
        String insertSql = "insert into oa_leave(user_id) VALUES(123)";
        dataSource.getConnection().prepareStatement(insertSql).execute();
    }
}
