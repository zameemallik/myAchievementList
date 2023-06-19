<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./header.jsp" %>
<main>
<form id="addTweet" action="/myAchievementList/User/Main" method="post" onsubmit="return checkNull()">
<h1 id="test">今日の成果を書き込もう！</h1>
<label for="catagories">
<div id="optionCatagory"><p>カテゴリー：
<select id="catagories" name="catagories" onchange="addOption()">
<option value="" selected disabled> </option>
<c:forEach var="catagory" items="${catagoriesList}">
<option value="<c:out value="${catagory}" />"><c:out value="${catagory }" /></option>
</c:forEach>
<option value="追加">追加</option>
</select>
</p>
</div>
</label>
<label for="detail">
<p>詳細 ： <textarea name="detail" id="detail" cols="40" rows="4"></textarea></p>
</label>
<input id="addTweetbtn" type="submit" value="投 稿">
</form>
<section id="did">
<c:forEach var="catagory" items="${catagoriesList}">
<div class="did_list">
<h2><c:out value="${catagory}" /></h2>
<c:forEach var="tweet" items="${tweetList}" >
<c:if test="${catagory == tweet.catagories}" >
<p><c:out value="${tweet.detail }" /><span>${tweet.month }/${tweet.date }</span></p>
</c:if>
</c:forEach>
</div>
</c:forEach>
</section>
<script>
function checkNull(){
	let title = document.getElementById('catagories').value;
	let detail = document.getElementById('detail').value;
	if(!title || !detail){
		if(document.getElementById("msg") == null){
		document.getElementById("test").insertAdjacentHTML('afterend', '<p style="color:red;" id="msg">カテゴリーと詳細をうめてください。</p>');
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

var before;
function addOption(){
	let s_option = document.getElementById('catagories').value;
	if(s_option == "追加"){
		before = document.getElementById('optionCatagory').innerHTML;
		document.getElementById('optionCatagory').innerHTML =
			'<p style="">新規カテゴリー：<input type="text" id ="catagories" name="catagories"><button onclick="dontAddCatagory()" type="button">やめる</button></p>';
	}
}

function dontAddCatagory(){
	document.getElementById('optionCatagory').innerHTML = before;
}
</script>
</main>
</body>
</html>