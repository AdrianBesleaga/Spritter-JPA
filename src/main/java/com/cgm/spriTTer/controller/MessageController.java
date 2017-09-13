package com.cgm.spriTTer.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cgm.spriTTer.domain.Message;
import com.cgm.spriTTer.dto.MessageDTO;
import com.cgm.spriTTer.dto.ServiceResponse;
import com.cgm.spriTTer.validators.MessageValidator;

@RestController
@RequestMapping(value = "/message")
public class MessageController {

	@Autowired
	MessageValidator validateMessage;

	@RequestMapping(method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public @ResponseBody MessageDTO message(@RequestBody Message message, HttpServletRequest request) {

		return validateMessage.validate(message, request);


	}

	@RequestMapping(method = RequestMethod.DELETE, consumes = "application/json", produces = "application/json")
	public @ResponseBody ServiceResponse deleteMessage(@RequestBody Message message, HttpServletRequest request) {

		return validateMessage.delete(message, request);

	}
}
