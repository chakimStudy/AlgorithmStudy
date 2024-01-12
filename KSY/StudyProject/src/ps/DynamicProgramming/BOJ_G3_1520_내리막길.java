package ps.DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_G3_1520_내리막길 {
    static int N, M;
    static int[][] map;
    static int[][] dp;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++){
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //logic
        dp = new int[N][M];
        visited = new boolean[N][M];
        dfs(0, 0);

        System.out.println(dp[0][0]);
    }

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    private static int dfs(int r, int c) {
//        System.out.println(r + " " + c);
        if (r == N-1 && c == M-1) {
            dp[r][c] = 1;
            return 1;
        }

        //주변으로 이동
        for (int di = 0; di < 4; di++){
            int nr = r + dr[di];
            int nc = c + dc[di];
            if (nr < 0 || nr >= N || nc < 0 || nc >= M || map[r][c] <= map[nr][nc]) continue;

            if (visited[nr][nc]){
                dp[r][c] += dp[nr][nc];
            }
            else{
                visited[nr][nc] = true;
                dp[r][c] += dfs(nr, nc);
            }
        }
        return dp[r][c];
    }
}