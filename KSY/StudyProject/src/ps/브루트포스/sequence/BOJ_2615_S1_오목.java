package ps.브루트포스.sequence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2615_S1_오목 {
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static int[][] pan = new int[19][19];

	public static void main(String[] args) throws IOException {
		//input
		for (int i = 0; i < 19; i++) {
			String[] line = in.readLine().split(" ");
			for (int j = 0; j < 19; j++) {
				pan[i][j] = Integer.parseInt(line[j]);
			}
		}

		//logic
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				int res = -1;
				if (pan[i][j] == 1) {
					res = search(i, j, 1);
				}
				else if (pan[i][j] == 2) {
					res = search(i, j, 2);
				}

				if (res != -1) {
					print(res, i, j);
					return;
				}
			}
		}
		System.out.println("0");
	}

	private static void print(int res, int i, int j) {
		System.out.println(pan[i][j]);
		System.out.println((i+1) + " " + (j+1));
		return;	
	}

	//아래, 오른쪽 대각선, 오른쪽, 오른쪽 위
	static int[] dr = {1, 1, 0, -1};
	static int[] dc = {0, 1, 1, 1}; //13퍼....
	
	private static int search(int i, int j, int num) {
		/*
		 * 8방 탐색으로 모든 방향 다 보고
		 * num이 있다면 거기로 계속 감. 5개있는ㄴ지 확인.
		 * 5개가 있다면 true 리턴. 동시에 5개가 있는 경우는 더 없으니까 true면 break; 종료.
		 * 
		 * -- NO! -- 이것땜에 계속 틀림
		 * 8방 탐색 하면 안됨..
		 * 해당 바둑알이 출력의 시작점이 될 수 있는 경우에만 go 함.
		 * 즉, 왼쪽 위인 경우에만 go 해야 함.
		 * 그렇기 때문에 8방 탐색이 아니라
		 * 아래, 오른쪽 대각선, 오른쪽, 오른쪽 위를 탐색해야함.
		 * 
		 * 그리고 탐색방향 반대에는 다른 바둑알이 있거나 바둑알이 없어야함(해당 바둑알이 처음 시작이어야 하니까)
		 */

		for (int di = 0; di < dr.length; di++) {
			int r = i + dr[di];
			int c = j + dc[di];
			
			int oppoR = i - dr[di];
			int oppoC = j - dc[di];
			if (oppoR >= 0 && oppoR < 19 && oppoC >= 0 && oppoC < 19) {
				if (pan[oppoR][oppoC] == num) continue;
			}
			if (r < 0 || r >= 19 || c < 0 || c >= 19) continue; //인덱스 체크!!
			if (pan[r][c] == num) {
				if (go(r, c, di, num)) return num;
			}
		}
		return -1;
	}
	private static boolean go(int r, int c, int di, int num) {
		//6개 연속이면 이긴거 아님.
		int cnt = 2; //이미 두개는 체크됨
		while(true) {
			int nr = r + dr[di];
			int nc = c + dc[di];
			if (nr < 0 || nr >= 19 || nc < 0 || nc >= 19) break; //인덱스 체크!!

			if (pan[nr][nc] == num) cnt++;
			else break;

			if (cnt >= 6) break;
			r = nr;
			c = nc;
		}

		return cnt == 5; //정확히 5개면 true;
	}

}
