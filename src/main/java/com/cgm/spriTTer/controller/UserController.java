package com.cgm.spriTTer.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cgm.spriTTer.domain.User;
import com.cgm.spriTTer.dto.ServiceResponse;
import com.cgm.spriTTer.repository.UserDAO;
import com.cgm.spriTTer.utils.SessionUtils;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	UserDAO userDAO;

	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView getUserPage() throws Exception {

		return new ModelAndView("redirect:/users");
	}

	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	protected ModelAndView getUser(@PathVariable String name, HttpServletRequest request) throws Exception {
		ModelAndView model;

		String sessionUserName = SessionUtils.getSessionAttribute(request, "userName");
		User currentUser = userDAO.findByName(sessionUserName);

		if (userDAO.findByName(name) != null) {
			User user = userDAO.findByName(name);
			model = new ModelAndView("user", "userNameText", user.getName());
			model.addObject("userId", user.getId());
			model.addObject("userPassword", user.getPassword());
			model.addObject("userMessages", user.getMessages());
			model.addObject("userFriends", user.getFriends());

			if (currentUser.getFriends().contains(userDAO.findByName(name))) {
				model.addObject("followButton", "UnFollow");
			} else {
				model.addObject("followButton", "Follow");
			}

		} else {
			model = new ModelAndView("user", "userNameText", null);
		}
		return model;
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody ServiceResponse createArtist(@RequestBody User user) {
		return new ServiceResponse(user.getName());
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public ServiceResponse updateUser(User user) {
		return new ServiceResponse(user.getName());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ServiceResponse deleteUser(@RequestHeader("token") String token, @PathVariable Long id) {
		System.out.println("Called delete User (" + id + ") service with token: " + token + " !");
		return new ServiceResponse("deleted : " + id);
	}

}
