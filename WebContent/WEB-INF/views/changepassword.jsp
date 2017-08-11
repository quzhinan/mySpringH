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
				<img src="<q:url value='/images/logo-dark.png'/>" alt="Klorofil Logo">
			</div>
			<p class="lead">密码变更</p>
		</div>
		<q:url var="urlPost" action="changepassword" method="save" />
		<form:form modelAttribute="login" action="${urlPost}" method="post">
			<div class="form-group">
				<label class="control-label sr-only">新密码</label>
				<form:input type="password" class="form-control" path="password"
					placeholder="新密码" value="${login.password}" />
				<form:errors path="password" />
			</div>
			<div class="form-group">
				<label class="control-label sr-only">重复新密码</label>
				<form:input type="password" class="form-control" path="repeatPassword"
					placeholder="重复新密码" value="${login.repeatPassword}" />
				<form:errors path="repeatPassword" />
			</div>
			<button type="submit" class="btn btn-primary btn-lg btn-block">保存</button>
			<div class="bottom">
				<q:url var="urlLogin" action="welcome"/>
				<span class="helper-text"><a href="${urlLogin}">返回登录</a></span>
			</div>
		</form:form>
	</div>
</div>
