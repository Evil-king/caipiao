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

import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.SignatureException;

/**
 * MD5 message-digest algorithm 5 （信息-摘要算法）缩写，广泛用于加密和解密技术，常用于文件校验
 * 
 * @author Flouny.Caesar
 *
 */
public class MD5 {
	
	/**
	 * 将字符串转加密成MD5串
	 * @param s
	 * @return
	 */
	public static String encryptMD5(String s) {
		char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
		
		try {
			byte[] strTemp = s.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char[] str = new char[j * 2];
			int k = 0;
			
			for (int i = 0; i < j; i++) {
				byte b = md[i];
				str[k++] = hexDigits[b >> 4 & 0xf];
				str[k++] = hexDigits[b & 0xf];
			}
			
			return new String(str);
		} catch (Exception e) {
			// ...
		}
		
		return null;
	}
	
	/**
	 * MD5加密
	 * @param data
	 * @return
	 */
	public static String encryptMD5(byte[] data) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(data);
			
			return BASE64.encryptBASE64(md5.digest());
		} catch (Exception e) {
			// ...
		}
		
		return null;
	}
	
	/**
     * 签名字符??
     * @param text 签名的字符串
     * @param key 密钥
     * @param input_charset 编码格式
     * @return 签名结果
     */
    public static String sign(String text, String key, String input_charset) {
    	text = text + key;
        return DigestUtils.md5Hex(getContentBytes(text, input_charset));
    }
    
    /**
     * 签名字符?
     * @param text 签名的字符串
     * @param sign 签名结果
     * @param key 密钥
     * @param input_charset 编码格式
     * @return 签名结果
     */
    public static boolean verify(String text, String sign, String key, String input_charset) {
    	text = text + key;
    	String mysign = DigestUtils.md5Hex(getContentBytes(text, input_charset));
    	if(mysign.equals(sign)) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }

    /**
     * @param content
     * @param charset
     * @return
     * @throws SignatureException
     * @throws UnsupportedEncodingException 
     */
    private static byte[] getContentBytes(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("MD5签名过程中出现错??指定的编码集不对,您目前指定的编码集是:" + charset);
        }
    }

}