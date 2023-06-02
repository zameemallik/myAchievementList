<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>やったことリスト</title>
</head>
<body>
<h1>ログイン画面</h1>
<c:if test = "${not empty errorMsg}">
<p style="color:red;">${errorMsg}</p>
</c:if>
<form action="/myAchievementList/n/Login" method="post">
<p>メールアドレスを入力してください:<label for="address"><input type="text" name="address" id="address"></label><br>
<label for="pass">パスワードを入力してください:<input type="password" name="pass" id="pass"></label></p>
<input type="submit" value="確定">
</form>
</body>
</html>