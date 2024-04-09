package ps.GraphSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G3_2206_벽부수고이동하기 {
    static int N, M;
    static int[][] map;
    static class Pos{
        int r, c;
        int depth, broken;
        public Pos(int r, int c, int depth, int broken){
            this.r = r;
            this.c = c;
            this.depth = depth;
            this.broken = broken;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++){
            String[] line = in.readLine().split("");
            for (int j = 0;j < M; j++){
                map[i][j] = Integer.parseInt(line[j]);
            }
        }

        //bfs
        Queue<Pos> q = new ArrayDeque<>();
        boolean[][][] visited = new boolean[N][M][2];
        q.offer(new Pos(0, 0, 1, 0));
        visited[0][0][0] = true;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        while(!q.isEmpty()){
            Pos cur = q.poll();

            if (cur.r == N-1 && cur.c == M-1){
                System.out.println(cur.depth);
                return;
            }

            for (int di = 0; di < 4; di++){
                int nr = cur.r + dr[di];
                int nc = cur.c + dc[di];
                if (nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc][cur.broken]) continue;

                if (map[nr][nc] == 1){
                    //벽이 있다면
                    //이미 한 번 부셨다면 패스
                    if (cur.broken >= 1) continue;
                    //아니라면 부시고 가기
                    visited[nr][nc][cur.broken+1] = true;
                    q.offer(new Pos(nr, nc, cur.depth+1, cur.broken+1));
                }
                else{
                    //벽이 없다면 그냥 가기
                    visited[nr][nc][cur.broken] = true;
                    q.offer(new Pos(nr, nc, cur.depth+1, cur.broken));
                }
            }
        }
        System.out.println(-1);
    }
}
