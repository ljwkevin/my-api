package com.yd.jpa.service.oa.leave;

import com.yd.common.util.Page;
import com.yd.jpa.entity.oa.Leave;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;

import java.util.List;
import java.util.Map;

/**
 * 请假工作流Service
 * @author Yd on  2018-03-01
 * @description
 **/
public interface LeaveWorkflowService {

    /**
     * 启动流程
     * @param entity
     * @param variables
     * @return
     */
    ProcessInstance startWorkflow(Leave entity, Map<String, Object> variables);

    /**
     * 查询待办任务
     * @param userId
     * @param page
     * @param pageParams
     * @return
     */
    List<Leave> findTodoTasks(String userId, Page<Leave> page, int[] pageParams);

    /**
     * 读取运行中的流程
     * @param page
     * @param pageParams
     * @return
     */
    List<Leave> findRunningProcessInstaces(Page<Leave> page, int[] pageParams);

    /**
     * 读取已结束中的流程
     * @param page
     * @param pageParams
     * @return
     */
    List<Leave> findFinishedProcessInstaces(Page<Leave> page, int[] pageParams);

    /**
     * 查询流程定义对象
     * @param processDefinitionId
     * @return
     */
    ProcessDefinition getProcessDefinition(String processDefinitionId);

}
