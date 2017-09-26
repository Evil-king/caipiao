package com.caipiao;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
	public static char[] getString(String s){
        
        return s.toCharArray();
    }
	public static void main(String[] args) {
		char[] cs=    getString("12345678");
        for (int i = 0; i < cs.length; i++) {
            System.out.print(cs[i]+",");
        }

//		boolean a =false;
//		boolean b =false;
//		boolean c =false;
//		
//		for(int i=0;i<=9;i++) {
//			if(i== 0 || i==5 || i==2 || i==7) {
//				a = true;
//			}else {
//				a = false;
//			}
//			for(int j =0 ;j<=9;j++) {
//				if(j== 2 || j==7 || j==3 || j==8) {
//					b = true;
//				}else {
//					b = false;
//				}
//				for(int k = 0;k<=9;k++ ) {
//					if(k== 1 || k==6 || k==4 || k==9) {
//						c = true;
//					}else {
//						c = false;
//					}
//					if(!(a && b && c) ){
//						
//						System.out.println(i+""+j+""+k);
//					}
//				}
//			}
//		}
//		rubbish("021");
//		zuhe();
	}
	
	/**
	 * 首尾差
	 * @return
	 */
	public List<String> sum(){
		List<String> list = new ArrayList<String>();
		for(int i =0 ; i<= 9;i++) {//百位
			for(int j =0;j<=9;j++) {//十位
				for(int k =0;k<=9;k++) {//个位
					if((i+k)%10==8) {
						System.out.println(i+""+j+""+k);
						list.add(i+""+j+""+k);
					}
				}
			}
		}
		return list;
	}
	/**
	 * 方程式定位
	 * @param str
	 * @return
	 */
	public static List<String> fangchengshi(String str){
		List<String> list = new ArrayList<String>();
		Map<Character,int[]> map = new HashMap<>();
		map.put('1',new int[]{1,4,7});
		map.put('2',new int[]{2,5,8});
		map.put('0',new int[]{0,3,6,9});
		int[] one = map.get(str.charAt(0));
		int[] two = map.get(str.charAt(1));
		int[] three = map.get(str.charAt(2));
		
		for(int i = 0;i<one.length;i++) {
			for(int j =0;j<two.length;j++) {
				for(int k=0;k<three.length;k++) {
					System.out.println(one[i]+""+two[j]+""+three[k]);
					list.add(one[i]+""+two[j]+""+three[k]);
				}
			}
		}
		return list;
	}
	/**
	 * 方程式定位
	 * @param nums
	 * @return
	 */
	public static List<String> fang(String nums) {
		List<String> list1 = new ArrayList<String>();
		// 定义固定数组并放入集合
				final int[] a = {0, 3, 6, 9};
				final int[] b = {1, 4, 7};
				final int[] c = {2, 5, 8};
				List<int[]> list = new ArrayList<int[]>();
				list.add(a);
				list.add(b);
				list.add(c);
				// 处理字符串并转换为数字放入顺序数组
				int[] orderArr = {Integer.parseInt(nums.charAt(0) + ""), Integer.parseInt(nums.charAt(1) + ""), Integer.parseInt(nums.charAt(2) + "")};
				List<int[]> orderList = new ArrayList<int[]>();
				// 根据顺序数组放入相应数组
				for (int i = 0; i < 3; i++) {
					orderList.add(list.get(orderArr[i]));
				}
				// 遍历打印所有情况
				for (int i = 0; i < orderList.get(0).length; i++) {
					int ii = orderList.get(0)[i];
					for (int j = 0; j < orderList.get(1).length; j++) {
						int jj = orderList.get(1)[j];
						for (int k = 0; k < orderList.get(2).length; k++) {
							int kk = orderList.get(2)[k];
							System.out.println(ii + "" + jj + "" + kk);
						}
					}
				}
			return list1;
	}
	
	/**
	 * 垃圾复式
	 * 百位 0527  十位 2738  个位 1649
	 * @return
	 */
	public static List<String> rubbish(String str){
		List<String> list = new ArrayList<String>();
//		for(int i=0;i<=9;i++) {
//			for(int j =0 ;j<=9;j++) {
//				for(int k = 0;k<=9;k++ ) {
////					if(!(a.contains(i) && b.contains(j) && c.contains(k))) {
//					if(!("0527".indexOf(i+"") > -1 && "2738".indexOf(j+"") > -1 && "1649".indexOf(k+"") > -1)) {
//						System.out.println(i+""+j+""+k);
//					}
//				}
//			}
//		}
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
		int[] one = map.get(str.charAt(0));
		int[] two = map.get(str.charAt(1));
		int[] three = map.get(str.charAt(2));
		
		
		//1、判断str是否是 百位-0526  十位-2738 个位-1649
		if(!("0527".indexOf(str.substring(0,1)) > -1 && "2738".indexOf(str.substring(1,2)) > -1 && "1649".indexOf(str.substring(2,3)) > -1)) {
		//2、根据 05-1649 16-2738 27-0549 38-0527 49-1638把str进行组合排序
			for(int i = 0;i<one.length;i++) {
				for(int j =0;j<two.length;j++) {
					for(int k=0;k<three.length;k++) {
						System.out.println(one[i]+""+two[j]+""+three[k]);
					}
				}
			}
			
		}else {
			System.out.println("不符合");
		}
		return list;
	}
	
	public static List<String> zuhe(){
		List<String> list = new ArrayList<String>();
		for(int i =0;i<=9;i++) {
			for(int j =0;j<=9;j++) {
				for(int k =0;k<=9;k++) {
					if(!("1469".indexOf(i+"") > -1 && "1469".indexOf(j+"") > -1 && "1469".indexOf(k+"") > -1)) {
						System.out.println(i+""+j+""+k);
					}
				}
			}
		}
		return list;
	}
}
