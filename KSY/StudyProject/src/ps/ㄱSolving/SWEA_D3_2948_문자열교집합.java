package ps.ㄱSolving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class SWEA_D3_2948_문자열교집합 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N, M;
			StringTokenizer st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			Set<String> set1 = new HashSet<>();
			Set<String> set2 = new HashSet<>(); //하나만 써도 됨
			
			st = new StringTokenizer(in.readLine());
			for (int i = 0; i < N; i++) {
				set1.add(st.nextToken());
			}
			st = new StringTokenizer(in.readLine());
			for (int i = 0; i < M; i++) {
				set2.add(st.nextToken());
			}
			
			//합침
			int n1 = set1.size();
			int n2 = set2.size();
			set1.addAll(set2);
			int n = set1.size();
			sb.append("#"+tc+" "+(n1+n2-n)+"\n");
		}
		System.out.println(sb);
	}
}
