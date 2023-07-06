package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Solution_G3_2252_줄세우기 {
	
	static List<List<Integer>> adj_list;
	
	static int N;
	
	public static void main(String[] args) throws IOException {
		//위상정렬ㅇ
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		adj_list = new ArrayList<List<Integer>>();
				
		String[] str = in.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		int M = Integer.parseInt(str[1]);

		int[] enterCount = new int[N + 1];
		
		for(int i = 0; i < N + 1; i++) {
			adj_list.add(new ArrayList<Integer>());
		}
				
		for(int i = 0; i < M; i++) {
			str = in.readLine().split(" ");
			int start = Integer.parseInt(str[0]);
			int end = Integer.parseInt(str[1]);
			adj_list.get(start).add(end);
			enterCount[end]++;
		}
		
		Queue<Integer> queue = new ArrayDeque<Integer>();

		for(int i = 1; i < N + 1; i++) {
			if(enterCount[i] == 0) {
				queue.offer(i);
			}
		}
		
		while(!queue.isEmpty()) {
			int now = queue.poll();
			sb.append(now).append(" ");
			for(int vertex : adj_list.get(now)) {
				enterCount[vertex] --;
				if(enterCount[vertex] == 0)
				queue.offer(vertex);				
			}
		}
		System.out.print(sb);
	}


}
