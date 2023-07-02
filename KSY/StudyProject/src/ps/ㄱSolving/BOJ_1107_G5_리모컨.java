package ps.ㄱSolving;

import java.util.Scanner;

public class BOJ_1107_G5_리모컨 {

	static int N;
	static String Nstr;
	static int[] broken;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		//input
		N = sc.nextInt();
		Nstr = Integer.toString(N);
		
		int brokenN = sc.nextInt();
		broken = new int[brokenN];
		for (int i = 0; i < brokenN; i++) {
			broken[i] = sc.nextInt();
		}
		
		makeNum(0);
	}
	private static void makeNum(int len) {
		
		for (int i = 0; i <= 9; i++) {
			
			if(Nstr.charAt(len) - '0' == i) {
				
			}
		}
	}
}


/*
 * 버튼 0~9, +, -
 * 
 * 채널 0~무한
 * 
 * 현재 100 -> N
 * 눌러야 하는 횟수
 * 
 * 음... 숫자를 통해 최대한 N에 가까이 가서 +, -로 이동?
 * 고장나지 않은 숫자를 이용해 N을 만들 수 있는가?
 * 재귀를 통해서 .......
 */