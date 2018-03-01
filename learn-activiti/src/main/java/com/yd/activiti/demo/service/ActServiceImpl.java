package com.yd.activiti.demo.service;

import com.yd.activiti.demo.entity.ActivitiResultDto;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Yd on  2018-03-01
 * @description
 **/
@Service
public class ActServiceImpl implements ActService {
    @Resource(name = "processEngine")
    private ProcessEngine processEngine;

    @Override//2018.2.13;lzc;01部署流程
    public  void deploy(String command){
        if ("deploy".equals(command)) {
            Date date = new Date();
            String s = date.toString();
            processEngine.getRepositoryService().createDeployment().name("百果园" + s).addClasspathResource("activiti/1733bbb.bpmn").deploy();
        }
    }

    @Override//2018.2.13;lzc;02启动流程
    public  void start(String param){
        processEngine.getRuntimeService().startProcessInstanceByKey(param);
    }

    @Override//2018.2.13;lzc;03根据人查看任务
    public List<ActivitiResultDto> queryByPerson(String person){
        List<ActivitiResultDto> activitiResultDtos = new ArrayList<>();
        ActivitiResultDto activitiResultDto = new ActivitiResultDto();
        String assignee=person;
        List<Task> Tasks = processEngine.getTaskService().createTaskQuery().taskAssignee(assignee).list();
        for (Task t:Tasks){
            activitiResultDto.setTaskId(t.getId());
            activitiResultDto.setTaskName(t.getName());
            activitiResultDtos.add(activitiResultDto);
        }
        //结果
        return  activitiResultDtos;
    }


    @Override//2018.2.13;lzc;04办理任务
    public  void action(String param){
        String taskId=param;
        processEngine.getTaskService().complete(taskId);
    }



}
