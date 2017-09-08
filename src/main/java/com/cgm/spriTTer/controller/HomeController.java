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
		ArtefactBuilder.firstUsers();
		ArtefactBuilder.firstMessages();

		ModelAndView model;

		model = new ModelAndView("/home");
		model.addObject("messageList", ArtefactBuilder.messages);

		return model;
	}
	
	@RequestMapping(value="home", method = RequestMethod.GET)
	protected @ResponseBody ModelAndView sameHome() {
		ArtefactBuilder.firstUsers();
		ArtefactBuilder.firstMessages();

		ModelAndView model;

		model = new ModelAndView("/home");
		model.addObject("messageList", ArtefactBuilder.messages);

		return model;
	}

}
