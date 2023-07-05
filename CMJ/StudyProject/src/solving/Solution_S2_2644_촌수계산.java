package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Solution_S2_2644_촌수계산 {
	static List<List<Integer>> adj_list;
	static boolean visited[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(in.readLine());
		String[] str = in.readLine().split(" ");
		int start = Integer.parseInt(str[0]);
		int end = Integer.parseInt(str[1]);
		int M = Integer.parseInt(in.readLine());
		adj_list = new ArrayList<>();
		for(int i = 0; i <= N; i++) {
			adj_list.add(new ArrayList<Integer>());
		}
		visited = new boolean[N + 1];
		for(int i = 0; i < M; i++) {
			str = in.readLine().split(" ");
			int a = Integer.parseInt(str[0]);
			int b = Integer.parseInt(str[1]);
			adj_list.get(a).add(b);
			adj_list.get(b).add(a);
		}
		dfs(start, end, 0);
		System.out.println(-1);
		
	}

	private static void dfs(int start, int end, int distance) {
		visited[start] = true;
		for(int vertex : adj_list.get(start)) {
			if(vertex == end) {
				System.out.println(distance + 1);
				System.exit(0);
			}
			if(!visited[vertex]) {
				dfs(vertex, end, distance + 1);
			}
		}
	}
}
