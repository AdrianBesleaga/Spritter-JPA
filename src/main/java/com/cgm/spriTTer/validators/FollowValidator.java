package com.cgm.spriTTer.validators;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgm.spriTTer.domain.User;
import com.cgm.spriTTer.dto.ServiceResponse;
import com.cgm.spriTTer.repository.UserDAO;
import com.cgm.spriTTer.utils.SessionUtils;

@Service
public class FollowValidator {
	@Autowired
	UserDAO userDAO;

	public ServiceResponse validate(User user, HttpServletRequest request) {
		String sessionUserName = SessionUtils.getSessionAttribute(request, "userName");

		User currentUser = userDAO.findByName(sessionUserName);
		User userToFollow = userDAO.findByName(user.getName());

		if (sessionUserName != null && currentUser != null) {

			if (currentUser.getFriends().contains(userToFollow)) {
				currentUser.getFriends().remove(userToFollow);
				userDAO.update(currentUser);
				return new ServiceResponse("Unfollowed");
			} else {
				currentUser.getFriends().add(userToFollow);
				userDAO.update(currentUser);
				return new ServiceResponse("Followed");
			}

		}

		return new ServiceResponse("Error");
	}

}
