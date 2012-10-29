package com.coderonrails.imgparser.core;

public class ContentSpider implements Runnable {

    /**
	 * 
	 */
    public void run() {
        /**
		 * 
		 */
        String url = null;

        while (true) {
            if (url != null) {
                FileGeter geter = new FileGeter();
                String savePath = url.replaceAll("(?is).*/", Commons.rootPath);
                geter.saveFile(url, savePath);
                ContentUrlCache.finishUrl(url);
            }
            url = ContentUrlCache.getUrl();
        }
    }

}
