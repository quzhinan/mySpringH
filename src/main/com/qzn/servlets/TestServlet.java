package com.qzn.servlets;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;

/**
 * Servlet implementation class TestServlet
 */

@WebServlet("/TestServlet")
public class TestServlet extends ServletProxy {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TestServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "http://www.town.aikawa.kanagawa.jp/feed.xml?type=rss_2.0&new1=1"; 
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "get,post");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");
		getXmlFile(url, response);
	}
	
	 public String getXmlFile(String url, HttpServletResponse response){    
	        //创建httpclient工具对象   
	        HttpClient client = new HttpClient();    
	        //创建post请求方法   
	        PostMethod myPost = new PostMethod(url);    
	        String responseString = null;    
	        try{    
	            //设置请求头部类型   
	            myPost.setRequestHeader("Content-Type", "application/xml");  
	            myPost.setRequestHeader("charset","UTF-8");  
	            int statusCode = client.executeMethod(myPost);    
	            if(statusCode == HttpStatus.SC_OK){    
	                BufferedInputStream bis = new BufferedInputStream(myPost.getResponseBodyAsStream());    
	                byte[] bytes = new byte[1024];    
//	                ByteArrayOutputStream bos = new ByteArrayOutputStream(); 
	                ServletOutputStream os = response.getOutputStream();
	                BufferedOutputStream bos = new BufferedOutputStream(os);
	                int count = 0;    
	                while((count = bis.read(bytes))!= -1){    
	                    bos.write(bytes, 0, count);    
	                }    
//	                byte[] strByte = bos.toByteArray();    
//	                responseString = new String(strByte,0,strByte.length,"utf-8");  
	                bos.flush();
	                bos.close();    
	                bis.close();    
	            }    
	        }catch (Exception e) {    
	            e.printStackTrace();    
	        }    
	        myPost.releaseConnection();    
	        return responseString;    
	    }    

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
