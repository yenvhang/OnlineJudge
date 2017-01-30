<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html lang="en">
	<head>
	    <meta charset="utf-8">
	    <title>用户登录</title>
  	</head>

  	<body>
  	<jsp:include page="/WEB-INF/views/include/header.jsp" flush="true" />
	
    <div class="container">

      <form action="${pageContext.request.contextPath}/accounts/login.action "onsubmit="onsubmit1();" method="POST" class="form-signin" style="max-width: 300px;margin: 0 auto;" role="form">
       <div class="form-group">
        <h2 class="form-signin-heading">请登录</h2>
      
        		<label>用户</label>
        		<input name="username" class="form-control"  placeholder="请输入邮箱地址或用户名" required="" type="text" />
        </div>
     
        <div class="form-group">
        		<label>密码</label>
        		<input name="password" class="form-control" placeholder="请输入密码" required="" type="password"/>
		  </div>
		
        <div class="checkbox">
          <label>
            <input id="rememberMe" name="rememberMe"  data-toggle="checkbox" type="checkbox" value="false"/ > 记住我
          </label>
          
          <a style="margin-left: 10px;"href="">忘记密码</a>
        </div>
        <input  type="submit"  class="btn btn-lg btn-primary btn-block" value="登录"/>
      </form>

    </div> <!-- /container -->
    
    
      <c:if test="${result['callback']}">
		    <c:if test="${!result['getIsUserExists']}">
				<script >
					$(function () {  $("[name='username']").attr('data-original-title',
							    
							    '用户名或邮箱不存在'
							    ).tooltip({
							      trigger: 'manual',
							      placement: 'right',
							    });
							    $("[name='username']").tooltip('show');
							    
							  });
					</script>
			</c:if>
			<c:if test="${result['getIsUserExists']}">
				<c:if test="${empty username }">
					<script >
					$(function () {  $("[name='password']").attr('data-original-title',
							    
							    '密码错误'
							    ).tooltip({
							      trigger: 'manual',
							      placement: 'right',
							    });
							    $("[name='password']").tooltip('show');
							    
							  });
					</script>
				</c:if>
			</c:if>
			</c:if>
		
<!--   <script type="text/javascript">
  	function onsubmit1() {
		var username=$('#username').val(),
			password=$('#password').val(),
			rememberMe=$('#rememberMe').is(':checked');
			alert(rememberMe);
			alert(	);
			
	}
  
  	
  
  </script>
   -->


<!--   <script type="text/javascript">
  		function doLoginAction(username,password,rememberMe) {
			var postData ={
				'username':username,
				'password':password,
				'rememberMe':rememberMe
			
			};
		
			
			$.ajax({
				type: 'POST',
				url:"${pageContext.request.contextPath}/accounts/login.action",
				data:postData,
				dataType: 'JSON',
				async:false,
				success:function(result){
				return processResult(result);
				}
			});
		}
  </script>
   <script type="text/javascript">
   		function processResult(result) {
   		
			if(result['isSuccessful']){
				alert('登录成功');
			}
			
			else{
				alert('登录失败');
			}
		}
   
   </script> -->

</body></html>