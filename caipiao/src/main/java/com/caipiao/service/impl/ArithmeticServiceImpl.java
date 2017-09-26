package com.caipiao.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caipiao.dao.CurrentDataMapper;
import com.caipiao.pojo.CurrentData;
import com.caipiao.service.ArithmeticService;
@Service
public class ArithmeticServiceImpl implements ArithmeticService{
	@Autowired
	private CurrentDataMapper currentDataMapper;
	/**
	 * 算法
	 */
	public Map<String, Map<String, List<String>>> primacy() {
		List<CurrentData> cds = currentDataMapper.queryLimitMore(2);//输出2
		Map<String, Map<String, List<String>>> bestMap = new HashMap<>();
		Map<String, List<String>> bigMap = null;
		for (CurrentData cd : cds) {
			String opencode = cd.getOpenCode().replace(",", "");
			List<String> list = new ArrayList<>();
			list.add(opencode.substring(0,3));
			list.add(opencode.substring(1, 4));
			list.add(opencode.substring(2,5));
			List<String> arrayList = null;
			bigMap = new HashMap<>();
			List<String> one1 = new ArrayList<>();
			List<String> two1 = new ArrayList<>();
			List<String> three1 = new ArrayList<>();
//			Map<String, List<String>> hashMap = null;
			Set<Integer> numSet1 = new HashSet<>();
			Set<Integer> numSet2 = new HashSet<>();
			Set<Integer> numSet3 = new HashSet<>();
			List<Set<Integer>> bigSet = new ArrayList<>();
			int num;
			//首尾和
//			hashMap = new HashMap<>();
			num = 0;
			for(String li : list) {
				arrayList = new ArrayList<String>();
				for(int i=0;i<=9;i++) { //百位
					for(int j=0;j<=9;j++) { //十位
						for(int k=0;k<=9;k++) { //个位
							if(((i + k) %10 == (Integer.parseInt(li.substring(0,1))+Integer.parseInt(li.substring(2,3))) % 10)) {
								arrayList.add(i+""+j+""+k);
								if(num == 0) {
									numSet1.add(Integer.valueOf(i+""+j+""+k));
								}else if(num ==1) {
									numSet2.add(Integer.valueOf(i+""+j+""+k));
								}else {
									numSet3.add(Integer.valueOf(i+""+j+""+k));
								}
							}
						}
					}
				}
				
				num++;
			}
			//方程式
			num = 0;
			Map<Character,int[]> map = new HashMap<>();
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
			for(String str : list) {
				int[] one = map.get(str.charAt(0));
				int[] two = map.get(str.charAt(1));
				int[] three = map.get(str.charAt(2));
				arrayList = new ArrayList<String>();
				for(int i = 0;i<one.length;i++) {
					for(int j =0;j<two.length;j++) {
						for(int k=0;k<three.length;k++) {
//							if ((one.toString().indexOf("" + i) > - 1 && two.toString().indexOf("" + j) > - 1 && three.toString().indexOf("" + k) > - 1)) {
								arrayList.add(one[i]+""+two[j]+""+three[k]);
								if(num == 0) {
									numSet1.add(Integer.valueOf(one[i]+""+two[j]+""+three[k]));
								}else if(num ==1) {
									numSet2.add(Integer.valueOf(one[i]+""+two[j]+""+three[k]));
								}else {
									numSet3.add(Integer.valueOf(one[i]+""+two[j]+""+three[k]));
								}
//							}
						}
					}
				}
				num++;
			}
			//胆组
			StringBuffer sb = new StringBuffer();
				Set<Integer> set = new HashSet<Integer>();
				num = 0;
				for (String str : list) {
					arrayList = new ArrayList<String>();
					for (int i = 0; i < str.length(); i++) {
						set.add(Integer.valueOf(String.valueOf(str.charAt(i))));
					}
					for (Integer t : set) {
						sb.append(t<5?t + 5:t - 5);
						sb.append(t);
					}
					for(int i = 0;i <= 9;i++) {
						for(int j = 0; j <=9 ; j++) {
							for(int k = 0; k <= 9; k++) {
								if (sb.length() == 4) {
									if (((sb.indexOf(i + "") > -1  && sb.indexOf(j + "") > -1 && sb.indexOf(k + "") > -1) && ((sb.indexOf(i + "") == sb.indexOf(j + "")) || (sb.indexOf(j + "") == sb.indexOf(k + "")) || (sb.indexOf(i + "") == sb.indexOf(k + ""))))) {
										arrayList.add(i + "" + j + "" + k);
										if(num == 0) {
											numSet1.add(Integer.valueOf(i+""+j+""+k));
										}else if(num ==1) {
											numSet2.add(Integer.valueOf(i+""+j+""+k));
										}else {
											numSet3.add(Integer.valueOf(i+""+j+""+k));
										}
									}
								} else if (sb.length() == 6) {
									if (((sb.indexOf(i + "") > -1  && sb.indexOf(j + "") > -1 && sb.indexOf(k + "") > -1) && (sb.indexOf(i + "") > -1  || sb.indexOf(j + "") > -1 || sb.indexOf(k + "") > -1))) {
										arrayList.add(i + "" + j + "" + k);
										if(num == 0) {
											numSet1.add(Integer.valueOf(i+""+j+""+k));
										}else if(num ==1) {
											numSet2.add(Integer.valueOf(i+""+j+""+k));
										}else {
											numSet3.add(Integer.valueOf(i+""+j+""+k));
										}
									}
								}
							}
						}
					}
					sb = new StringBuffer();
					set = new HashSet<Integer>();
					num++;
				}
			
//			//垃圾复式
			num = 0;
			Map<Character,int[]> mapRubbish = new HashMap<>();
			mapRubbish.put('0',new int[]{1,6,4,9});
			mapRubbish.put('5',new int[]{1,6,4,9});
			mapRubbish.put('1',new int[]{2,7,3,8});
			mapRubbish.put('6',new int[]{2,7,3,8});
			mapRubbish.put('2',new int[]{0,5,4,9});
			mapRubbish.put('7',new int[]{0,5,4,9});
			mapRubbish.put('3',new int[]{0,5,2,7});
			mapRubbish.put('8',new int[]{0,5,2,7});
			mapRubbish.put('4',new int[]{1,6,3,8});
			mapRubbish.put('9',new int[]{1,6,3,8});
			for(String li : list) {
				arrayList = new ArrayList<String>();
				int[] one = mapRubbish.get(li.charAt(0));
				int[] two = mapRubbish.get(li.charAt(1));
				int[] three = mapRubbish.get(li.charAt(2));
				//1、判断str是否是 百位-0526  十位-2738 个位-1649
				if(!("0527".indexOf(li.substring(0,1)) > -1 && "2738".indexOf(li.substring(1,2)) > -1 && "1649".indexOf(li.substring(2,3)) > -1)) {
					//2、根据 05-1649 16-2738 27-0549 38-0527 49-1638把str进行组合排序
					for(int i = 0;i<one.length;i++) {
						for(int j =0;j<two.length;j++) {
							for(int k=0;k<three.length;k++) {
								arrayList.add(one[i]+""+two[j]+""+three[k]);
								if(num == 0) {
									numSet1.add(Integer.valueOf(i+""+j+""+k));
								}else if(num ==1) {
									numSet2.add(Integer.valueOf(i+""+j+""+k));
								}else {
									numSet3.add(Integer.valueOf(i+""+j+""+k));
								}
							}
						}
					}
					
				}
				num++;
			}
			bigSet.add(numSet1);
			bigSet.add(numSet2);
			bigSet.add(numSet3);
			int nums = 0;
			for (Set<Integer> sets : bigSet) {
				for (Integer i = 0; i < 999; i++) {
					if (!sets.contains(i)) {
						String iStr = (i + "").length() == 3?i + "":((i + "").length() == 2?"0" + i + "":"00" + i + "");
						if(nums == 0) {
							one1.add(iStr);
						}else if(nums ==1) {
							two1.add(iStr);
						}else {
							three1.add(iStr);
						}
					}
				}
				nums++;
			}
			bigMap.put("one", one1);
			bigMap.put("two", two1);
			bigMap.put("three", three1);
			bestMap.put(bestMap.size() == 0?"Front":"After", bigMap);
		}
		return bestMap;
	}
	
	/**
	 * 四个按钮
	 * @return
	 */
	public Map<String, Map<String, List<String>>> primacy2() {
		CurrentData cd = currentDataMapper.queryToDataOne(1);//输出三个
		String opencode = cd.getOpenCode().replace(",", "");
		List<String> list = new ArrayList<>();
		list.add(opencode.substring(0,3));
		list.add(opencode.substring(1, 4));
		list.add(opencode.substring(2,5));
		List<String> arrayList = null;
		Map<String, List<String>> hashMap = null;
		Map<String, Map<String, List<String>>> bigMap = new HashMap<>();
		int num;
		//首尾和
		hashMap = new HashMap<>();
		num = 0;
		for(String li : list) {
			arrayList = new ArrayList<String>();
			for(int i=0;i<=9;i++) { //百位
				for(int j=0;j<=9;j++) { //十位
					for(int k=0;k<=9;k++) { //个位
						if(!((i + k) %10 == (Integer.parseInt(li.substring(0,1))+Integer.parseInt(li.substring(2,3))) % 10)) {
							arrayList.add(i+""+j+""+k);
						}
					}
				}
			}
			hashMap.put(num == 0?"one":(num == 1?"two":"three"), arrayList);
			num++;
		}
		bigMap.put("foreAndAft", hashMap);
		//方程式
		hashMap = new HashMap<>();
		num = 0;
		Map<Character,int[]> map = new HashMap<>();
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
		for(String str : list) {
			int[] one = map.get(str.charAt(0));
			int[] two = map.get(str.charAt(1));
			int[] three = map.get(str.charAt(2));
			arrayList = new ArrayList<String>();
			for(int i = 0;i<=9;i++) {
				for(int j =0;j<=9;j++) {
					for(int k=0;k<=9;k++) {
						if (!(one.toString().indexOf("" + i) > - 1 && two.toString().indexOf("" + j) > - 1 && three.toString().indexOf("" + k) > - 1)) {
							arrayList.add(i+""+j+""+k);
						}
//						arrayList.add(one[i]+""+two[j]+""+three[k]);
					}
				}
			}
			hashMap.put(num == 0?"one":(num == 1?"two":"three"), arrayList);
			num++;
		}
		bigMap.put("equation", hashMap);
		//胆组
		StringBuffer sb = new StringBuffer();
		Set<Integer> set = new HashSet<Integer>();
		hashMap = new HashMap<>();
		num = 0;
		for (String str : list) {
			arrayList = new ArrayList<String>();
			for (int i = 0; i < str.length(); i++) {
				set.add(Integer.valueOf(String.valueOf(str.charAt(i))));
			}
			for (Integer t : set) {
				sb.append(t<5?t + 5:t - 5);
				sb.append(t);
			}
			for(int i = 0;i <= 9;i++) {
				for(int j = 0; j <=9 ; j++) {
					for(int k = 0; k <= 9; k++) {
						if (sb.length() == 4) {
							if (!(sb.indexOf(i + "") > -1  && sb.indexOf(j + "") > -1 && sb.indexOf(k + "") > -1) && ((sb.indexOf(i + "") == sb.indexOf(j + "")) || (sb.indexOf(j + "") == sb.indexOf(k + "")) || (sb.indexOf(i + "") == sb.indexOf(k + "")))) {
								arrayList.add(i + "" + j + "" + k);
							}
						} else if (sb.length() == 6) {
							if (!(sb.indexOf(i + "") > -1  && sb.indexOf(j + "") > -1 && sb.indexOf(k + "") > -1) && (sb.indexOf(i + "") > -1  || sb.indexOf(j + "") > -1 || sb.indexOf(k + "") > -1)) {
								arrayList.add(i + "" + j + "" + k);
							}
						}
					}
				}
			}
			sb = new StringBuffer();
			set = new HashSet<Integer>();
			hashMap.put(num == 0?"one":(num == 1?"two":"three"), arrayList);
			num++;
		}
		bigMap.put("bile", hashMap);
		
//		//垃圾复式
		hashMap = new HashMap<>();
		num = 0;
		Map<Character,int[]> mapRubbish = new HashMap<>();
		mapRubbish.put('0',new int[]{1,6,4,9});
		mapRubbish.put('5',new int[]{1,6,4,9});
		mapRubbish.put('1',new int[]{2,7,3,8});
		mapRubbish.put('6',new int[]{2,7,3,8});
		mapRubbish.put('2',new int[]{0,5,4,9});
		mapRubbish.put('7',new int[]{0,5,4,9});
		mapRubbish.put('3',new int[]{0,5,2,7});
		mapRubbish.put('8',new int[]{0,5,2,7});
		mapRubbish.put('4',new int[]{1,6,3,8});
		mapRubbish.put('9',new int[]{1,6,3,8});
		for(String li : list) {
			arrayList = new ArrayList<String>();
			int[] one = mapRubbish.get(li.charAt(0));
			int[] two = mapRubbish.get(li.charAt(1));
			int[] three = mapRubbish.get(li.charAt(2));
			//1、判断str是否是 百位-0526  十位-2738 个位-1649
			if(("0527".indexOf(li.substring(0,1)) > -1 && "2738".indexOf(li.substring(1,2)) > -1 && "1649".indexOf(li.substring(2,3)) > -1)) {
				//2、根据 05-1649 16-2738 27-0549 38-0527 49-1638把str进行组合排序
				for(int i = 0;i<one.length;i++) {
					for(int j =0;j<two.length;j++) {
						for(int k=0;k<three.length;k++) {
							arrayList.add(one[i]+""+two[j]+""+three[k]);
						}
					}
				}
				
			}
			hashMap.put(num == 0?"one":(num == 1?"two":"three"), arrayList);
			num++;
		}
		bigMap.put("garbage ", hashMap);
		return bigMap;
	}
	
	
	/**
	 * 带方法标示
	 * @param type
	 * @return
	 */
	public Map<String, Map<String, List<String>>> primacyParam(String[] type) {
		Map<String, Map<String, List<String>>> map = primacy2();
		String typeStr = "foreAndAft equation bile garbage";
		String temp;
		for(int i=0;i<type.length;i++) {
			temp = type[i];
			if(typeStr.indexOf(temp) > -1) {
				map.remove(temp);
			}
		}
		return map;
	}

	/**
	 * 不带方法标示
	 * @param type
	 * @return
	 */
	public Map<String, List<String>> primacyParamNo(String[] type) {
		Map<String, Map<String, List<String>>> map = primacy2();
		String typeStr = "foreAndAft equation bile garbage";
		String temp;
		for(int i=0;i<type.length;i++) {
			temp = type[i];
			if(typeStr.indexOf(temp) > -1) {
				map.remove(temp);
			}
		}
		List<String> list1 = new ArrayList<>();
		List<String> list2 = new ArrayList<>();
		List<String> list3 = new ArrayList<>();
		Map<String, List<String>> rMap = new HashMap<>();
		String name;
		Map<String, List<String>> sMap;
		for (Entry<String, Map<String, List<String>>> entry : map.entrySet()) {
			name = entry.getKey();
			sMap = entry.getValue();
			for (Entry<String, List<String>> en : sMap.entrySet()) {
				switch (en.getKey()) {
				case "one":
					list1.addAll(en.getValue());
					break;
				case "two":
					list2.addAll(en.getValue());
					break;
				case "three":
					list3.addAll(en.getValue());
					break;
				}
			}
		}
		rMap.put("one", list1);
		rMap.put("two", list2);
		rMap.put("three", list3);
		return rMap;
	}
	
	/**
	 * 判断所有前三 中三 后三是否中奖
	 * @return
	 */
	public Map<String, Map<String, String>> getPrize() {
		List<CurrentData> list = currentDataMapper.queryLimitMore(61);
		Map<String, Map<String, String>> rMap = new HashMap<>();
		int count = 0;
		for (CurrentData data : list) {
			if (count == 60) {
				continue;
			}
			CurrentData old = list.get(count + 1);
			String code = data.getOpenCode().replace(",", "");
			String[] numStr = {code.substring(0, 3), code.substring(1, 4), code.substring(2)};
			Map<String, List<String>> codeMap = codePrimacy(old.getOpenCode());
			Map<String, String> numMap = new HashMap<>();
			String ex = data.getExpect();
			int i = 0;
			for (Entry<String, List<String>> en : codeMap.entrySet()) {
				String num = (i == 0?"one":(i == 1?"two":"three"));
				List<String> numList = (List<String>) en.getValue();
				if (numList.toString().indexOf(numStr[i]) > -1) {
					numMap.put(num, "1");
				} else {
					numMap.put(num, "0");
				}
				i++;
			}
			rMap.put(ex, numMap);
			count++;
		}
		return rMap;
	}
	
	/**
	 * 有用的方法
	 * @param code
	 * @return
	 */
	public Map<String, List<String>> codePrimacy(String code) {
		Map<String, List<String>> bigMap = null;
			String opencode = code.replace(",", "");
			List<String> list = new ArrayList<>();
			list.add(opencode.substring(0,3));
			list.add(opencode.substring(1, 4));
			list.add(opencode.substring(2,5));
			List<String> arrayList = null;
			bigMap = new HashMap<>();
			List<String> one1 = new ArrayList<>();
			List<String> two1 = new ArrayList<>();
			List<String> three1 = new ArrayList<>();
			Set<Integer> numSet1 = new HashSet<>();
			Set<Integer> numSet2 = new HashSet<>();
			Set<Integer> numSet3 = new HashSet<>();
			List<Set<Integer>> bigSet = new ArrayList<>();
			int num;
			//首尾和
			num = 0;
			for(String li : list) {
				arrayList = new ArrayList<String>();
				for(int i=0;i<=9;i++) { //百位
					for(int j=0;j<=9;j++) { //十位
						for(int k=0;k<=9;k++) { //个位
							if(((i + k) %10 == (Integer.parseInt(li.substring(0,1))+Integer.parseInt(li.substring(2,3))) % 10)) {
								arrayList.add(i+""+j+""+k);
								if(num == 0) {
									numSet1.add(Integer.valueOf(i+""+j+""+k));
								}else if(num ==1) {
									numSet2.add(Integer.valueOf(i+""+j+""+k));
								}else {
									numSet3.add(Integer.valueOf(i+""+j+""+k));
								}
							}
						}
					}
				}
				num++;
			}
			//方程式
			num = 0;
			Map<Character,int[]> map = new HashMap<>();
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
			for(String str : list) {
				int[] one = map.get(str.charAt(0));
				int[] two = map.get(str.charAt(1));
				int[] three = map.get(str.charAt(2));
				arrayList = new ArrayList<String>();
				for(int i = 0;i<one.length;i++) {
					for(int j =0;j<two.length;j++) {
						for(int k=0;k<three.length;k++) {
							arrayList.add(one[i]+""+two[j]+""+three[k]);
							if(num == 0) {
								numSet1.add(Integer.valueOf(one[i]+""+two[j]+""+three[k]));
							}else if(num ==1) {
								numSet2.add(Integer.valueOf(one[i]+""+two[j]+""+three[k]));
							}else {
								numSet3.add(Integer.valueOf(one[i]+""+two[j]+""+three[k]));
							}
//							}
						}
					}
				}
				num++;
			}
			//胆组
			StringBuffer sb = new StringBuffer();
			Set<Integer> set = new HashSet<Integer>();
//				hashMap = new HashMap<>();
			num = 0;
			for (String str : list) {
				arrayList = new ArrayList<String>();
				for (int i = 0; i < str.length(); i++) {
					set.add(Integer.valueOf(String.valueOf(str.charAt(i))));
				}
				for (Integer t : set) {
					sb.append(t<5?t + 5:t - 5);
					sb.append(t);
				}
				for(int i = 0;i <= 9;i++) {
					for(int j = 0; j <=9 ; j++) {
						for(int k = 0; k <= 9; k++) {
							if (sb.length() == 4) {
								if (((sb.indexOf(i + "") > -1  && sb.indexOf(j + "") > -1 && sb.indexOf(k + "") > -1) && ((sb.indexOf(i + "") == sb.indexOf(j + "")) || (sb.indexOf(j + "") == sb.indexOf(k + "")) || (sb.indexOf(i + "") == sb.indexOf(k + ""))))) {
									arrayList.add(i + "" + j + "" + k);
									if(num == 0) {
										numSet1.add(Integer.valueOf(i+""+j+""+k));
									}else if(num ==1) {
										numSet2.add(Integer.valueOf(i+""+j+""+k));
									}else {
										numSet3.add(Integer.valueOf(i+""+j+""+k));
									}
								}
							} else if (sb.length() == 6) {
								if (((sb.indexOf(i + "") > -1  && sb.indexOf(j + "") > -1 && sb.indexOf(k + "") > -1) && (sb.indexOf(i + "") > -1  || sb.indexOf(j + "") > -1 || sb.indexOf(k + "") > -1))) {
									arrayList.add(i + "" + j + "" + k);
									if(num == 0) {
										numSet1.add(Integer.valueOf(i+""+j+""+k));
									}else if(num ==1) {
										numSet2.add(Integer.valueOf(i+""+j+""+k));
									}else {
										numSet3.add(Integer.valueOf(i+""+j+""+k));
									}
								}
							}
						}
					}
				}
				sb = new StringBuffer();
				set = new HashSet<Integer>();
				num++;
			}
			
//			//垃圾复式
			num = 0;
			Map<Character,int[]> mapRubbish = new HashMap<>();
			mapRubbish.put('0',new int[]{1,6,4,9});
			mapRubbish.put('5',new int[]{1,6,4,9});
			mapRubbish.put('1',new int[]{2,7,3,8});
			mapRubbish.put('6',new int[]{2,7,3,8});
			mapRubbish.put('2',new int[]{0,5,4,9});
			mapRubbish.put('7',new int[]{0,5,4,9});
			mapRubbish.put('3',new int[]{0,5,2,7});
			mapRubbish.put('8',new int[]{0,5,2,7});
			mapRubbish.put('4',new int[]{1,6,3,8});
			mapRubbish.put('9',new int[]{1,6,3,8});
			for(String li : list) {
				arrayList = new ArrayList<String>();
				int[] one = mapRubbish.get(li.charAt(0));
				int[] two = mapRubbish.get(li.charAt(1));
				int[] three = mapRubbish.get(li.charAt(2));
				//1、判断str是否是 百位-0526  十位-2738 个位-1649
				if(!("0527".indexOf(li.substring(0,1)) > -1 && "2738".indexOf(li.substring(1,2)) > -1 && "1649".indexOf(li.substring(2,3)) > -1)) {
					//2、根据 05-1649 16-2738 27-0549 38-0527 49-1638把str进行组合排序
					for(int i = 0;i<one.length;i++) {
						for(int j =0;j<two.length;j++) {
							for(int k=0;k<three.length;k++) {
								arrayList.add(one[i]+""+two[j]+""+three[k]);
								if(num == 0) {
									numSet1.add(Integer.valueOf(i+""+j+""+k));
								}else if(num ==1) {
									numSet2.add(Integer.valueOf(i+""+j+""+k));
								}else {
									numSet3.add(Integer.valueOf(i+""+j+""+k));
								}
							}
						}
					}
					
				}
				num++;
			}
			bigSet.add(numSet1);
			bigSet.add(numSet2);
			bigSet.add(numSet3);
			int nums = 0;
			for (Set<Integer> sets : bigSet) {
				for (Integer i = 0; i < 999; i++) {
					if (!sets.contains(i)) {
						String iStr = (i + "").length() == 3?i + "":((i + "").length() == 2?"0" + i + "":"00" + i + "");
						if(nums == 0) {
							one1.add(iStr);
						}else if(nums ==1) {
							two1.add(iStr);
						}else {
							three1.add(iStr);
						}
					}
				}
				nums++;
			}
			bigMap.put("one", one1);
			bigMap.put("two", two1);
			bigMap.put("three", three1);
		return bigMap;
	}
	
	/**
	 * 通过参数查询前三 中三 后三的值
	 * @param type
	 * @param openCode
	 * @return
	 */
	public List<String> getPrizePrarm(String type, String openCode) {
		Map<String, List<String>> map = codePrimacy(openCode);
		return map.get(type);
	}
	
}
