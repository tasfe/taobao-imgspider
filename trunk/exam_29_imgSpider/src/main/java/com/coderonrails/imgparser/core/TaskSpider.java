package com.coderonrails.imgparser.core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskSpider implements Runnable {

    /**
	 * 
	 */
    public void run() {
        /**
		 * 
		 */
        String url = Commons.rootUrl;

        while (true) {
            try {
                if (url != null) {
                    FileGeter geter = new FileGeter();
                    String html = geter.getPage(url);
                    TaskUrlCache.finishUrl(url);
                    getUrl(html);
                }
                url = TaskUrlCache.getUrl();
                Thread.currentThread().sleep(1);
            }
            catch (Exception e) {
                continue;
            }
        }
    }

    public void getUrl(String html) {
        Matcher m = Pattern.compile("(?is)((href)|(src))=['\"]([^\"']*?)['\"]").matcher(html);
        while (m.find()) {
            String url = m.group(4);
            if (!url.toLowerCase().startsWith("http")) {
                url = Commons.host + url;
                if (isContent(url, Commons.regex)) {
                    ContentUrlCache.addUrl(url);
                }
                else {
                    TaskUrlCache.addUrl(url);
                }
            }
        }
    }

    public static boolean isContent(String url, String regex) {
        Matcher m = Pattern.compile(regex).matcher(url);
        return m.find();
    }

    public static void main(String[] args) {
        new TaskSpider()
                .getUrl("<a href=\"http://www.baidu.com/gaoji/preferences.html\"  onmousedown=\"return user_c({'fm':'set','tab':'setting','login':'0'})\">����");
    }
}
