package com.yd.jdk.io;

import com.yd.jdk.Constant;

import java.io.*;

/**
 * Java IO流是既可以从中读取，也可以写入到其中的数据流。流通常会与数据源、数据流向目的地相关联，比如文件、网络等等。
 * 流和数组不一样，不能通过索引读写数据。在流中，你也不能像数组那样前后移动读取数据，除非使用RandomAccessFile 处理文件。流仅仅只是一个连续的数据流。
 * <p>
 * <p>
 * 某些类似PushbackInputStream 流的实现允许你将数据重新推回到流中，以便重新读取。
 * 然而你只能把有限的数据推回流中，并且你不能像操作数组那样随意读取数据。流中的数据只能够顺序访问。
 * <p>
 * Java IO流通常是基于字节或者基于字符的。字节流通常以“stream”命名，比如InputStream和OutputStream。
 * 除了DataInputStream 和DataOutputStream 还能够读写int, long, float和double类型的值以外，其他流在一个操作时间内只能读取或者写入一个原始字节。
 *
 * @author Yd on 2018-06-22
 */
public class ByteStream {

    public static void main(String[] args) {

    }

    public static void pipe() throws IOException {
        try(
                PipedOutputStream pipedOutputStream = new PipedOutputStream();
                PipedInputStream pipedInputStream =new PipedInputStream(pipedOutputStream)
                ){}
    }

    public static void input() throws IOException {
        InputStream input = new FileInputStream(Constant.FILENAME);
        //read()方法返回一个整数，代表了读取到的字节的内容(译者注：0 ~ 255)。当达到流末尾没有更多数据可以读取的时候，read()方法返回-1。
        int data = input.read();
        while (data != -1) {
            data = input.read();
        }
    }

    public static void output() throws IOException {
        OutputStream output = new FileOutputStream(Constant.FILENAME);
        output.write("Hello World".getBytes());
        output.close();
    }

    //你可以将流整合起来以便实现更高级的输入和输出操作。
    // 比如，一次读取一个字节是很慢的，所以可以从磁盘中一次读取一大块数据，然后从读到的数据块中获取字节。
    // 为了实现缓冲，可以把InputStream包装到BufferedInputStream中。
    //缓冲只是通过流整合实现的其中一个效果。你可以把InputStream包装到PushbackInputStream中，之后可以将读取过的数据推回到流中重新读取，在解析过程中有时候这样做很方便。
    // 或者，你可以将两个InputStream整合成一个SequenceInputStream。
    public static void composit() throws FileNotFoundException {//组合流
        InputStream input = new BufferedInputStream(new FileInputStream(Constant.FILENAME));
    }

    public static void inputstream() throws IOException {
        try (InputStream inputstream = new FileInputStream(Constant.FILENAME)) {
            int data = inputstream.read();//InputStream的子类可能会包含read()方法的替代方法。比如，DataInputStream允许你利用readBoolean()，readDouble()等方法读取Java基本类型变量int，long，float，double和boolean。
            while (data != -1) {
                System.out.print((char) data);
                data = inputstream.read();
            }

        }
    }

    //往FileOutputStream里写数据的时候，这些数据有可能会缓存在内存中。
    //在之后的某个时间，比如，每次都只有X份数据可写，或者FileOutputStream关闭的时候，才会真正地写入磁盘。
    // 当FileOutputStream没被关闭，而你又想确保写入到FileOutputStream中的数据写入到磁盘中，
    // 可以调用flush()方法，该方法可以保证所有写入到FileOutputStream的数据全部写入到磁盘中。
    public static void outputStream() throws IOException {
        OutputStream output = null;
        try {
            output = new FileOutputStream(Constant.FILENAME,true);//appends to file
//            while (hasMoreData()) {
//                int data = getMoreData();
                output.write("".getBytes());
            output.flush();
//            }
        } finally {
            if (output != null) {
                output.close();
            }

        }
    }
}
