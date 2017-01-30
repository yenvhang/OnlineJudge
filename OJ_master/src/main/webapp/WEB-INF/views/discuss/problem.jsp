<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>排名</title>
 <script type="text/javascript" src="http://cdn.staticfile.org/jquery/1.11.1/jquery.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/include/header.jsp" flush="true" />
<body>

	<div class="container">
	
		<ul class="nav nav-tabs">
			<li role="presentation"><a
				href="${basePath}/problems/${problemId}">题目</a></li>
			<li role="presentation"><a
				href="${basePath}/submissions/querySubmission/p?problem_id=${problemId}">我的提交</a>
			</li>
			<li class="active" role="presentation"><a
				href="${basePath}/discuss/problem/comment?problem_id=${problemId}">讨论区</a></li>
			<li role="presentation"　><a
				href="${basePath}/sort/problem/users/?problem_id=${problemId}">本题排名</a>
			</li>
		</ul>
		<div class="panel panel-default">
			<ul class="answer-list">
				<li class="answer-list-item clearfix" data-id="103188">
					<!-- <div class="votebar">
						<button class="js-click-like up " title="赞同">
							<i class="vote-arrow"></i><span class="count">3</span>
						</button>
						<button class="js-click-dislike down " title="反对">
							<i class="vote-arrow"></i>
						</button>
					</div> -->
					<div class="answer-content clearfix">
						<ul class="media-list">
							<c:forEach var="comment"  items="${comments}">
								<li class="media">
									<a class="media-left media-top" href="${basePath}/accounts/userDetail?userId=${comment.from.user_id}"><img
										src="https://images.nowcoder.com/head/656m.png@0e_50w_50h_0c_1i_1o_90Q_1x.png" 
										alt="...">
									</a>
									<div class="media-body">
										<h4 class="media-heading">${comment.from.username}</h4>
											${comment.content}
										<c:if test="${!empty comment.replys}">
											<br>
											<div class="well">
											<c:forEach var="reply"  items="${comment.replys}">
													<div class="media">
														<a class="media-left media-top" href="${basePath}/accounts/userDetail?userId=${reply.from.user_id}"> <img
															src="https://images.nowcoder.com/head/656m.png@0e_50w_50h_0c_1i_1o_90Q_1x.png"
															alt="...">
														</a>
														<div class="media-body">
														<c:if test="${!empty reply.to}">	
															<h4 class="media-heading">${reply.from.username} 回复: ${reply.to.username}</h4>
														</c:if>
														<c:if test="${empty reply.to}">	
															<h4 class="media-heading">${reply.from.username}</h4>
														</c:if>
															${reply.content}
														</div>
														<span class="answer-time">发表于  <fmt:formatDate value="${reply.createTime }" type="both" pattern="YYYY-MM-dd HH:mm:ss" /></span>
															<input type="hidden" value="${reply.from.user_id}" id="to_id">
															<input type="hidden" value="${comment.comment_id}" id="comment_id">
															<a class="cmt-reply" href="javascript:void(0);" >回复</a>
													</div>
												<br>
											</c:forEach>
											</div>
										</c:if>
									</div>
									<span class="answer-time">发表于  <fmt:formatDate value="${comment.createTime }"
								type="both" pattern="YYYY-MM-dd HH:mm:ss" /></span>
								<input type="hidden" value="${comment.from.user_id}" id="to_id">
								<input type="hidden" value="${comment.comment_id }">
								<a class="cmt-reply" href="javascript:void(0);" >回复</a>
								</li>
								<br>
								</c:forEach>
						
						</ul>
						<div class="answer-content">
							<div class="form-group">
								<form onsubmit="makeComment();return false;" method="POST">
									<input id="problem_id" type="hidden" value="${problemId}" name="problem_id"/> 
									<textarea id="content" class="form-control" rows="3" name="content"></textarea>
									<input type="submit" class="btn btn-info" value="评论"/>
								</form>
							</div>
							<br>
						</div>
						
						
					<script type="text/javascript">
					/* $('.p-cmt-reply').click(function (){
						if($(this).hasClass("active")){
				 			$('.js-answer-content').remove(); 
				 			$(this).removeClass("active");
				 		 }
				 		 else{
				 			 $(this).addClass("active");
				 			var to_id =$(this).prev().val();
				 			 $(this).after("<div class='js-answer-content'><div class='form-group'><form onsubmit='makeComment();return false;' method='POST'><input id='problem_id' type='hidden' value='${problemId}' name='problem_id'/><input id='to_id' type='hidden' value="+to_id+ " name='to_id'/> <textarea id='content' class='form-control' rows='3' name='content'></textarea><input type='submit' class='btn btn-info' value='评论'/></form></div></div>"); 
									
						}
					})
					 */
					
				
				  	  $(".cmt-reply").click(function(){
				 		 if($(this).hasClass("active")){
				 			$('.js-answer-content').remove(); 
				 			$(this).removeClass("active");
				 		 }
				 		 else{
				 			 $(this).addClass("active");
				 			var to_id =$(this).prev().val();
				 			var comment_id =$(this).prev().prev().val();
							 $(this).after("<div class='js-answer-content'><div class='form-group'><form onsubmit='makeChildComment("+to_id+","+comment_id+
									");return false;' method='POST'><input id='problem_id' type='hidden' value='${problemId}' name='problem_id'/><input id='to_id' type='hidden' value="+to_id+ " name='to_id'/> <textarea id='content' class='form-control' rows='3' name='content'></textarea><input type='submit' class='btn btn-info' value='评论'/></form></div></div>"); 
						}
				 	}); 
					function makeChildComment(comment_id,to_id){
					
						var postData={
								'comment_id':comment_id,
								'content':$('#content').val(),
								'to_id':to_id
						}
					
						$.ajax({
							type :'POST',
							url:'${basePath}/discuss/problem/addChildComment',
							data:postData,
							dataType:'JSON',
							success:function(result){
								if(result['IsOk']){
									window.location.reload();
								}
							}
						})
					}
					
					
					function makeComment(){
							var postData={
									'problem_id':$('#problem_id').val(),
									'content':$('#content').val(),
									'to_id':$('to_id').val()
							}
						
							$.ajax({
								type :'POST',
								url:'${basePath}/discuss/problem/addComment',
								data:postData,
								dataType:'JSON',
								success:function(result){
									if(result['IsOk']){
										window.location.reload();
									}
								}
							})
						}
					
					</script>
					</div>
				</li>
			</ul>
		</div>
</body>
</html>