package com.yd.jdk.io;

/**
 * System.in, System.out, System.err
 * System.in, System.out, System.err这3个流同样是常见的数据来源和数据流目的地。
 * JVM启动的时候通过Java运行时初始化这3个流，所以你不需要初始化它们(尽管你可以在运行时替换掉它们)。
 * @author Yd on  2018-06-22
 * @description
 **/
public class SystemTest {

    //System.in是一个典型的连接控制台程序和键盘输入的InputStream流。
    // 通常当数据通过命令行参数或者配置文件传递给命令行Java程序的时候，System.in并不是很常用。
    // 图形界面程序通过界面传递参数给程序，这是一块单独的Java IO输入机制。
    public void in(){

    }
}
