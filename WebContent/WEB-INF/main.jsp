<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Tweet, java.util.List" %>
<% List<Tweet> tweetList = (List<Tweet>)session.getAttribute("tweetList"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>やったことリスト</title>
</head>
<body>
<h1>やったことリスト</h1>
<form action="/myAchievementList/User/Logout" method="get" onsubmit="return getConfirm()">
<button>ログアウト</button>
</form>
<form action="/myAchievementList/User/Main" method="post" onsubmit="return checkNull()">
<h3 id="test">今日の成果を書き込もう！</h3>
<p>タイトル：<input type="text" name="title" id="title"></p>
<p>詳細：<input type="text" name="detail" id="detail"></p>
<input type="submit" value="投稿">
</form>
<% for(Tweet tweet: tweetList){ %>
<h2><%= tweet.getTitle() %></h2>
<p><%= tweet.getDetail() %></p>
<p>投稿日:<%= tweet.getDate() %></p>
<% } %>
<script>
function checkNull(){
	let title = document.getElementById("title").value;
	let detail = document.getElementById("detail").value;
	if(!title || !detail){
		if(document.getElementById("msg") == null){
		document.getElementById("test").insertAdjacentHTML('afterend', '<p style="color:red;" id="msg">タイトルと詳細をうめてください。</p>');
		}
		return false;
	}
	if(document.getElementById("msg") != null){
		document.getElementById("msg").remove();
	}
	return true;
}

function getConfirm(){
	if(confirm('本当にログアウトしますか？')){
		return true;
	}else{
		return false;
	}
}
</script>
</body>
</html>