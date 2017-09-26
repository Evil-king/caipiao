package com.caipiao.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class SessionHelper {
	
	public static String getRegisterCode() {
		return getManager().getValue("code", "code", true);
	}
	
	public static HttpServletRequest getRequest() {
		
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	}
	
	public static HttpServletResponse getResponse() {
		
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
	}
	
	private static SessionManager getManager(){
		
		return new SessionManager(getRequest());
	}
}
