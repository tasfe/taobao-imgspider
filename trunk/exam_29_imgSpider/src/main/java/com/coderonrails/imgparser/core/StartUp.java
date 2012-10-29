package com.coderonrails.imgparser.core;

public class StartUp {
    public static void main(String[] args) {
        Thread t = new Thread(new TaskSpider());
        t.start();
        Thread t1 = new Thread(new ContentSpider());
        t1.start();
        try {
            t.join();
            t1.join();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
