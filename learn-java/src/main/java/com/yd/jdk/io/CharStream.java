package com.yd.jdk.io;

import com.yd.jdk.Constant;

import java.io.*;

/**
 * Reader和Writer除了基于字符之外，其他方面都与InputStream和OutputStream非常类似。他们被用于读写文本。
 *
 * @author Yd on 2018-06-22
 * 比如，你有一个程序需要处理磁盘上的大量文件，这个任务可以通过并发操作提高性能。
 * 又比如，你有一个web服务器或者聊天服务器，接收许多连接和请求，这些任务都可以通过并发获得性能的提升。
 * <p>
 * <p>
 * 如果你需要并发处理IO，这里有几个问题可能需要注意一下：
 * <p>
 * 在同一时刻不能有多个线程同时从InputStream或者Reader中读取数据，也不能同时往OutputStream或者Writer里写数据。
 * 你没有办法保证每个线程读取多少数据，以及多个线程写数据时的顺序。
 * <p>
 * 如果线程之间能够保证操作的顺序，它们可以使用同一个stream、reader、writer。
 * 比如，你有一个线程判断当前的输入流来自哪种类型的请求，然后将流数据传递给其他合适的线程做后续处理。当有序存取流、reader、writer时，这种做法是可行的。
 * 请注意，在线程之间传递流数据的代码应当是同步的。
 */
public class CharStream {

    //Reader的read()方法返回一个字符，意味着这个返回值的范围在0到65535之间(当达到流末尾时，同样返回-1)。
    // 这并不意味着Reade只会从数据源中一次读取2个字节，Reader会根据文本的编码，一次读取一个或者多个字节。
    public static void reader() throws IOException {
        Reader reader = new FileReader(Constant.FILENAME);

        int data = reader.read();

        while (data != -1) {

            char dataChar = (char) data;

            data = reader.read();
        }
    }

    public static void writer() throws IOException {
        Writer writer = new FileWriter(Constant.FILENAME);

        writer.write("Hello World Writer");

        writer.close();

        Writer writer1 = new OutputStreamWriter(new FileOutputStream(Constant.FILENAME));
    }

    //整合Reader与InputStream
    public static void composit() throws FileNotFoundException {
        Reader reader = new InputStreamReader(new FileInputStream(Constant.FILENAME));

    }

}
