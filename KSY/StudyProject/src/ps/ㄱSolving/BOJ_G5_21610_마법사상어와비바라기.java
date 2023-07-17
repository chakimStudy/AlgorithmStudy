package ps.ㄱSolving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G5_21610_마법사상어와비바라기 {

    static int N, M;
    static int[][] map;
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
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++){
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        /*
        처음에 구름 위치 다 넣고
        이동한 위치에 물 증가
        증가한 칸에 물복사버그
        물이 2 이상인 칸에 구름 생성(사라진 칸 제외)
         */
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(new Pos(N-1, 0));
        q.offer(new Pos(N-1, 1));
        q.offer(new Pos(N-2, 0));
        q.offer(new Pos(N-2, 1));

        int[] dr = {0, -1, -1, -1, 0, 1, 1, 1};
        int[] dc = {-1, -1, 0, 1, 1, 1, 0, -1};

        while(M-- > 0){
            st = new StringTokenizer(in.readLine());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken()); //여기ㅓ 실수로 -1함

            //구름 이동한 칸에 물 1씩 증가 후 사라짐
            Queue<Pos> moveQ = new ArrayDeque<>();
            boolean[][] visited = new boolean[N][N]; //사라진 칸
            while(!q.isEmpty()){
                Pos cur = q.poll();

                int nr = getIdx(cur.r + dr[d]*s);
                int nc = getIdx(cur.c + dc[d]*s);
                moveQ.offer(new Pos(nr, nc));
                map[nr][nc]++;
                visited[nr][nc] = true;
            }

            //물이 증가한 칸에 물복사 버그 마법 시전
            while(!moveQ.isEmpty()){
                Pos cur = moveQ.poll();

                int cnt = 0;
                for (int di : new int[]{1, 3, 5, 7}){
                    int nr = cur.r + dr[di];
                    int nc = cur.c + dc[di];
                    if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                    if (map[nr][nc] > 0) cnt++;
                }
                map[cur.r][cur.c] += cnt; //물이 있는 수 만큼 증가
            }

            //물이 2 이상인 곳에 구름이 생기고 물의 양이 2 줄어듦
            for (int i = 0; i < N; i++){
                for (int j = 0; j < N; j++){
                    if (map[i][j] >= 2 && !visited[i][j]){
                        q.offer(new Pos(i, j));
                        map[i][j] -= 2;
                    }
                }
            }
        }

        int amount = 0;
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                amount += map[i][j];
            }
        }
        System.out.println(amount);
    }
    static public int getIdx(int i){
        while(i < 0) i += N;
        return i % N;
    }
}
