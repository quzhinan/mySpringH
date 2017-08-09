<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page trimDirectiveWhitespaces="true"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="q" uri="http://www.quzhinan.com/tags"%>
<!-- LEFT SIDEBAR -->
		<div id="sidebar-nav" class="sidebar">
			<div class="sidebar-scroll">
				<nav>
					<ul class="nav">
						<li><a href="index.htm" class="${index}"><i class="lnr lnr-home"></i> <span>Dashboard</span></a></li>
						<li><a href="elements.htm" class="${elements}"><i class="lnr lnr-code"></i> <span>Elements</span></a></li>
						<li><a href="charts.htm" class="${charts}"><i class="lnr lnr-chart-bars"></i> <span>Charts</span></a></li>
						<li><a href="panels.htm" class="${panels}"><i class="lnr lnr-cog"></i> <span>Panels</span></a></li>
						<li><a href="notifications.htm" class="${notifications}"><i class="lnr lnr-alarm"></i> <span>Notifications</span></a></li>
						<%-- <li>
							<a href="#subPages" data-toggle="collapse" class="collapsed"><i class="lnr lnr-file-empty"></i> <span>Pages</span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>
							<div id="subPages" class="collapse ">
								<ul class="nav">
									<li><a href="page-profile.htm" class="${page-profile}">Profile</a></li>
									<li><a href="page-login.htm" class="${page-login}">Login</a></li>
									<li><a href="page-lockscreen.htm" class="${page-lockscreen}">Lockscreen</a></li>
								</ul>
							</div>
						</li> --%>
						<li><a href="tables.htm" class="${tables}"><i class="lnr lnr-dice"></i> <span>Tables</span></a></li>
						<li><a href="typography.htm" class="${typography}"><i class="lnr lnr-text-format"></i> <span>Typography</span></a></li>
						<li><a href="icons.htm" class="${icons}"><i class="lnr lnr-linearicons"></i> <span>Icons</span></a></li>
					</ul>
				</nav>
			</div>
		</div>
<!-- END LEFT SIDEBAR -->