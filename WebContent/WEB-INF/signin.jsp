<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String errorMsg = (String) request.getAttribute("errorMsg"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>やったことリスト</title>
</head>
<body>
<h1>新規登録</h1>
<% if(errorMsg != null){ %>
<p style="color:red;"><%= errorMsg %></p>
<% } %>
<form action="/myAchievementList/n/SignIn" method="post" onsubmit="return checkNull()" id="form">
<p><label for="address">メールアドレス：<input type="text" name="address" id="address"></label></p>
<p><label for="name">名前：<input type="text" name="name" id="name"></label></p>
<p><label for="password">パスワード：<input type="password" name="password" id="password"> <span id="forCheckPass" style="color:red;"></span></label></p>
<p><label for="passwordCheck">パスワード（確認用）：<input type="password" id="passwordCheck"></label></p>
<input type="submit" value="登録">
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
</body>
</html>