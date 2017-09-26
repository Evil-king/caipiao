package com.caipiao.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caipiao.dao.CurrentDataMapper;
import com.caipiao.dao.HotWaterDigitalMapper;
import com.caipiao.dao.UserMapper;
import com.caipiao.exception.BaseException;
import com.caipiao.pojo.CurrentData;
import com.caipiao.pojo.HotWaterDigital;
import com.caipiao.pojo.User;
import com.caipiao.service.CurrentDataService;

@Service
public class CurrentDataServiceImpl implements CurrentDataService {
	@Autowired
	private CurrentDataMapper currentDataMapper;
	@Autowired
	private HotWaterDigitalMapper hotWaterDigitalMapper;
	@Autowired
	private UserMapper userMapper;
	
	private Logger logg = org.slf4j.LoggerFactory.getLogger(CurrentDataServiceImpl.class);
	 
	
	/**
	 * 获取实时最新60条数据
	 */
	public List<CurrentData> sendData(String phone,String token) {
		
		User user = new User();
		user.setPhone(phone);
		user.setToken(token);
		User uu = userMapper.queryByParam(user);
		if(uu.getToken()==null || uu.getToken().equals("")) {
			throw new BaseException(1, "请重新登录");
		}
		if(!(uu.getToken().equals(token))) {
				throw new BaseException(1, "账号在其他设备上登录");
		}
		long time = new Date().getTime() - uu.getModifiedTime().getTime();
		if(time > 48 * 60 * 60 * 1000) {
			user.setToken("");
			user.setPhone(phone);
			if(userMapper.updateByParam(user) < 1) {
				throw new BaseException(1, "更新失败");
			}
			throw new BaseException(1, "登录过期");
			
		}
		
		
		 List<CurrentData> vlist = new ArrayList<>();
		 List<CurrentData> cd =  currentDataMapper.queryLimitMore(60);
		 //按照期号从取出来的list中从小到大排列
		 Collections.sort(cd,new Comparator<CurrentData>() {
			@Override
			public int compare(CurrentData cud1, CurrentData cud2) {
					int c1 = Integer.parseInt(cud1.getExpect().substring(5));
					int c2 = Integer.parseInt(cud2.getExpect().substring(5));
					if(c1 > c2) {
						return 1;
					}
					if(c1 == c2) {
						return 0;
					}
				return -1;
			}
			 
		 });  
		 for(CurrentData data : cd) {
			 CurrentData  cv = new CurrentData();
			 cv.setExpect(data.getExpect());
			 cv.setOpenTime(data.getOpenTime().substring(11,16));
			 cv.setOpenCode(data.getOpenCode());
			 vlist.add(cv);
		 }
		return vlist;
	}

	
	/**
	 * 计算数字出现次数
	 * @param str
	 * @return
	 */
//	private  Map<String,Integer> getMin2(String str) {
//		String[] numbers = str.split(",");
//		Map<String,Integer> maps = new HashMap<String,Integer>();
//	    for(String s : numbers){
//	        if(maps.containsKey(s)){
//	            int num = maps.get(s);
//	            num++;
//	            maps.put(s, num);
//	        }else{
//	            maps.put(s, 1);
//	        }
//	    }
//	    Map<String,Integer> li = new HashMap<String,Integer>();
//	    for(int i = 0; i < 2; i++){
//	    	Entry<String, Integer> in = null;
//	    	for(Entry<String, Integer> entry : maps.entrySet()){
//		    	if(in == null){
//		    		in = entry;
//		    	} else {
//		    		if(in.getValue() < entry.getValue()){
//		    			in = entry;
//		    		}
//		    	}
//		    }
//	    	li.put(in.getKey(), in.getValue());
//	    	maps.remove(in.getKey());
//	    }
//	    return li;
//	}
	
	
	/**
	 * 冷水数字：取最新20条数据，得到开奖号后两位和开奖时间，放入VO。得到所有开奖号的后两位得到出现次数最
	 * 少的两个数字，以及它们出现的次数，并计算出期号（该期号不是currentData的期号），存入数据库中。
	 * 从库中得到所有期冷水数字，处理后返回，格式为1期一个String "X,X"。
	 */
	public Map<String, List<String>> getIcdData(Integer number) {
		List<CurrentData> cds =  currentDataMapper.queryAll(); // 此处改为查全部
		int[] decades = new int[10];
		int[] theUnits = new int[10];
		List<String> numList = null;
		CurrentData cd = null;
		Map<String, List<String>> rMap = new HashMap<>();
		int num = 0;
		for (int count = cds.size() - 1; count >= 0 ; count--) {
			numList = new ArrayList<>();
			cd = cds.get(count);
			// 处理计数器
			for(int i = 0; i < decades.length; i++) {
				decades[i] += 1;
				theUnits[i] += 1;
			}
			String code = cd.getOpenCode().replace(",", "");
			decades[Integer.valueOf(code.substring(3, 4))] = 0;
			theUnits[Integer.valueOf(code.substring(4))] = 0;
			// 处理期号
			String temp = cd.getExpect().substring(8);
			Integer exInt = Integer.valueOf(temp) + 1;
			String ex;
			if (exInt == 121) {
				ex = new SimpleDateFormat("yyyyMMdd").format(new Date()) + "001";
			} else {
				ex = cd.getExpect().substring(0, 8) + (("" + exInt).length() == 3?"" + exInt:(("" + exInt).length() == 2?"0" + exInt:"00" + exInt));
			}
			// 如果是最后60期，得到冷号并插入返回map
			if (num > cds.size() - number) {
				String decade = null, theUnit = null;
				int decadeTemp = 0, theUnitTemp = 0;
				for (int i = 0; i < decades.length; i++) {
					if (decadeTemp < decades[i]) {
						decade = i + "";
						decadeTemp = decades[i];
					}
					if (theUnitTemp < theUnits[i]) {
						theUnit = i + "";
						theUnitTemp = theUnits[i];
					}
				}
				numList.add(decade);
				numList.add(theUnit);
				rMap.put(ex, numList);
			}
			num++;
		}
		return rMap;
	}
	
	
	/**
	 * 热水数字：取出最新的20条数据，得到开奖号后两位和期号后两位，存入库中，
	 * 并错位返回每一期后两位，重复则取反号。
	 */
	public Map<String, List<String>> getHotData() {
		List<CurrentData> cds =  currentDataMapper.queryLimitMore(61);//查询出最新的20条数据
		Map<String, String> map = new HashMap<String, String>();
		List<HotWaterDigital> hotList = new ArrayList<HotWaterDigital>();
		HotWaterDigital hotWaterDigital;
		Map<String, List<String>> numMap = new HashMap<>();
		List<String> list = null;
		for (CurrentData cd : cds) {
			list = new ArrayList<>();
			int len = cd.getExpect().length();
			hotWaterDigital = new HotWaterDigital();
			hotWaterDigital.setExpectTwoLast(cd.getExpect());
			String hao = cd.getOpenCode().replace(",", "");
			String haoa = hao.substring(3);
			String ex, num1, num2;
			num1 = haoa.substring(0, 1);
			num2 = haoa.substring(1);
			if (num1.equals(num2)) {
				num2 = String.valueOf(Integer.valueOf(num2) >= 5?Integer.valueOf(num2) - 5:Integer.valueOf(num2) + 5);
			}
			list.add(num1);
			list.add(num2);
			hotWaterDigital.setOpenCodeTwoLast(haoa);
			
			String temp = cd.getExpect().substring(8);
			Integer exEnd = Integer.valueOf(temp) + 1;
			ex = String.valueOf(exEnd);
			if (exEnd == 121) {
				ex = new SimpleDateFormat("yyyyMMdd").format(new Date()) + "001";
			} else {
				ex = cd.getExpect().substring(0, 8) + (ex.length() == 3?ex:(ex.length() == 2?"0" + ex:"00" + ex));
			}
			map.put(hotWaterDigital.getExpectTwoLast(), hotWaterDigital.getOpenCodeTwoLast());
			hotList.add(hotWaterDigital);
			numMap.put(ex, list);
		}
		hotWaterDigitalMapper.create(hotList);
		return numMap;
	}
	
	/**
	 * 定位：得到20期数据，每一期取前后一期的开奖号个位（前或后空了则没有该期），两数组合即为该期定位号
	 * 如两数个位相等，则再次往后取。
	 * @return 格式为 “期号-前一期个位，后一期个位”
	 */
	public Map<String, Map<String, List<String>>> positioning() {
		List<CurrentData> list = currentDataMapper.queryLimitMore(61);
		Map<String, Map<String, List<String>>> rMap = new HashMap<>();
		List<Map<String, Map<String, List<String>>>> rList = new ArrayList<>();
		int count = 0;
		for (CurrentData data : list) {
			if (count == list.size()-1) {
				continue;
			}
			String g, s, ex, newCode, oldCode;
			
			Map<String, List<String>> wMap = new HashMap<>();
			List<String> sList = new ArrayList<>();
			List<String> gList = new ArrayList<>();
			String newG = "", oldG = "", newS = "", oldS = "";
			newCode = data.getOpenCode().replace(",", "");
			oldCode = list.get(count + 1).getOpenCode().replace(",", "");
			newG = newCode.substring(4);
			oldG = oldCode.substring(4);
			newS = newCode.substring(3, 4);
			oldS = oldCode.substring(3, 4);
			int i = 0;
			while (newG.equals(oldG)) {
				oldG = list.get(count + 1 + i).getOpenCode().replace(",", "").substring(3, 4);
				i++;
			}
			i = 0;
			while (newS.equals(oldS)) {
				oldS = list.get(count + 1 + i).getOpenCode().replace(",", "").substring(4);
				i++;
			}
			
			String temp = list.get(count).getExpect().substring(8);
			Integer exEnd = Integer.valueOf(temp) + 1;
			ex = String.valueOf(exEnd);
			if (exEnd == 121) {
				ex = new SimpleDateFormat("yyyyMMdd").format(new Date()) + "001";
			} else {
				ex = list.get(count).getExpect().substring(0, 8) + (ex.length() == 3?ex:(ex.length() == 2?"0" + ex:"00" + ex));
			}
			sList.add(newS);
			sList.add(oldS);
			gList.add(newG);
			gList.add(oldG);
			wMap.put("theUnit", gList);
			wMap.put("decade", sList);
			rMap.put(ex, wMap);
//			rList.add(rMap);
			count++;
		}
		return rMap;
	}

	
	/**
	 * 一星区三个方案:
	 * 方案一：直接杀掉689
	 * 方案二： 直接杀掉123
	 * 方案三：直接杀掉579
	 * 每期再杀掉一个当期开奖号，这样每期就是杀掉四个号码，剩下的六个号码判断开奖号，如果四个数中有重复的就开对码
	 */
	public Map<String, Map<String, List<String>>> getStar() {
		List<CurrentData> cds = currentDataMapper.queryLimitMore(61);//查询出最新的五组数
		Map<String, Map<String, List<String>>> exMap = new HashMap<>();  // 存 key是期号的map
		Map<String, List<String>> planMap = null;  // 存 key是planX的map
		List<String> sList = null; // planMap的value
		String[] killStr = {"689", "123", "579"};
		for (CurrentData cd : cds) {
			planMap = new HashMap<>();
			String codeEndOne = cd.getOpenCode().replace(",", "").substring(4);
			String expect = cd.getExpect();
			String temp = expect.substring(8);;
			int exInt = Integer.valueOf(temp) + 1;
			if (exInt == 121) {
				expect = new SimpleDateFormat("yyyyMMdd").format(new Date()) + "001";
			} else {
				expect = expect.substring(0, 8) + ((exInt + "").length() == 3?exInt + "":((exInt + "").length() == 2?"0" + exInt:"00" + exInt));
			}
			for (int i = 0; i < 3; i++) {
				sList = new ArrayList<String>();
				String kill = killStr[i];
				if (kill.indexOf(codeEndOne) > -1) {
					int codeInt = Integer.valueOf(codeEndOne);
					codeInt = codeInt >= 5?codeInt - 5:codeInt + 5;
					kill += codeInt + "";
				} else {
					kill += codeEndOne;
				}
				for (int j = 0; j < 10; j++) {
					if (!(kill.indexOf(j + "") > -1)) {
						sList.add(j + "");
					}
				}
				planMap.put(i == 0?"plan1":(i == 1?"plan2":"plan3"), sList);
			}
			exMap.put(expect, planMap);
		}
		return exMap;
	}
	
	/**
	 * 一星区二星区复制：
	 * 取5期数据。从中得到冷号热号以及定位号，再加上1星区复制。除去得到的数据算出需要的数据并返回。
	 * @return
	 */
	public Map<String,Object> getTwoStarCopy() {
		List<CurrentData> cds =  currentDataMapper.queryLimitMore(5);//查询出最新的5条数据
		List<String> hotList = new ArrayList<>();
		List<String> icdList = new ArrayList<>();
		List<String> posiList = new ArrayList<>();
		Map<String,Object> rMap = new HashMap<>();
		StringBuffer sb = new StringBuffer();
		
		String g, s, ex, newCode, oldCode;
		int n = 1, xb = 0;
		String posList = null;
		
		int num1 = 0;
		
		// 1一星准备准备
		Map<String, Map<String, List<String>>> exMap = new HashMap<>();  // 存 key是期号的map
		Map<String, List<String>> planMap = null;  // 存 key是planX的map
		List<String> sList = null; // planMap的value
		String[] killStr = {"689", "123", "579"};
		
		for (CurrentData cd : cds) {
			if (num1 == 0) {
				planMap = new HashMap<>();
				String codeEndOne = cd.getOpenCode().replace(",", "").substring(4);
				String expect = cd.getExpect();
				String temp = expect.substring(8);;
				int exInt = Integer.valueOf(temp) + 1;
				expect = expect.replace(temp, (exInt + "").length() == 3?exInt + "":((exInt + "").length() == 2?"0" + exInt:"00" + exInt));
				for (int i = 0; i < 3; i++) {
					sList = new ArrayList<String>();
					String kill = killStr[i];
					if (kill.indexOf(codeEndOne) > -1) {
						int codeInt = Integer.valueOf(codeEndOne);
						codeInt = codeInt >= 5?codeInt - 5:codeInt + 5;
						kill += codeInt + "";
					} else {
						kill += codeEndOne;
					}
					for (int j = 0; j < 10; j++) {
						if (!(kill.indexOf(j + "") > -1)) {
							sList.add(j + "");
						}
					}
					planMap.put(i == 0?"plan1":(i == 1?"plan2":"plan3"), sList);
				}
				exMap.put(expect, planMap);
			}
			
			if (num1 == 0) {
				String hao = cd.getOpenCode().replace(",", "");
				String haoa = hao.substring(3);
				hotList = new ArrayList<>();
				for (int i = 0; i < 10; i++) {
					for (int j = 0; j < 10; j++) {
						if (!(haoa.indexOf(i + "") > -1) && !(haoa.indexOf(j + "") > -1)) {
							hotList.add(i + "" + j);
						}
					}
				}
			}
			
			sb.append(cd.getOpenCode().replace(",", "").substring(2, 4));
			
			num1++;
		}
		
		Map<String, List<String>> iceMap = getIcdData(2);
		String icdStr = "";
		for (Entry<String, List<String>> en : iceMap.entrySet()) {
			icdStr = en.getValue().toString();
		}
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (!(icdStr.indexOf(i + "") > -1 || icdStr.indexOf(j + "") > -1)) {
					icdList.add(i + "" + j);
				}
			}
		}
		newCode = cds.get(0).getOpenCode().replace(",", "");
		oldCode = cds.get(1).getOpenCode().replace(",", "");
		String newG = "", oldG = "", newS = "", oldS = "", g1, s1;
		newG = newCode.substring(4);
		oldG = oldCode.substring(4);
		newS = newCode.substring(3, 4);
		oldS = oldCode.substring(3, 4);
		int ii = 2;
		while (newG.equals(oldG)) {
			oldG = cds.get(ii).getOpenCode().replace(",", "");
			ii++;
		}
		ii = 2;
		while (newS.equals(oldS)) {
			oldS = cds.get(ii).getOpenCode().replace(",", "");
			ii++;
		}
		g1 = newG + oldG;
		s1 = newS + oldS;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (!(s1.indexOf(i + "") > -1) && !(g1.indexOf(j + "") > -1)) {
					posiList.add(i + "" + j);
				}
			}
		}
		rMap.put("star", exMap);
		rMap.put("icd", icdList);
		rMap.put("hot", hotList);
		rMap.put("position", posiList);
		return rMap;
	}
	
	/**
	 * 后三百位杀掉六期以上的号码，
	 * 剩下的号码为开奖号，每期杀掉四个，
	 * 如果杀的只有三个，就杀掉当期开奖号的本身，
	 * 如果有重复就取对数
	 * @return
	 */
	public Map<String, Map<String, List<String>>> endThree(int countNum) {
		List<CurrentData> cds =  currentDataMapper.queryLimitMore(countNum + 1);//查询出最新的66条数据
		List<CurrentData> cd = null;
		Map<String, List<String>> numMap = null;
		Map<String, Map<String, List<String>>> exMap = new HashMap<>();
		for (int count = 0; count < (cds.size() > 6?cds.size() - 6:1); count++) {
			cd = new ArrayList<>();
			numMap = new HashMap<>();
			String now = cds.get(count).getOpenCode().replace(",", "");
			for (int count2 = 0; count2 < (cds.size() > 6?6:cds.size()); count2++) {
				cd.add(cds.get(count2 + count));
			}
			String temp = cds.get(count).getExpect().substring(8);
			Integer exInt = Integer.valueOf(temp) + 1;
			String ex;
			if (exInt == 121) {
				ex = new SimpleDateFormat("yyyyMMdd").format(new Date()) + "001";
			} else {
				ex = cds.get(count).getExpect().substring(0, 8) + (("" + exInt).length() == 3?"" + exInt:(("" + exInt).length() == 2?"0" + exInt:"00" + exInt));
			}
			Set<Integer> gSet = new HashSet<>();
			Set<Integer> sSet = new HashSet<>();
			Set<Integer> bSet = new HashSet<>();
			List<String> gList = new ArrayList<>();
			List<String> sList = new ArrayList<>();
			List<String> bList = new ArrayList<>();
			for (CurrentData c : cd) {
				bSet.add(Integer.valueOf(c.getOpenCode().replace(",", "").substring(2, 3)));
				sSet.add(Integer.valueOf(c.getOpenCode().replace(",", "").substring(3, 4)));
				gSet.add(Integer.valueOf(c.getOpenCode().replace(",", "").substring(4)));
			}
			Set<Integer> tempSet = null;
			tempSet = new HashSet<>();
			for (int i = 0; i < 10; i++) {
				if (!gSet.contains(i) && tempSet.size() < 4) {
					tempSet.add(i);
				}
			}
			gSet = tempSet;
			tempSet = new HashSet<>();
			for (int i = 0; i < 10; i++) {
				if (!sSet.contains(i) && tempSet.size() < 4) {
					tempSet.add(i);
				}
			}
			sSet = tempSet;
			tempSet = new HashSet<>();
			for (int i = 0; i < 10; i++) {
				if (!bSet.contains(i) && tempSet.size() < 4) {
					tempSet.add(i);
				}
			}
			bSet = tempSet;
			int num = 0;
			if (gSet.size() < 4) {
				gSet.add(Integer.valueOf(now.substring(4)));
			}
			while (gSet.size() < 4) {
				Object[] g = gSet.toArray();
				int in = (Integer) g[num];
				gSet.add(in >= 5?in - 5: in + 5);
				num++;
			}
			num = 0;
			if (sSet.size() < 4) {
				gSet.add(Integer.valueOf(now.substring(3, 4)));
			}
			while (sSet.size() < 4) {
				Object[] s = sSet.toArray();
				int in = (Integer) s[num];
				sSet.add(in >= 5?in - 5: in + 5);
				num++;
			}
			num = 0;
			if (bSet.size() < 4) {
				gSet.add(Integer.valueOf(now.substring(2,3)));
			}
			while (bSet.size() < 4) {
				Object[] b = bSet.toArray();
				int in = (Integer) b[num];
				bSet.add(in >= 5?in - 5: in + 5);
				num++;
			}
			for (int j = 0; j < 10; j++) {
				if (!gSet.contains(j)) {
					gList.add(j + "");
				}
				if (!sSet.contains(j)) {
					sList.add(j + "");
				}
				if (!bSet.contains(j)) {
					bList.add(j + "");
				}
			}
			numMap.put("theUnit", gList);
			numMap.put("decade", sList);
			numMap.put("hundreds", bList);
			exMap.put(ex, numMap);
		}
		return exMap;
	}
}
