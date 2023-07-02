package ps.Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_G4_15685_드래곤커브 {

	/*
	 * 좌표 인덱스 변화로 풀 수 있을 것 같음.
	 * **주의할 점
	 * 1. x, y 좌표가 c, r 좌표임!!
	 * 
	 * 실수한 부분
	 * - 0세대는 따로 생각해야 함. 즉, 미리 0세대 그려놓고 함수 시작.
	 * - 새로 추가되는 커브만큼만 더 그려줘야 하는데 오버해서 그림. 즉, 단계별 그림의 차이점을 잘 파악할 것.
	 * 
	 */
	
	static class Pos{
		int r, c;

		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	//우, 상, 좌, 하
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {1, 0, -1, 0};
	
	static int N;
	static int[][] map = new int[101][101];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		
		//드래곤 커브 입력 및 그리기
		for (int i = 0; i < N; i++) {
			//input
			StringTokenizer st = new StringTokenizer(in.readLine());
			int c = Integer.parseInt(st.nextToken()); //r, c 반대!
			int r = Integer.parseInt(st.nextToken());
			int di = Integer.parseInt(st.nextToken());
			int th = Integer.parseInt(st.nextToken());
			
			List<Integer> dir = new ArrayList<>();
			dir.add(di);
			
			//0세대 그리기
			map[r][c] = 1;
			int nr = r + dr[di];
			int nc = c + dc[di];
			map[nr][nc] = 1;
			
			draw(new Pos(nr, nc), dir, th); //드래곤 커브 그리기
		}
		
		//네 꼭짓점 다 1인 사각형의 수 카운트
		int cnt = 0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (map[i][j] == 1 && map[i+1][j] == 1 &&
						map[i][j+1] == 1 && map[i+1][j+1] == 1) cnt++;
			}
		}
		System.out.println(cnt);
	}

	private static void draw(Pos start, List<Integer> dir, int th) {
		//th가 0보다 작아지면 끝
		if (th < 1) return;

		//새로 추가되는 커브 생성
		//dir에 +1%4, reverse 한 것이 새로 추가되는 커브!!
		List<Integer> ls = new ArrayList<>();
		for (int di : dir) {
			ls.add((di + 1) % 4);
		}
		Collections.reverse(ls);
		
		//새로 추가된 커브 그리기
		int nr = start.r;
		int nc = start.c;
		map[nr][nc] = 1;
		for (int di : ls) {
			nr += dr[di];
			nc += dc[di];
			map[nr][nc] = 1;
		}
		
		//다음 세대 그리기
		dir.addAll(ls); //기존 + 새로 추가된 것 합쳐서 다음으로 넘김
		draw(new Pos(nr, nc), dir, th-1);
	}
}
