package com.qzn.tags;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.jstl.fmt.LocalizationContext;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.tag.common.fmt.BundleSupport;

import com.qzn.helper.SystemOptionsHelper;
import com.qzn.models.Master;

public class MasterTag extends TagSupport {

	private static final long serialVersionUID = -8047582108498695232L;

	private String var;
	private String code;
	private String value;

	public String getVar() {
		return var;
	}

	public void setVar(String var) {
		this.var = var;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int doStartTag() {
		return SKIP_BODY;
	}

	public int doEndTag() throws JspException {

		JspWriter writer = pageContext.getOut();

		StringBuffer sb = new StringBuffer();

		ResourceBundle bundle = null;
		LocalizationContext locCtxt = BundleSupport.getLocalizationContext(this.pageContext);
		if (locCtxt != null) {
			bundle = locCtxt.getResourceBundle();
		}

		if (this.value == null) {
			List<Master> masters = SystemOptionsHelper.getInstance().getMaster(this.code);
			if (bundle != null) {
				for (Master master : masters) {
					if (master.getText() == null) {
						master.setText(bundle.getString(master.getLabel()));
					}
				}
			}
			if (this.var == null) {
				for (Master master : masters) {
					sb.append(master.getValue() + ":" + master.getText() + ";");
				}
			} else {
				pageContext.setAttribute(this.var, masters, PageContext.PAGE_SCOPE);
			}
		} else {
			Master master = SystemOptionsHelper.getInstance().getMaster(this.code, this.value);
			if (master != null) {
				if (this.var == null) {
					if (bundle != null) {
						if (master.getText() == null) {
							master.setText(bundle.getString(master.getLabel()));
						}
						sb.append(master.getText());
					}
				} else {
					pageContext.setAttribute(this.var, master, PageContext.PAGE_SCOPE);
				}
			}
		}

		if (this.var == null) {
			try {
				writer.print(sb.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return EVAL_PAGE;
	}
}
