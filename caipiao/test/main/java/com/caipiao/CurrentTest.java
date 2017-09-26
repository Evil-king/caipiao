package com.caipiao;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.caipiao.pojo.CurrentData;
import com.caipiao.vo.IceDataVO;

public class CurrentTest {
	
//	public List<IceDataVO> getData() {
//		String result = "";
//		String url = "http://f.apiplus.net/cqssc-10.json";
//        int timeout = 6 * 1000;
//        JSONObject jsonObject = null;
//        List<IceDataVO> vlist = new ArrayList<>();
//        SimpleDateFormat sd = new SimpleDateFormat("HH:mm");
//        
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        try {
//            HttpGet httpPost = new HttpGet(url);
//            RequestConfig requestConfig = RequestConfig.custom()
//                    .setSocketTimeout(timeout)
//                    .setConnectTimeout(timeout)
//                    .setConnectionRequestTimeout(timeout)
//                    .build();
//            httpPost.setConfig(requestConfig);
//            CloseableHttpResponse response = httpClient.execute(httpPost);
//            try {
//                HttpEntity entity = response.getEntity();
//                result = EntityUtils.toString(entity);
//                jsonObject = JSON.parseObject(result);
//                JSONArray data = jsonObject.getJSONArray("data");
//              List<CurrentData> list = JSON.parseArray(data+"", CurrentData.class);
//              for(CurrentData cp : list) {
//            	  CurrentData cd  = new CurrentData();
//            	  	cd.setExpect(cp.getExpect());
//            	  	cd.setOpenCode(cp.getOpenCode());
//            	  	cd.setOpenTime(cp.getOpenTime());
//            	  	cd.setOpenTimesTamp(cp.getOpenTimesTamp());
//            	  	IceDataVO ice =  new IceDataVO();
//            	  	ice.setOpenCode(cd.getOpenCode().replace(",", "").substring(2, 4));
//            	  	ice.setOpentime(sd.format(cp.getOpenTime()));
//            	  	vlist.add(ice);
//              }
//              
//                EntityUtils.consume(entity);
//            } finally {
//                response.close();
//            }
//        } catch(IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                httpClient.close();
//            } catch (IOException e) {
//            }
//        }
//        return vlist;
//	}
	
	public static List<String> toU(String str) {
//		String str="10,16,0,8,16,10,16,5,5,7,16,7,13,13,8";
//        String[] numbers = str.split(",");
//         
//        Map<String,Integer> maps = new TreeMap<String,Integer>();
//        for(String s : numbers){
//            if(maps.containsKey(s)){
//                int num = maps.get(s);
//                num++;
//                maps.put(s, num);
//            }else{
//                maps.put(s, 1);
//            }
//        }
//         
//        for(Entry<String, Integer> entry : maps.entrySet()){
//            System.out.println("数字："+entry.getKey()+",出现次数："+entry.getValue());
//        }
		String[] numbers = str.split(",");
		Map<String,Integer> maps = new LinkedHashMap<String,Integer>();
	    for(String s : numbers){
	        if(maps.containsKey(s)){
	            int num = maps.get(s);
	            num++;
	            maps.put(s, num);
	        }else{
	            maps.put(s, 1);
	        }
	    }
	    List<String> li = new ArrayList<String>();
	    for(int i = 0; i < 2; i++){
	    	Entry<String, Integer> in = null;
	    	for(Entry<String, Integer> entry : maps.entrySet()){
		    	if(in == null){
		    		in = entry;
		    	} else {
		    		if(in.getValue() > entry.getValue()){
		    			in = entry;
		    		}
		    	}
//		        System.out.println("数字："+entry.getKey()+",出现次数："+entry.getValue());
		    }
	    	li.add(in.getKey());
	    	maps.remove(in.getKey());
	    }
	    System.out.println(li);
	    return li;
	}
	
	
	
	
	public static void main(String[] args) {
		
//		CurrentTest.toU();
//		CurrentTest tt = new CurrentTest();
//		List<IceDataVO> vv = tt.getData();
//		System.out.println(vv);
//		StringBuffer sb = new StringBuffer();
//		String temp = "";
//		for(int i=0;i<vv.size();i++) {
//			IceDataVO str = vv.get(i);
//			String ss = str.getOpenCode();
//			char[] chas = ss.toCharArray();
//			for(int j =0;j<chas.length;j++) {
//				temp = chas[j]+"";
//				sb.append(temp).append(",");
//			}
//		}
//		if(sb.length() > 0) {
//			sb.deleteCharAt(sb.length()-1);
//			System.out.println(sb.deleteCharAt(sb.length()-1));
//			CurrentTest.toU(sb.toString());
//		}
	}
}
