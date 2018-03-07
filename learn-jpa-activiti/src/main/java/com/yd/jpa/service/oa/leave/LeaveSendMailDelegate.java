package com.yd.jpa.service.oa.leave;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

/**
 * @author Yd on  2018-03-06
 * @description
 **/
public class LeaveSendMailDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("-------------");
        delegateExecution.setVariable("approvalPeople","ming");
    }
}
