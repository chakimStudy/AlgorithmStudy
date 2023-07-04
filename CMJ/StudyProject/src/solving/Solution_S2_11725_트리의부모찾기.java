package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Solution_S2_11725_트리의부모찾기 {
	static int array[];
	static boolean visited[];
	static List<List<Integer>> adj_list;
			
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(in.readLine());
		adj_list = new ArrayList<>();
		for(int i = 0; i < N + 1; i++) {
			adj_list.add(new ArrayList<>());
		}
		
		for(int i = 0; i < N - 1; i++) {
			 String[] str = in.readLine().split(" ");
			 int start = Integer.parseInt(str[0]);
			 int end = Integer.parseInt(str[1]);
			 adj_list.get(start).add(end);
			 adj_list.get(end).add(start);
		}
		array = new int[N + 1];
		visited = new boolean[N + 1];
		
		dfs(1);
		
		for(int i = 2; i < N + 1; i++) {
			sb.append(array[i] + "\n");
		}
		System.out.println(sb);
	}

	private static void dfs(int now) {
		for(int vertex : adj_list.get(now)) {
			if(!visited[vertex]) {
				array[vertex] = now;
				visited[vertex] = true;
				dfs(vertex);
			}
		}
	}
}
