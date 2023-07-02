package ps.Simulation;

import java.util.Scanner;

public class BOJ_2563_S5_색종이 {

	static int N = 100;
	static boolean[][] pan;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int paperN = sc.nextInt();
		pan = new boolean[N][N];
		while(paperN-- > 0) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			
			//색종이 붙임
			for (int r = x; r < x + 10; r++) {
				for (int c = y; c < y + 10; c++) {
					pan[r][c] = true;
				}
			}
		}
		
		//true인 부분이 색종이가 붙은 부분
		int area = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (pan[r][c]) area++;
			}
		}
		System.out.println(area);
	}
}
