package com.coderonrails.imgparser.core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Commons {
    public static String rootUrl = "http://pic.yesky.com/";
    public static String rootPath = "D:/data/down/mic/";
    public static String host = "http://pic.yesky.com/";
    public static String regex = "jpg$|jpeg$|png$";
    public static int size = 1000;

    static {
        Properties p = new Properties();
        try {
        	//String path = Commons.class.getResource("").getPath();
        	String classLoaderPath = Commons.class.getClassLoader().getResource("").getPath();
        	
        	//System.out.println(path + "\n" + classLoaderPath);
            p.load(new FileInputStream(classLoaderPath+"/conf.properties"));
            rootUrl = p.getProperty("rootUrl");
            rootPath = p.getProperty("rootPath");
            host = p.getProperty("host");
            size = Integer.parseInt(p.getProperty("size"));
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
