package com.cgm.spriTTer.database.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cgm.spriTTer.domain.User;


@SuppressWarnings("rawtypes")
public class UserRowMapper implements RowMapper {

    public User mapRow(ResultSet rs, int line) throws SQLException {
        UserResultSetExctractor extractor = new UserResultSetExctractor();
        return extractor.extractData(rs);
    }
}
