<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/include/header.jsp" flush="true" />
	<div class="container">
		<div class="row">
		  <div class="col-md-4">
		    <div class="thumbnail">
		      <img data-src="few.js/300*300" alt="..."/>
		      <div class="caption"> 
		      	<div class="container">
			        <h5>学校：青岛大学</h5>
			        <h5>年龄:  22</h5>
			        <h5>博客:  www.baidu.com</h5>
			        <p><a href="${basePath}/discuss/personal/sendMessage?toId=1" class="btn btn-default" role="button">私信</a>
			        	<a href="#" class="btn btn-default" role="button">GitHub</a>
			        </p>
		        </div>
		      </div>
		    </div>
		  
		  </div>
		  <div class="col-md-8">
		  	<%--  <jsp:include page="/WEB-INF/views/include/personalMessage.jsp" />--%>
		  	<ul class="nav nav-tabs"　role="tablist">
				<li class="active" role="presentation"><a href="${basePath}/accounts/userDetail?userId=${tempuser.user_id}">查看信息</a>
				</li>
			</ul>
			<div class="container">
				<h4 id="solved">通过的题目</h4>
				<h4 id="beingsolved">正在攻克的题目</h4>
				
			</div>
		  
		  </div>
		</div>
	
	</div>
	<script type="text/javascript">
		$(function(){
			load();
		})
		
		function load(){
			var id =${tempuser.user_id}
			 getSolvedIds(id);
			 getBeingSolvedIds(id);
		}
		function getSolvedIds(id){
			var postData={
					'userId' :id
			}
			$.ajax({
				type:'POST',
				url: '${basePath}/problems/solved',
				data: postData,
				dataType: 'JSON',
				success:function(result){
					var js="";
					for(var i=0;i<result['ids'].length;i++){
						js=js+"&nbsp&nbsp<a href='${basePath}/problems/"+result['ids'][i]+"'>"+result['ids'][i]+"</a>";
					}
					$('#solved').after(js);
				}
				
			})
				
			
		}
		
		function getBeingSolvedIds(id){
			var postData={
					'userId' :id
			}
			$.ajax({
				type:'POST',
				url: '${basePath}/problems/beingsolved',
				data: postData,
				dataType: 'JSON',
				success:function(result){
					var js="";
					for(var i=0;i<result['ids'].length;i++){
						js=js+"&nbsp&nbsp<a href='${basePath}/problems/"+result['ids'][i]+"'>"+result['ids'][i]+"</a>";
					}
					$('#beingsolved').after(js);
				}
		})
		}
	</script>
</body>
</html>