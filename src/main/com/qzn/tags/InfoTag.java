package com.qzn.tags;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import com.qzn.auth.Authenticator;

public class InfoTag extends TagSupport {

	private static final long serialVersionUID = 5158904790766904284L;

	private String var;
	private String type;

	public String getVar() {
		return var;
	}

	public void setVar(String var) {
		this.var = var;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int doStartTag() {
		return SKIP_BODY;
	}

	public int doEndTag() throws JspException {

		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		if (Authenticator.loadActiveUser(request).isAuthorized()) {
			String info = "";
			if (type.equals("user_name")) {
				info = Authenticator.loadActiveUser(request).getUserInfo().getFullname();
			}
			if (var == null) {
				JspWriter writer = pageContext.getOut();
				try {
					writer.print(info);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				pageContext.setAttribute(this.var, info, PageContext.PAGE_SCOPE);
			}
		}

		return EVAL_PAGE;
	}
}
