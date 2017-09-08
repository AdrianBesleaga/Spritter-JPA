<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<jsp:include page='header.jsp'>
    <jsp:param name="title" value="Home"/>
</jsp:include>

	<%
		if (session.getAttribute("userName") != null) {
	%>


	<div id="messageForm" class="form">
		<p>Post Message</p>

		<input type="text" id="messageText" class="input"
			placeholder="Something cool" />

		<p id="messageFormMessage"></p>

		<button id="messageButton" class="btn">Post</button>
	</div>

<div class="messages">
	<c:forEach items="${messageList}" var="map">
		<p>
			<c:forEach items="${map.value}" var="message">

				<textarea rows="4" cols="50" readonly> ${message.text} </textarea>
				<p>
					Posted by : <a class="upper" href="user/${message.user}">${message.user}</a>
				</p>
				<p>${message.date}</p>
				<br>

			</c:forEach>
		</p>
	</c:forEach>
	</div>
	<%
		} else {
	%>
	<h1>Welcome to SpriTTer (Spring Twitter)</h1>
	<h2>Please login</h2>
	
	<iframe width="853" height="505" src="https://www.youtube.com/embed/SBDYYGER5iM" frameborder="0" allowfullscreen></iframe>
	
	<%}%>

</body>

</html>