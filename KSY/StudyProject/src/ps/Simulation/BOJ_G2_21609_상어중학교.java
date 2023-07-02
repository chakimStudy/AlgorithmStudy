package ps.Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G2_21609_상어중학교 {

	/*
	 * 블록: 검은색(-1), 무지개(0), 일반(1~M)
	 * 
	 * 블록 그룹: 검은색 X, 무지개 0~무한, 일반 최소 하나. 다 같은 색.
	 * 블록의 개수는 2 이상. 다 인접해있어야 함.
	 * 기준 블록 : 일반 블록 중에서 행의 번호가 가장 작은, 열의 번호가 가장 작은 블록
	 * 
	 * 블록 그룹이 존재하는 동안 다음이 반복되어야 함.(오토 플레이 기능)
	 * 
	 * 1. 크기가 가장 큰 블록 그룹 찾기 - 무지개 수 많은 거 - 기준 블록 행이 가장 큰 거 - 기준 블록 열이 가장 큰 거
	 * 2. 블록 그룹의 모든 블록 제거. B^2 점수 획득
	 * 3. 중력 작용 - 검은색 블록 말고 다른 블록들이 다 아래로 이동. 다른 블록이나 경계를 만나기 전까지 (그냥 밑으로 내리는 거!)
	 * 4. 반시계 회전
	 * 5. 중력 작용
	 * 
	 * 
	 * => 오토 플레이가 끝났을 때 획득한 점수의 합
	 */
	static class Pos{
		int r, c;

		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Pos [r=" + r + ", c=" + c + "]";
		}
		
	}
	static class Group{
		List<Pos> block;
		int size;
		int rainbowSize;
		int minR, minC;
		public Group(List<Pos> block, int size, int rainbowSize, int minR, int minC) {
			super();
			this.block = block;
			this.size = size;
			this.rainbowSize = rainbowSize;
			this.minR = minR;
			this.minC = minC;
		}
		
		
	}
	static int N, M;
	static int[][] map;
	static int score;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//logic
		/*
		 * 1. 블록 그룹 찾기..
		 * 일반 블록에서부터 시작해서 bfs를 한다. 무지개나 같은 숫자이면 계속 간다.. 
		 * 계속 가면서 이걸 클래스에 저장하자
		 * 클래스에 블록의 좌표 리스트, 블록의 개수, 무지개 수, 기준 블록의 행렬을 담자(즉, 일반 블록 중에서 행의 번호와 열의 번호의 최솟값)
		 * 
		 * 이 객체를 리스트나 pq에 담아서 정렬로.. 크기가 가장 큰 거 찾는다
		 * 블록들 다 제거하고 점수 얻고
		 * 중력, 배돌, 중력
		 * 
		 * 일케 한다.
		 */
		
		//블록 그룹이 존재하는동안 반복
		while(true) {
			// 1. 블록 그룹 찾기..
			visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] > 0 && !visited[i][j]) {
						bfs(new Pos(i, j));
					}
				}
			}
			if (pq.isEmpty()) break; //블록 그룹 없음.
			print();
			
			// 2. 블록 그룹의 모든 블록 제거. B^2 점수 획득
			removeGroup(pq.poll());
			pq.clear();
			print();
			
			//3. 중력 작용
			down();
			print();
			
			//4. 반시계 회전
			rotate();
			print();
			
			//5. 중력 작용
			down();
			print();
		}
		
		System.out.println(score);
	}
	private static void print() {

//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
//		System.out.println();
	}
	private static void rotate() {
		int[][] rotate = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				rotate[N-1-j][i] = map[i][j];
			}
		}
		map = rotate;
	}
	private static void down() {
		
		int[][] downMap = new int[N][N];
		for (int c = 0; c < N; c++) {
			Queue<Integer> q = new ArrayDeque<>();
			int r = N-1;
			int qR = r; //큐에 넣은 값들을 저장할 인덱스
			int qC = c;
			while(r >= 0) {
				int val = map[r][c];
				if (val == -1) {
					downMap[r][c] = -1;
					//여기서 멈추고 이 밑에 넣어줌.
					while(!q.isEmpty()) {
						downMap[qR--][qC] = q.poll();
					}
					//큐에 있는거 다 넣고, -1까지 사이 빈 공간은 -10으로 채워야 함
					while(qR != r-1) {
						if (downMap[qR][qC] == 0) downMap[qR][qC] = -10;
						qR--;
					}
//					qR = r-1; //-1 위에 넣기
				}
				else if (val >= 0) {
					q.offer(val);
				}
				r--;
			}
			while(!q.isEmpty()) {
				downMap[qR--][qC] = q.poll();
			}
			for (int downR = qR; downR >= 0; downR--) {
				downMap[downR][qC] = -10; //빈칸은 -10으로 채워줌
			}
		}
		
		map = downMap;
	}
	private static void removeGroup(Group removed) {
		score += (removed.size*removed.size); //점수 획득
		
		//블록 삭제 : -10으로 만들자
		for (Pos pos : removed.block) {
			map[pos.r][pos.c] = -10;
		}
	}
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	//크기가 가장 큰 블록 그룹 찾기 - 무지개 수 많은 거 - 기준 블록 행이 가장 큰 거 - 기준 블록 열이 가장 큰 거
	static Queue<Group> pq = new PriorityQueue<>(new Comparator<Group>() {

		@Override
		public int compare(Group o1, Group o2) {
			if (o1.size != o2.size) {
				return o2.size - o1.size; //내림차순으로
			}
			else if (o1.rainbowSize != o2.rainbowSize) {
				return o2.rainbowSize - o1.rainbowSize;
			}
			else if (o1.minR != o2.minR) {
				return o2.minR - o1.minR;
			}
			else
				return o2.minC - o1.minC;
		}
	});
	private static void bfs(Pos pos) {
		Queue<Pos> q = new ArrayDeque<>();
		Queue<Pos> rainbow = new ArrayDeque<>();
		q.offer(pos);
		
		//bfs할 떄 큐에도 넣으면서, 클래스도 만들자
		List<Pos> block = new ArrayList<>();
		int rainbowSize = 0;
		int minR = pos.r;
		int minC = pos.c; //이걸 maxvalue로 해줬었는데 이러면 처음 시작하는 값은 반영이 안됨..!
		/*
		 * 하다가 생각났는데
		 * 무지개는 visited 안해야할듯.
		 */
		visited[pos.r][pos.c] = true;
		block.add(new Pos(pos.r, pos.c));
		int val = map[pos.r][pos.c]; 
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			
			for (int di = 0; di < 4; di++) {
				int nr = cur.r + dr[di];
				int nc = cur.c + dc[di];
				
				if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc] || map[nr][nc] == -1) continue;
				
				if (map[nr][nc] == val) {
					//값이 같은 일반 블록이면 visited하고 이동
					visited[nr][nc] = true;
					q.offer(new Pos(nr, nc));
					block.add(new Pos(nr, nc));
					
					if (minR > nr) {
						minR = nr;
						minC = nc;
					}
					else if (minR == nr && minC > nc) {
						minC = nc;
					}
					
				}
				else if (map[nr][nc] == 0) {
					//무지개 블록이면 visited 하지 않고 이동 가능 - 이러면 무한반복이니까, visited 해주고 visited를 계속 초기화 하면 됨.
					visited[nr][nc] = true;
					q.offer(new Pos(nr, nc));
					block.add(new Pos(nr, nc));
					rainbow.add(new Pos(nr, nc));
					rainbowSize++;
				}
				//값이 다른 일반 블록으로는 갈 수 없음.
			}
		}
		
		//무지개 블록은 다시 false로
		while(!rainbow.isEmpty()) {
			Pos bow = rainbow.poll();
			visited[bow.r][bow.c] = false;
		}
		
		//생성된 그룹을 pq에 넣는다.
		Group group = new Group(block, block.size(), rainbowSize, minR, minC);
//		System.out.println(block);
//		System.out.println(rainbowSize);
//		System.out.println(minR);
//		System.out.println(minC);
		
		if (block.size() >= 2)
			pq.add(group);
	}
	
	/*
	 * 실수한 부분
	 * 1. visited를 계속 새로 만들어주는게 맞음
	 * 2. 중력 작용할 떄 맵을 새로 만들어서.. 해줘야함
	 * 3. 중력 작용할 때 큐에 있는거 다 넣고 -1까지의 빈칸은 -10으로 채워줘야 함
	 * 4. rotate 방향 틀림
	 * 5. rotate 후 map에 대입
	 * 
	 * 6. 기준블록 r, c 저장할 때 초기값 maxvalue로 해줬었는데 이러면 처음 시작하는 값은 반영이 안됨..!
	 */
	
}
