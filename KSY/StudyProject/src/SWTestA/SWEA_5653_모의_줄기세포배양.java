package SWTestA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_5653_모의_줄기세포배양 {
	/*
	 * NM은 50이하
	 * K는 300이하
	 * 초기 50 + 위 300 + 아래 300 = 650.. 700 * 700?
	 * 
	 * 흠 그냥 맵을 만들지 말고
	 * 세포 상태는 비활성 상태 -> 활성 상태 -> 죽은 상태 : 로 끝나기 때문에
	 * 살아있는 세포는 queue에 넣어서 관리하다가
	 * 마지막에 k초 후에 queue에 있는 세포 수를 출력하면 되겠다.
	 * 
	 * 하나의 셀에 동시에 번식하려고 하는 경우 처리? => contains, equals 활용
	 * 
	 */
	static class Cell implements Comparable<Cell>{
		int r, c;
		int power; //생명력 수치
		boolean status; //false: 비활성 , true: 활성
		int powerCnt;
		int createTime;
		public Cell(int r, int c, int power, boolean status, int createTime) {
			super();
			this.r = r;
			this.c = c;
			this.power = power;
			this.powerCnt = power;
			this.status = status;
			this.createTime = createTime;
		}
		public Cell(int r, int c) {
			this.r = r;
			this.c = c;
		}
		@Override
		public boolean equals(Object obj) {
			if (getClass() != obj.getClass()) return false;
			Cell other = (Cell) obj;
			if (r == other.r && c == other.c) return true; //좌표가 같으면 같은 것
			return false;
		}
		@Override
		public int compareTo(Cell o) {
			if (createTime != o.createTime) return createTime - o.createTime; //음수면 바꾸지 않는다. 오름차순
			if (powerCnt != o.powerCnt) return powerCnt - o.powerCnt;
			return o.power - power; //내림차순
		}
		@Override
		public String toString() {
			return "Cell [r=" + r + ", c=" + c + ", power=" + power + ", status=" + status + ", powerCnt=" + powerCnt
					+ ", createTime=" + createTime + "]";
		}
		
	}
	static int N, M, K;
	static Queue<Cell> pq = new PriorityQueue<>();
	static Queue<Cell> die = new ArrayDeque<>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		
		int T = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			
			StringTokenizer st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			// 입력받으면서 cell 정보들을 큐에 저장
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine());
				for (int j = 0; j < M; j++) {
					int val = Integer.parseInt(st.nextToken());
					if (val != 0) {
						pq.add(new Cell(i, j, val, false, 0));
					}
				}
			}
			
			/*
			 * K초 동안 돌면서
			 * cell이 비활성상태 -> powerCnt 검사 
			 * 		-> 1 이상이면 --
			 * 		-> 0이면 활성상태로 바꿈
			 * cell이 활성상태 -> powerCnt 검사
			 * 		-> powerCnt < power : bfs로 방문하지 않은 곳(큐에 없는 곳)으로 번식 (pq써야함)
			 * 		-> powerCnt == power : 큐에 다시 넣지 않음(죽은 상태)
			 * 
			 * 큐에는 비활성상태 또는 활성상태의 세포만 저장(죽은 세포는 없음)
			 */

			while(K-- > 0) {
				int n = pq.size();
				System.out.println("time " + K);
				
				while(n-- > 0) {
					System.out.println("cell " + n);
					Cell cur = pq.poll();
					System.out.println(cur);
					
					if (!cur.status) {
						if (cur.powerCnt >= 1) {
							cur.powerCnt--;
						}
						else cur.status = true;
						pq.offer(cur);
					}
					else {
						//번식하고, cnt증가하고, 작으면 큐에 넣음
						grow(cur);
						cur.powerCnt++;
						if (cur.power > cur.powerCnt) pq.offer(cur);
						else die.offer(cur);
					}
				}
			}
			
			//큐의 크기
			System.out.println("+++");
			for (Cell c : pq) {
				System.out.println(c);
			}
			sb.append(pq.size()).append("\n");
			System.out.println(" ------------- ");
		}
		System.out.println(sb);
	}

	private static void grow(Cell cur) {
		int[] dr = {1, -1, 0, 0};
		int[] dc = {0, 0, 1, -1};
		for (int di = 0; di < 4; di++) {
			int nr = cur.r + dr[di];
			int nc = cur.c + dc[di];
			if (pq.contains(new Cell(nr, nc)) || die.contains(new Cell(nr, nc))) continue;
			System.out.println(nr + " " + nc);
			pq.offer(new Cell(nr, nc, cur.power, false, cur.createTime + 1));
		}
	}
	
}
