package com.yd.springmvc.controller.purOrder;

import com.yd.jpa.service.purOrder.PurOrderService;
import com.yd.springmvc.handle.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Yd on  2018-03-06
 * @description 请购单
 **/
@Controller
@RequestMapping("/purOrder")
public class PurOrderController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PurOrderService purOrderService;

    @RequestMapping("/deployWorkflow/{bpmnFileName}")
    @ResponseBody
    public ResponseResult deployWorkflow(@PathVariable("bpmnFileName") String bpmnFileName, @RequestParam(value = "workflowName", required = false) String workflowName, @RequestParam(value = "category", required = false) String category) {
        //部署流程
        return ResponseResult.success(purOrderService.deployWorkflow(bpmnFileName, workflowName, category));
    }

    @RequestMapping("/startWorkflow/{userId}/{processDefKey}/{bussniessKey}")
    @ResponseBody
    public ResponseResult startWorkflow(@PathVariable("userId") String userId, @PathVariable("bussniessKey") String bussniessKey, @PathVariable("processDefKey") String processDefKey) {
        //启动流程
        return ResponseResult.success(purOrderService.startWorkflow(userId, bussniessKey, processDefKey));
    }

    @RequestMapping("/getToDoList/{userId}/{processDefKey}")
    @ResponseBody
    public ResponseResult getToDoList(@PathVariable("userId") String userId, @PathVariable("bussniessKey") String bussniessKey) {
        //获取代办任务
        return ResponseResult.success(purOrderService.getToDoList(userId, bussniessKey));
    }

    @RequestMapping("/taskAssignee/{userId}/{taskId}")
    @ResponseBody
    public ResponseResult taskAssignee(@PathVariable("userId") String userId, @PathVariable("taskId") String taskId) {
        //指派人
        return ResponseResult.success(purOrderService.taskAssignee(taskId, userId));
    }

//    @RequestMapping("/handleProcess/{userId}/{taskId}")
//    @ResponseBody
//    public ResponseResult handleProcess(@PathVariable("userId") String userId, @PathVariable("taskId") String taskId) {
//        //处理任务
//        return ResponseResult.success(purOrderService.handleProcess(userId, taskId));
//    }

    @RequestMapping("/handleProcess/{userId}/{taskId}")
    @ResponseBody
    public ResponseResult handleProcess(@PathVariable("userId") String userId, @PathVariable("taskId") String taskId, @RequestBody Map<String, Object> varies) {
        //处理任务
        return ResponseResult.success(purOrderService.handleProcess(userId, taskId, varies));
    }

    @RequestMapping("/queryHistoryByOwner/{userId}/{finished}")
    @ResponseBody
    public ResponseResult queryHistoryByOwner(@PathVariable("userId") String userId, @PathVariable("finished") boolean finished) {
        //查询个人历史任务
        return ResponseResult.success(purOrderService.queryHistoryByOwner(userId, finished));
    }

    @RequestMapping("/queryHistory/{userId}/{finished}")
    @ResponseBody
    public ResponseResult queryHistory(@PathVariable("userId") String userId, @PathVariable("finished") boolean finished) {
        //查询历史任务
        return ResponseResult.success(purOrderService.queryHistory(userId, finished));
    }
}
