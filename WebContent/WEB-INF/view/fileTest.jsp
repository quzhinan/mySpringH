<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page trimDirectiveWhitespaces="true"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.titleDiv{
	width: 50%;
	/* height: 100px; */
	min-height: 100px;
	text-align :left;
	font-size: 30px;
	color: blue;
}
</style>
</head>
<body>
<div class="titleDiv">文件操作</div>
<form:form modelAttribute="email" action="fileUpload.htm" method="post">
	<table>
		<tr>
			<td><input type="file" id="uploadFile" value="选择文件"/>
			<input type="submit" id="submit" value="提交">
			</td>
		</tr>
		<tr>
			<td><input type="button" value="返回" onclick="window.location='index.htm'"/>
			</td>
		</tr>
	</table>
	
	
</form:form>
</body>
</html>