package com.qzn.tags;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class MenuTag extends TagSupport {

	private static final long serialVersionUID = -7653478160697598643L;
	
	private String action;
	private String method;
	
	@SuppressWarnings("unused")
	private List<Param> params;
	
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
	
	public int doStartTag() {

		JspWriter writer=pageContext.getOut();
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
		
		StringBuffer sb = new StringBuffer();
		sb.append(request.getContextPath());
		sb.append("/" + action);
		if (this.method != null) {
			sb.append("/" + method);
		}
		String url = sb.toString();
		String requestURI = (String)request.getAttribute("javax.servlet.forward.request_uri");
		
		sb = new StringBuffer();
		sb.append("<li");
		
		if (requestURI != null && requestURI.startsWith(url)) {
			sb.append(" active");
		}
		sb.append("><a href=\"");
		sb.append(url);
		sb.append(".ies\">");

		try {
			writer.print(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return EVAL_BODY_INCLUDE;
	}
	
	public int doEndTag() throws JspException {
		
		JspWriter writer=pageContext.getOut();
		
		StringBuffer sb = new StringBuffer();
		sb.append("</a></li>");
		
		try {
			writer.print(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return EVAL_PAGE;
	}
}
