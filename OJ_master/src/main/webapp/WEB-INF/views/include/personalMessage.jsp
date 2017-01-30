<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<ul class="nav nav-tabs"　role="tablist">
			<li class="active" role="presentation"><a href="${basePath}/problems/${problem.problem_id}">查看信息</a>
			</li>
			<li role="presentation"><a
				href="${basePath}/submissions/querySubmission?problem_id=${problem.problem_id}">修改信息</a>
			</li>
			<li role="presentation"><a
				href="${basePath}/submissions/querySubmission?problem_id=${problem.problem_id}">修改密码</a>
			</li>
			
</ul>