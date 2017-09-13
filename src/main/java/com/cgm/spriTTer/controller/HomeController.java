package com.cgm.spriTTer.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cgm.spriTTer.domain.Message;
import com.cgm.spriTTer.services.MessageServices;

@Controller
@RequestMapping(value = "/")
public class HomeController {

	@Autowired
	MessageServices messageService;

	@RequestMapping(method = RequestMethod.GET)
	protected @ResponseBody ModelAndView home() {

		return new ModelAndView("home");
	}

	@RequestMapping(value = "home", method = RequestMethod.GET)
	protected @ResponseBody ModelAndView sameHome() {
		return new ModelAndView("home");
	}

	@RequestMapping(value = "messages", method = RequestMethod.GET)
	protected @ResponseBody Map<String, List<Message>> allMessage(HttpServletRequest request) {

		return messageService.getFriendMessages(request);
	}

}
