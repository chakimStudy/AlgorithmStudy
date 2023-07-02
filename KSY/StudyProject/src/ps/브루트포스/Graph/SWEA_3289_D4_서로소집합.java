package ps.브루트포스.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_3289_D4_서로소집합 {
	
	static int[] set;
	static int N, M;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		
		int T = Integer.parseInt(in.readLine());
		int tc = 1;
		while(T-- > 0) {
			sb.append("#").append(tc).append(" ");
			
			StringTokenizer st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			makeSet();
			
			while(M-- > 0) {
				st = new StringTokenizer(in.readLine());
				int cmd = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				switch(cmd) {
				case 0:
					union(a, b);
					break;
				case 1:
					sb.append(findSet(a) == findSet(b) ? 1 : 0);
					break;
				}
			}
			
			
			sb.append("\n");
			tc++;
		}
		System.out.println(sb);
	}
	private static int findSet(int a) {
		if (set[a] == a) return a;
		
		return set[a] = findSet(set[a]);
	}
	private static boolean union(int a, int b) {
		int aRep = findSet(a);
		int bRep = findSet(b);
		if (aRep == bRep) {
			return false;
		}
		
		set[bRep] = aRep;
		return true;
	}
	private static void makeSet() {
		set = new int[N+1]; //1~N
		
		for (int i = 1; i <= N; i++) {
			set[i] = i;
		}
	}
}
