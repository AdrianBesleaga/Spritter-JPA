package com.cgm.spriTTer.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cgm.spriTTer.domain.User;
import com.cgm.spriTTer.dto.ServiceResponse;
import com.cgm.spriTTer.validators.FollowValidator;

@RestController
@RequestMapping(value = "/follow")
public class FollowController {
	
	@Autowired
	FollowValidator followValidator;
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody ServiceResponse follow(@RequestBody User user, HttpServletRequest request) {

		return followValidator.validate(user, request);

	}

}
