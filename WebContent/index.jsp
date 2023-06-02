<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="model.User" %>
    <% User user= (User)session.getAttribute("user");
    if(user != null){
    	response.sendRedirect("/myAchievementList/User/Main");
    }
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>やったことリスト</title>
</head>
<body>
<h1>やったことリスト</h1>
<p>毎日やったことを記録することによって勉強の復習と意欲向上をめざしませんか?</p>
<a href="n/Login">ログイン</a><br>
<a href="n/SignIn">新規登録</a>
</body>
</html>