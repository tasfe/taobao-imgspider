/**
 * 
 */
package com.zangb.spider;

import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author Administrator
 *
 */
public class PageGetter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String uri = "http://img03.taobaocdn.com/imgextra/i3/106952478/T21j1jXiVaXXXXXXXX_!!106952478.jpg";
		String uri2 = "http://detail.china.alibaba.com/offer/1080110090.html";
		//PageGetter.getPageContent(uri2);
		
		//String page = PageGetter.getPage(uri2, "taobaocdn.com");
		String page = FileGetter.getString("f:\\1080110090.html");
		ArrayList<String> list = PageGetter.getSrcs(page);
		for(String s:list){
			//System.out.println(s);
		}
	}
	
	/**
	 * 获取网页的内容
	 * @param url
	 * @param host
	 * @return
	 */
	public static String getPage(String url,String host){
		HttpClient client = new HttpClient();
		//hostConfiguration
		HostConfiguration hostConfig = new HostConfiguration();
		hostConfig.setHost(host, 80);
		
		//HttpMethod
		HttpMethod method = new GetMethod(url);
		
		client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");
		client.setHostConfiguration(hostConfig);
		
		try {
			int result = client.executeMethod(method);
			String body = method.getResponseBodyAsString();
			return body;	
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取<img>元素的src列表
	 * @param page
	 * @return
	 */
	public static ArrayList<String> getSrcs(String page){
		ArrayList<String> result = new ArrayList<String>();
		Document doc = Jsoup.parse(page);
		Elements imgEles = doc.select("img");
		for(Element img:imgEles){
			String src = img.attr("src");
			if(src=="http://img03.taobaocdn.com/imgextra/i3/106952478/T21j1jXiVaXXXXXXXX_!!106952478.jpg"){
				System.out.println(src);
			}
			if(StringUtils.isNotBlank(src)){
				result.add(src);
			}		
		}
		return result;
	}
	
	public static String getPageContent(String uri){
		HttpClient client = new HttpClient();
		HostConfiguration hostConfig = new HostConfiguration();
		hostConfig.setHost("alibaba.com",80);
		client.setHostConfiguration(hostConfig);
		
		HttpMethod method = new GetMethod(uri);
		//Header header = new Header();
		client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");
		
		//method.addRequestHeader(header);
		
		
		try {
			int result = client.executeMethod(method);
			client.getState();
			String body = method.getResponseBodyAsString();
			//InputStream bodyS = method.getResponseBodyAsStream();
			System.out.println(new String(body));
			
		   Image image;
		   
		   //ImageInputStream iis = ImageIO.createImageInputStream(bodyS);
		   
		   //BufferedImage img = ImageIO.read(bodyS); 
		   
		  // BufferedImage bi = reader.read(0, param);
		   //ImageIO.write(img, "jpg", new File("f:\\1.jpg"));
		   
			
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally{
			
		}
		
		
		return "";
	}

}
