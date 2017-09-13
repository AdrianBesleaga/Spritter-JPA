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
			url: "http://localhost:8080/twitter-jpa/register",
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
			url: "http://localhost:8080/twitter-jpa/login",
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
			url: "http://localhost:8080/twitter-jpa/follow",
			data: JSON.stringify(Data),
			processData: false,
			cache: false,
			async: true,
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			success: function(data) {
				console.log(data);
				if (data.code == 200) {					
					if(data.message.indexOf("Followed") !== -1){
						$("#followButton").text("UnFollow");
					}else{
						$("#followButton").text("Follow");
					}
					
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
			"text": textMessage
		};
		console.log(textMessage);
		$.ajax({
			type: "PUT",
			url: "http://localhost:8080/twitter-jpa/message",
			data: JSON.stringify(Data),
			processData: true,
			cache: false,
			async: true,
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			success: function(data) {
				if (data.text.length > 3) {
					addMessageToDiv(data);
				}else{
					$("#messageFormMessage").text("Post a longer message!");
				}
			},
			failure: function(errMsg) {
				alert(errMsg);
			}
		});
	});

	$("#searchButton").click(function() {
		var searchName = document.getElementById('userNameSearch').value;
		if (searchName.length > 1) {
			
	    	 $.get("search/"+searchName, function(data, status){ 
	    		 if(data.indexOf("not exist") !=-1){
	    			 $("#searchFormMessage").text(data);
	    		 }else{
	    			 $("#searchFormMessage").html("<a href='/twitter-jpa//user/"+data+"'> "+data+"</a>");	 
	    		 }
	    	 });
	    	 		
		}else{
			$("#searchFormMessage").text("Search something");
		}
	});
});

function deleteMessage(Data) {
	Message = JSON.parse(Data);
	$.ajax({
		type: "DELETE",
		url: "http://localhost:8080/twitter-jpa/message",
		data: Data,
		processData: true,
		cache: false,
		async: true,
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		success: function(data) {
			console.log(data);
			if (data.code == 200) {
				$("#message-"+Message.id).remove();
			}
		},
		failure: function(errMsg) {
			alert(errMsg);
		}
	});
}

function getMessages(){
    if(window.location.href.endsWith("/twitter-jpa/") && document.getElementById("messages")) {
    	
    	var messages = document.getElementById("messages");
    	messages.innerHTML = "";
    	
    	 $.get("message", function(data, status){   	       
    		 
    		  for(var user in data){
    		      for(var message in data[user]){
    		    	  console.log("User : " + user + " : " + data[user][message].text + " date : " + new Date(data[user][message].date));
    		    	  
    		    	  if(data[user][message].text){
    		    		  var html = '<textarea rows="4" cols="50" readonly="">'+ data[user][message].text +'</textarea>';
    		    		  html += '<p>Posted by : <a class="upper" href="user/'+user+'">'+user+'</a></p>';
    		    		  html += '<p>'+ new Date(data[user][message].date) +'</p>';
    		    		  html += '<br>';
    		    		  messages.innerHTML += html;
    		    	  }
    		    	  
    		      }
    		  }
    	        
    	        
    	    });
    	 
    }
}

function addMessageToDiv(data){
	
	var messages = document.getElementById("messages");
	
	  if(data.text){
		  var html = '<textarea rows="4" cols="50" readonly="">'+ data.text +'</textarea>';
		  html += '<p>Posted by : <a class="upper" href="user/'+data.user.name+'">'+data.user.name+'</a></p>';
		  html += '<p>'+ new Date(data.date) +'</p>';
		  html += '<br>';
		  messages.innerHTML += html;
	  }
}

$(document).ready(function () {
	
getMessages();

});