package com.cgm.spriTTer.database.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.cgm.spriTTer.domain.User;

public class UserResultSetExctractor implements ResultSetExtractor<User> {
	public User extractData(ResultSet rs) throws SQLException {
		User cashDrawer = new User();
		cashDrawer.setName(rs.getString("name"));
		return cashDrawer;
	}
}
