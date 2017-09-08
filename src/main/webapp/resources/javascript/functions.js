$(document).ready(function() {
	$("#showRegister").click(function() {
		if ($('#registerForm').is(':visible')) {
			$('#registerForm').hide();
		}
		else {
			hideAll();
			$('#registerForm').show();
		}
	});
	$("#showLogin").click(function() {
		if ($('#loginForm').is(':visible')) {
			$('#loginForm').hide();
		}
		else {
			hideAll();
			$('#loginForm').show();
		}
	});

	function hideAll() {
		$('#registerForm').hide();
		$('#loginForm').hide();
		$('#searchForm').hide();
	}
	$("#registerButton").click(function() {
		var userPassword = document.getElementById('userPassword').value;
		var userName = document.getElementById('userName').value;
		var Data = {
			"name": userName,
			"password": userPassword
		};
		$.ajax({
			type: "POST",
			url: "http://localhost:8080/twitter/register",
			data: JSON.stringify(Data),
			processData: false,
			cache: false,
			async: true,
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			success: function(data) {
				console.log(data);
				$("#registerFormMessage").text(data.message);
			},
			failure: function(errMsg) {
				alert(errMsg);
			}
		});
	});
	$("#loginButton").click(function() {
		var userPassword = document.getElementById('loginUserPassword').value;
		var userName = document.getElementById('loginUserName').value;
		var Data = {
			"password": userPassword,
			"name": userName
		};
		$.ajax({
			type: "POST",
			url: "http://localhost:8080/twitter/login",
			data: JSON.stringify(Data),
			processData: false,
			cache: false,
			async: true,
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			success: function(data) {
				console.log(data);
				$("#loginFormMessage").text(data.message);
				if (data.message.indexOf("Logged In") !== -1) {
					location.reload();
				}
			},
			failure: function(errMsg) {
				alert(errMsg);
			}
		});
	});
	$("#followButton").click(function() {
		var userName = document.getElementById('followButton').value;
		var Data = {
			"name": userName
		};
		$.ajax({
			type: "POST",
			url: "http://localhost:8080/twitter/follow",
			data: JSON.stringify(Data),
			processData: false,
			cache: false,
			async: true,
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			success: function(data) {
				console.log(data);
				if (data.code == 200) {
					location.reload();
				}
			},
			failure: function(errMsg) {
				alert(errMsg);
			}
		});
	});
	$("#messageButton").click(function() {
		var textMessage = document.getElementById('messageText').value;
		var Data = {
			"text": textMessage,
			"user": "",
			"date": ""
		};
		console.log(textMessage);
		$.ajax({
			type: "PUT",
			url: "http://localhost:8080/twitter/message",
			data: JSON.stringify(Data),
			processData: true,
			cache: false,
			async: true,
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			success: function(data) {
				console.log(data);
				if (data.code == 200) {
					location.reload();
				}else{
					$("#messageFormMessage").text(data.message);
				}
			},
			failure: function(errMsg) {
				alert(errMsg);
			}
		});
	});

	$("#searchButton").click(function() {
		var user = document.getElementById('userNameSearch').value;
		if (user.length > 1) {
			window.location.replace("http://localhost:8080/twitter/user/" + user);
		}
	});
});

function deleteMessage(Data) {
	$.ajax({
		type: "DELETE",
		url: "http://localhost:8080/twitter/message",
		data: Data,
		processData: true,
		cache: false,
		async: true,
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		success: function(data) {
			console.log(data);
			if (data.code == 200) {
				location.reload();
			}
		},
		failure: function(errMsg) {
			alert(errMsg);
		}
	});
}