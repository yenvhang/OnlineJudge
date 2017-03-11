<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>题目</title>

<link href="${pageContext.request.contextPath}/assets/css/screen.css"
	media="screen" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/assets/css/coderay.css"
	media="screen" rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/assets/css/codemirror.css"
	media="screen" rel="stylesheet" type="text/css" />


<script src="${pageContext.request.contextPath}/assets/js/codemirror.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/assets/js/clike.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/assets/js/clojure.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/assets/js/cm_mode.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/assets/js/diff.js"
	type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/assets/js/htmlmixed.js"
	type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/assets/js/scheme.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/assets/js/smalltalk.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/assets/js/text.js"
	type="text/javascript"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/include/header.jsp" flush="true" />
	<div class="container">
		<ul class="nav nav-tabs">
			<li class="active" role="presentation"><a
				href="${basePath}/problems/${problemId}">题目</a></li>
			<li role="presentation"><a
				href="${basePath}/submissions/querySubmission/p?problem_id=${problemId}">我的提交</a>
			</li>
			<li role="presentation"><a
				href="${basePath}/discuss/problem/comment?problem_id=${problemId}">讨论区</a></li>
			<li role="presentation"><a
				href="${basePath}/sort/problem/users/?problem_id=${problemId}">本题排名</a>
			</li>
		</ul>
		<div class="panel panel-default">
			<br />
			<div class="panel-heading">
				<h3 class="panel-title">${problem.problem_id}${problem.title}</h3>
			</div>
			<div class="panel-body">${problem.content}</div>
			<br />
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">
				<div class="panel-title">输入</div>
			</div>
			<div class="panel-body">${problem.input }</div>
		</div>
		<br />
		<div class="panel panel-default">
			<div class="panel-heading">
				<div class="panel-title">输出</div>
			</div>
			<div class="panel-body">${problem.output }</div>
		</div>
		<br />
		<div class="panel panel-default">
			<div class="panel-heading">
				<div class="panel-title">样列输入</div>
			</div>
			<div class="panel-body">
				<pre>${problem.input_sample }</pre>
			</div>
		</div>
		<br />
		<div class="panel panel-default">
			<div class="panel-heading">
				<div class="panel-title">样列输出</div>
			</div>
			<div class="panel-body">
				<pre>${problem.output_sample}</pre>
			</div>
		</div>
		<br />
		<div class="panel panel-default">
			<div class="panel-footer">作者</div>
			<div class="panel-body">${problem.author }</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">
				<div class="panel-title">提交</div>
			</div>

			<div class="panel-body">
				<a href="#" id="submission_link"
					onclick="show_submission(); return false;">提交代码</a>
				<div id="submission">
					<form onsubmit="submission_code(); return false;">
						<div style="margin: 0; padding: 0; display: inline">
							<input name="utf8" type="hidden" value="&#x2713;" /> <input
								id="problem_id" name="problem_id" type="hidden" value="${problem.problem_id }" />
						</div>
						<div>
							<label for="compiler_id">编译器</label><br /> <select
								id="compiler_id" name="compiler_id">
								<option value="3">C (gcc 4.7.2)</option>
								<option value="2">C++ (g++ 4.7.2)</option>
								<option value="10">Java (javac 1.6.0)</option>
							</select>
						</div>
						<div>
							<div class="rfloat">
								<input checked="checked" id="advanced_editor"
									name="advanced_editor" onchange="toggle_editor()"
									onclick="toggle_editor()" type="checkbox" value="1" /> 使用高级编辑器
							</div>
							<label for="code">代码</label><br />
							<textarea id="code" name="code"></textarea>
						</div>
						<div class="row">
							<input name="commit" type="submit" value="提交代码" /> <img
								style="width: 50px; height: 50px; display: none;"
								id="loading-gif" src="${basePath}/assets/loading.gif">
						</div>
						<div id="result"></div>
					</form>
				</div>

				<!-- 	<textarea class="Java" name="code" rows="15" cols="100"></textarea> -->
			</div>

		</div>
	</div>



	<script type="text/javascript">
		function showLoading() {
			var i = document.getElementById("loading-gif");
			if (i.style.display == "none") {
				i.style.display = "inline";
			} else {
				i.style.display = "none";
			}

		}
		function submission_code() {
			var problem_id = $('#problem_id').val();
			var code = $('#code').val();
			createSubmissionAction(problem_id, code);
			return false;
		}
		function createSubmissionAction(problem_id, code) {
			var postData = {
				'problem_id' : problem_id,
				'code' : code
			};
			showLoading();
			
					$.ajax({
						type : 'POST',
						url : '${basePath}/problems/createSubmission.action',
						data : postData,
						dataType : "JSON",
						success : function(result) {
							showLoading();
							var i = document.getElementById('result');

							if (result['msg'] == "完全正确") {
								i.innerHTML = "<div class='alert alert-success' role='alert'>"
										+ result['msg']
										+ "<a  style='margin : 10px' href='${basePath}/submissions/querySubmission/s?submission_id="
										+ result['submissionId']
										+ "'"
										+ ">查看详情</a></div>"
							} else if (result['msg'] == "运行超时"
									|| result['msg'] == "编译错误"
									|| result['msg'] == "内存溢出"
									|| result['msg'] == "系统错误") {

								i.innerHTML = "<div class='alert alert-info' role='alert'>"
										+ result['msg']
										+ "<a  style='margin : 10px' href='${basePath}/submissions/querySubmission/s?submission_id="
										+ result['submissionId']
										+ "'"
										+ ">查看详情</a></div>"
							} else {
								i.innerHTML = "<div class='alert alert-warning' role='alert'>"
										+ result['msg']
										+ "<a  style='margin : 10px' href='${basePath}/submissions/querySubmission/s?submission_id="
										+ result['submissionId']
										+ "'"
										+ ">查看详情</a></div>"
							}

						},
						error : function() {
							i.innerHTML = "<div class=alert alert-warning role='alert'>"
									+ "网络错误"
									+ "<a  style='margin : 10px' href='${basePath}/submissions/querySubmission/s?submission_id="
									+ result['submissionId']
									+ "'"
									+ ">查看详情</a></div>"
						}
					});

		}
		function show_submission() {
			$('#submission').show();
			$('#submission_link').hide();
		};

		var editor;
		function toggle_editor() {
			var cm = $('.CodeMirror'), c = $('#code');
			if ($('#advanced_editor').prop('checked')) {
				cm.show();
				editor.setValue(c.val());
				c.hide();
			} else {
				c.val(editor.getValue()).show();
				cm.hide();
			};
			return true;
		}
		function set_mode() {
			var compiler = $('#compiler_id option:selected').text();
			var modes = [ 'Javascript', 'Haskell', 'Lua', 'Pascal', 'Python',
					'Ruby', 'Scheme', 'Smalltalk', 'Clojure',
					[ 'PHP', 'text/x-php' ], [ 'C ', 'text/x-csrc' ],
					[ 'C++ ', 'text/x-c++src' ], [ 'Java ', 'text/x-java' ],
					[ 'C#', 'text/x-csharp' ], [ '', 'text/plain' ] ];
			for (var i = 0; i != modes.length; ++i) {
				var n = modes[i], m = modes[i];
				if ($.isArray(n)) {
					m = n[1];
					n = n[0];
				}
				if (compiler.indexOf(n) >= 0) {
					editor.setOption('mode', m.toLowerCase());
					break;
				}
			}
		};
		$(document).ready(function() {
			editor = CodeMirror.fromTextArea(document.getElementById("code"), {
				lineNumbers : true,
			});
			$('#code').blur(function() {
				editor.setValue($('#code').val());
			});
			$('#compiler_id').change(set_mode);
			set_mode();
			toggle_editor();
			$('#submission').hide();
		});

		//]]>
	</script>


</body>
</html>