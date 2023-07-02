package ps.ㄱSolving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S2_20413_MVP다이아몬드 {

	static int N;
	static int b, s, q, p, d;
	static String mvp;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		StringTokenizer st = new StringTokenizer(in.readLine());
		b = 0;
		s = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());
		p = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		
		mvp = in.readLine();
		
		//logic
		int total = 0;
		int twoMonth = 0;
		int newVal = 0;
		for (char c : mvp.toCharArray()) {
			switch(c) {
			case 'B': 
				newVal = s-1;
				twoMonth = newVal - twoMonth; //이전달 값을 저장
				break;
			case 'S':
				newVal = q-1;
				twoMonth = newVal - twoMonth; //이전달 값을 저장
				break;
			case 'G':
				newVal = p-1;
				twoMonth = newVal - twoMonth; //이전달 값을 저장
				break;
			case 'P':
				newVal = d-1;
				twoMonth = newVal - twoMonth; //이전달 값을 저장
				break;
			case 'D':
				newVal = d;
				twoMonth = newVal;
				break;
			}
			total += twoMonth;
			
//			System.out.println("grade: " + newVal + " pre: " + twoMonth);
//			System.out.println(total);
		}
		System.out.println(total);
	}
}
