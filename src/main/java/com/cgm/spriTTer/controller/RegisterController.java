package com.cgm.spriTTer.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cgm.spriTTer.builder.ArtefactBuilder;
import com.cgm.spriTTer.domain.User;
import com.cgm.spriTTer.dto.ServiceResponse;

@RestController
@RequestMapping(value = "/register")
public class RegisterController {

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody ServiceResponse register(@RequestBody User user, HttpServletRequest request) {
		String message;
		if (ArtefactBuilder.getUsers().containsKey(user.getName())) {
			message = "UserName already taken";
		} else if (user.getName().length() > 3 && user.getPassword().length() > 3) {
			user.setId(ArtefactBuilder.id++);
			ArtefactBuilder.addUser(user);
			message = "Registered " + user.getName() + " with id  " + user.getId();
		} else {
			message = "Error";
		}

		return new ServiceResponse(message);
	}

}
