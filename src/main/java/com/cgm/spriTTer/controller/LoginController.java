package com.cgm.spriTTer.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cgm.spriTTer.builder.ArtefactBuilder;
import com.cgm.spriTTer.domain.User;
import com.cgm.spriTTer.dto.ServiceResponse;

@RestController
public class LoginController {

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody ServiceResponse login(@RequestBody User user, HttpServletRequest request) {

		String message = "Wrong Login Credentials";

		if (user.getName() != null && user.getPassword() != null
				&& ArtefactBuilder.getUsers().containsKey(user.getName())) {
			if (user.getPassword().equals(ArtefactBuilder.getUsers().get(user.getName()).getPassword())) {
				message = "Logged In";
				request.getSession().setAttribute("userName", user.getName());
			} else {
				message = "Wrong Password";
				request.getSession().removeAttribute("userName");
			}
		}
		return new ServiceResponse(message);
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public @ResponseBody ModelAndView logout(HttpServletRequest request) {
		request.getSession().setAttribute("userName", null);
		request.getSession().removeAttribute("userName");
		return new ModelAndView("redirect:/");

	}

}
