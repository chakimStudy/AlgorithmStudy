package ps.브루트포스.sequence;

import java.util.Scanner;

public class BOJ_1476_S5_날짜계산 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int e, s, m;
		e = sc.nextInt();
		s = sc.nextInt();
		m = sc.nextInt();
		
		int cnt = 0;
		while(true) {
			e -= 1;
			s -= 1;
			m -= 1;
			cnt++;
			
			if (e == 0 && s == 0 && m == 0) break;
			
			if (e == 0) e = 15;
			if (s == 0) s = 28;
			if (m == 0) m = 19;
		}
		System.out.println(cnt);
	}
}
