package com.cgm.spriTTer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cgm.spriTTer.builder.ArtefactBuilder;

@Controller
@RequestMapping(value = "/")
public class HomeController {

	@RequestMapping(method = RequestMethod.GET)
	protected @ResponseBody ModelAndView home() {

		return new ModelAndView("home", "messageList", ArtefactBuilder.messages);
	}
	
	@RequestMapping(value = "home",method = RequestMethod.GET)
	protected @ResponseBody ModelAndView sameHome() {
		return new ModelAndView("home", "messageList", ArtefactBuilder.messages);
	}

}
