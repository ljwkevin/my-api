package com.yd.dubbo.generic;

import com.alibaba.dubbo.config.ServiceConfig;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.yd.dubbo.hello.service.DemoService;

/**
 * @author Yd on  2018-06-02
 * @description
 **/
public class GenericProvider {
    public static void main(String[] args) {
        // 用com.alibaba.dubbo.rpc.service.GenericService可以替代所有接口实现
        GenericService xxxService = new MyGenericService();

        // 该实例很重量，里面封装了所有与注册中心及服务提供方连接，请缓存
        ServiceConfig<GenericService> service = new ServiceConfig<GenericService>();
        // 弱类型接口名
        service.setInterface("com.yd.dubbo.hello.service.DemoService");
        service.setVersion("1.0.0");
        // 指向一个通用服务实现
        service.setRef(xxxService);

        // 暴露及注册服务
        service.export();
    }
}
