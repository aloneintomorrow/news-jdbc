package com.laptrinhjavaweb.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtil {
	public static SessionUtil sessionUtil = null;
	public static SessionUtil getInstance() {
		 if(sessionUtil == null) {
			 sessionUtil = new SessionUtil();
		 }
		 return sessionUtil;
	}
	public void putValue(HttpServletRequest request, String name, Object value) {
		request.getSession().setAttribute(name, value);
	}
	public Object getValue(HttpServletRequest request, String name) {
		return request.getSession().getAttribute(name);
	}
	public void removeValue(HttpServletRequest request,String key) {
		request.getSession().removeAttribute(key);
	}
	
}