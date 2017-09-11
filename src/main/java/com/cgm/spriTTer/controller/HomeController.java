package com.cgm.spriTTer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cgm.spriTTer.repository.MessageDAO;

@Controller
@RequestMapping(value = "/")
public class HomeController {
	
	@Autowired
	MessageDAO messageDAO;

	@RequestMapping(method = RequestMethod.GET)
	protected @ResponseBody ModelAndView home() {

		return new ModelAndView("home", "messageList", messageDAO.findAll());
	}
	
	@RequestMapping(value = "home",method = RequestMethod.GET)
	protected @ResponseBody ModelAndView sameHome() {
		return new ModelAndView("home", "messageList", messageDAO.findAll());
	}

}
