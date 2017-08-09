package com.qzn.tags;

import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.springframework.web.util.HtmlUtils;
import org.springframework.web.util.JavaScriptUtils;

public class OutTag extends BodyTagSupport {

	private static final long serialVersionUID = -7746663408599545919L;

	private String var;
	private Object value;
	private boolean htmlEscape = true;
	private boolean javaScriptEscape = false;
	private String formatDate;
	private String formatCurrency;
	private String separateIndex;
	private String separateString;

	public String getVar() {
		return var;
	}

	public void setVar(String var) {
		this.var = var;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public boolean isHtmlEscape() {
		return htmlEscape;
	}

	public void setHtmlEscape(boolean htmlEscape) {
		this.htmlEscape = htmlEscape;
	}

	public boolean isJavaScriptEscape() {
		return javaScriptEscape;
	}

	public void setJavaScriptEscape(boolean javaScriptEscape) {
		this.javaScriptEscape = javaScriptEscape;
	}

	public String getFormatDate() {
		return formatDate;
	}

	public void setFormatDate(String formatDate) {
		this.formatDate = formatDate;
	}

	public String getFormatCurrency() {
		return formatCurrency;
	}

	public void setFormatCurrency(String formatCurrency) {
		this.formatCurrency = formatCurrency;
	}

	public String getSeparateIndex() {
		return separateIndex;
	}

	public void setSeparateIndex(String separateIndex) {
		this.separateIndex = separateIndex;
	}

	public String getSeparateString() {
		return separateString;
	}

	public void setSeparateString(String separateString) {
		this.separateString = separateString;
	}

	public int doEndTag() throws JspException {

		JspWriter writer = pageContext.getOut();
		// HttpServletRequest request =
		// (HttpServletRequest)pageContext.getRequest();

		Object out = null;
		if (value != null) {
			out = value;
		} else if (getBodyContent() != null) {
			out = getBodyContent().getString();
		}

		if (out == null) {
			out = "";
		} else {
			if (formatDate != null) {
				DateFormat dateFormatter = new SimpleDateFormat(formatDate);
				out = dateFormatter.format((Date) out);
			} else if (formatCurrency != null) {
				Double number = Double.parseDouble(out.toString());
				if (formatCurrency.equals("CNY")) {
					NumberFormat nf = DecimalFormat.getCurrencyInstance(Locale.CHINA);
					Currency.getInstance(formatCurrency);
					out = nf.format(number / 100.0);
				} else if (formatCurrency.equals("JPY")) {
					NumberFormat nf = DecimalFormat.getCurrencyInstance(Locale.JAPAN);
					out = nf.format(number);
				} else {
					if ("".equals(formatCurrency)) {
						formatCurrency = "JPY";
					}
					DecimalFormat df = new DecimalFormat();
					df.setCurrency(Currency.getInstance(formatCurrency));
					out = df.getCurrency().getSymbol() + df.format(number);
				}
			} else {
				if (javaScriptEscape) {
					out = JavaScriptUtils.javaScriptEscape(out.toString());
				} else if (htmlEscape) {
					out = HtmlUtils.htmlEscape(out.toString());
				}
			}
		}

		StringBuffer sb = new StringBuffer(
				out.toString().replace("\r\n", "<BR/>").replace("\n", "<BR/>").replace("\r", "<BR/>"));

		if (separateIndex != null) {
			List<Integer> indexs = new ArrayList<Integer>();
			String[] indexsTemp = separateIndex.split(",");
			for (int i = 0; i < indexsTemp.length; i++) {
				indexs.add(Integer.valueOf(indexsTemp[i]));
			}
			if (indexsTemp.length == 1) {
				int index = Integer.valueOf(indexsTemp[0]);
				for (int i = 1; i < sb.length() / index; i++) {
					indexs.add(index);
				}
			}
			int offset = 0;
			String flag = separateString == null ? " " : separateString;
			for (int step = 0; step < indexs.size(); step++) {
				offset += indexs.get(step);
				if (offset >= sb.length()) {
					break;
				}
				sb.insert(offset, flag);
				offset += flag.length();
			}
		}

		try {
			writer.print(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return EVAL_PAGE;
	}
}
