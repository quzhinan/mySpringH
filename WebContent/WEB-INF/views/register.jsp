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
			<p class="lead">注册</p>
		</div>
		<q:url var="urlPost" action="register" method="save" />
		<form:form modelAttribute="user" action="${urlPost}" method="post">
			<div class="form-group">
				<label class="control-label sr-only">用户名</label>
				<form:input type="text" class="form-control" path="username"
					placeholder="用户名" />
				<form:errors path="username" />
			</div>
			<div class="form-group">
				<label class="control-label sr-only">昵称</label>
				<form:input type="text" class="form-control" path="fullname"
					placeholder="昵称" />
				<form:errors path="fullname" />
			</div>
			<div class="form-group">
				<label class="control-label sr-only">性别</label>
				<q:master var="sex" code="user.sex.status" />
				<form:select path="sex" class="form-control">
					<form:options items="${sex}" itemValue="value" itemLabel="text" />
				</form:select>
				<form:errors path="sex" />
			</div>
			<div class="form-group">
				<label class="control-label sr-only">年龄</label>
				<form:input type="text" class="form-control" path="age"
					placeholder="年龄" />
				<form:errors path="age" />
			</div>
			<div class="form-group">
				<label class="control-label sr-only">邮箱</label>
				<form:input type="email" class="form-control" path="email"
					placeholder="邮箱" />
				<form:errors path="email" />
			</div>
			<button type="submit" class="btn btn-primary btn-lg btn-block">注册</button>
			<div class="bottom">
				<q:url var="urlLogin" action="welcome"/>
				<span class="helper-text">已有账户？<a href="${urlLogin}">登录</a></span>
			</div>
		</form:form>
	</div>
</div>
