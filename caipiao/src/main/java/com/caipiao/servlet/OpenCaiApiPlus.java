package com.caipiao.servlet;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.caipiao.pojo.CurrentData;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;



/**
 * 获取实时数据
 * @author Fox
 *
 */
@Component
public class OpenCaiApiPlus {

    private static final Logger log = Logger.getLogger(OpenCaiApiPlus.class);
 // 此处需要传递的3个参数，必填项
  	private static	final String name = "cqssc";// 彩种
  	private static	final String uid = "811333";// 用户ID
  	private static	final String token = "95924d6d6230ad13c5e42903290b75497aa59022";// token

    /**
     * 重庆时时彩 - 高频
     * 白天 [10:00, 21:50] 每10分钟一期，共72期
     * 夜间 [22:00, 01:55] 每05分钟一期，共48期
     * @return
     */
    public static List<CurrentData> cqssc(int num) {
    	String url = "http://api.caipiaokong.com/lottery/";
    	url += "?name=" + name;
    	url += "&format=json";// 数据格式，此文件仅支持json
    	url += "&uid=" + uid;
    	url += "&token=" + token;
    	url += "&num=" + num;
    	boolean flag = false;
    	List<CurrentData> list = null;
    do {
    		if(flag == true) {
    			try {
					Thread.sleep(6 * 1000);
					flag = false;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
    		}
    	String urlAll = new StringBuffer(url).toString();
		String charset = "UTF-8";
		try {
			Thread.sleep(6 * 1000);
		} catch (InterruptedException e2) {
			e2.printStackTrace();
		}
		try {
		list = new ArrayList<CurrentData>();
		String jsonResult = get(urlAll, charset);
		if (jsonResult == null || jsonResult.indexOf("null") > -1) {// 判断请求结果是否为null，为空的话则重新请求
			continue;
		}
		JSONObject object = JSONObject.fromObject(jsonResult);
		log.info("object:"+object+new Date());
		CurrentData data = null ;
		String number ="";
			Iterator it = object.keys();
			while (it.hasNext()) {
				String key = (String) it.next();
				String value = object.getString(key);
				JSONObject object1 = JSONObject.fromObject(value);
				data =  new CurrentData();
				data.setExpect(key);
				number = object1.getString("number");
				data.setOpenCode(number);
				data.setOpenTime(object1.getString("dateline"));
				list.add(data);
			}
			List<CurrentData> cd = ConnectionFactory.getInstance().selectLast15();
			log.info("进入循环-----------------------------");
				for(CurrentData cds : cd) {
					if(cds.getOpenCode().equals(number)) {
						flag = true;
						break;
					}
				}
			Thread.sleep(6 * 1000);//防止出现违规
		} catch (JSONException e) {
			e.printStackTrace();
		}catch (InterruptedException e1) {
			e1.printStackTrace();
		}catch (Exception e1) {
			e1.printStackTrace();
			cqssc(num);
		}
    	}while(flag == true && num == 1);
        return list ;
    }
    
    public static String get(String urlAll, String charset) {
		BufferedReader reader = null;
		String result = null;
		StringBuffer sbf = new StringBuffer();
		String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";// ģ�������
		try {
			URL url = new URL(urlAll);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestMethod("GET");
			connection.setReadTimeout(30000);
			connection.setConnectTimeout(30000);
			connection.setRequestProperty("User-agent", userAgent);
			connection.connect();
			InputStream is = connection.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is, charset));
			String strRead = null;
			while ((strRead = reader.readLine()) != null) {
				sbf.append(strRead);
				sbf.append("\r\n");
			}
			reader.close();
			result = sbf.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
