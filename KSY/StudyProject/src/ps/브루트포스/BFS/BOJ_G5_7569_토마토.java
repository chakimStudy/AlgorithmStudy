package ps.브루트포스.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G5_7569_토마토 {
	static class Pos{
		int h, r, c;
		int depth;

		public Pos(int h, int r, int c, int depth) {
			super();
			this.h = h;
			this.r = r;
			this.c = c;
			this.depth = depth;
		}

	}

	static int N, M, H;
	static int[][][] box;
	static int[][][] count;
	static int maxDay;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(in.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		box = new int[H][N][M];


		Queue<Pos> zeroQ = new ArrayDeque<>();
		Queue<Pos> oneQ = new ArrayDeque<>();
		
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(in.readLine());
				for (int k = 0; k < M; k++) {
					box[i][j][k] = Integer.parseInt(st.nextToken());
					if (box[i][j][k] == 0) zeroQ.offer(new Pos(i, j, k, 0));
					if (box[i][j][k] == 1) oneQ.offer(new Pos(i, j, k, 0));
				}
			}
		}
		if (oneQ.size() == H * N * M) {
			System.out.println(0);
			return;
		}

		//logic
		count = new int[H][N][M];
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				Arrays.fill(count[i][j], Integer.MAX_VALUE);
			}
		}

		//1에서 bfs
		//위, 아래, 왼쪽, 오른쪽, 앞, 뒤
		int[] dh = {-1, 1, 0, 0, 0, 0};
		int[] dr = {0, 0, -1, 1, 0, 0};
		int[] dc = {0, 0, 0, 0, -1, 1};
		
		while(!oneQ.isEmpty()) {
			Pos curPos = oneQ.poll();

			for (int di = 0; di < dh.length; di++) {
				int nh = curPos.h + dh[di];
				int nr = curPos.r + dr[di];
				int nc = curPos.c + dc[di];
				if (nh < 0 || nh >= H || nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
				if (box[nh][nr][nc] == -1 || box[nh][nr][nc] == 1) continue;
				
				//0일때만 아래
				count[nh][nr][nc] = Math.min(count[nh][nr][nc], curPos.depth+1);
				box[nh][nr][nc] = 1;
				oneQ.offer(new Pos(nh, nr, nc, curPos.depth+1));
			}
		}

		while(!zeroQ.isEmpty()) {
			Pos pos = zeroQ.poll();
			if (box[pos.h][pos.r][pos.c] == 0) {
				System.out.println("-1");
				System.exit(0);
				break;
			}
			maxDay = Math.max(maxDay, count[pos.h][pos.r][pos.c]);
		}
		
		System.out.println(maxDay);
	}
}

//logic
/*
 * 그냥 완탐같다.
 * 
 * 여러 방향 탐색..
 * 근데 이것도 저번에 한 것 처럼
 * box를 바로 바꾸면 바꾼게 다음에 영향을 미치니까..
 * 그냥 새로운 3차 배열 만들어서 거기에 적으면서 해야할듯
 * 그리고 그 결과를 다시 박스에 저장하려면
 * 깊은복사.
 *
 * N * M * H = 1000000 이정도면 괜찮겠찌?
 * 여기에 6방향
 * 
 * => 시간초과
 * 아님 !!!!! 이렇게 하면 너무 비효율적
 * 어차피 우리가 알고싶은건 토마토가 모두 익을 때까지 며칠이 걸리느냐이다.
 * 그렇기 때문에 for문 돌면서 0이 있으면 bfs 돌려서 가장  가까운 1이 어디있는지 찾음.
 * 그 거리 구함.
 * 모든 0에 대해 거리 구해서 가장 큰 값ㅅ이 답.
 * 
 * => 메모리 초과
 * : 엄청난 실수를 했다 ! visited를 만듦
 * 
 * => 시간초과..
 * 
 * 
 * 게시판
 * 익은 토마토에 대한 데이터 처리를 하셨으면 그 데이터는 삭제를 해야 하는데, 이 코드엔 그런 코드가 없어서,
 *  이미 있던 데이터까지 중복 처리를 하느라 시간이 걸리는 것 같습니다.
 *  처리한 데이터는 버리도록 바꿔보는게 좋을 것 같네요.
 *  추가로 현재 익은 토마토들을 처리하면, 그 토마토 위치는 더이상 처리 할 필요가 없습니다.
 *  큐를 2개 사용함으로써 1초마다 처리해야 하는 익은 토마토 데이터를 분리 할 수 있습니다 
 *  
 *  
 *  휴..
 *  나 처음에 1인거 다 입력받고
 *  각각을 시작으로 해서 bfs 돌렸는데
 *  그렇게 하면 검사가 너무 중복됨..
 *  
 *  그래서 일단 입력받을때 1인거 다 큐에 넣고 (이 큐는 이제 익은 토마토만 들어있음)
 *  큐에서 탐색하면서 해당 부분이 0이면 1로 만들어서(익혀서)큐에 넣고
 *  depth는 계속 갱신하고
 *  이런시긍로 하면 됨.!
 */


