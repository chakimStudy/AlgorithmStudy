package ps.GraphSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ_G5_10026_적록색약 {
    static class Pos{
        int r, c;
        public Pos(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    static boolean[][] visited;
    static int N;
    static char[][] map, map2;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        map = new char[N][N];
        map2 = new char[N][N];
        for (int i = 0; i < N; i++){
            map[i] = in.readLine().toCharArray();
            for (int j = 0; j < N; j++){
                if (map[i][j] == 'R' || map[i][j] == 'G') map2[i][j] = 'S';
                else map2[i][j] = map[i][j];
            }
        }

        //bfs1 : 적록색약이 아닌 사람
        visited = new boolean[N][N];
        int cnt1 = 0;
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                if (visited[i][j]) continue;

                visited[i][j] = true;
                bfs1(new Pos(i, j));
                cnt1++;
            }
        }

        //bfs2 : 적록색약인사람
        visited = new boolean[N][N];
        int cnt2 = 0;
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                if (visited[i][j]) continue;

                visited[i][j] = true;
                bfs2(new Pos(i, j));
                cnt2++;
            }
        }

        System.out.println(cnt1 + " " + cnt2);
    }

    private static void bfs2(Pos start) {
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(start);

        while(!q.isEmpty()){
            Pos cur = q.poll();

            for (int di = 0; di < 4; di++){
                int nr = cur.r + dr[di];
                int nc = cur.c + dc[di];
                if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc] || map2[nr][nc] != map2[cur.r][cur.c]) continue;

                visited[nr][nc] = true;
                q.offer(new Pos(nr, nc));
            }
        }
    }

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    private static void bfs1(Pos start) {
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(start);

        while(!q.isEmpty()){
            Pos cur = q.poll();

            for (int di = 0; di < 4; di++){
                int nr = cur.r + dr[di];
                int nc = cur.c + dc[di];
                if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc] || map[nr][nc] != map[cur.r][cur.c]) continue;

                visited[nr][nc] = true;
                q.offer(new Pos(nr, nc));
            }
        }
    }
}
