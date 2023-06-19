<%@ page language="java"
    pageEncoding="UTF-8" import="model.UserCalendar, model.Tweet, java.util.*"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AchievementList</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css">
<link rel="stylesheet" href="../css/index.css">
<link rel ="stylesheet" href="../css/header.css">
<link rel ="stylesheet" href="../css/main.css">
<link rel="stylesheet"  href="../css/calendar.css">
</head>
<body>
<header>
<div class="wrapper">
<p style="font-family:cursive;">AchievementList</p>
<ul>
<c:choose>
<c:when test="${not empty user }">
<li><a href="/myAchievementList/User/Main">Main</a></li>
<li><a href="/myAchievementList/User/UserCalendar">Calendar</a></li>
<li><form action="/myAchievementList/User/Logout" method="get" onsubmit="return getConfirm()">
<button id="logout_btn">Logout</button>
</form></li>
</c:when>
<c:otherwise>
<li><a href="/myAchievementList/">Top</a></li>
<li><a href="/myAchievementList/n/Login">Login</a></li>
<li><a href="/myAchievementList/n/Signin">Signin</a></li>
</c:otherwise>
</c:choose>
</ul>
</div>
</header>