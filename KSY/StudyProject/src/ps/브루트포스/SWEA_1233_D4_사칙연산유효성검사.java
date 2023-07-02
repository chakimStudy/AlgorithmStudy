package ps.브루트포스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_1233_D4_사칙연산유효성검사 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		
		int tc = 1;
		while(tc <= 10) {
			sb.append("#" + tc + " ");
			boolean isExpression = true;
			
			//input
			int N = Integer.parseInt(in.readLine());
			
			for (int i = 1; i <= N; i++) {
				String[] line = in.readLine().split(" ");
				
				if (line.length == 2) { //left 노드. 숫자여야 함.
					if (line[1].charAt(0) - '0' < 0 && line[1].charAt(0) - '0' > 9) {
						isExpression = false;
					}
				}
				else { //문자여야 함
					if (line[1].charAt(0) - '0' >= 0 && line[1].charAt(0) - '0' <= 9) {
						isExpression = false;
					}
				}
			}
			sb.append((isExpression)? 1 : 0).append("\n");
			
			tc++;
		}
		System.out.println(sb);
	}
}
