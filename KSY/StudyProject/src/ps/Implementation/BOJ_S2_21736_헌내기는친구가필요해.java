package ps.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_S2_21736_헌내기는친구가필요해 {
    static class Pos{
        int r, c;
        public Pos(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        char[][] map = new char[N][M];
        Pos start = null;
        for (int i = 0; i < N; i++){
            map[i] = in.readLine().toCharArray();
            for (int j = 0; j < M; j++){
                if (map[i][j] == 'I') start = new Pos(i, j);
            }
        }

        Queue<Pos> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
        q.offer(start);
        visited[start.r][start.c] = true;
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        int cnt = 0;
        while(!q.isEmpty()){
            Pos cur = q.poll();

            for (int di = 0; di < 4; di++){
                int nr = cur.r + dr[di];
                int nc = cur.c + dc[di];
                if (nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc] || map[nr][nc] == 'X') continue;

                visited[nr][nc] = true;
                q.offer(new Pos(nr, nc));
                if (map[nr][nc] == 'P') cnt++;
            }
        }
        System.out.println((cnt > 0)? cnt : "TT");
    }
}
