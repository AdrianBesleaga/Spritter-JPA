package com.cgm.spriTTer.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cgm.spriTTer.domain.User;
import com.cgm.spriTTer.dto.ServiceResponse;
import com.cgm.spriTTer.validators.LoginValidator;

@RestController
public class LoginController {

	@Autowired
	LoginValidator loginValidator;

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody ServiceResponse login(@RequestBody User user, HttpServletRequest request) {

		return new ServiceResponse(loginValidator.validate(user,request));
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public @ResponseBody ModelAndView logout(HttpServletRequest request) {
		request.getSession().removeAttribute("userName");
		return new ModelAndView("redirect:/");

	}

}
