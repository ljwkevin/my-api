package com.yd.jpa.service.oa.leave;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;

/**
 * @author Yd on  2018-03-06
 * @description
 **/
public class LeaveSendMailDelegate implements JavaDelegate {
    private ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("-------------");
        delegateExecution.setVariable("approvalPeople", "ming");
        HistoricProcessInstance historicProcessInstance = processEngine.getHistoryService().createHistoricProcessInstanceQuery().processInstanceId(delegateExecution.getProcessInstanceId()).singleResult();
        historicProcessInstance.getStartUserId();//获取到发起者人Id

    }
}
