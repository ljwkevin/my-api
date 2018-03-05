package com.yd.jpa.entity.oa;

import com.yd.annotation.ColumnDesc;
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
//@Data
@Entity
@Table(name = "OA_LEAVE")
public class Leave implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getRealityStartTime() {
        return realityStartTime;
    }

    public void setRealityStartTime(Date realityStartTime) {
        this.realityStartTime = realityStartTime;
    }

    public Date getRealityEndTime() {
        return realityEndTime;
    }

    public void setRealityEndTime(Date realityEndTime) {
        this.realityEndTime = realityEndTime;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Map<String, Object> getVariables() {
        return variables;
    }

    public void setVariables(Map<String, Object> variables) {
        this.variables = variables;
    }

    public ProcessInstance getProcessInstance() {
        return processInstance;
    }

    public void setProcessInstance(ProcessInstance processInstance) {
        this.processInstance = processInstance;
    }

    public HistoricProcessInstance getHistoricProcessInstance() {
        return historicProcessInstance;
    }

    public void setHistoricProcessInstance(HistoricProcessInstance historicProcessInstance) {
        this.historicProcessInstance = historicProcessInstance;
    }

    public ProcessDefinition getProcessDefinition() {
        return processDefinition;
    }

    public void setProcessDefinition(ProcessDefinition processDefinition) {
        this.processDefinition = processDefinition;
    }

    @Override
    public String toString() {
        return "Leave{" +
                "id=" + id +
                ", processInstanceId='" + processInstanceId + '\'' +
                ", userId='" + userId + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", realityStartTime=" + realityStartTime +
                ", realityEndTime=" + realityEndTime +
                ", applyTime=" + applyTime +
                ", leaveType='" + leaveType + '\'' +
                ", reason='" + reason + '\'' +
                ", task=" + task +
                ", variables=" + variables +
                ", processInstance=" + processInstance +
                ", historicProcessInstance=" + historicProcessInstance +
                ", processDefinition=" + processDefinition +
                '}';
    }
}
