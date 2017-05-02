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
.titleDiv {
	width: 50%;
	/* height: 100px; */
	min-height: 100px;
	text-align: left;
	font-size: 30px;
	color: blue;
}
</style>
</head>
<body>
	<div class="titleDiv">文件操作</div>

	<table border="0" cellpadding="5" cellspacing="10">
		<tr>
			<td>
				<div>单文件上传</div>
			</td>
		</tr>
		<tr>
			<td>
				<form action="singleFileUpload.htm" method="post"
					enctype="multipart/form-data">
					<table border="0">
						<tr>
							<td><input type="file" id="singleFileUpload" name="singleFileUpload"
								value="选择文件" /></td>
							<td><input type="submit" id="submit" value="提交"></td>
						</tr>
						<c:if test="${singleResult ne null}">
							<tr>
								<td>上传结果:</td>
								<td><label>${singleResult}</label></td>
							<tr>
						</c:if>
					</table>
				</form>
			</td>
		</tr>
		<tr>
			<td>
				<div>多文件上传</div>
			</td>
		</tr>
		<tr>
			<td>
				<form action="multipleFileUpload.htm" method="post"
					enctype="multipart/form-data">
					<table>
						<tr>
							<td><input type="file" id="multipleFileUpload" name="multipleFileUpload"
								value="选择文件" multiple="multiple" /></td>
							<td><input type="submit" id="submit" value="提交"></td>
						</tr>
						<c:if test="${multipleResult ne null}">
							<tr>
								<td>上传结果:</td>
								<td><label>${multipleResult}</label></td>
							<tr>
						</c:if>
					</table>
				</form>
			</td>
		</tr>
		<tr>
			<td><input type="button" value="返回"
				onclick="window.location='index.htm'" /></td>
		</tr>
	</table>
</body>
</html>