package com.qzn.tags;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.jstl.fmt.LocalizationContext;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.tag.common.fmt.BundleSupport;

public class NaviTag extends TagSupport {

	private static final long serialVersionUID = 5358572410388645479L;
	
	public int doStartTag() {
		return SKIP_BODY;
	}
	
	public int doEndTag() throws JspException {
		
		JspWriter writer=pageContext.getOut();
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();

		String contextPath = request.getContextPath();
		String uri = (String)request.getAttribute("javax.servlet.forward.request_uri");
		
		String action = uri.substring(contextPath.length() + 1, uri.length() - 4);
		String[] actionList = action.split("/");

		StringBuffer sb = new StringBuffer();

		if (actionList.length > 1) {
			String actionName = actionList[0];
			String methodName = actionList[1];

			LocalizationContext locCtxt = BundleSupport.getLocalizationContext(this.pageContext);
			if (locCtxt != null) {
				ResourceBundle bundle = locCtxt.getResourceBundle();
				if (bundle != null) {
					String actionMenu = bundle.getString("action." + actionName + ".menu");
					String methodMenu = bundle.getString("action." + actionName + "." + methodName + ".menu");
					sb.append("<a href=\"");
					sb.append(request.getContextPath() + "/" + actionName + ".ies");
					sb.append("\"><span>" + actionMenu + "</span></a>");
					sb.append("\n");
					sb.append("<a href=\"");
//					sb.append(request.getContextPath() + "/" + actionName + "/" + methodName + ".ies");
					sb.append("javascript:void(0);");
					sb.append("\"><span>" + methodMenu + "</span></a>");
				}
				
			}
			try {
				writer.print(sb.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return EVAL_PAGE;
	}
}
