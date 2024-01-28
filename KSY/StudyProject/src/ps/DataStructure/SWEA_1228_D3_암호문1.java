package ps.DataStructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SWEA_1228_D3_암호문1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuffer sb = new StringBuffer();
		
		int tc = 1;
		while(tc <= 10) {
			sb.append("#" + tc + " ");
			
			//input
			int N = Integer.parseInt(sc.nextLine());
			List<Integer> code = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				code.add(sc.nextInt());
			}
			sc.nextLine();
			int cmdN = Integer.parseInt(sc.nextLine());
			while(cmdN-- > 0) {
				String str = sc.next();
				int idx = sc.nextInt();
				int numN = sc.nextInt();
				List<Integer> subLs = new ArrayList<>();
				for (int i = idx; i < idx + numN; i++) {
					subLs.add(sc.nextInt());
				}
				code.addAll(idx, subLs);
			}
			
			//print
			for (int i = 0; i < 10; i++) {
				sb.append(code.get(i)).append(" ");
			}
			
			sb.append("\n");
			tc++;
			sc.nextLine();
		}
		System.out.println(sb);
	}
	
}
