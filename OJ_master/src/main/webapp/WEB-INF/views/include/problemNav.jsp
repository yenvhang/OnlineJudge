<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<ul class="nav nav-tabs">
			<li class="active" role="presentation"><a href="${basePath}/problems/${problem.problem_id}"
			role="tab"　data-toggle="tab">题目</a>
			</li>
			<li role="presentation"　role="tab"><a
				href="${basePath}/submissions/querySubmission?problem_id=${problem.problem_id}"
				role="tab"　data-toggle="tab">我的提交</a>
			</li>
			<li role="presentation"　role="tab"><a
				href="${basePath}/submissions/querySubmission?problem_id=${problem.problem_id}"
				role="tab"　data-toggle="tab">讨论区</a>
			</li>
			<li role="presentation"　><a　
				href="${basePath}/submissions/querySubmission?problem_id=${problem.problem_id}"
				role="tab"　data-toggle="tab">本题排名</a>
			</li>
</ul>