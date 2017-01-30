<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
			<li class="active" role="presentation">
			<a href="${basePath}/message/personalMessage">私信</a></li>
			<li role="presentation">
			<a href="${basePath}/message/systemMessage">系统通知</a></li>
		</ul>
		
		<div class="container">
			<div class="row">
				<c:forEach var="message" items="${messages}">
					<div class="media">
						
							<c:if test="${user.user_id eq message.toId}">
								<a class="media-left media-middle" href="#">
							    	<img src="https://images.nowcoder.com/head/656m.png@0e_50w_50h_0c_1i_1o_90Q_1x.png" alt="...">
								</a>
							</c:if>
					  		<div class="media-body">
							    <h4 class="media-heading">${message.content}</h4>
							     <fmt:formatDate value="${message.createTime}" type="both" pattern="YYYY-MM-dd HH:mm:ss"/>
							</div>
							<c:if test="${user.user_id != message.toId}">
								<a class="media-right media-middle" href="#">
							    	<img src="https://images.nowcoder.com/head/656m.png@0e_50w_50h_0c_1i_1o_90Q_1x.png" alt="...">
								</a>
							</c:if>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>