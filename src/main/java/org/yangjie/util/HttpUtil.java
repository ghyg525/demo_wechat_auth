package org.yangjie.util;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class HttpUtil {
	
	private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

	private static final OkHttpClient client = new OkHttpClient();
	
	private static final MediaType jsonMediaType =  MediaType.parse("application/json; charset=utf-8");
	private static final MediaType xmlMediaType =  MediaType.parse("application/xml; charset=utf-8");
	
	
    /**
     * get
     * @param url
     * @return
     * @throws Exception
     */
    public static String get(String url) {
		return execute(new Request.Builder().url(url).get().build());
    }
    
    /**
     * get
     * @param url
     * @return
     * @throws Exception
     */
    public static byte[] getBytes(String url) {
    	return executeBytes(new Request.Builder().url(url).get().build());
    }

    
    /**
     * post (json)
     * @param url
     * @return
     */
    public static String postJson(String url, String body) {
    	return post(url, body, jsonMediaType);
    }
    
    /**
     * post (xml)
     * @param url
     * @param body
     * @return
     */
    public static String postXml(String url, String body) {
    	return post(url, body, xmlMediaType);
    }
    
    /**
     * post (map)
     * @param url
     * @param formMap
     * @return
     */
    public static String postMap(String url, Map<String, String> formMap){
    	FormBody.Builder form = new FormBody.Builder();
    	if (formMap!=null && !formMap.isEmpty()) {
    		for(String key : formMap.keySet()){
    			form.add(key, formMap.get(key));
    		}
			return post(url, form.build());
		}
    	return null;
    }
    
    /**
     * post (string)
     * @param url
     * @param body
     * @param mediaType
     * @return
     */
    private static String post(String url, String body, MediaType mediaType) {
    	return post(url, RequestBody.create(mediaType, body));
    }
    
    /**
     * post
     * @param url
     * @param body
     * @return
     */
    private static String post(String url, RequestBody body){
    	return execute(new Request.Builder().url(url).post(body).build());
    }
    
    /**
     * execute
     * @param request
     * @return
     */
    private static String execute(Request request){
    	try {
			return client.newCall(request).execute().body().string();
		} catch (IOException e) {
			logger.error("okhttp请求出错", e);
		}
    	return null;
    }
    
    /**
     * execute
     * @param request
     * @return
     */
    private static byte[] executeBytes(Request request){
    	try {
    		return client.newCall(request).execute().body().bytes();
    	} catch (IOException e) {
    		logger.error("okhttp请求出错", e);
    	}
    	return null;
    }
    
}
