<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>排名</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/include/header.jsp" flush="true" />
<body>
	<div class="container">
	<ul class="nav nav-tabs">
			<li role="presentation"><a
				href="${basePath}/sort/users">总排名</a></li>
			<li class="active" role="presentation"><a
				href="${basePath}/sort/user/weekSort">本周排名</a>
			</li>
			
		</ul>
		<table class="table table-hover table-striped">
			<thead>
				<tr>
					<th>排名</th>
					<th>用户名</th>
					<th>通过题数</th>
					<th>提交数</th>
					<th>通过率</th>

				</tr>
			</thead>
			<tbody>
				<c:forEach var="sort" varStatus="status" items="${sorts}">
					<tr>
						<td>${status.index+1}</td>
						<td><a href="${basePath}/accounts/userDetail?userId=${submission.user.user_id}">${sort.user.username}</a></td>
						<td>${sort.acceptCount}</td>
						<td>${sort.submitCount}</td>
						<c:choose>
							<c:when test="${sort.submitCount eq 0}">
								<td>0% ${sort.acceptCount}/${sort.submitCount}</td>
							</c:when>
							<c:otherwise>
								<td><fmt:formatNumber type="number"
										value="${sort.acceptCount *100/sort.submitCount}"
										maxFractionDigits="2" />%</td>
							</c:otherwise>
						</c:choose>
					</tr>
				</c:forEach>

			</tbody>
		</table>

		

	</div>
</body>
</html>