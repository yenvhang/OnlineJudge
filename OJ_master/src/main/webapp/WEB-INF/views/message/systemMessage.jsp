<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>我的消息</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/include/header.jsp" flush="true" />
	<div class="container">
		<ul class="nav nav-tabs">
			<li role="presentation">
			<a href="${basePath}/message/personalMessage">私信</a></li>
			<li class="active" role="presentation">
			<a href="${basePath}/message/systemMessage">系统通知</a></li>
		</ul>
		<div class="container">
			<div class="row">
				<c:forEach var="comment"  items="${comments}">
					<a href="${basePath}/message/systemMessageDetail?commentId=${comment.comment_id}">${comment.from.username}回复了你的评论${comment.content}</a>
					<br>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>