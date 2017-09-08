package com.cgm.spriTTer.controller;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cgm.spriTTer.builder.ArtefactBuilder;
import com.cgm.spriTTer.database.contract.UserDataStore;
import com.cgm.spriTTer.domain.User;
import com.cgm.spriTTer.dto.ServiceResponse;
import com.cgm.spriTTer.repository.UserDAO;

@RestController
public class LoginController {

	@Autowired
	UserDAO userDAO;
	@Autowired
	UserDataStore userDataStore;

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody ServiceResponse login(@RequestBody User user, HttpServletRequest request) {

		String message = "Wrong Login Credentials";

		if (user.getName() != null && user.getPassword() != null && userDAO.findByName(user.getName()) != null) {
			if (user.getPassword().equals(userDAO.findByName(user.getName()).getPassword())) {
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
