package com.yd.dubbo.generic;

import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.rpc.service.GenericService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yd on  2018-06-02
 * @description
 **/
public class GenericConsumer {

    public static void main(String[] args) {
        // 引用远程服务
        // 该实例很重量，里面封装了所有与注册中心及服务提供方连接，请缓存
        ReferenceConfig<GenericService> reference = new ReferenceConfig<GenericService>();
        // 弱类型接口名
        reference.setInterface("com.yd.dubbo.hello.service.DemoService");
        reference.setVersion("1.0.0");
        // 声明为泛化接口
        reference.setGeneric(true);

        // 用com.alibaba.dubbo.rpc.service.GenericService可以替代所有接口引用
        GenericService genericService = reference.get();

        // 基本类型以及Date,List,Map等不需要转换，直接调用
        Object result = genericService.$invoke("sayHello", new String[]{"java.lang.String"}, new Object[]{"world"});

        // 用Map表示POJO参数，如果返回值为POJO也将自动转成Map
        Map<String, Object> person = new HashMap<String, Object>();
        person.put("name", "xxx");
        person.put("password", "yyy");
        // 如果返回POJO将自动转成Map
        Object result2 = genericService.$invoke("findPerson", new String[]
                {"com.xxx.Person"}, new Object[]{person});
    }
}
