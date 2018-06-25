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

    //InputStreamReader和OutputStreamWriter
    //两个类把字节流转换成字符流，中间做了数据的转换，类似适配器模式的思想。
    public static void StreamReader() throws IOException {
        InputStream inputStream = new FileInputStream(Constant.FILENAME);
        InputStreamReader reader = new InputStreamReader(inputStream);
        int data = reader.read();//read()方法返回一个包含了读取到的字符内容的int类型变量(译者注：0~65535)。
        while (data != -1) {
            char theChar = (char) data;//这里不会造成数据丢失，因为返回的int类型变量data只有低16位有数据，高16位没有数据
            data = reader.read();
        }
        reader.close();

        //第二个参数，此时该InputStreamReader会将输入的字节流转换成UTF8字符流。
        reader = new InputStreamReader(inputStream, "UTF-8");

        OutputStream outputStream = new FileOutputStream(Constant.FILENAME);
        Writer writer = new OutputStreamWriter(outputStream);
        writer.write("Hello World");//将该输出字节流转换成字符流
        writer.close();

    }

    //如果你想明确指定一种编码方案，利用InputStreamReader配合FileInputStream来替代FileReader(译者注：FileReader没有可以指定编码的构造函数)。
    // InputStreamReader可以让你设置编码处理从底层文件中读取的字节。
    //同样，FileWriter不能指定编码，可以通过OutputStreamWriter配合FileOutputStream替代FileWriter。
    public static void FileReaderWriter() throws IOException {
        Reader reader = new FileReader(Constant.FILENAME);
        int data = reader.read();
        while (data != -1) {
            //do something with data...
            data = reader.read();
        }
        reader.close();

        Writer writer = new FileWriter(Constant.FILENAME, true); //appends to file
    }

    //管道与字符数组相关的reader和writer，主要涉及PipedReader、PipedWriter、CharArrayReader、CharArrayWriter。
    //PipedReader能够从管道中读取字符流。与PipedInputStream类似，不同的是PipedReader读取的是字符而非字节。换句话说，PipedReader用于读取管道中的文本。
    public static void pipe() throws IOException {
        PipedWriter pipedWriter = new PipedWriter();
        Reader reader = new PipedReader(pipedWriter);
        int data = reader.read();
        while(data != -1) {
            //do something with data...
            data = reader.read();
        }
        reader.close();
    }
}
