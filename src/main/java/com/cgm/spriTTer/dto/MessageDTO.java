package com.cgm.spriTTer.dto;

import java.util.Date;

import com.cgm.spriTTer.domain.User;

public class MessageDTO {
	private String text;
	private User user;
	private Date date;
	
	public MessageDTO(String text, User user, Date date) {
		super();
		this.text = text;
		this.user = user;
		this.date = date;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
