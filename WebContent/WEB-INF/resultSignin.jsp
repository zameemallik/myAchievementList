<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.User"%>
<% User user = (User) session.getAttribute("user"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>やったことリスト</title>
</head>
<body>
<% if(user != null){ %>
<h1>登録完了！ご利用ありがとうございます。</h1>
<a href="/myAchievementList/">早速利用する</a>
<% }else{ %>
<h1>登録失敗</h1>
<p>登録処理中なんらかのエラーが発生したみたいです。お手数おかけしますが、最初からやり直してください。</p>
<a href="/myAchievementList/n/SignIn">もう一度試す</a>
<% } %>
</body>
</html>