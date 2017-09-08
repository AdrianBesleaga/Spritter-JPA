package com.cgm.spriTTer.builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.cgm.spriTTer.domain.Message;
import com.cgm.spriTTer.domain.User;
import com.cgm.spriTTer.utils.SecurityUtils;
import com.cgm.spriTTer.utils.TimeUtils;

public class ArtefactBuilder {
	public static int id = 0;
	public static Map<String, ArrayList<String>> friends = new HashMap<String, ArrayList<String>>();
	public static Map<String, User> users = new HashMap<String, User>();
	public static Map<String, ArrayList<Message>> messages = new HashMap<String, ArrayList<Message>>();

	public static void firstUsers() {
		if (users.isEmpty()) {
			new User("adrian", SecurityUtils.md5("123"));
			new User("alex", SecurityUtils.md5("123"));
			new User("daniel", SecurityUtils.md5("123"));
		}
	}

	public static void firstMessages() {
		if (messages.isEmpty()) {
			new Message("Hello SpriTTer !!!", "adrian", TimeUtils.currentTime());
			new Message("Hello Again !!!", "adrian", TimeUtils.currentTime());
			new Message("What a beautiful day !", "daniel", TimeUtils.currentTime());
		}
	}

	public static void addUser(User user) {
		users.put(user.getName(), user);
	}

	public static void addMessage(String user, Message message) {
		if (messages.containsKey(user)) {
			messages.get(user).add(message);
		} else {
			ArrayList<Message> listMes = new ArrayList<Message>();
			listMes.add(message);
			messages.put(user, listMes);
		}
	}


	public static Map<String, User> getUsers() {
		return users;
	}

	public static Map<String, ArrayList<Message>> getMessages() {
		return messages;
	}

}
