package com.caipiao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class CaiPiaoTest {
	public static void main(String[] args) {
		
//		String opencode = CurrentTest.getData().replace(",", "");
//		String firstThree = opencode.substring(0,3);
//		String middleThree = opencode.substring(1, 4);
//		String lastThree = opencode.substring(2,5);
//		List<String> array = new ArrayList<>();
//		array.add(firstThree);
//		array.add(middleThree);
//		array.add(lastThree);
//		System.out.println(array);
		
		
		//CaiPiaoTest.fangchengshi(array);
//		CaiPiaoTest.rubbish(array);
	}
	
//	public static String getData() {
//		String url = "http://f.apiplus.net/cqssc-10.json";
//		String result = "";
//        int timeout = 6 * 1000;
//        JSONObject jsonObject = null;
//        String firstCode ="";
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
//                firstCode = data.getJSONObject(0).getString("opencode");
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
//        return firstCode;
//	}
//	
	/**
	 * 方程式
	 * @param list
	 * @return
	 */
	public static List<String> fangchengshi(List<String> list){
		List<String> list2 = new ArrayList<String>();
		Map<Character,int[]> map = new HashMap<Character, int[]>();
		map.put('1',new int[]{1,4,7});
		map.put('4',new int[]{1,4,7});
		map.put('7',new int[]{1,4,7});
		map.put('2',new int[]{2,5,8});
		map.put('5',new int[]{2,5,8});
		map.put('8',new int[]{2,5,8});
		map.put('0',new int[]{0,3,6,9});
		map.put('3',new int[]{0,3,6,9});
		map.put('6',new int[]{0,3,6,9});
		map.put('9',new int[]{0,3,6,9});
		
		for (String str : list) {
			int[] one = map.get(str.charAt(0));
			int[] two = map.get(str.charAt(1));
			int[] three = map.get(str.charAt(2));
				
			for(int i = 0;i<one.length;i++) {
				for(int j =0;j<two.length;j++) {
					for(int k=0;k<three.length;k++) {
						System.out.println(one[i]+""+two[j]+""+three[k]);
//						list2.add(one[i]+""+two[j]+""+three[k]);
					}
				}
			}
			System.out.println();
		}
		return list2;
	}
	
	/**
	 * 垃圾复式
	 * 百位 0527  十位 2738  个位 1649
	 * @return
	 */
	public static List<String> rubbish(List<String> str){
		List<String> list = new ArrayList<String>();
		Map<Character,int[]> map = new HashMap<>();
		map.put('0',new int[]{1,6,4,9});
		map.put('5',new int[]{1,6,4,9});
		map.put('1',new int[]{2,7,3,8});
		map.put('6',new int[]{2,7,3,8});
		map.put('2',new int[]{0,5,4,9});
		map.put('7',new int[]{0,5,4,9});
		map.put('3',new int[]{0,5,2,7});
		map.put('8',new int[]{0,5,2,7});
		map.put('4',new int[]{1,6,3,8});
		map.put('9',new int[]{1,6,3,8});
		for(String array : str) {
			int[] one = map.get(array.charAt(0));
			int[] two = map.get(array.charAt(1));
			int[] three = map.get(array.charAt(2));
			//1、判断str是否是 百位-0526  十位-2738 个位-1649
			if(!("0527".indexOf(array.substring(0,1)) > -1 && "2738".indexOf(array.substring(1,2)) > -1 && "1649".indexOf(array.substring(2,3)) > -1)) {
				//2、根据 05-1649 16-2738 27-0549 38-0527 49-1638把str进行组合排序
				for(int i = 0;i<one.length;i++) {
					for(int j =0;j<two.length;j++) {
						for(int k=0;k<three.length;k++) {
							System.out.println(one[i]+""+two[j]+""+three[k]);
						}
					}
				}
				
			}
		}
		return list;
	}
}
