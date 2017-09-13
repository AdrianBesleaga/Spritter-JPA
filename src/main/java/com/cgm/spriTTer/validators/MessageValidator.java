package com.cgm.spriTTer.validators;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgm.spriTTer.domain.Message;
import com.cgm.spriTTer.dto.MessageDTO;
import com.cgm.spriTTer.dto.ServiceResponse;
import com.cgm.spriTTer.repository.MessageDAO;
import com.cgm.spriTTer.repository.UserDAO;
import com.cgm.spriTTer.utils.SessionUtils;

@Service
public class MessageValidator {

	@Autowired
	MessageDAO messageDAO;

	@Autowired
	UserDAO userDAO;

	public MessageDTO validate(Message message, HttpServletRequest request) {
		String sessionUserName = SessionUtils.getSessionAttribute(request, "userName");
		if (sessionUserName != null && message.getText().length() > 3) {
			message.setUser((userDAO.findByName(sessionUserName)));
			messageDAO.update(message);
			return new MessageDTO(message.getText(),message.getUser(),message.getDate());
		}

		return new MessageDTO("",null,null);
	}

	public ServiceResponse delete(Message message, HttpServletRequest request) {
		String sessionUserName = SessionUtils.getSessionAttribute(request, "userName");

		if (sessionUserName != null && userDAO.findByName(sessionUserName).getName().equals(sessionUserName)) {

			if (messageDAO.findById(message.getId()) != null) {
				messageDAO.delete(message.getId());
				return new ServiceResponse("Message Deleted", 200);
			} else {
				return new ServiceResponse("Message Not Deleted", 202);
			}

		} else {
			return new ServiceResponse("This user has no messages ! ", 202);
		}
	}
}
