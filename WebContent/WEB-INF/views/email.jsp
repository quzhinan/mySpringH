<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page trimDirectiveWhitespaces="true"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="q" uri="http://www.quzhinan.com/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.emailTable {
	width: 50%;
	/* height: 500px; */
	min-height: 100px;
	font-size: 5px;
	color: red;
}
</style>
</head>
<body>
<q:url var="urlPost" action="manager/emailFunc" method="email"/>
	<form:form modelAttribute="email" action="${urlPost}" method="post">
		<table class="emailTable" border="0">
			<tr>
				<td>接收邮箱:</td>
				<td><form:input path="toEmailAddresses" /></td>
			</tr>
			<tr>
				<td>邮件标题:</td>
				<td><form:input path="subject" /></td>
			</tr>
			<tr>
				<td>邮件内容:</td>
				<td><form:textarea path="content"
						style="height:80px;width:200px;" /></td>
			</tr>
			<tr>
				<td>发出邮箱:</td>
				<td><form:input path="fromEmailAddress" /></td>
			</tr>
			<tr>
				<td>发送者:</td>
				<td><form:input path="fromPersonName" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="发送" /></td>
			</tr>
			<c:if test="${result ne null}">
				<tr>
					<td>发送结果：</td>
					<td><textarea style="height: 80px; width: 200px;">${result}</textarea></td>
				</tr>
			</c:if>
			<tr>
				<td colspan="2"><input type="button" value="返回"
					onclick="window.location='index.htm'" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>