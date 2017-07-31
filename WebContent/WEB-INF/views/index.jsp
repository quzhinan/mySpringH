<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="q" uri="http://www.quzhinan.com/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.titleDiv {
	width: 50%;
	/* height: 100px; */
	min-height: 100px;
	margin: 0 auto;
	text-align: center;
	font-size: 80px;
	color: red;
}
</style>

</head>
<body>
	<div class="titleDiv">SpringMVC</div>
	<a href="fileOperation.htm">文件操作</a><br/>
	<q:url var="urlPost" action="manager/emailFunc" method="email"/>
	<a href="${urlPost}">邮件操作</a><br/>
	<a href="address.htm">地理位置</a><br/>
	<a href="frontPage.htm">首页</a>
</body>
</html>