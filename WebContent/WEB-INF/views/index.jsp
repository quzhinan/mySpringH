<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="q" uri="http://www.quzhinan.com/tags"%>
<table>
	<tr id="usersTr">
		<td style="width: 100px;">序号</td>
		<td style="width: 100px;">姓名</td>
		<td style="width: 100px;">年龄</td>
		<td style="width: 100px;">地址</td>
	</tr>
	<c:forEach var="item" items="${pagination.items}" varStatus="status">
		<tr>
			<td><input name="ids" type="checkbox" /></td>
			<td><q:out value="${item.username}" /></td>
			<td><q:out value="${item.age}" /></td>
			<td><q:out value="${item.address}" /></td>
		</tr>
	</c:forEach>
</table>


