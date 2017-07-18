package caipiao;

public class Test {
	
	public static void main(String[] args) {
		for(int i =0 ; i<= 9;i++) {//百位
			for(int j =0;j<=9;j++) {//十位
				for(int k =0;k<=9;k++) {//个位
					if((i+k)%10==8) {
						System.out.println(i+""+j+""+k);
					}
				}
			}
		}
	}
}
