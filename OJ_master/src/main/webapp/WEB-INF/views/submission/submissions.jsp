<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>提交列表</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/include/header.jsp" flush="true" />
<body>
	<div class="container">
		<div class="row">
			<div class="page-header col-lg-8">
				<h1>
					提交列表
				</h1>
			</div>
			<div class="input-group col-lg-4">
				<input type="text" class="form-control" placeholder="请输入关键字"/ >
				<span class="input-group-btn">
					<button class="btn btn-info btn-search">查找</button>
				</span>
			</div>
		</div>
		<table class="table table-hover table-striped">
			<thead>
				<tr>
					<th>提交时间</th>
					<th>结果</th>
					<th>得分</th>
					<th>题目</th>
					<th>用时(ms)</th>
					<th>内存(kb)</th>
					<th>用户</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="submission" items="${submissions}">
					<tr>
						<td><fmt:formatDate value="${submission.submitTime}" type="both" pattern="YYYY-MM-dd HH:mm:ss"/></td>
						<td>${submission.judgeResult.judgeResultName }</td>
						<td>${submission.judgeScore }</td>
						<td><a href="${basePath}/problems/${submission.problem.problem_id}">${submission.problem.problem_id}</a></td> 
						<td>${submission.useTime }</td>
						<td>${submission.usememory }</td>
						<td><a href="${basePath}/accounts/userDetail?userId=${submission.user.user_id}">${submission.user.username}</a></td>
					</tr>
				</c:forEach>

			</tbody>
		</table>

		<div class="row">
			<div class="col-lg-2"></div>
			<div class="col-lg-10">
				<ul class="pagination" style="height: auto;">

					<li><a href="${basePath}/submissions?page=${page-1}">« 上一页</a></li>
					<c:choose>
						<c:when test="${totals/pageSize >12}">
							<c:forEach begin="1" step="1" varStatus="status" end="12">
								<c:choose>
									<c:when test="${status.first}">
										<li class="active"><a
											href="${basePath}/submissions?page=${status.index-1+page}">${status.index-1+page}</a>
										</li>
									</c:when>
									<c:otherwise>
										<li class=""><a
											href="${basePath}/submissions?page=${status.index-1+page}">${status.index-1+page}</a>
										</li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<li><a href="${basePath}/submissions?page=${page+1}">下一页 »</a></li>
						</c:when>
						<c:otherwise>
							<c:forEach begin="1" step="1" varStatus="status"
								end="${totals/pageSize}">
								<li class=""><a href="${basePath}/submissions?page=${index}">${status.index+page}</a>
								</li>
							</c:forEach>
							<li class="disable"><a
								href="${basePath}/submissions?page=${page+1 }">下一页 »</a></li>
						</c:otherwise>

					</c:choose>
				</ul>
			</div>

		</div>

	</div>
</body>
</html>