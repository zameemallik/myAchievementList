<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./header.jsp" %>
<main>
<h1>ログイン画面</h1><br>
<c:if test = "${not empty errorMsg}">
<p style="color:red;">${errorMsg}</p>
</c:if>
<form action="/myAchievementList/n/Login" method="post">
<p> <label for="address">メールアドレスを入力してください：<input type="text" name="address" id="address"></label><br><br>
<label for="pass">パスワードを入力してください：<input type="password" name="pass" id="pass"></label></p><br>
<input class="submitbtn" type="submit" value="確定">
</form>
</main>
</body>
</html>