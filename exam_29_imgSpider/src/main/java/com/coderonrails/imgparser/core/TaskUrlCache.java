package com.coderonrails.imgparser.core;

import java.util.HashMap;
import java.util.Map;

/**
 * @author coderonrails
 */
public class TaskUrlCache {
    static Map<String, Boolean> urlCache = new HashMap<String, Boolean>();

    public synchronized static void addUrl(String url) {
        if (!urlCache.containsKey(url)) {
            urlCache.put(url, false);
        }
    }

    public synchronized static String getUrl() {
        for (String key : urlCache.keySet()) {
            if (urlCache.get(key) == false) {
                return key;
            }
        }
        return null;
    }

    public synchronized static void finishUrl(String url) {
        urlCache.put(url, true);
    }
}
