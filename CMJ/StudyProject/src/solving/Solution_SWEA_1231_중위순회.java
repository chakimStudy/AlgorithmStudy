package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_SWEA_1231_중위순회 {
	static StringBuilder sb;
	static char[] tree;
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System. in));
		for(int test_case = 1; test_case <= 10; test_case++)
		{
			sb = new StringBuilder();
			N = Integer.parseInt(in.readLine());
			tree = new char[N + 1];
			for(int i = 0; i < N; i++) {
				String[] str = in.readLine().split(" ");
				tree[Integer.parseInt(str[0])] = str[1].charAt(0);
			}
			search(1, test_case);
			System.out.println("#" + test_case + " "  + sb);
		}
	}

	private static void search(int i, int t) {
		if(i > N) return;
		search(i * 2, t);
		sb.append(tree[i]);
		search(i * 2 + 1, t);
	}
}
