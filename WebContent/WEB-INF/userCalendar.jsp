<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./header.jsp" %>
<main>
<div class="calendar">
<h1>${userCalendar.year}年${userCalendar.month }月</h1>
<p>
<a href="?year=${userCalendar.year}&month=${userCalendar.month-1}">前月</a>
<a href="?year=${userCalendar.year}&month=${userCalendar.month+1}">翌月</a>
</p>
<table>
<tr>
<th>日</th>
<th>月</th>
<th>火</th>
<th>水</th>
<th>木</th>
<th>金</th>
<th>土</th>
</tr>
<c:forEach var="row" items="${userCalendar.data }" >
	<tr>
      <c:forEach var="col" items="${row }" >
      	<c:choose>
      		<c:when test="${fn:startsWith(col,'*')}" >
      			<td class="today">${fn:substringAfter(col, "*")}
      		</c:when>
      		<c:otherwise>
      			<td>${col }
      		</c:otherwise>
      	</c:choose>
      	<% String tw = "tweetday" + pageContext.getAttribute("col");
      	Set<String> twList= (Set<String>)request.getAttribute(tw);
      	request.setAttribute("twList", twList); %>
      	<c:forEach var="tweetcatagory" items="${twList}" >
      		<p><c:out value="${tweetcatagory}" /></p>
      	</c:forEach>
      	</td>
      </c:forEach>
	</tr>
</c:forEach>
</table>
</div>
</main>
</body>
</html>