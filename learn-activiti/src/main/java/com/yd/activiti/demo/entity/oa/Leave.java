package com.yd.activiti.demo.entity.oa;

import com.yd.activiti.demo.entity.IdEntity;
import com.yd.annotation.ColumnDesc;
import lombok.Data;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * @author Yd on  2018-03-01
 * @description 请假Entity
 **/
@Data
@Entity
@Table(name = "OA_LEAVE")
public class Leave extends IdEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column
    private String processInstanceId;
    @Column
    private String userId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "startTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "endTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "realityStartTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date realityStartTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "realityEndTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date realityEndTime;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date applyTime;

    @Column
    @ColumnDesc(columnDesc = "请假类型")
    private String leaveType;

    @Column
    private String reason;

    //-- 临时属性 --//
    // 流程任务
    @Transient
    private Task task;

    @Transient
    private Map<String, Object> variables;

    // 运行中的流程实例
    @Transient
    private ProcessInstance processInstance;

    // 历史的流程实例
    @Transient
    private HistoricProcessInstance historicProcessInstance;

    // 流程定义
    @Transient
    private ProcessDefinition processDefinition;
}
