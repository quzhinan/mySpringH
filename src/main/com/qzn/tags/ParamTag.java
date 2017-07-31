package com.qzn.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class ParamTag extends BodyTagSupport {

	private static final long serialVersionUID = -6851898425724924446L;
	
	private String name;
	private String value;
	private boolean valueSet;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
		this.valueSet = true;
	}

	public int doEndTag() throws JspException {

		Param param = new Param();
		param.setName(this.name);
		
		if (this.valueSet) {
			param.setValue(this.value);
		} else if (getBodyContent() != null) {
			param.setValue(getBodyContent().getString().trim());
		}

		ParamAware paramAware = (ParamAware) findAncestorWithClass(this, ParamAware.class);
		if (paramAware == null) {
			throw new JspException("The param tag must be a descendant of a tag that supports parameters");
		}

		paramAware.addParam(param);
		
		return EVAL_PAGE;
	}


	public void release() {
		super.release();
		this.name = null;
		this.value = null;
		this.valueSet = false;
	}
}
