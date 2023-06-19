<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./header.jsp" %>
<main>
<h1>新規登録</h1>
<c:if test="${not empty errorMsg}">
<p style="color:red;">${ errorMsg }</p>
</c:if>
<form action="/myAchievementList/n/Signin" method="post" onsubmit="return checkNull()" id="form">
<p><label for="address">メールアドレス：<input type="email" name="address" id="address"></label></p><br>
<p><label for="name">名前：<input type="text" name="name" id="name"></label></p><br>
<p id="forCheckPass" style="color:red;"></p>
<p><label for="password">パスワード：<input type="password" name="password" id="password"></label></p><br>
<p><label for="passwordCheck">パスワード（確認用）：<input type="password" id="passwordCheck"></label></p><br>
<input class="submitbtn" type="submit" value="登 録">
</form>
<script>
document.getElementById("passwordCheck").addEventListener("change", checkPassword)

function checkPassword(){
	let password = document.getElementById('password').value;
	let passwordCheck = document.getElementById('passwordCheck').value;
	if(password != passwordCheck){
		document.getElementById('forCheckPass').textContent = 'パスワードが一致しておりません。';
	}else{
		document.getElementById('forCheckPass').textContent = '';
	}

}

function checkNull(){
	let title = document.getElementById('address').value;
	let name = document.getElementById('name').value;
	let password = document.getElementById('password').value;
	let passwordCheck = document.getElementById('passwordCheck').value;
	if(!title || !name || !password || passwordCheck != password){
		if(document.getElementById('msg') == null){
		document.getElementById('form').insertAdjacentHTML('afterend', '<p style="color:red;" id="msg">すべての項目を正しくうめてください。</p>');
		}
		return false;
	}
	if(document.getElementById('msg') != null){
		document.getElementById('msg').remove();
	}
	return true;
}
</script>
</main>
</body>
</html>