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

import java.io.IOException;

/**
 * BASE64 严格地说，属于编码格式，而非加密算法
 * Base64内容传送编码被设计用来把任意序列的8位字节描述为一种不易被人直接识别的形式
 * (The Base64 Content-Transfer-Encoding is designed to represent arbitrary sequences of octets in a form that need not be humanly readable.)
 * 
 * @author Flouny.Caesar
 *
 */
public class BASE64 {
	
	/**
	 * BASE64解密
	 * @param key
	 * @return
	 */
	@SuppressWarnings("restriction")
	public static byte[] decryptBASE64(String key) {
		if (StringUtil.isBlank(key)) return null;
		
	    try {
			return (new sun.misc.BASE64Decoder()).decodeBuffer(key);
		} catch (IOException e) {
			// ...
		}
		
		return null;  
	}
	
	/**
	 * BASE64加密
	 * @param key
	 * @return
	 */
	@SuppressWarnings("restriction")
	public static String encryptBASE64(byte[] key) {
		try {
			return (new sun.misc.BASE64Encoder()).encodeBuffer(key); 
		} catch (Exception e) {
			// ...
		}
		
		return null;
	}
}