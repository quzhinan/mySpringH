package com.qzn.filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.qzn.auth.Authenticator;
import com.qzn.log.LogLevel;
import com.qzn.models.User;
import com.qzn.utils.CookieUtil;
import com.qzn.utils.DateUtil;
import com.qzn.utils.StringUtil;
import com.qzn.utils.WebUtil;

@Component
public class TrackLogFilter extends HttpServlet implements Filter {

	private static final long serialVersionUID = 5497744146730186671L;

	private static Logger trackLogger = LogManager.getLogger();

	public static final String TRACK_LOG_KEY = "X-TSS-STATS";

	private static final String HTTP_HEADER_LOGIN_USER_NAME = "LOGIN_USER_ID";

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		String username = request.getHeader(HTTP_HEADER_LOGIN_USER_NAME);
		String trackLog = CookieUtil.getCookie(request, TRACK_LOG_KEY);
		CookieUtil.SetCookie(response, TrackLogFilter.TRACK_LOG_KEY, "");
		if (!StringUtils.isEmpty(trackLog)) {
			trackLog = trackLog.replace("%2C", ",");
		}

		if (StringUtils.isEmpty(trackLog)) {

			trackLog = request.getHeader(TRACK_LOG_KEY);
		}

		if (StringUtils.isNotEmpty(trackLog)) {
			String ip = WebUtil.getIp(request);

			User user = (User) Authenticator.loadActiveUser().getUserInfo();
			String loginUserId = "";
			if (user != null) {
				loginUserId = user.getUsername();
			} else if (!StringUtil.isBlank(username)) {
				loginUserId = username;
			}

			// 処理開始日時
			Date startDate = DateUtil.getSysdate();

			// リクエストURL
			String reqeustUrl = request.getRequestURI();

			CustomHttpServletResponse cresponse = new CustomHttpServletResponse((HttpServletResponse) response);

			chain.doFilter(request, cresponse);
			Date endDate = DateUtil.getSysdate();

			if ("Companion,App,Start".equals(trackLog)) {
				User contact = (User) request.getSession().getAttribute("contact");
				if (contact != null) {
					trackLog = trackLog + "," + contact.getId();
				}
			}
			byte[] data = cresponse.getCopy();
			String htmStr = new String(data, response.getCharacterEncoding());
			int length = htmStr.getBytes().length;

			String ua = request.getHeader("User-Agent");
			if (ua != null) {
				ua = new String(ua.getBytes("ISO-8859-1"), "UTF-8");
			}
			Long doTime = endDate.getTime() - startDate.getTime();
			String logInfo = String.format(
					"\"%s\" \"%s\" \"%s\" \"%s\" \"%s\" \"%s\" \"%s\" \"%s\" \"%s\" \"%s\" \"%s\"", getServerIp(), "-",
					loginUserId, DateUtil.getDateString(startDate, DateUtil.DATETIME_FORMAT_LOG), reqeustUrl,
					response.getStatus(), length, doTime, ip, WebUtil.urlEncode(ua, WebUtil.WEBUTIL_DEFAULT_ENCODING),
					trackLog);

			trackLogger.log(LogLevel.TRACK, logInfo);
		} else {
			chain.doFilter(request, response);
		}
	}

	private static String getServerIp() {
		String serverIp = StringUtil.STRING_EMPTY;
		try {
			Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
			InetAddress ip = null;
			while (allNetInterfaces.hasMoreElements()) {
				NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
				Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
				while (addresses.hasMoreElements()) {
					ip = (InetAddress) addresses.nextElement();
					if (ip != null && ip instanceof Inet4Address) {
						serverIp = ip.getHostAddress();
					}
				}
			}
		} catch (Exception ex) {
			trackLogger.error(ex.getMessage(), ex);
		}

		return serverIp;
	}

	public class CustomServletOutputStream extends ServletOutputStream {

		private OutputStream outputStream;
		private ByteArrayOutputStream byteArrayOutputStream;

		public CustomServletOutputStream(OutputStream outputStream) {
			this.outputStream = outputStream;
			byteArrayOutputStream = new ByteArrayOutputStream();
		}

		public boolean isReady() {
			return false;
		}

		@Override
		public void write(int b) throws IOException {
			outputStream.write(b);
			byteArrayOutputStream.write(b);
		}

		public byte[] getCopy() {
			return byteArrayOutputStream.toByteArray();
		}

		@Override
		public void setWriteListener(WriteListener arg0) {
		}
	}

	public class CustomHttpServletResponse extends HttpServletResponseWrapper {

		private ServletOutputStream outputStream;
		private PrintWriter writer;
		private CustomServletOutputStream cout;

		/**
		 * Constructs a response adaptor wrapping the given response.
		 *
		 * @throws IllegalArgumentException
		 *             if the response is null
		 */
		public CustomHttpServletResponse(HttpServletResponse response) {
			super(response);
		}

		@Override
		public ServletOutputStream getOutputStream() throws IOException {
			if (writer != null) {
				throw new IllegalStateException("getWriter() has already been called on this response.");
			}

			if (outputStream == null) {
				outputStream = getResponse().getOutputStream();
				cout = new CustomServletOutputStream(outputStream);
			}
			return cout;
		}

		@Override
		public PrintWriter getWriter() throws IOException {
			if (outputStream != null) {
				throw new IllegalStateException("getOutputStream() has already been called on this response.");
			}

			if (writer == null) {
				cout = new CustomServletOutputStream(getResponse().getOutputStream());
				writer = new PrintWriter(new OutputStreamWriter(cout, getResponse().getCharacterEncoding()), true);
			}
			return writer;
		}

		@Override
		public void flushBuffer() throws IOException {
			if (writer != null) {
				writer.flush();
			} else if (outputStream != null) {
				cout.flush();
			}
		}

		public byte[] getCopy() {
			if (cout != null) {
				return cout.getCopy();
			} else {
				return new byte[0];
			}
		}
	}
}
