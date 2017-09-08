<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<jsp:include page='header.jsp'>
    <jsp:param name="title" value="Profile"/>
</jsp:include>

	<!-- End of Header -->

	<c:if test="${userNameText != null}">

		<h1>${userNameText}</h1>
		<p>Id : ${userId}</p>
		<p>Password : ${userPassword}</p>

		<c:if test="${followButton != null && userNameText != sessionScope.userName}">
			<button id="followButton" value="${userNameText}" class="btn">${followButton}</button>
			<br>
		</c:if>
		<h3>Follows</h3>

		<c:forEach items="${userFriends}" var="friend">

			<a class="friend upper" href="user/${friend}">${friend}</a>

		</c:forEach>
		<br>

		<h3>Messages</h3>

		<c:forEach items="${userMessages}" var="message" varStatus="loop">

			<textarea rows="4" cols="50" readonly> ${message.text} </textarea>
			<p>
				Posted by : <a class="upper" href="user/${message.user}">${message.user}</a>
			</p>
			<p>${message.date}</p>
			
			<c:if test="${userNameText == sessionScope.userName}">
			<button value='{"text": "${message.text}" , "user": "${message.user}" , "date": "${message.date}"}' onclick="deleteMessage(this.value)" class="btn red" >Delete Message</button>
			<br>
			</c:if>

		</c:forEach>

	</c:if>

	<c:if test="${userNameText == null}">
		<h1>This user does not exist !</h1>
	</c:if>

	<%if (session.getAttribute("userName") != null) {
	} else {}
	%>

</body>

</html>