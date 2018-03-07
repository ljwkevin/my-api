package com.yd.jpa.activiti.leave;

import com.yd.jpa.BaseTest;
import com.yd.jpa.service.oa.leave.LeaveWorkflowService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
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
        //1.部署流程
        Deployment deployment = processEngine.getRepositoryService()//获取流程资源服务
                .createDeployment()//创建一个部署任务
                .addClasspathResource("activiti/leave/leave.bpmn")//部署位置
                .name("leave process activiti")//部署 名称
                .category("a")//部署类别
                .deploy();//部署
        //发布前测试;重要的3张表 01`act_re_deployment`  02`act_re_procdef`   03`act_ru_task`
        System.out.println("--------------\n" + deployment.getId() + "  " + deployment.getName());
    }

    @Test
    public void testStartWorkflow() {
        //2.开始流程
//        ProcessInstance pi = processEngine.getRuntimeService().startProcessInstanceByKey("leave");
        String bussniessKey = "abc2";
        ProcessInstance pi = processEngine.getRuntimeService().startProcessInstanceByKey("leave", bussniessKey);
        //涉及到数据库 1-act_hi_procinst 2-act_ru_task  3-act_ru_execution
        System.out.println(pi.getDeploymentId() + " pid " + pi.getId());
    }

    @Test
    public void testQryTask() {
        List<Task> taskList = processEngine.getTaskService().createTaskQuery().taskUnassigned().list();
//        List<Task> taskList = processEngine.getTaskService().createTaskQuery().list();
        if (taskList == null) {
            return;
        }
        for (Task task : taskList) {
            System.out.println(task.getId() + "" + task.getName() + "" + task.getCreateTime() + "" + task.getAssignee());
        }

        //根据业务表示（如业务id）
        Task task = processEngine.getTaskService().createTaskQuery()
                .processInstanceBusinessKey("abc1") //业务id
                .processDefinitionKey("leave")//部署流程定义id
//                    .taskId("10004")
                .taskDefinitionKey("_3")
                .singleResult();//单个结果
        processEngine.getRuntimeService().createProcessInstanceQuery().processInstanceId("").list();
        System.out.println(task.getDelegationState() + " " + task.getId() + "" + task.getName() + "" + task.getCreateTime() + "" + task.getAssignee());
    }

    @Test
    public void testClaim() {
        //领导签收、办理 任务
        Task task = processEngine.getTaskService()
                .createTaskQuery()
//                    .taskId("10004")
//                .taskDefinitionKey("_3")//假设_3为项目主管节点
//                .processInstanceBusinessKey("abc")
                .processDefinitionKey("leave")
                .singleResult();
        assertNotNull(task);
        String assigner = "leader123";
        processEngine.getTaskService().claim(task.getId(), assigner);

        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("deptLeaderPass", true);
        processEngine.getTaskService().complete(task.getId(), variables);
    }



    @Test
    public void testComplete() {
        //领导签收、办理 任务
        Task task = processEngine.getTaskService()
                .createTaskQuery()
//                    .taskId("10004")
                .taskDefinitionKey("_3")//假设_3为项目主管节点
                .processInstanceBusinessKey("abc")
                .singleResult();
        assertNotNull(task);

        processEngine.getTaskService().complete(task.getId());
    }

    @Test
    public void testQryHistory() {
        //查询流程轨迹 以及走过的节点
        List<HistoricActivityInstance> historicActivityInstanceList = processEngine.getHistoryService()
                .createHistoricActivityInstanceQuery()
//                .processInstanceId("2501") //实例流程id
//                .taskAssignee("leader123")   //查询任务被办理了
//                .finished()//结束的流程
                .unfinished()//为结束的流程
                .orderByHistoricActivityInstanceEndTime().desc()    //排序
                .list();

        for (HistoricActivityInstance historicActivityInstance : historicActivityInstanceList) {
            System.out.println(historicActivityInstance.getStartTime()+""+historicActivityInstance.getEndTime()+""+historicActivityInstance.getCalledProcessInstanceId());
        }
    }


}
