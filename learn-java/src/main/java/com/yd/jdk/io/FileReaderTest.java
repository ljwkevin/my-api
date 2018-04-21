package com.yd.jdk.io;

import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * @author Yd on  2018-04-19
 * @description
 **/
public class FileReaderTest {

    public static void main(String[] args) throws Exception {
        StringBuffer sb = new StringBuffer();
        char[] buf = new char[1024];
        String fileName ="";
        FileReader fileReader = new FileReader(fileName);
        while (fileReader.read(buf)>0){
            sb.append(buf);
        }
        sb.toString();
    }
}
