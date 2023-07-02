package ps.브루트포스.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2668_G5_숫자고르기 {

	static int N;
	static int[] nums;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();

		N = Integer.parseInt(in.readLine());
		nums = new int[N+1]; //1~N
		for (int i = 1; i <= N; i++) {
			nums[i] = Integer.parseInt(in.readLine());
		}

		//logic

		visited = new boolean[N+1];
		result = new boolean[N+1];

		for (int i = 1; i <= N; i++) {
			visited[i] = true;
			start = i;
			dfs(nums[i]);
			
			//init
			Arrays.fill(visited, false);
			isSave = false;
		}

		for (int i = 1; i <= N; i++) {
			if (result[i]) maxCnt++; 
		}

		//print
		sb.append(maxCnt).append("\n");
		for (int i = 1; i <= N; i++) {
			if (result[i]) sb.append(i).append("\n");
		}
		System.out.println(sb);
	}

	static int maxCnt = 0;
	static boolean[] visited;
	static boolean[] result;
	static boolean isSave = false;
	static int start;
	private static void dfs(int i) {
		if (visited[i]) {
			if (i == start) {
				result = getVisited();
				isSave = true;
			}
			return;
		}

		visited[i] = true;
		dfs(nums[i]);
		if (!isSave) visited[i] = false;
	}

	private static boolean[] getVisited() {
		boolean[] newVisit = result.clone();
		for (int i = 1; i <= N; i++) {
			if (visited[i]) newVisit[i] = true;
		}
		return newVisit;
	}
}
/*
 * 부분집합 + set(X 걍 배열 씀)을 이용하면 될 것 같다.
 * 큰 거 부터 구하는게 가지치기 하는 데 유리하기 때문에 빼는 걸 고르자
 * 
 * => 이렇게 하면 메모리초과.. 정신나갈거같당.
 * N이 최대 100이기 때문에 재귀로 들어가면 안되는 것 같다..
 * 
 * 그래서 결국 찾아봤는데
 * 경로 문제로 풀더라!! for문을 통해 모든 노드에서부터 시작해서
 * dfs로 따라 들어가면서 싸이클이 만들어지면 그 길이 /2를 저장하면 된다.
 * 
 */