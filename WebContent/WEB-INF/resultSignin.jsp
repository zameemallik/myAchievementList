<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./header.jsp" %>
<main>
<c:choose>
<c:when test="${not empty user}">
<h1>登録完了！<c:out value="${user.name }" />さん、ようこそ！<br />一緒に日々成長していきましょう！</h1>
<a href="/myAchievementList/">早速利用する</a>
</c:when>
<c:otherwise>
<h1>登録失敗</h1>
<p>登録処理中なんらかのエラーが発生したみたいです。お手数おかけしますが、最初からやり直してください。</p>
<a href="/myAchievementList/n/SignIn">もう一度試す</a>
</c:otherwise>
</c:choose>
</main>
</body>
</html>