package com.yd.tfidf;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private static String logFileName = "D:/DRIVERS/log.txt";

    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream(logFileName);
        BufferedWriter br = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(logFileName)));
        Map<String, HashMap<String, Integer>> normal = ReadFiles.NormalTFOfAll("d:/dir");
        for (String filename : normal.keySet()) {
            br.newLine();
            br.write("fileName " + filename);
            //  System.out.println("fileName " + filename);
            br.newLine();
            //   System.out.println("TF " + normal.get(filename).toString());
            br.write("TF " + normal.get(filename).toString());
        }
        br.newLine();
        br.write("-----------------------------------------");
        //   System.out.println("-----------------------------------------");

        Map<String, HashMap<String, Float>> notNarmal = ReadFiles.tfOfAll("d:/dir");
        for (String filename : notNarmal.keySet()) {
            br.newLine();
            br.write("fileName " + filename);
            // System.out.println("fileName " + filename);
            br.newLine();
            br.write("TF " + notNarmal.get(filename).toString());
            //  System.out.println("TF " + notNarmal.get(filename).toString());
        }
        br.newLine();
        br.write("-----------------------------------------");
        //  System.out.println("-----------------------------------------");

        Map<String, Float> idf = ReadFiles.idf("d;/dir");
        for (String word : idf.keySet()) {
            br.newLine();
            br.write("keyword :" + word + " idf: " + idf.get(word));
            // System.out.println("keyword :" + word + " idf: " + idf.get(word));
        }
        br.newLine();
        br.write("-----------------------------------------");
        //System.out.println("-----------------------------------------");

        Map<String, HashMap<String, Float>> tfidf = ReadFiles.tfidf("d:/dir");
        for (String filename : tfidf.keySet()) {
            br.newLine();
            br.write("fileName " + filename);
            //     System.out.println("fileName " + filename);
            br.newLine();
            br.write("" + tfidf.get(filename));
            //   System.out.println(tfidf.get(filename));
        }
        System.out.println("������������" + "\t" + logFileName);
        br.close();
    }
}