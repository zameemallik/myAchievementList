<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="model.User" %>
    <% User user= (User)session.getAttribute("user");
    if(user != null){
    	response.sendRedirect("/myAchievementList/User/Main");
    }
    %>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>やったことリスト</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css">
<link rel="stylesheet" href="./css/index.css">
<link rel ="stylesheet" href="./css/header.css">
<link rel="stylesheet"  href="./css/calendar.css">
</head>
<body>
<header>
<div class="wrapper">
<p>やったことリスト</p>
<ul>
<li><a href="/myAchievementList/">Top</a></li>
<li><a href="/myAchievementList/n/Login">Login</a></li>
<li><a href="/myAchievementList/n/Signin">Signin</a></li>
</ul>
</div>
</header>
<main>
<section>
<p>毎日やったことを記録することによって勉強の復習と意欲向上をめざしませんか?</p>
<a href="n/Login">ログイン</a><br>
<a href="n/Signin">新規登録</a>
<section>
</main>
</body>
</html>