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
				href="${basePath}/problems/${problemId}">题目</a></li>
			<li role="presentation"><a
				href="${basePath}/submissions/querySubmission/p?problem_id=${problemId}">我的提交</a>
			</li>
			<li  role="presentation"><a
				href="${basePath}/discuss/problem/comment?problem_id=${problemId}">讨论区</a></li>
			<li class="active" role="presentation"　><a
				href="${basePath}/sort/problem/users/comment?problem_id=${problemId}">本题排名</a>
			</li>
		</ul>
		<div class="row">
			<div class="page-header col-lg-8">
				<h1>
				排名 <small>你的当前排名：　${rank}/${totals} </small> 
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
					<th>排名</th>
					<th>用户</th>
					<th>用时(ms)</th>
					<th>内存(kb)</th>
					<th>提交时间</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="submission" varStatus="status" items="${submissions}">
					<tr>
						<td>${status.index+1+(pageSize*(page-1))}</td>
						<td><a href="${basePath}/accounts/userDetail?userId=${submission.user.user_id}">${submission.user.username}</a></td>
						<td>${submission.useTime}</td>
						<td>${submission.usememory}</td>
						<td><fmt:formatDate value="${submission.submitTime}"
						type="both" pattern="YYYY-MM-dd HH:mm:ss"/></td>
					
					</tr>
				</c:forEach>

			</tbody>
		</table>

		<div class="row">
			<div class="col-lg-2"></div>
			<div class="col-lg-10">
				<ul class="pagination" style="height: auto;">
			<c:if test="${(page-1)*pageSize gt 0}">
 				<li><a href="${basePath}/sort/problem/users?page=${page-1}&&problem_id=${submissions[0].problem_id}">« 上一页</a></li>
 			</c:if>
 			
             <c:choose>
             	<c:when test="${totals/pageSize gt 12}">
             		<c:forEach begin="1" step="1" varStatus="status" end="12">
	            		<c:if test="${status.index+page-1 lt (totals/pageSize+1)}">
		            		<c:choose>
		            			<c:when test="${status.first}">
			            			 <li class="active">
			            		 	 	<a href="${basePath}/sort/problem/users?page=${status.index-1+page}&&problem_id=${submissions[0].problem_id}">${status.index-1+page}</a>
			      					</li>
		            			</c:when>
		            			<c:otherwise>
		            				<li class="">
			            					<a href="${basePath}/sort/problem/users?page=${status.index-1+page}&&problem_id=${submissions[0].problem_id}">${status.index-1+page}</a>
			      					</li>
		            			</c:otherwise>
		            		</c:choose>
	            		</c:if>
 		  			</c:forEach>
 		  		</c:when>
 		 
             
             	<c:otherwise>
	             	<c:forEach begin="1" step="1" varStatus="status" end="${totals/pageSize}">
		            		<c:choose>
	            				<c:when test="${status.index eq page}">
			            			 <li class="active">
			            		 	 	<a href="${basePath}/sort/problems/users?page=${status.index}&&problem_id=${submissions[0].problem_id}">${status.index}</a>
			      					</li>
	            				</c:when>
	            				<c:otherwise>
		            				<li class="">
			            				<a href="${basePath}/sort/problems/users?page=${status.index}&&problem_id=${submissions[0].problem_id}">${status.index}</a>
			      					</li>
	            				</c:otherwise>
	            		</c:choose>
	 		  		</c:forEach>
             	</c:otherwise>
             </c:choose>
             
              	<c:if test="${(page+1)*pageSize lt totals}">
 						<li><a href="${basePath}/sort/problems/users?page=${page+1}&&problem_id=${submissions[0].problem_id}">« 下一页</a></li>
 				</c:if>
				</ul>
			</div>

		</div>

	</div>
</body>
</html>