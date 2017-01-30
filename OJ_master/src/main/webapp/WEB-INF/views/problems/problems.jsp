<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	    <meta charset="utf-8">
	    <title>题目列表</title>
  	</head>
  	<body>
  	<jsp:include page="/WEB-INF/views/include/header.jsp" flush="true" />
	<body>
		<div class="container">
		<div class="row">
	<div class="page-header col-lg-8">
  <h1>Welcome ${user.username} <small>You have solved ${user.submitted}/ ${totals} problems</small></h1>
	</div>
    <div class="input-group col-lg-4">  
           <input type="text" class="form-control" placeholder="请输入关键字" / >  
                <span class="input-group-btn">  
                   <button class="btn btn-info btn-search">查找</button>  
                </span>  
     </div> 
		</div> 
  		<table class="table table-hover table-striped">
   			<thead>
					<tr>
						<th>#</th>
						<th>标题</th>					
						<th>通过率</th>
						<th>难度</th>					
						<th>作者</th>
					
					</tr> 
   			</thead>
   			<tbody>
   				<c:forEach var="problem" items="${list}" >
					<tr>
						<td>${problem.problem_id}</td>
						<td><a href="${basePath}/problems/${problem.problem_id}">${problem.title}</a></td>
						<c:choose>
						
						<c:when test="${problem.submitted eq 0}">
							<td>0% ${problem.solved}/${problem.submitted} </td>
						</c:when> 
						<c:otherwise>
							<td><fmt:formatNumber type="number" value="${problem.solved *100/problem.submitted}" maxFractionDigits="2"/>%  (${problem.solved}/${problem.submitted})</td>
						</c:otherwise> 
						</c:choose>
						<td>${problem.level}</td>
						<td>${problem.author}</td> 
					</tr>  
				</c:forEach> 
					
   			</tbody>
 			</table>	
 	
 		<div class="row">		 
 					<div class="col-lg-2">
 				
     			<%--  <select>
     			 <option><a href="${basePath}/problems?pageSize=20">20</a></option>
     			 <option><a href="${basePath}/problems?pageSize=50">50</a></option>
     			 <option><a href="${basePath}/problems?pageSize=100">100</a></option>
     			 </select> --%>
     			 </div> 
					<div class="col-lg-10">
						<ul class="pagination" style="height: auto;" >
          	<c:if test="${(page-1)*pageSize gt 0}">
 				<li><a href="${basePath}/problems?page=${page-1}">« 上一页</a></li>
 			</c:if>
 			
             <c:choose>
             	<c:when test="${totals/pageSize gt 12}">
             		<c:forEach begin="1" step="1" varStatus="status" end="12">
	            		<c:if test="${status.index+page-1 lt (totals/pageSize+1)}">
		            		<c:choose>
		            			<c:when test="${status.first}">
			            			 <li class="active">
			            		 	 	<a href="${basePath}/problems?page=${status.index-1+page}">${status.index-1+page}</a>
			      					</li>
		            			</c:when>
		            			<c:otherwise>
		            				<li class="">
			            				<a href="${basePath}/problems?page=${status.index-1+page}">${status.index-1+page}</a>
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
			            		 	 	<a href="${basePath}/problems?page=${status.index}">${status.index}</a>
			      					</li>
	            				</c:when>
	            				<c:otherwise>
		            				<li class="">
			            				<a href="${basePath}/problems?page=${status.index}">${status.index}</a>
			      					</li>
	            				</c:otherwise>
	            		</c:choose>
	 		  		</c:forEach>
             	</c:otherwise>
             </c:choose>
             
              	<c:if test="${(page+1)*pageSize lt totals}">
 						<li><a href="${basePath}/problems?page=${page+1}">« 下一页</a></li>
 				</c:if>
      </ul>
      			</div>
		
				</div>
		
		</div>
	</body>
</html>