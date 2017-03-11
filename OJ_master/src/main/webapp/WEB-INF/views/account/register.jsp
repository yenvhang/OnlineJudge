<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html lang="en">
	<head>
	    <meta charset="utf-8">
	    <title>用户注册</title>
  	</head>

  	<body>
  	<jsp:include page="/WEB-INF/views/include/header.jsp" flush="true" />
	
    <div class="container">

      <form action="${basePath}/accounts/register" onsubmit="onSubmit();return false;"  method="POST"  class="form-signin" style="max-width: 300px;margin: 0 auto;" role="form">
       <div class="form-group">
        <h2 class="form-signin-heading">注册</h2>
       	<label>邮箱</label>
        		<input id="email" name="email" class="form-control"  placeholder="请输入邮箱地址" required="" type="text" maxlength="64"/>
        </div>
        <div class="form-group">
        		<label>邮箱验证码</label>
        		<div class="form-inline">
        		<input id="code" name="code" class="form-control"  placeholder="请输入验证码" required="" type="text" maxlength="16" />
        		<input class="btn btn-primary" type="button"  value="发送"　></button>
        		</div>
        		
        </div>
        <div class="form-group">
        		<label>用户名</label>
        		<input id="username" name="username" class="form-control"  placeholder="请输入用户名" required="" type="text" maxlength="64" />
        </div>
     
        <div class="form-group">
        		<label>密码</label>
        		<input id="password" name="password" class="form-control" placeholder="请输入密码" required="" type="password" maxlength="16"/>
		  </div>
		  
		  <div class="form-group">
        		<label>重复密码</label>
        		<input id="password2" name="password2" class="form-control" placeholder="请确认密码" required="" type="password" maxlength="16"/>
		  </div>
		  <div class="form-group">
		 <label>手机号码</label>
        		<input id="phone" name="phone" class="form-control"  placeholder="请输入手机号码" required="" type="text" maxlength="16"/>
        		</div>
        		<div class="form-group">
        　<label>学校</label>
        		<input id="school" name="school" class="form-control"  placeholder=""  type="text" maxlength="64"/>
       </div>
        <div class="form-group">
        	<input type="submit" class="btn btn-lg btn-primary btn-block" value="注册"/>
        </div>
      </form>

    </div> <!-- /container -->
    
    <script type="text/javascript">
    	function onSubmit(){
    		var email = $('#email').val(),
    		 code    =  $('#code').val(),
    		 username=  $('#username').val(),
    		 password=  $('#password').val(),
    		 password2= $('#password2').val(),
    		 phone    = $('#phone').val(),
    		 school   = $('#school').val();
    		
    		doRegister(email,code,username,password,password2,phone,school);
    	}
    </script>
    
    <script type="text/javascript">
		function doRegister(email,code,username,password,password2,phone,school){
	
			var postData = {
				 'email' :email,
	    		 'username': username,
	    		 'password'  : password,
	    		 'password2' : password2,
	    		 'phone'     : phone,
	    		 'school'    : school
		};
		$.ajax({
			type: 'POST',
			url: '${basePath}/accounts/register',
			data: postData,
			dataType: 'JSON',
			success: function(result){
				if(result['isSuccessful']){
					window.location="${basePath}/accounts/login";
				}
				else{
				
					if(result['isUsernameExists']){
						$("[name='username']").attr('data-original-title',
							    
							    '用户已存在'
							    ).tooltip({
							      trigger: 'manual',
							      placement: 'right',
							    });
							    $("[name='username']").tooltip('show');
					}
					if(!result['isUsernameLegal']){
							$("[name='username']").attr('data-original-title',
							    '用户名含非法字符'
							    ).tooltip({
							      trigger: 'manual',
							      placement: 'right',
							    });
							    $("[name='username']").tooltip('show');
					}
					if(!result['isEmailLegal']){
						$("[name='email']").attr('data-original-title',
							    '邮箱含非法字符'
							    ).tooltip({
							      trigger: 'manual',
							      placement: 'right',
							    });
							    $("[name='email']").tooltip('show');
					}
					if(result['isEmailExists']){
						$("[name='email']").attr('data-original-title',
							    '邮箱已存在'
							    ).tooltip({
							      trigger: 'manual',
							      placement: 'right',
							    });
							    $("[name='email']").tooltip('show');
					}
					if(!result['isPasswordLegal']){
						$("[name='password']").attr('data-original-title',
							    '密码含非法字符'
							    ).tooltip({
							      trigger: 'manual',
							      placement: 'right',
							    });
							    $("[name='password']").tooltip('show');
					}
						if(!result['isPasswordEqual']){
						$("[name='password']").attr('data-original-title',
							    '两次输入密码不相同'
							    ).tooltip({
							      trigger: 'manual',
							      placement: 'right',
							    });
							    $("[name='password']").tooltip('show');
					}
						return false;
				}
			}}
		);
	}
    
    </script>
</body></html>