package com.yd.javaWeb.io;

import java.io.*;

/**
 * 序列化的数据包括哪些
 * @author Yd on  2018-05-14
 * @description
 **/
public class Serialize implements Serializable{
    private static final long serialVersionUID = 1L;
    public int num = 1994;

    public static void main(String[] args) {
        //把对象 写入文件
        try {
            FileOutputStream outputStream = new FileOutputStream("./"+Serialize.class.getSimpleName()+".dat");
            ObjectOutputStream oos = new ObjectOutputStream(outputStream);
            Serialize serializable = new Serialize();
            oos.writeObject(serializable);
            oos.flush();//从工作内存 刷新到磁盘中
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //读取二进制文件
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./"+Serialize.class.getSimpleName()+".dat"));
            byte[] buf = new byte[1024];
            Serialize s = (Serialize) ois.readObject();
            System.out.println(s.num);
            System.out.println(ois.available());//流数据读取一次就会被清空
            ois.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
