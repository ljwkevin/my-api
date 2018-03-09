package com.yd.jpa.service.purOrder.imp;

import com.yd.jpa.service.purOrder.PurOrderService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricActivityInstanceQuery;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author Yd on  2018-03-08
 * @description 请购单ServiceImp
 **/
@Transactional
@Service("activiti.purOrderService")
public class PurOrderServiceImp implements PurOrderService {
    @Resource(name = "processEngine")
    private ProcessEngine processEngine;

    @Override
    public Deployment deployWorkflow(String bpmnFileName, String workflowName, String category) {
        //1.部署流程
        DeploymentBuilder deploymentBuilder = processEngine.getRepositoryService()//获取流程资源服务
                .createDeployment();//创建一个部署任务
        Assert.notNull(bpmnFileName, "bpmnFileName must not null");
        //再次判断默认路径下，文件是否存在 todo

        if (StringUtils.isEmpty(workflowName)) {
            deploymentBuilder.name(workflowName);//部署 名称
        }
        if (StringUtils.isEmpty(category)) {
            deploymentBuilder.category(category);//部署类别
        }

        return deploymentBuilder.deploy();//部署
    }

    @Override
    public ProcessInstance startWorkflow(String userId, String bussniessKey, String processDefKey) {
        //userId 保存到ACT_HI_PROCINST 的START_USER_ID_
        processEngine.getIdentityService().setAuthenticatedUserId(userId);
        ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey(processDefKey, bussniessKey);
        return processInstance;
    }

    @Override
    public List<Task> getToDoList(String userId, String processDefKey) {
        TaskQuery taskQuery = processEngine.getTaskService().createTaskQuery();
        if (!StringUtils.isEmpty(processDefKey)) {
            taskQuery.processDefinitionKey(processDefKey);
        }
        if (!StringUtils.isEmpty(userId)) {
            //通过UserId查找相对应的角色，通过角色找到对应的节点
        }
        taskQuery.taskUnassigned();//未指派
        return taskQuery.list();
    }

    @Override
    public Boolean taskAssignee(String taskId, String userId) {
        Assert.notNull(taskId, "taskId must not null");
        Boolean isSuccessed = true;//默认返回
        try {
            processEngine.getTaskService().setAssignee(taskId, userId);
        } catch (Exception e) {
            isSuccessed = false;
        }

        return isSuccessed;
    }

    @Override
    public Boolean handleProcess(String userId, String bussniessKey, String processDefKey, String actionId) {
        //先获取任务
        TaskQuery taskQuery = processEngine.getTaskService().createTaskQuery();
        Assert.notNull(bussniessKey, "bussniessKey must not null");
        Assert.notNull(processDefKey, "processDefKey must not null");
        taskQuery.processDefinitionKey(processDefKey);
        taskQuery.processInstanceBusinessKey(bussniessKey);
        taskQuery.taskDefinitionKey(actionId);
        Task task = taskQuery.active().singleResult();

        Assert.notNull(task, "this task is disposed");

        //签收任务
        processEngine.getTaskService().claim(task.getId(), userId);

        //办理任务
        processEngine.getTaskService().complete(task.getId());

        return true;//默认返回
    }

    @Override
    public Boolean handleProcess(String userId, String taskId) {
        //先获取任务
        TaskQuery taskQuery = processEngine.getTaskService().createTaskQuery();
        Assert.notNull(taskId, "taskId must not null");
        taskQuery.taskId(taskId);//任务Id
        Task task = taskQuery.active().singleResult();

        Assert.notNull(task, "this task is disposed");

        //签收任务
        processEngine.getTaskService().claim(task.getId(), userId);

        //办理任务
        processEngine.getTaskService().complete(task.getId());

        return true;//默认返回
    }

    @Override
    public Boolean handleProcess(String userId, String taskId, Map<String, Object> varies) {
        //先获取任务
        TaskQuery taskQuery = processEngine.getTaskService().createTaskQuery();
        Assert.notNull(taskId, "taskId must not null");
        taskQuery.taskId(taskId);//任务Id
        Task task = taskQuery.active().singleResult();

        Assert.notNull(task, "this task is disposed");

        //签收任务
        processEngine.getTaskService().claim(task.getId(), userId);

        //办理任务
        processEngine.getTaskService().complete(task.getId(), varies);
        return true;
    }

    @Override
    public List<HistoricProcessInstance> queryHistoryByOwner(String userId, boolean finished) {
        HistoricProcessInstanceQuery historicProcessInstanceQuery = processEngine.getHistoryService().createHistoricProcessInstanceQuery();
        historicProcessInstanceQuery.startedBy(userId);
        if (finished) {
            historicProcessInstanceQuery.finished();
        } else {
            historicProcessInstanceQuery.unfinished();
        }

        return historicProcessInstanceQuery.list();
    }

    @Override
    public List<HistoricActivityInstance> queryHistory(String userId, boolean finished) {
        HistoricActivityInstanceQuery historicActivityInstanceQuery = processEngine.getHistoryService()
                .createHistoricActivityInstanceQuery()
                .taskAssignee(userId) //查询谁办理过的任务
                .orderByHistoricActivityInstanceEndTime().desc();    //排序
        if (finished) {
            historicActivityInstanceQuery.finished();
        } else {
            historicActivityInstanceQuery.unfinished();
        }

        return historicActivityInstanceQuery.list();
    }
}
