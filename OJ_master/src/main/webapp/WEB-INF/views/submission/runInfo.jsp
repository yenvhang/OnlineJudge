<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>运行结果</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/include/header.jsp" flush="true" />
<body>
	<div class="container">
		

		<div class="row">
		
			<pre id="code">
			
			</pre>
			<h3 id="runInfo"></h3>

		</div>
	</div>


	<script type="text/javascript">
		$(function(){
			load();
		})
		
	function load() {
			var i =${submissionId};
			getCode(i);
			getRunInfo(i);
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