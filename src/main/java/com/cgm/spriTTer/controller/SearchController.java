package com.cgm.spriTTer.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cgm.spriTTer.domain.User;
import com.cgm.spriTTer.repository.UserDAO;


@RestController
@RequestMapping(value = "/search")
public class SearchController {
	
	@Autowired
	UserDAO userDAO;
	
	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	protected String getUser(@PathVariable String name, HttpServletRequest request) throws Exception {

		if (userDAO.findByName(name) != null) {
			User user = userDAO.findByName(name);
			return user.getName();
		}
		return "Does not exist";
	}
}
