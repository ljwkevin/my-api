package com.yd.javaWeb.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Buffer、Channel、selector
 *
 * @author Yd on  2018-05-15
 * @description
 **/
public class NIOTest {


    public void selector() {
        try {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            Selector selector = Selector.open();
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.configureBlocking(false);//设置非阻塞方式
            ssc.socket().bind(new InetSocketAddress(8080));
            ssc.register(selector, SelectionKey.OP_ACCEPT);//注册监听事件
            while (true) {
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectedKeys.iterator();
                while (it.hasNext()) {
                    SelectionKey key = it.next();
                    if ((key.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT) {

                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
