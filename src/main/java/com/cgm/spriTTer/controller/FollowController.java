package com.cgm.spriTTer.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cgm.spriTTer.builder.ArtefactBuilder;
import com.cgm.spriTTer.domain.User;
import com.cgm.spriTTer.dto.ServiceResponse;
import com.cgm.spriTTer.utils.SessionUtils;

@RestController
@RequestMapping(value = "/follow")
public class FollowController {
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody ServiceResponse follow(@RequestBody User user, HttpServletRequest request) {

		String sessionUserName = SessionUtils.getSessionAttribute(request, "userName");

		if (sessionUserName != null && ArtefactBuilder.friends.containsKey(sessionUserName)) {
			if (ArtefactBuilder.friends.get(sessionUserName).contains(user.getName())) {
				ArtefactBuilder.friends.get(sessionUserName).remove(user.getName());
				return new ServiceResponse("Unfollowed");
			} else {
				ArtefactBuilder.friends.get(sessionUserName).add(user.getName());
				return new ServiceResponse("Followed");
			}

		}

		if (sessionUserName != null && ArtefactBuilder.friends.containsKey(sessionUserName) == false) {
			ArrayList<String> friendList = new ArrayList<String>();
			friendList.add(user.getName());
			ArtefactBuilder.friends.put(sessionUserName, friendList);
			return new ServiceResponse("Followed");
		}
		
		return new ServiceResponse("Error");

	}

}
