<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>提交列表</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/include/header.jsp" flush="true" />
	<div class="container">
		<ul class="nav nav-tabs">
			<li role="presentation"><a
				href="${basePath}/problems/${problemId}">题目</a></li>
			<li class="active" role="presentation"><a
				href="${basePath}/submissions/querySubmission/p?problem_id=${problemId}">我的提交</a>
			</li>
			<li  role="presentation"><a
				href="${basePath}/discuss/problem/comment?problem_id=${problemId}">讨论区</a></li>
			<li role="presentation"　><a
				href="${basePath}/sort/problem/users/?problem_id=${problemId}">本题排名</a>
			</li>
		</ul>
		<table class="table table-hover table-striped">
			<thead>
				<tr>
					<th>#</th>
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
						<td id="submissionId"><a
							onclick="callback(${submission.submission_id})">${submission.submission_id}</a></td>
						<td><fmt:formatDate value="${submission.submitTime}"
								type="both" pattern="YYYY-MM-dd HH:mm:ss" /></td>
						<td>${submission.judgeResult.judgeResultName }</td>
						<td>${submission.judgeScore }</td>
						<td><a
							href="${basePath}/problems/${submission.problem.problem_id}">${submission.problem.problem_id}</a></td>
						<td>${submission.useTime }</td>
						<td>${submission.usememory }</td>
						<td><a href="${basePath}/accounts/userDetail?userId=${submission.user.user_id}">${submission.user.username}</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<div class="row">
			<h3>最近一次提交的代码</h1>
			<pre id="code">
			
		</pre>
			<h1 id="runInfo"></h1>

		</div>
	</div>


	<script type="text/javascript">
		$(function(){
			load();
		})
		
	function load() {
			var i =${submissions[0].submission_id};
			getCode(i);
			getRunInfo(i);
		}
		
		function callback(submissionId){
			getCode(submissionId);
			getRunInfo(submissionId);
		}
			
		
		
		function getCode(submissionId){
			var postData ={
					'submission_id' :submissionId
			}
			$.ajax({
				type : 'POST',
				url  : '${basePath}/submissions/queryCode',
				data : postData,
				dataType : 'JSON',
				success:function(result){
					var pre=document.getElementById('code');
					pre.innerHTML=result['code'];
				}
		})
		}
		
		function getRunInfo(submissionId){
			var postData ={
					'submission_id' :submissionId
			}
			$.ajax({
				type : 'POST',
				url  : '${basePath}/submissions/queryRunInfo',
				data : postData,
				dataType : 'JSON',
				success:function(result){
					var runInfo=document.getElementById('runInfo');
					runInfo.innerHTML=result['runInfo'];
				}
		})
			
		}
		
	</script>

</body>
</html>