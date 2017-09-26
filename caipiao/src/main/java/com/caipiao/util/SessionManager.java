package com.caipiao.util;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.util.StringUtils;

public class SessionManager {
private HttpServletRequest request;
	
	private String sessionTempName;
	
	public SessionManager(HttpServletRequest request, String sessionTempName) {
		this.request = request;
		this.sessionTempName = sessionTempName;
	}

	public SessionManager(HttpServletRequest request) {
		this(request, "__default_temp_session__");
	}
	
	public String getValue(String sessionName, String key, boolean isEncrypted) {
		return getValue(sessionName, key, "", isEncrypted);
	}

	public String getValue(String sessionName, String key, String defaultValue, boolean isEncrypted) {
		Map<String, String> valueMap = parseSession(sessionName, isEncrypted);
		String value = valueMap.get(key);
		
		return StringUtils.isEmpty(value)?defaultValue:value;
	}

	public void setLogValue(String sessionName, String key, String value) {
		Map<String, String> valueMap = parseSession(sessionName, false);
		
		if (!StringUtils.isEmpty(value)) {
			valueMap.put(key, value);
		} else {
			valueMap.remove(key);
		}
	}
	
	public void setValue(String sessionName, String key, String value, boolean isEncrypted) {
		Map<String, String> valueMap = parseSession(sessionName, isEncrypted);
		
		if (!StringUtils.isEmpty(value)) {
			valueMap.put(key, value);
		} else {
			valueMap.remove(key);
		}
	}
	
	public void save(HttpServletRequest request, String sessionName, boolean isEncrypted) {
		save(request, sessionName, 0, isEncrypted);
	}
	
	public void cleanSession(HttpServletRequest request, String sessionName) {
		
		request.getSession().removeAttribute(sessionName);
	}
	
	public void save(HttpServletRequest request, String sessionName, int age, boolean isEncrypted) {
		Map<String, String> valueMap = parseSession(sessionName, isEncrypted);
		StringBuffer sb = new StringBuffer();

		for (Entry<String, String> e : valueMap.entrySet()) {
			String key = (String) e.getKey();
			if (!StringUtils.isEmpty(key)) {
				String value = (String) e.getValue();
				if (!StringUtils.isEmpty(value)) {
					if (sb.length() > 0) sb.append('&');

					sb.append(key);
					sb.append('=');

					try {
						sb.append(URLEncoder.encode(value, "UTF-8"));
					} catch (UnsupportedEncodingException ex) {
						
						throw new UnsupportedOperationException(ex.getMessage(), ex);
					}
				}
			}
		}

		addSession(request, sessionName, sb.toString(), age, isEncrypted);
	}

	private String getSessionValue(String sessionName, boolean isEncrypted) {
		String sessionValue = getSessionValue(sessionName, null);
		if (sessionValue == null) return null;

		String decodedValue = null;
		try {
			decodedValue = URLDecoder.decode(sessionValue, "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			
			throw new UnsupportedOperationException(e.getMessage(), e);
		}

		if(isEncrypted) {
			try {
			    byte[] decodeValueBytes = decodedValue.getBytes("ISO-8859-1");
			    byte[] decryptedResult = new Encrypter().decrypt(decodeValueBytes);
			    return new String(decryptedResult, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				
				throw new UnsupportedOperationException(e.getMessage(), e);
			}
		}
		
		return decodedValue;
	}
	
	@SuppressWarnings("unchecked")
	private Map<String, String> parseSession(String sessionName, boolean isEncrypted) {
		Map<String, String> valueMap = (Map<String, String>) getRequest().getAttribute(sessionName);
		if (valueMap != null) return valueMap;
		
		valueMap = new HashMap<String, String>();
		getRequest().setAttribute(sessionName, valueMap);
		
		String sessionValue = null;
		if(isEncrypted) {
			sessionValue = getSessionValue(sessionName, true);
		} else {
			sessionValue = getSessionValue(sessionName, false);
		}
		
		if (StringUtils.isEmpty(sessionValue)) return valueMap;
		
		String[] kvPairs = sessionValue.split("&");
		for (int i = 0; i < kvPairs.length; i++) {
			if (!StringUtils.isEmpty(kvPairs[i])) {
				int offset = kvPairs[i].indexOf('=');
				if (offset > 0) {
					String key = kvPairs[i].substring(0, offset);
					String value = kvPairs[i].substring(offset + 1);
					if (!StringUtils.isEmpty(key) && !StringUtils.isEmpty(value)) {
						try {
							valueMap.put(key, URLDecoder.decode(value, "UTF-8"));	
						} catch (UnsupportedEncodingException ex) {
							
							throw new UnsupportedOperationException(ex.getMessage(), ex);
						}
					}
				}
			}
		}
		
		return valueMap;
	}
	
	private String getSessionValue(String sessionName, String defaultValue) {
		HttpSession session = getRequest().getSession();
		Enumeration<String> enumeration = session.getAttributeNames();
		while(enumeration.hasMoreElements()) {
			String key = (String) enumeration.nextElement();
			if (key.equals(sessionName)) return (String) session.getAttribute(key);
		}
    
    	return defaultValue;
    }
	
	private void addSession(HttpServletRequest request, String sessionName, String sessionValue, int age, boolean isEncrypted) {
		HttpSession session = request.getSession();
		if(isEncrypted) {
			String encryptedValue;
			try {
				encryptedValue = new String(new Encrypter().encrypt(sessionValue .getBytes("UTF-8")), "ISO-8859-1");

				encryptedValue = URLEncoder.encode(encryptedValue, "ISO-8859-1");
			} catch (UnsupportedEncodingException e) {
				
				throw new UnsupportedOperationException(e.getMessage(), e);
			}
			
			session.setAttribute(sessionName, encryptedValue);
		} else {
			try {
				sessionValue = URLEncoder.encode(sessionValue, "ISO-8859-1");
			} catch (UnsupportedEncodingException e) {
				
				throw new UnsupportedOperationException(e.getMessage(), e);
			}
			session.setAttribute(sessionName, sessionValue);
		}
		
		session.setMaxInactiveInterval(age);
	}

	private HttpServletRequest getRequest() {
		return request;
	}

	public void setSessionTempName(String sessionTempName) {
		this.sessionTempName = sessionTempName;
	}

	public String getSessionTempName() {
		return this.sessionTempName;
	}
}
