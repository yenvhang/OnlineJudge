<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>排名</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/include/header.jsp" flush="true" />

<div id="main">
    <div class="container">
        <div class="post detail">

            <div class="votebar">
                #if($like>0)
                <button class="click-like up pressed" data-id="$!{news.id}" title="èµå"><i class="vote-arrow"></i><span class="count">$!{news.likeCount}</span></button>
                #else
                <button class="click-like up" data-id="$!{news.id}" title="èµå"><i class="vote-arrow"></i><span class="count">$!{news.likeCount}</span></button>
                #end
                #if($like<0)
                <button class="click-dislike down pressed" data-id="$!{news.id}" title="åå¯¹"><i class="vote-arrow"></i></button>
                #else
                <button class="click-dislike down" data-id="$!{news.id}" title="åå¯¹"><i class="vote-arrow"></i></button>
                #end
            </div>

            <div class="content">
                <div class="content-img">
                    <img src="$!{news.image}" alt="">
                </div>
                <div class="content-main">
                    <h3 class="title">
                        <a target="_blank" rel="external nofollow" href="$!{news.link}">$!{news.title}</a>
                    </h3>
                    <div class="meta">
                        $!{news.link}
                              <span>
                                  <i class="fa icon-comment"></i> $!{news.commentCount}
                              </span>
                    </div>
                </div>
            </div>
            <div class="user-info">
                <div class="user-avatar">
                    <a href="/user/$!{owner.id}"><img width="32" class="img-circle" src="$!{owner.headUrl}"></a>
                </div>
                <!--
                <div class="info">
                    <h5>åäº«è</h5>

                    <a href="http://nowcoder.com/u/125701"><img width="48" class="img-circle" src="http://images.nowcoder.com/images/20141231/622873_1420036789276_622873_1420036771761_%E8%98%91%E8%8F%87.jpg@0e_200w_200h_0c_1i_1o_90Q_1x" alt="Thumb"></a>

                    <h4 class="m-b-xs">å½±æµ</h4>
                    <a class="btn btn-default btn-xs" href="http://nowcoder.com/signin"><i class="fa icon-eye"></i> å³æ³¨TA</a>
                </div>
                -->
            </div>

            <div class="subject-name">æ¥èª <a href="/user/$!{owner.id}">$!{owner.name}</a></div>
        </div>
        <!--
        <div class="social-share-button text-center" data-title="è¯»ãWeb å¨æ å·¥ç¨å¸çèªæä¿®å»ã http://nowcoder.com/posts/wt2rwy åäº«èª @å½±æµ åå»ºçãwebå¼åçæäººä¹æã http://nowcoder.com/subjects/2245( æ³çæ´å¤ï¼ä¸è½½ @çå®¢ç½ appï¼http://nowcoder.com/download )" data-type="text" data-url="null" data-img="http://nowcoder.com/screenshots/8/469448.png">
            <h5>åäº«å°</h5>
            <a href="javascript:;" class="btn btn-danger" rel="external nofollow" data-site="weibo" onclick="return SocialShareButton.share(this);"><i class="fa icon-weibo"></i> å¾®å</a>
            <span class="dropdown weixin-qrcode-dropdown">
                <button class="btn btn-success" id="weixin-qrcode" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fa icon-weixin"></i> å¾®ä¿¡</button>
                <span class="dropdown-menu weixin-qrcode-dropdown-menu" aria-labelledby="weixin-qrcode" data-url="http://nowcoder.com/posts/wt2rwy">
                    <span class="weixin-qrcode" aria-labelledby="weixin-qrcode" data-url="http://nowcoder.com/posts/wt2rwy"><canvas width="132" height="132"></canvas></span>æ«æäºç»´ç <br>ä¸è½½çå®¢ç½
                </span>
            </span>
        </div>
        <div role="alert" class="alert alert-warning subscribe-banner">
            ä½¿ç¨ãå¤´æ¡å«å¦ãå®¢æ·ç«¯ï¼æ¥ææ´å¥½çéè¯»ä½éªã
            <a href="http://nowcoder.com/download?ref=web_posts" target="_blank" title="çå®¢ç½å®¢æ·ç«¯" class="btn btn-info btn-sm pull-right">ç«å³ä½éª</a>
        </div>
        -->

        <div class="post-comment-form">
            #if($user)
            <span>è¯è®º ($!{news.commentCount})</span>
            <form method="post" action="/addComment">
                <input name="newsId" type="hidden" value="$!{news.id}">

                <div class="form-group text required comment_content">
                    <label class="text required sr-only">
                        <abbr title="required">*</abbr> è¯è®º</label>
                    <textarea rows="5" class="text required comment-content form-control" name="content" id="content"></textarea>
                </div>
                <div class="text-right">
                    <input type="submit" name="commit" value="æ äº¤" class="btn btn-default btn-info">
                </div>
            </form>
            #else
            <div class="login-actions">
                <a class="btn btn-success" href="/?pop=1">ç»å½åè¯è®º</a>
            </div>
            #end
        </div>

        <div id="comments" class="comments">
            #foreach($commentvo in $comments)
            <div class="media">
                <a class="media-left" href="/user/$!{commentvo.user.id}">
                    <img src="$!{commentvo.user.headUrl}">
                </a>
                <div class="media-body">
                    <h4 class="media-heading">
                        <small class="date">$date.format('yyyy-MM-dd HH:mm:ss', $!{commentvo.comment.createdDate})
                        </small>
                    </h4>
                    <div>$!{commentvo.comment.content}</div>
                </div>
            </div>
            #end
        </div>

    </div>
    <script type="text/javascript">
          $(function(){

            // If really is weixin
            $(document).on('WeixinJSBridgeReady', function() {

              $('.weixin-qrcode-dropdown').show();

              var options = {
                "img_url": "",
                "link": "http://nowcoder.com/j/wt2rwy",
                "desc": "",
                "title": "è¯»ãWeb å¨æ å·¥ç¨å¸çèªæä¿®å»ã"
              };

              WeixinJSBridge.on('menu:share:appmessage', function (argv){
                WeixinJSBridge.invoke('sendAppMessage', options, function (res) {
                  // _report('send_msg', res.err_msg)
                });
              });

              WeixinJSBridge.on('menu:share:timeline', function (argv) {
                WeixinJSBridge.invoke('shareTimeline', options, function (res) {
                  // _report('send_msg', res.err_msg)
                });
              });

              // $(window).on('touchmove scroll', function() {
              //   if ((window.innerHeight + window.scrollY) >= document.body.offsetHeight) {
              //     $('div.backdrop').show();
              //     $('div.share-help').show();
              //   } else {
              //     $('div.backdrop').hide();
              //     $('div.share-help').hide();
              //   }
              // });

            });

          })

    </script>
    <script type="text/javascript" src="/scripts/main/site/detail.js"></script>
</div>

</body>
