package com.yd.jpa.activiti.leave;

import com.yd.jpa.BaseTest;
import com.yd.jpa.entity.oa.Leave;
import com.yd.jpa.service.oa.leave.LeaveWorkflowService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

/**
 * @author Yd on  2018-03-06
 * @description
 **/
public class LeaveWorkflowServiceTest extends BaseTest {
    @Resource(name = "processEngine")
    private ProcessEngine processEngine;
    @Autowired
    private LeaveWorkflowService leaveWorkflowService;

    @Test
    public void testDeploy() {
        //部署流程
        Deployment deployment = processEngine.getRepositoryService()//获取流程资源服务
                .createDeployment()//创建一个部署任务
                .addClasspathResource("activiti/leave/ccc.bpmn")//部署位置
                .name("leave process activiti")//部署 名称
                .deploy();//部署
        //发布前测试;重要的3张表 01`act_re_deployment`  02`act_re_procdef`   03`act_ru_task`
        System.out.println("--------------\n" + deployment.getId() + "  " + deployment.getName());
    }

    @Test
    public void testStartWorkflow(){
        Leave leave = new Leave();
        leave.setUserId("Yd");
        leave.setApplyTime(new Date());
        leave.setStartTime(new jodd.datetime.JDateTime("2012-05-22 12:00").convertToDate());
        leave.setEndTime(new jodd.datetime.JDateTime("2012-05-23 09:00").convertToDate());
        leave.setLeaveType("公休");

        Map<String, Object> variables = new HashMap<String, Object>();
        leaveWorkflowService.startWorkflow(leave, variables);
        assertNotNull(leave.getProcessInstanceId());

        ProcessInstance processInstance = processEngine.getRuntimeService().createProcessInstanceQuery()
                .processInstanceBusinessKey(leave.getId().toString()).singleResult();
        assertNotNull(processInstance);

    }

}
