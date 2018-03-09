package com.yd.jpa.service.purOrder;

import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.util.List;
import java.util.Map;

/**
 * @author Yd on  2018-03-08
 * @description 请购单service
 **/
public interface PurOrderService {

    /**
     * 部署流程
     *
     * @param bpmnFileName 流程定义文件名
     * @param workflowName 流程名称
     * @param category     流程类别
     * @return
     */
    Deployment deployWorkflow(String bpmnFileName, String workflowName, String category);

    /**
     * 开启流程
     *
     * @param userId        用户id
     * @param bussniessKey  业务标识
     * @param processDefKey 流程定义标识
     * @return
     */
    ProcessInstance startWorkflow(String userId, String bussniessKey, String processDefKey);

    /**
     * 获取某个流程的代办任务
     *
     * @param userId        用户id
     * @param processDefKey 流程定义标识
     * @return
     */
    List<Task> getToDoList(String userId, String processDefKey);

    /**
     * 任务指派
     *
     * @param taskId 任务ID
     * @param userId 用户Id
     * @return
     */
    Boolean taskAssignee(String taskId, String userId);

    /**
     * 处理任务（流程节点）
     *
     * @param userId        办理人
     * @param bussniessKey  业务标识
     * @param processDefKey 流程标识（属于哪个业务流程）
     * @param actionId      执行动作（比如确认、再确认、审批等等）
     * @return
     */
    Boolean handleProcess(String userId, String bussniessKey, String processDefKey, String actionId);

    /**
     * 处理流程
     *
     * @param userId 用户Id
     * @param taskId 任务Id
     * @return
     */
    Boolean handleProcess(String userId, String taskId);

    /**
     * 处理流程，如果有排他网关，需要传入单独的标识
     *
     * @param userId
     * @param taskId
     * @param varies 标识
     * @return
     */
    Boolean handleProcess(String userId, String taskId, Map<String, Object> varies);

    /**
     * 查询 历史轨迹
     *
     * @param userId   查询人
     * @param finished 任务是否结束
     * @return
     */
    List<HistoricProcessInstance> queryHistoryByOwner(String userId, boolean finished);

    /**
     * 查询 历史轨迹
     *
     * @param userId   查询人
     * @param finished 任务是否结束
     * @return
     */
    List<HistoricActivityInstance> queryHistory(String userId, boolean finished);
}
