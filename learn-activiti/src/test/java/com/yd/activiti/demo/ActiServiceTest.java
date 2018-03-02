package com.yd.activiti.demo;

import com.yd.activiti.BaseTest;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Yd on  2018-03-01
 * @description
 **/
public class ActiServiceTest extends BaseTest {

    @Resource(name = "processEngine")
    private ProcessEngine processEngine;

    @Test//6.2部署流程
    public void deploy() throws  Exception{
        Deployment deployment = processEngine.getRepositoryService()
                .createDeployment()
                .name("1440aaa")
                .addClasspathResource("activiti/Leave.bpmn.xml")
                .deploy();
        //发布前测试;重要的3张表 01`act_re_deployment`  02`act_re_procdef`   03`act_ru_task`
        System.out.println("###########");
        System.out.println(deployment.getId()+"  "+deployment.getName());

    }
    @Test //6.3启动流程
    public void startProcess(){
        ProcessInstance pi = processEngine.getRuntimeService()
                .startProcessInstanceByKey("aaa");
        System.out.println(pi.getId()+" "+ pi.getActivityId());

    }
    @Test //6.4查看指定person任务
    public void queryMyTask(){
        String assignee="刘志成";
        List<Task> list = processEngine.getTaskService()
                .createTaskQuery()
                .taskAssignee(assignee)
                .list();
        if(list!=null && list.size()>0){
            for (Task t:list){
                System.out.println("任务id:"+t.getId());
                System.out.println("任务名称: "+t.getName());
                System.out.println("创建时间: "+t.getCreateTime());
                System.out.println("任务的办理人: "+t.getAssignee());
                System.out.println("流程实例id: "+t.getProcessInstanceId());
                System.out.println("执行对象id: "+t.getExecutionId());
                System.out.println("流程定义id: "+t.getProcessInstanceId());
                System.out.println("###########################");
            }
        }
    }

    @Test //6.5办理任务
    public void completeTask(){
        String taskid="75002";
        processEngine.getTaskService().complete(taskid);
        System.out.println("complete task");

    }


}
