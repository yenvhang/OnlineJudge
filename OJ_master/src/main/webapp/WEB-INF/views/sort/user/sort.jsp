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
			<li class="active" role="presentation"><a
				href="${basePath}/sort/users">总排名</a></li>
			<li role="presentation"><a
				href="${basePath}/sort/user/weekSort">本周排名</a>
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
					<th>个性签名</th>
					<th>总得分</th>
					<th>通过题数</th>
					<th>提交数</th>
					<th>通过率</th>

				</tr>
			</thead>
			<tbody>
				<c:forEach var="user" varStatus="status" items="${users}">
					<tr>
						<td>${status.index+1+(pageSize*(page-1))}</td>
						<td><a href="${basePath}/accounts/userDetail?userId=${submission.user.user_id}">${user.username}</a></td>
						<td>${user.signature}</td>
						<td>${user.score}</td>
						<td>${user.solved}</td>
						<td>${user.submitted}</td>
						<c:choose>
							<c:when test="${user.submitted eq 0}">
								<td>0% ${user.solved}/${user.submitted}</td>
							</c:when>
							<c:otherwise>
								<td><fmt:formatNumber type="number"
										value="${user.solved *100/user.submitted}"
										maxFractionDigits="2" />%</td>
							</c:otherwise>
						</c:choose>
					</tr>
				</c:forEach>

			</tbody>
		</table>

		<div class="row">
			<div class="col-lg-2"></div>
			<div class="col-lg-10">
				<ul class="pagination" style="height: auto;">
			<c:if test="${(page-1)*pageSize gt 0}">
 				<li><a href="${basePath}/sort/users?page=${page-1}">« 上一页</a></li>
 			</c:if>
 			
             <c:choose>
             	<c:when test="${totals/pageSize gt 12}">
             		<c:forEach begin="1" step="1" varStatus="status" end="12">
	            		<c:if test="${status.index+page-1 lt (totals/pageSize+1)}">
		            		<c:choose>
		            			<c:when test="${status.first}">
			            			 <li class="active">
			            		 	 	<a href="${basePath}/sort/users?page=${status.index-1+page}">${status.index-1+page}</a>
			      					</li>
		            			</c:when>
		            			<c:otherwise>
		            				<li class="">
			            				<a href="${basePath}/sort/users?page=${status.index-1+page}">${status.index-1+page}</a>
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
			            		 	 	<a href="${basePath}/sort/users?page=${status.index}">${status.index}</a>
			      					</li>
	            				</c:when>
	            				<c:otherwise>
		            				<li class="">
			            				<a href="${basePath}/sort/users?page=${status.index}">${status.index}</a>
			      					</li>
	            				</c:otherwise>
	            		</c:choose>
	 		  		</c:forEach>
             	</c:otherwise>
             </c:choose>
             
              	<c:if test="${(page+1)*pageSize lt totals}">
 						<li><a href="${basePath}/sort/users?page=${page+1}">« 下一页</a></li>
 				</c:if>
				</ul>
			</div>

		</div>

	</div>
</body>
</html>