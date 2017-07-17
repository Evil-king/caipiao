/**
 * Copyright 2014-2017 www.lychee.com
 * All rights reserved.
 * 
 * @project
 * @author Flouny.Caesar
 * @version 2.0
 * @date 2015-12-01
 */
package com.caipiao.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式 
 * 
 * @author Flouny.Caesar
 *
 */
public class ExpressUtil {
	
	/**
	 * 判断手机格式是否正确
	 * @param phone
	 * @return
	 */
	public static boolean isPhone(String phone) {
		if (StringUtil.isBlank(phone)) return false;
		
		Pattern pattern = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[012356789])|(17[0-9]))\\d{8}$");
		
		return pattern.matcher(phone).matches();
	}
	
	/**
	 * 判断手机格式是否正确
	 * @param phone
	 * @return
	 */
	public static boolean isNotPhone(String phone) {
		
		return !isPhone(phone);
	}
	
	/**
	 * 校验是否是邮箱
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email){
		Pattern pattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
		Matcher matcher = pattern.matcher("a@aa.com");
		
		return matcher.matches();
	}
	
	/**
	 * 校验邮箱格式不正确
	 * @param email
	 * @return
	 */
	public static boolean isNotEmail(String email){
		
		return !isEmail(email);
	}
}