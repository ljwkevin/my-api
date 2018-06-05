package com.yd.dubbo;

import com.alibaba.dubbo.rpc.RpcContext;

/**
 * @author Yd on  2018-06-02
 * @description
 **/
public class Test {
    public static void main(String[] args) {
        // 本端是否为消费端，这里会返回true
        boolean isConsumerSide = RpcContext.getContext().isConsumerSide();
        // 获取最后一次调用的提供方IP地址
        String serverIP = RpcContext.getContext().getRemoteHost();
        // 获取当前服务配置信息，所有配置信息都将转换为URL的参数
        String application = RpcContext.getContext().getUrl().getParameter("application");
    }
}
