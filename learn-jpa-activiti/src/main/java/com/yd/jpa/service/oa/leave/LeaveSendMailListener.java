package com.yd.jpa.service.oa.leave;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 请假流程结束监听器
 *
 * @author Yd on  2018-03-01
 * @description
 **/
@Service
@Transactional
public class LeaveSendMailListener implements ExecutionListener {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void notify(DelegateExecution execution) throws Exception {
        String processInstanceId = execution.getProcessInstanceId();
        Date reportBackTimeout = (Date) execution.getVariable("reportBackTimeout");

        System.out.println("执行流程超时时间为" + reportBackTimeout);

        logger.debug(" 流程id为{}", processInstanceId);
    }

}
