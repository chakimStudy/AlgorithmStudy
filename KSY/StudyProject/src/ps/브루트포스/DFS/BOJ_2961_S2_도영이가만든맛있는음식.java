package ps.브루트포스.DFS;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_2961_S2_도영이가만든맛있는음식 {
	static boolean[] visited;
	static int N;
	static int minDiff = 1000000000;
	static int[] sArr;
	static int[] bArr;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		sArr = new int[N];
		bArr = new int[N];
		visited = new boolean[N];

		for (int i = 0; i < N; i++) {
			sArr[i] = sc.nextInt();
			bArr[i] = sc.nextInt();
		}
		choice(0, 1, 0, 0);	//s: 신맛은 계속 곱해줘야 하기 때문에 1로 초기화
		System.out.println(minDiff);
	}

	private static void choice(int idx, int s, int b, int cnt) {
		int diff = 0;
		if (cnt >= 1) { //처음에 idx로 하려 했는데 적어도 하나의 재료를 써야 하기 때문에.. 그냥  cnt 추가함
			//diff 계산 및 저장
			diff = Math.abs(s - b);
			if (minDiff > diff) minDiff = diff;
		}
		if (idx == N) return;
		//for문 없음. 현재 원소 포함 or 미포함
		visited[idx] = true;
		choice(idx + 1, s * sArr[idx], b + bArr[idx], cnt + 1);
		visited[idx] = false;
		choice(idx + 1, s, b, cnt);
	}
}
