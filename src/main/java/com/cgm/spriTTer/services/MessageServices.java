package com.cgm.spriTTer.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgm.spriTTer.domain.Message;
import com.cgm.spriTTer.domain.User;
import com.cgm.spriTTer.repository.MessageDAO;
import com.cgm.spriTTer.repository.UserDAO;
import com.cgm.spriTTer.utils.SessionUtils;

@Service
public class MessageServices {

	@Autowired
	MessageDAO messageDAO;

	@Autowired
	UserDAO userDAO;

	public Map<String, List<Message>> getFriendMessages(HttpServletRequest request) {

		Map<String, List<Message>> allMessages = new HashMap<String, List<Message>>();
		try {
			String sessionUserName = SessionUtils.getSessionAttribute(request, "userName");
			User currentUser = userDAO.findByName(sessionUserName);
			allMessages.put(currentUser.getName(), currentUser.getMessages());
			List<User> friends = currentUser.getFriends();

			for (User friend : friends) {
				allMessages.put(friend.getName(), friend.getMessages());
			}
		} catch (Exception e) {
			System.out.println("No Session to get current user");
		}

		return allMessages;
	}
}
