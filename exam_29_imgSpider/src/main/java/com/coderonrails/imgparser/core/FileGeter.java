package com.coderonrails.imgparser.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class FileGeter {
    /**
     * @param url
     * @param savePath
     * @return
     */
    public void saveFile(String url, String savePath) {
        URL uri = null;
        HttpURLConnection conn = null;
        OutputStream os = null;
        try {
            uri = new URL(url);
            conn = (HttpURLConnection) uri.openConnection();
            if (conn.getContentLength() < Commons.size) {
                return;
            }
            System.err.println(url);
            int index = savePath.lastIndexOf("/");
            String path = savePath.substring(0, index);
            if (!new File(path).exists()) {
                new File(path).mkdirs();
            }

            os = new FileOutputStream(savePath);
            InputStream is = conn.getInputStream();
            byte[] content = new byte[1024];
            int length = 0;
            while ((length = is.read(content)) > -1) {
                os.write(content, 0, length);
            }
            os.flush();
        }
        catch (Exception e) {
            // e.printStackTrace();
        }
        finally {
            try {
                if (conn != null) {
                    conn.disconnect();
                }
                if (os != null) {
                    os.close();
                }
            }
            catch (IOException e) {
                // e.printStackTrace();
            }
        }

    }

    public String getPage(String url) {
        URL uri = null;
        HttpURLConnection conn = null;
        BufferedReader br = null;
        String line = "";
        String content = "";
        try {
            uri = new URL(url);
            conn = (HttpURLConnection) uri.openConnection();
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while ((line = br.readLine()) != null) {
                content += line + "\r\n";
            }
        }
        catch (Exception e) {
            // e.printStackTrace();
        }
        finally {
            try {
                if (conn != null) {
                    conn.disconnect();
                }
                if (br != null) {
                    br.close();
                }
            }
            catch (IOException e) {
                // e.printStackTrace();
            }
        }
        return content;
    }

    public static void main(String[] args) {
        // String url = "http://ww4.sinaimg.cn/bmiddle/86548d32jw1dt1njpx58pj.jpg";
        // String savePath = "D:/1.jpg";
        // new FileGeter().saveFile(url, savePath);
        // System.out.println(new FileGeter().getPage("http://www.google.com"));
    }
}
