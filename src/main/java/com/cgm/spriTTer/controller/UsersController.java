package com.cgm.spriTTer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cgm.spriTTer.repository.UserDAO;

@RestController
@RequestMapping(value = "/users")
public class UsersController {
	
	@Autowired
	UserDAO userDAO;
	
	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView getUsers() throws Exception {

		ModelAndView model;

		model = new ModelAndView("users", "usersList", userDAO.findAll());

		return model;
	}
}
