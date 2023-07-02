package ps.ㄱSolving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S3_11659_구간합구하기_fenwick {

	static int N, M;
	static int[] tree;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(in.readLine());
		tree = new int[N+1];
		for (int i = 0; i < N; i++) {
			//입력받으면서 펜윅트리 만들기
			int num = Integer.parseInt(in.readLine());
			update(i, num);
		}
	}
	private static void update(int i, int num) {
		while(i <= N) {
			
		}
	}
}
