package com.cgm.spriTTer.validators;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgm.spriTTer.domain.User;
import com.cgm.spriTTer.repository.UserDAO;

@Service
public class LoginValidator {

	@Autowired
	UserDAO userDAO;

	public String validate(User user, HttpServletRequest request) {
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
		return message;
	}
}
