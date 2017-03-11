<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/views/include/base.jsp" flush="true" />
<div class="navbar navbar-inverse navbar-extra">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="/">Online Judge</a>
		</div>
		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li><a href="${basePath}/index">首页</a> </a></li>
				<li id="subscribe"><a href="${basePath}/submissions"><font
						color="orange">提交</font></a></li>
				<li><a href="${basePath}/problems">题目</a></li>
				<li><a href="${basePath}/discuss">分类</a>
				<li><a href="${basePath}/contest/">比赛</a></li>
				<li><a href="${basePath}/sort/users">排名</a></li>
				<li><a href="${basePath}/share">分享</a></li>
				<li><a href="${basePath}/aticle">关于</a></li>
			</ul>

			<c:if test="${empty user}">
				<div class="navbar-form navbar-right">
					<a class="btn btn-primary" href="${basePath}/accounts/login">登录</a>
					<a class="btn btn-default" href="${basePath}/accounts/register">注册</a>
				</div>
			</c:if>
			<c:if test="${!empty user}">
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown"><a 　href="javascript:void(0);"
						class="dropdown-toggle" data-toggle="dropdown">
							${user.username} <span class="caret"></span>
					</a> 
						<ul class="dropdown-menu">
							<li><a href="#"><span class="badge badge-important">14</span>Profile</a></li>
							<li><a href="${basePath}/accounts/userDetail">我的资料</a></li>
							<li class="divider"></li>
							<li><a href="/submissions/">My Submissions<span class="badge badge-important"></span></a></li>
							<li><a href="/progress/">My Progress</a></li>
							<li><a href="/list/">My List</a></li>
							<li><a href="/session/">Manage Sessions</a></li>
							<li><a href="/notes/">My Notes</a></li>
							<!-- TODO:  Dashboard  -->
							<li><a href="/accounts/password/change/">Change Password</a></li>
							<li class="divider"></li>
							<li><a href="${basePath}/accounts/logout">注销</a></li>
						</ul>
					</li>
					<li><a href="${basePath}/message/personalMessage">消息<span class="badge badge-important">14</span></a></li>
				</ul>
			</c:if>

		</div> 
		<!--/.navbar-collapse -->
	</div>
</div>
</div>


