package com.cgm.spriTTer.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cgm.spriTTer.builder.ArtefactBuilder;
import com.cgm.spriTTer.domain.Message;
import com.cgm.spriTTer.dto.ServiceResponse;
import com.cgm.spriTTer.utils.SessionUtils;
import com.cgm.spriTTer.utils.TimeUtils;

@RestController
@RequestMapping(value = "/message")
public class MessageController {
	@RequestMapping(method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public @ResponseBody ServiceResponse message(@RequestBody Message message, HttpServletRequest request) {

		if (message.getText().length() > 3) {
			new Message(message.getText(), request.getSession().getAttribute("userName").toString(),
					TimeUtils.currentTime());
		} else {
			return new ServiceResponse("Post a longer message ! ", 202);
		}

		return new ServiceResponse(message.getText());
	}
	
	
	
	@RequestMapping(method = RequestMethod.DELETE, consumes = "application/json", produces = "application/json")
	public @ResponseBody ServiceResponse deleteMessage(@RequestBody Message message, HttpServletRequest request) {

		String sessionUserName = SessionUtils.getSessionAttribute(request, "userName");
		
		if (sessionUserName != null && ArtefactBuilder.messages.containsKey(sessionUserName)) {
			
			if(ArtefactBuilder.messages.get(sessionUserName).contains(message)) {
				ArtefactBuilder.messages.get(sessionUserName).remove(message);
				return new ServiceResponse("Message Deleted", 200);
			}else {
				return new ServiceResponse("Message Not Deleted", 202);
			}
			
		} else {
			return new ServiceResponse("This user has no messages ! ", 202);
		}

		
	}
}
