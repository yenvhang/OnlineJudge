<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>私信发送</title>
<jsp:include page="/WEB-INF/views/include/base.jsp" flush="true" />
</head>
<body>
	<div class="container">
		<h1>发私信给${toUser.username}</h1>
		<form method="post" action="${basePath}/discuss/personal/sendMessage.action" >
			<input type="hidden" name="toId" value="${toUser.user_id}">
			<textarea class="form-control" rows="5"　placeholder="可发送200字以内内容" name="content"></textarea>
			<input type="submit" value="发送" /> 
		</form>
	
	</div>
</body>
</html>