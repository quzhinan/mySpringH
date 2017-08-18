<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page trimDirectiveWhitespaces="true"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="q" uri="http://www.quzhinan.com/tags"%>
<div class="auth-box register">
	<div class="content">
		<div class="header">
			<div class="logo text-center">
				<img src="<q:url value='/images/logo-dark.png'/>"
					alt="Klorofil Logo">
			</div>
			<p class="lead">密码重置</p>
		</div>
		<q:url var="urlPost" action="resetpassword" method="save" />
		<form action="${urlPost}" method="post">
			<div class="form-group">
				<label class="control-label sr-only">请输入需要重置的账号</label>
				<input type="text" class="form-control" id="username" name="username"
					placeholder="请输入需要重置的账号" value="${username}" />
			</div>
			<div class="form-group">
				<label class="control-label sr-only">请输入注册时填写的邮箱</label>
				<input type="text" class="form-control" id="email" name="email"
					placeholder="请输入注册时填写的邮箱" value="${email}" />
			</div>
			<div class="form-group">
				<c:if test="${not empty msg}">
					<fmt:message key="${msg}" />
				</c:if>
			</div>
			<button type="submit" class="btn btn-primary btn-lg btn-block">重置</button>
			<div class="bottom">
				<q:url var="urlLogin" action="welcome" />
				<span class="helper-text"><a href="${urlLogin}">返回登录</a></span>
			</div>
		</form>
	</div>
</div>
