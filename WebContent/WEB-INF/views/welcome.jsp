<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page trimDirectiveWhitespaces="true"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="q" uri="http://www.quzhinan.com/tags"%>
<html class="fullscreen-bg">
<div class="vertical-align-wrap">
	<div class="vertical-align-middle">
		<div class="auth-box ">
			<div class="left">
				<div class="content">
					<div class="header">
						<div class="logo text-center">
							<img src="images/logo-dark.png" alt="Klorofil Logo">
						</div>
						<p class="lead">Login to your account</p>
					</div>
					<q:url var="urlPost" action="welcome" method="login"/>
					<form:form modelAttribute="login" action="${urlPost}" method="post">
						<div class="form-group">
							<label for="signin-email" class="control-label sr-only">Email</label>
							<form:input type="text" class="form-control" id="signin-email"
								value="quzhinan" path="username" placeholder="username"/>
						</div>
						<div class="form-group">
							<label for="signin-password" class="control-label sr-only">Password</label>
							<form:input type="password" class="form-control" id="signin-password"
								value="thisisthepassword" path="password" placeholder="password"/>
						</div>
						<div class="form-group clearfix">
							<label class="fancy-checkbox element-left"> <input
								type="checkbox"> <span>Remember me</span>
							</label>
						</div>
						<button type="submit" class="btn btn-primary btn-lg btn-block">LOGIN</button>
						<div class="bottom">
							<span class="helper-text"><i class="fa fa-lock"></i> <a
								href="#">Forgot password?</a></span>
						</div>
					</form:form>
				</div>
			</div>
			<div class="right">
				<div class="overlay"></div>
				<div class="content text">
					<h1 class="heading"></h1>
				</div>
			</div>
		</div>
	</div>
</div>
</html>