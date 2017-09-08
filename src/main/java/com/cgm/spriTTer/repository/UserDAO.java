package com.cgm.spriTTer.repository;

import org.springframework.stereotype.Repository;

import com.cgm.spriTTer.domain.User;

@Repository
public class UserDAO extends AbstractDAO <User> {
	protected UserDAO() {
		super(User.class);
	}
}
