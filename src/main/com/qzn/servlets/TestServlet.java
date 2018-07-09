package com.qzn.servlets;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

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
//		String jsonUrl = "http://www.town.aikawa.kanagawa.jp/feed.xml?type=rss_2.0&new1=1";
		String fileUrl = "/Users/quzhinan/datas/pic/722a0002eec58da7ccb9.gif";
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "get,post");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");
//		readJson(jsonUrl, response);
		readFileFromLocal(fileUrl, response);
	}

	public void readFileFromUrl(String url, HttpServletResponse response) {
		HttpClient client = new HttpClient();
		PostMethod myPost = new PostMethod(url);
		try {
			int statusCode = client.executeMethod(myPost);
			if (statusCode == HttpStatus.SC_OK) {
				BufferedInputStream bis = new BufferedInputStream(myPost.getResponseBodyAsStream());
				byte[] bytes = new byte[1024];
				ServletOutputStream os = response.getOutputStream();
				BufferedOutputStream bos = new BufferedOutputStream(os);
				int count = 0;
				while ((count = bis.read(bytes)) != -1) {
					bos.write(bytes, 0, count);
				}
				bos.flush();
				bos.close();
				bis.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		myPost.releaseConnection();
	}
	
	public void readFileFromLocal(String url, HttpServletResponse response) {
		
		File file = new File(url);
		try {
			FileInputStream fis = new FileInputStream(file);
			BufferedInputStream bis = new BufferedInputStream(fis);
			byte[] bytes = new byte[1024];
			ServletOutputStream os = response.getOutputStream();
			BufferedOutputStream bos = new BufferedOutputStream(os);
			int count = 0;
			while ((count = bis.read(bytes)) != -1) {
				bos.write(bytes, 0, count);
			}
			bos.flush();
			bos.close();
			bis.close();
			fis.close();
//			byte[] data = null;
//			data = new byte[fis.available()];
//			fis.read(data);
//			fis.close();
//			BASE64Encoder encoder = new BASE64Encoder();
//			String temPath = encoder.encode(data);
//			String retPath = "base64," + temPath;
//			response.getWriter().write(retPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void readJson(String url, HttpServletResponse response) {
		
		HttpURLConnection connection = null;
		InputStreamReader isr = null;
		OutputStreamWriter osw = null;
		try {
			URL targetUrl = new URL(url);
			connection = (HttpURLConnection) targetUrl.openConnection();
			connection.setRequestMethod("POST");
			
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				isr = new InputStreamReader(connection.getInputStream(), "UTF-8");
				osw = new OutputStreamWriter(response.getOutputStream(), "UTF-8");
				BufferedReader br = new BufferedReader(isr);
				String line = null;
				while ((line = br.readLine()) != null) {
					osw.write(line);
				}
				osw.flush();
				osw.close();
				isr.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
