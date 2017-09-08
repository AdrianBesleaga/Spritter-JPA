<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css"
	href="/twitter/resources/static/style.css">
<title>Profile Page</title>
<base href="http://localhost:8080/twitter/">
</head>
<body>

	<ul class="headerMenu">
		<li><a class="btn active" href="/twitter/">Home</a></li>
		<%
			if (session.getAttribute("userName") == null) {
		%>
		<li><button id="showLogin" class="btn">Login</button></li>
		<li><button id="showRegister" class="btn">Register</button></li>
		<%
			}
		%>
		<%
			if (session.getAttribute("userName") != null) {
		%>
		<li><a id="myProfile" class="btn"
			href="/twitter/user/<%=session.getAttribute("userName")%>">Profile</a></li>
		<li><a id="usersLink" class="btn" href="/twitter/users">Users</a></li>
		<li><a id="logout" class="btn" href="/twitter/logout">Logout</a></li>
		<%
			}
		%>
	</ul>

	<div id="registerForm" class="form">
		<p>Register</p>

		<input type="text" id="userName" class="input" placeholder="name" />
		<input type="password" id="userPassword" class="input"
			placeholder="password" />
		<p id="registerFormMessage"></p>

		<button id="registerButton" class="btn">Register</button>
	</div>


	<div id="loginForm" class="form">
		<p>Login</p>

		<input type="text" id="loginUserName" class="input" placeholder="name" />
		<input type="password" id="loginUserPassword" class="input"
			placeholder="password" />
		<p id="loginFormMessage"></p>

		<button id="loginButton" class="btn">Login</button>
	</div>

	<!-- End of Header -->

	<c:if test="${userNameText != null}">

		<h1>${userNameText}</h1>
		<p>Id : ${userId}</p>
		<p>Password : ${userPassword}</p>

		<c:if test="${followButton != null}">
			<button id="followButton" value="${userNameText}" class="btn">${followButton}</button>
			<br>
		</c:if>
		<h3>Follows</h3>

		<c:forEach items="${userFriends}" var="friend">

			<a href="user/${friend}">${friend}</a>

		</c:forEach>
		<br>

		<h3>Messages</h3>

		<c:forEach items="${userMessages}" var="message">

			<textarea rows="4" cols="50" readonly> ${message.text} </textarea>
			<p>
				Posted by : <a href="user/${message.user}">${message.user}</a>
			</p>
			<p>${message.date}</p>
			</br>

		</c:forEach>

	</c:if>

	<c:if test="${userNameText == null}">
		<h1>This user does not exist !</h1>
	</c:if>

	<%
		if (session.getAttribute("userName") != null) {
	%>
	<%
		} else {
	%>

	<%
		}
	%>

</body>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="/twitter/resources/javascript/functions.js"></script>
</html>