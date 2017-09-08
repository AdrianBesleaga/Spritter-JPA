<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<jsp:include page='header.jsp'>
    <jsp:param name="title" value="Profile"/>
</jsp:include>

	<!-- End of Header -->


	<div id="searchForm" class="form">
		<p>Search</p>

		<input type="text" id="userNameSearch" class="input"
			placeholder="name" />
		<p id="registerFormMessage"></p>

		<button id="searchButton" class="btn">Search</button>
	</div>


	<%
		if (session.getAttribute("userName") != null) {
	%>




	<table class="usersList">
		<c:forEach items="${usersList}" var="map">
			<tr>
				<td><a href="http://localhost:8080/twitter/user/${map.key}">${map.key}</a>
				</td>
			</tr>
		</c:forEach>
	</table>

	<%
		} else {
	%>
	<h1>Please login</h1>
	<%
		}
	%>

</body>

</html>