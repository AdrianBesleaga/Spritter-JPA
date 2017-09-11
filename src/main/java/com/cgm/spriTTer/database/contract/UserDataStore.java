package com.cgm.spriTTer.database.contract;

import java.util.List;

import com.cgm.spriTTer.domain.User;

public interface UserDataStore {
	void storeUser(User user);

	List<User> readUser();
}
