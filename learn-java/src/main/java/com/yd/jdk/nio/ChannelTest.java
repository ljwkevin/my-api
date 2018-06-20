package com.yd.jdk.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * channel 相关方法
 *
 * @author Yd on  2018-06-20
 * @description
 **/
public class ChannelTest {


    public static void channelTransferFrom(String fromFileName, String toFileName) throws IOException {
        RandomAccessFile fromFile = new RandomAccessFile(fromFileName, "rw");
        FileChannel fromChannel = fromFile.getChannel();
        RandomAccessFile toFile = new RandomAccessFile(toFileName, "rw");
        FileChannel toChannel = toFile.getChannel();
        long position = 0;
        long count = fromChannel.size();


        toChannel.transferFrom(fromChannel, position, count);
        //是不是发现这个例子和前面那个例子特别相似？除了调用方法的FileChannel对象不一样外，其他的都一样。
        fromChannel.transferTo(position, count, toChannel);

    }
}
