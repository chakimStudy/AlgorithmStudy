package ps.브루트포스.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class BOJ_17471_G4_게리맨더링 {
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static Map<Integer, int[]> nodes;	//adj : 0~N-1
	static int[] population;			//1~N
	static int minDiff = Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {

		//input
		N = Integer.parseInt(in.readLine());
		nodes = new HashMap<>();
		population = new int[N + 1]; // 구역은 1~N
		//popu
		String[] line = in.readLine().split(" ");
		for (int j = 0; j < line.length; j++) {
			population[j+1] = Integer.parseInt(line[j]);
		}

		//nodes
		for (int node = 1; node <= N; node++) {
			line = in.readLine().split(" ");
			int[] adjs = new int[line.length-1];
			for (int j = 1; j < line.length; j++) {
				adjs[j-1] = Integer.parseInt(line[j]);
			}
			nodes.put(node, adjs.clone());
		}

		//logic
		for (int i = 1; i <= N; i++) {
			System.out.println("bfs: "+ i);
			bfs(i);
			
		}
		System.out.println(minDiff);
	}

	private static void bfs(int node) {
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[N + 1];
		visited[node] = true;
		
		q.add(node);
		int curPopuDiff = 0;
		while(!q.isEmpty()) {
			if (connected(visited)) {
				curPopuDiff = getPopuDiff(visited);
				if (minDiff > curPopuDiff) minDiff = curPopuDiff;
			}
			
			int n = q.poll();
			visited[n] = true;
			if (!nodes.containsKey(n)) continue;
			for (int adj : nodes.get(n)) {
				if (visited[adj]) continue;
				q.add(adj);
			}
		}
	}

	private static boolean connected(boolean[] visited) {

		/*
		 * true: 이미 다 연결되어 있음.
		 * false인 것들의 연결을 확인해야 함
		 */
		//우선 false인 것 set에 추가
		falseSet = new HashSet<>();
		int start = 0;
		for (int i = 1; i <= N; i++) {
			if (!visited[i]) {
				falseSet.add(i);
				start = i;
			}
		}
		
		return dfs(start, visited.clone(), 0);
		
	}
	
	static Set<Integer> falseSet = new HashSet<>();
	static Set<Integer> falseSet2 = new HashSet<>();
	
	private static boolean dfs(int i, boolean[] visited, int dep) {
		if (dep == falseSet.size()) {
			return falseSet.equals(falseSet2);
		}
		
		for (int adj : nodes.get(i)) {
			if (visited[adj]) continue;
			falseSet2.add(adj);
			dfs(adj, visited, dep + 1);
		}
		return true;
	}

	private static int getPopuDiff(boolean[] visited) {
		int trueSum = 0;
		int falseSum = 0;

		for (int i = 1; i <= N; i++) {
			if (visited[i]) trueSum += population[i];
			else falseSum += population[i];
		}
		return Math.abs(trueSum - falseSum);
	}
}


/*
 * 
 * n개의 구역.. 모든 구역을 두 선거구로 나눠야 한다.
 * 선거구 A를 false, 선거구 B를 true로 두고 
 * 모든 경우를 탐색하면서 카운트 하는데,
 * A또는 B가 공집합이거나 나눈 선거구가 연결되어 있지 않으면 카운트하지 않는다.
 * 
 * 처음에는 연결되어 있는 걸 따라가면서 확장시키면 좋겠다는 생각을 했지만
 * 음.. 그렇게 해도 될 것 같기도... => BFS?
 * 
 * 1. 입력받음
 * 2. 입력받은 구역을 for로 하나씩 돌면서
 * 		true이면 continue
 * 		false이면 true로 바꾸고 인접한 구역으로 들어가고 나와서 다시 false
 * 3. 인구 차 계산
 * 		하나라도 true가 되면 그것도 선거구로 가능하기 때문에 인구 차 계산
 * 		현 선거구가 다 연결되어 있으면 인구 차 계산 (구역 하나만 있어도도 연결되어 있는 것.)
 * 		그렇지 않으면 카운트하지 않음.
 * 4. 기저조건
 * 		근데 기저조건을 모르겠네.. 좀 더 빨리 끊을 수 있을 것 같은데
 * 		음! 선거구 인구수의 차를 최소로 해야 하는데,
 * 		이전에 계산한 인구수의 차보다 현재 계산한 인구수의 차가 더 늘어나면 끊는다.!
 * 
 * bfs 하고싶었는데 하다보니 dfs처럼 됨.. 근데 이렇게 하니까 연결되었는지 판별하는게 어려움
 * dfs는 좀 아닌거같음.
 * 마침 수업에서 큐 배우는데 bfs에서는 큐 씀.
 * bfs 다 까먹믐./ 근데 bfs쓰면 연결 판별..가능..?
 * 이런거 완전 기초같은데 다 까먹어서 못하겟음
 * 다음주에 배우는데 좀 미리 찾아봄
 * 
 * 
 * bfs로 한다면
 * 1. 입력받음
 * 2. for문 root = 1~N:
 * 		bfs(root) 
 * 		1) 큐 생성
 * 		2) 큐에 root 넣음
 * 		3) while(!Q.isEmpty()):
 * 			a) node = Q.front()
 * 			*) if (connected(Q)) 인구차 계산
 * 			b) for : node 의 adj node
 * 				: Q.push(adj)
 * 3. 최소 인구차 출력
 * 
 */