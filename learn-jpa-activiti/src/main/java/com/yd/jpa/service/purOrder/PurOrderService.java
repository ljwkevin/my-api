package com.yd.jpa.service.purOrder;

import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.util.List;

/**
 * @author Yd on  2018-03-08
 * @description 请购单service
 **/
public interface PurOrderService {

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
     * 处理任务（流程节点）
     *
     * @param userId        办理人
     * @param bussniessKey  业务标识
     * @param processDefKey 流程标识（属于哪个业务流程）
     * @param actionId      执行动作（比如确认、再确认、审批等等）
     * @return
     */
    Boolean handleProcess(String userId, String bussniessKey, String processDefKey, String actionId);

    List<HistoricActivityInstance> queryHistory(String userId, boolean finished);
}
