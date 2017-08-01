package com.qzn.tags;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import com.qzn.auth.Authenticator;

public class RoleTag extends TagSupport {

	private static final long serialVersionUID = -8047582108498695232L;

	private String var;
	private String type;
	private String level;
	
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

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public int doStartTag() {

		int result = SKIP_BODY;
		
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
		if (Authenticator.loadActiveUser(request).isAuthorized()) {
			boolean role = Authenticator.loadActiveUser(request).getUserInfo().isHasRole(request, type, level);
			if (role == true) {
				if (var == null) {
					result = EVAL_BODY_INCLUDE;
				} else {
					pageContext.setAttribute(this.var, role, PageContext.PAGE_SCOPE);
				}
			}
		}
		
		return result;
	}
	
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
}
