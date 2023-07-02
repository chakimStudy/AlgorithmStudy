package ps.브루트포스.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BOJ_1325_S1_효율적인해킹 {
	//dfs
	static int N, M;
	static List<List<Integer>> connection = new ArrayList<>();
	static int maxCnt = 0;
	static boolean[] visited;
	static int[] count;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		
		String[] nm = in.readLine().split(" ");
		N = Integer.parseInt(nm[0]);
		M = Integer.parseInt(nm[1]);
		
		//init
		count = new int[N+1];
		for (int i = 0; i <= N; i++) {
			connection.add(new ArrayList<>());//먼저 할당해줌. N개의 컴퓨터
		}
		
		while(M-- > 0) {
			String[] line = in.readLine().split(" ");
			int com1 = Integer.parseInt(line[0]);
			int com2 = Integer.parseInt(line[1]);
			
			connection.get(com1).add(com2);
			//A가 B를 신뢰하는 경우, B를 해킹하면 A도 해킹할 수 있다!! 고로 이렇게 넣어줘야 함.
			//문제를 제대로 읽자...
			//근데 이렇게 넣어서 이 방식대로 하면 시간초과남... 
		}
		
		//logic
		int max = 0;
		for (int com = 1; com <= N; com++) {
			visited = new boolean[N+1]; //1~N
			int cnt = bfs(com);
//			max = Math.max(max, cnt);
		}
        for (int i = 1; i <= N; i++) {
            max = Math.max(max, count[i]);
        }
		
		//print
		for (int i = 1; i <= N; i++) {
			if (max == count[i]) sb.append(i).append(" ");
		}
		System.out.println(sb);
	}
	
	private static int bfs(int com) {
		Queue<Integer> q = new ArrayDeque<>();
		
		q.offer(com);
		visited[com] = true; //헐 이거 빼먹음..---
		
		while(!q.isEmpty()) {
			int node = q.poll();
			
			for (int adj : connection.get(node)) {
				if (visited[adj]) continue;

				visited[adj] = true; //큐로 들어갈때 방문표시
				count[adj]++; //검색해서 추가한 부분. 해당 노드의 값을 증가시켜줌..
				q.offer(adj);
			}
		}
		return count[com];
	}
	
}
/*
 * 내가 한 방식..
 * 진짜 시간초과 너무하다
 * 
 * 
 * 1. dfs + 인접리스트 (B -> A)
 * 처음에 dfs로 풀었는데 시간초과가 나기도 했고 그냥 bfs가 더 직관적일 것 같아서 bfs로 바꿨다.
 * 
 * 
 * 2. bfs + 인접리스트 (B -> A)
 * B를 감염시키면 A도 감염되는 형태이기 때문에 B -> A 형태로 인접 리스트에 넣어주었따.
 * 그렇게 하고 모든 노드를 for 문으로 돌면서 bfs를 수행하여 연결된 노드들을 다 카운트해주었다.
 * 즉, 해당 노드를 감염시켰을 때 어디까지 갈 수 있는지 봤다.
 * 
 * 근데 계속 시간초과가 ㅇ났따.. 짜증난다.. 열 번은 낸 거 같은데
 * 배열이 더 빠른가 해서 배열로 바꿔서도 내봤는데 그냥 계속 시간초과가 났다.
 * 
 * 
 * 3. bfs + 인접리스트 (A -> B)
 * 결국 검색을 했다.. 차이점은 다음과 같다.
 * 1. 인접리스트를 받을 때 B->A 순으로 넣어주는게 아니라 A->B 순으로 넣어준다.
 * 2. 그리고 감염시킬 수 있는 컴퓨터의 수를 담고 있는 int[] count를 만들어준다.
 * 
 * 이렇게 되면 list.get(A)에는 감염시켰을 떄 A에도 영향을 미치는 컴퓨터들의 list를 담고 있따.
 * 그래서 모든 노드에 대한 bfs를 돌면서 count[adj]++해준다.(A를 감염시킬 수 있는 노드라는 뜻)
 * 
 * 이렇게 되면 count에는 각 해당 노드를 감염시켰을 떄 추가로 감염시킬 수 있는 컴퓨터의 수를 담고있게 된다.
 * 그 다음에 for문을 돌면서 모든 노드를 탐색하면서 count가 max인 노드들을 출력해주면 된다.......
 */
