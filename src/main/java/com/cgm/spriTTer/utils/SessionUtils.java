package com.cgm.spriTTer.utils;

import javax.servlet.http.HttpServletRequest;

public class SessionUtils {
	public static String getSessionAttribute(HttpServletRequest request, String attribute) {

		if(request.getSession().getAttribute(attribute) != null) {
			return request.getSession().getAttribute(attribute).toString();
		}
		
		return null;

	}
}
