package com.qzn.tags;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

public class UrlTag extends TagSupport implements ParamAware {

	private static final long serialVersionUID = 1L;

	private String var;
	private String value;
	private String action;
	private String method;
	private boolean isWebservice;

	private List<Param> params;

	public String getVar() {
		return var;
	}

	public void setVar(String var) {
		this.var = var;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

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

	public boolean getIsWebservice() {
		return isWebservice;
	}

	public void setIsWebservice(boolean isWebservice) {
		this.isWebservice = isWebservice;
	}

	public void addParam(Param param) {
		params.add(param);
	}

	public int doStartTag() {
		params = new ArrayList<Param>();
		return EVAL_BODY_INCLUDE;
	}

	public int doEndTag() throws JspException {

		JspWriter writer = pageContext.getOut();
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();

		int suffixType = 0;
		StringBuffer sb = new StringBuffer();
		sb.append(request.getContextPath());
		if (this.value != null) {
			sb.append(this.value);
		} else if (this.action != null) {
			sb.append("/" + action);
			if (this.method != null) {
				sb.append("/" + method);
			}
			suffixType = 1;
		}
		// for (Param param : params) {
		// sb.append("/" + param.getName() + "/" + param.getValue());
		// }
		if (suffixType == 1) {
			if (isWebservice) {
				sb.append(".ws");
			} else {
				sb.append(".htm");
			}
		}
		String prefix = "?";
		for (Param param : params) {
			sb.append(prefix + param.getName() + "=" + param.getValue());
			prefix = "&";
		}
		String url = sb.toString();

		if (var == null) {
			try {
				writer.print(url);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			pageContext.setAttribute(this.var, url, PageContext.PAGE_SCOPE);
		}

		return EVAL_PAGE;
	}
}
