package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

public class Solution_S2_1260_DFS와BFS {
	
	static boolean[] visited;
	static List<List<Integer>> list;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] str = in.readLine().split(" ");
		
		int N = Integer.parseInt(str[0]);
		int M = Integer.parseInt(str[1]);
		int V = Integer.parseInt(str[2]);
		
		list = new ArrayList<>();
		for(int i = 0; i <= N; i++) {
			list.add(new ArrayList<>());
		}
		
		for(int i = 0; i < M; i++) {
			str = in.readLine().split(" ");
			int start = Integer.parseInt(str[0]);
			int end = Integer.parseInt(str[1]);
			list.get(start).add(end);
			list.get(end).add(start);
		}
		
		for(int i = 0; i <= N; i++) {
			Collections.sort(list.get(i));
		}
		
		visited = new boolean[N + 1];
		
		dfs(V);
		System.out.println();
		visited = new boolean[N + 1];
		
		bfs(V);
		
	}

	private static void bfs(int v) {
		System.out.print(v + " ");

		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(v);
		visited[v] = true;
		while(!queue.isEmpty()) {
			int vertex = queue.poll();
			for(int asd: list.get(vertex)) {
				if(!visited[asd]) {
					queue.offer(asd);
					System.out.print(asd + " ");
					visited[asd] = true;
				}
			}
		}
	}

	private static void dfs(int v) {
		System.out.print(v + " ");
		visited[v] = true;
		for(int vertex: list.get(v)) {
			if(!visited[vertex]) {
				visited[vertex] = true;
				dfs(vertex);
			}
		}
	}
}
