package ps.Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_G1_23290_마법사상어와복제 {

	/*
	 * 
	 * class Fish{
	 * 	int r, c;
	 * 	int direc; //1~8
	 * }
	 * class Cell{
	 * 	List<Fish> fishes;
	 * 	int smellTime;
	 * 	
	 * }
	 * 
	 * 4X4 map 자료구조
	 * 
	 * 
	 * 1. 물고기가 한 칸 이동(8방)
	 * 		상어 칸, 냄새 칸, 범위 벗어나는 칸 continue;
	 * 		이동할 수 있을 때까지 45도 반시계 회전
	 * 		이동할 수 없으면 이동 안 함
	 * 2. 상어가 연속 3 칸 이동(4방)
	 * 		범위 벗어나는 칸 continue;
	 * 		이동하는 칸들에 물고기는 사라지고 냄새가 남음(냄새 = S로 설정)
	 * 		물고기 젤 많은 방법으로 이동! 사전순 이동!
	 * 3. 냄새가 S-2인 것들은 0으로 만들어줌
	 * 4. 물고기 복제
	 * 
	 * 
	 */
	static class Fish{
		int r, c;
		int direc;
		public Fish(int r, int c, int direc) {
			super();
			this.r = r;
			this.c = c;
			this.direc = direc;
		}
		@Override
		public String toString() {
			return "Fish [r=" + r + ", c=" + c + ", direc=" + direc + "]";
		}
		
		
	}
	static class Cell{
		List<Fish> fishes;
		int smellTime;
		public Cell(int smellTime) {
			super();
			this.fishes = new ArrayList<>();
			this.smellTime = smellTime;
		}
		public Cell(List<Fish> fishes, int smellTime) {
			super();
			this.fishes = fishes;
			this.smellTime = smellTime;
		}
		@Override
		public String toString() {
			return fishes + " " + smellTime ;
		}
		
	}
	static int N = 4;
	static int M, S;
	static Cell[][] map;
	static int shR, shC;
	
	static Cell[][] moveMap; //물고기가 이동할 맵
	static int removeFish = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		M = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		//map 초기화
		map = new Cell[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = new Cell(0);
			}
		}

		//물고기 정보 입력
		for (int i = 0; i < M; i++) {
			//격자 좌표는 1~N
			st = new StringTokenizer(in.readLine());
			int r = Integer.parseInt(st.nextToken()) -1;
			int c = Integer.parseInt(st.nextToken()) -1;
			int dir = Integer.parseInt(st.nextToken()) -1;
			Fish fish = new Fish(r, c, dir);
			map[r][c].fishes.add(fish);
		}
		
		//상어 정보 입력
		st = new StringTokenizer(in.readLine());
		shR = Integer.parseInt(st.nextToken())-1;
		shC = Integer.parseInt(st.nextToken())-1;
		
		
		//simulation
		//moveMap 초기화
		moveMap = new Cell[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				moveMap[i][j] = new Cell(0);
			}
		}
		
		for (int s = 1; s <= S; s++) {
			//1. 물고기 이동
			//맵을 보면서 리스트에 물고기가 있으면 이동
//			System.out.println("물고기 이동 전");
//			for (int i = 0; i < N; i++) {
//				System.out.println(Arrays.toString(moveMap[i]));
//			}
//			System.out.println();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j].fishes.size() > 0) {
						moveFish(i, j);
					}
				}
			}
//			System.out.println("물고기 이동");
//			for (int i = 0; i < N; i++) {
//				System.out.println(Arrays.toString(moveMap[i]));
//			}
//			System.out.println();

			//2. 상어 이동
			moveShark(s);
			//3. 냄새가 S-2인 것들은 0으로 만들어줌
			//4. 물고기 복제
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					//복제
					for (Fish fish : moveMap[i][j].fishes) {
						map[i][j].fishes.add(new Fish(fish.r, fish.c, fish.direc));
					}
					map[i][j].smellTime = Math.max(moveMap[i][j].smellTime, map[i][j].smellTime);
					
					//냄새 처리
					if (map[i][j].smellTime == s-2) {
						map[i][j].smellTime = 0;
					}
				}
			}
//			출력..
//			System.out.println(" 복제");
//			for (int i = 0; i < N; i++) {
//				System.out.println(Arrays.toString(map[i]));
//			}
//			System.out.println();
			
			//moveMap 초기화
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					moveMap[i][j] = new Cell(0);
				}
			}
		}
		//남은 물고기 수 세기
		int total = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				total += map[i][j].fishes.size();
			}
		}
		System.out.println(total);
	}

	//상좌하우
	static int[] sDr = {-1, 0, 1, 0};
	static int[] sDc = {0, -1, 0, 1};
	private static void moveShark(int s) {
		/*
		 * 2. 상어가 연속 3 칸 이동(4방)
		 * 		범위 벗어나는 칸 continue;
		 * 		이동하는 칸들에 물고기는 사라지고 냄새가 남음(냄새 = S로 설정)
		 * 		물고기 젤 많은 방법으로 이동! 사전순 이동!
		 */

		maxSum = 0;
		//현재 상어의 위치 : 0
		moveR[0] = shR;
		moveC[0] = shC;
//		System.out.println(Arrays.toString(moveR));
//		System.out.println(Arrays.toString(moveC));
//		System.out.println(shR + " " + shC);
		
		visited = new boolean[4][4];
//		visited[shR][shC] = true; //하면 안됨
		none = true;
		dfs(0, 0);
		
		shR = bestMoveR[3];
		shC = bestMoveC[3];
//		System.out.println(Arrays.toString(moveR));
//		System.out.println(Arrays.toString(moveC));
//		System.out.println(Arrays.toString(bestMoveR));
//		System.out.println(Arrays.toString(bestMoveC));
		
//		System.out.println(shR + " " + shC);
		
		//이동하는 길의 물고기들 다 없앰
		for (int i = 1; i < 4; i++) {
			int r = bestMoveR[i];
			int c = bestMoveC[i];
			int size = moveMap[r][c].fishes.size();
			if (size > 0) {
//				System.out.println("remove: " +r + " " + c);
				moveMap[r][c].fishes.clear();
				moveMap[r][c].smellTime = s;
			}
		}
		removeFish += maxSum;
	}

	static int maxSum = 0;
	static int[] moveR = new int[4];
	static int[] moveC = new int[4];
	static int[] bestMoveR = new int[4];
	static int[] bestMoveC = new int[4];
	static boolean none = true;
	
	static boolean[][] visited;
	private static void dfs(int depth, int sum) {
		if (depth == 3) {
			if (none) {
				bestMoveR = Arrays.copyOf(moveR, 4);
				bestMoveC = Arrays.copyOf(moveC, 4);
				
				none = false;
			}
			if (maxSum < sum) {
				maxSum = sum;
				bestMoveR = Arrays.copyOf(moveR, 4);
				bestMoveC = Arrays.copyOf(moveC, 4);
			}
			return;
		}
		
		for (int di = 0; di < 4; di++) {
			int nr = moveR[depth] + sDr[di];
			int nc = moveC[depth] + sDc[di];
			
			if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
			
			moveR[depth+1] = nr;
			moveC[depth+1] = nc;
//			System.out.println(nr + " " + nc);
//			System.out.println(moveMap[nr][nc].fishes.size());
			
			if (visited[nr][nc]) dfs(depth+1, sum);
			else {
				visited[nr][nc] = true;
				dfs(depth+1, sum+moveMap[nr][nc].fishes.size());
				visited[nr][nc] = false;
			}
			
		}
	}

	static int[] fDr = {0, -1, -1, -1, 0, 1, 1, 1};
	static int[] fDc = {-1, -1, 0, 1, 1, 1, 0, -1};
	
	private static void moveFish(int r, int c) {
		/*
		 *  1. 물고기가 한 칸 이동(8방)
		 * 		상어 칸, 냄새 칸, 범위 벗어나는 칸 continue;
		 * 		이동할 수 있을 때까지 45도 반시계 회전
		 * 		이동할 수 없으면 이동 안 함
		 */
		for (int k = 0; k < map[r][c].fishes.size(); k++) {
			Fish fish = new Fish(map[r][c].fishes.get(k).r, map[r][c].fishes.get(k).c, map[r][c].fishes.get(k).direc);
			//물고기가 현재 가진 방향부터 반시계로 8방 탐색
			int di = fish.direc;
			boolean isMove = false;
			for (int i = 0; i < 8; i++) {
				int nr = fish.r + fDr[di];
				int nc = fish.c + fDc[di];
				
				if (nr < 0 || nr >= N || nc < 0 || nc >= N
						|| (nr == shR && nc == shC)
						|| (map[nr][nc].smellTime != 0)) {
					//못가면 인덱스 증가하고 패스
					di--;
					if (di < 0) di = 7;
					continue;
				}
				
				else {
					//이동할 수 있으면 이동하고 끝. 
					fish.direc = di; //아마
					moveMap[nr][nc].fishes.add(new Fish(nr, nc, di));
					//만약 이동 못했으면 그 자리에 추가
					isMove = true;
					break;
				}
			}
			if (!isMove) {
				moveMap[fish.r][fish.c].fishes.add(new Fish(fish.r, fish.c, fish.direc)); 
			}
		}
	}
	/*
	 * 실수한 부분
	 * 1. 객체 복사 ---****중요!!
	 *  - 생성자 사용해야 함
	 * 2. 상어의 이동
	 * 	- 방문한 곳에 또 방문할 수 있음. 단 물고기를 더해주지 않으면 됨.
	 * 	- 처음 위치는 방문한 곳이 아님.
	 * 	- 상어가 물고기에 닿지 않는 경우가 있음. 그럴 땐 사전순으로 가장 빠른 거! 상상상!
	 */
	
}
