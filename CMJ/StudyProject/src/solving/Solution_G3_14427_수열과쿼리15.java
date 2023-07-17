package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution_G3_14427_수열과쿼리15 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(in.readLine());
		String[] str = in.readLine().split(" ");
		
		int[] change_count = new int[N + 1];

		
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[0] == o2[0]) return o1[1] - o2[1];
				else return o1[0] - o2[0];
			}
		});

		for(int i = 0; i < N; i++) {
			int num =Integer.parseInt(str[i]); 
			pq.offer(new int[] {num, i + 1, 0});
			
			
			
		}
		
		
		int M = Integer.parseInt(in.readLine());
		for(int i = 0; i < M; i++) {
			String cmd = in.readLine();
			if(cmd.equals("2")) { // 2명령일 때
				int now[];
				while(true) {
					now = pq.peek();
					if(change_count[now[1]] == now[2]) {
						sb.append(now[1] + "\n");
						break;
					}else {
						pq.poll();
					}
				}
				
			}
			else { // 1명령일 때
				String[] cmd1 = cmd.split(" ");
				
				int num = Integer.parseInt(cmd1[2]);
				int index = Integer.parseInt(cmd1[1]);
				change_count[index]++;
				pq.offer(new int[] {num, index, change_count[index]});
			}
		}
		System.out.print(sb);
	}
}
