package ps.ㄱSolving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_G3_17142_연구소3 {
    /*
    조합 + bfs
    조합 : 활성 바이러스 위치 선택
    bfs : 바이러스 퍼트리기
     */
    static class Pos{
        int r, c;
        int depth;
        public Pos(int r, int c){
            this.r = r;
            this.c = c;
        }
        public Pos(int r, int c, int depth){
            this.r = r;
            this.c = c;
            this.depth = depth;
        }

        @Override
        public String toString() {
            return "(" + r + ", " + c + ")";
        }
    }
    static int N, M;
    static int[][] map;
    static List<Pos> virus;
    static int minTime = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        virus = new ArrayList<>();
        for (int i = 0; i < N; i++){
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2){
                    virus.add(new Pos(i, j));
                }
            }
        }
        System.out.println(virus);

        //바이러스 위치 선택
        int virusN = virus.size();
        combRes = new int[M];
        comb(0, 0, virusN); //조합: 뽑은 원소 수, 시작 인덱스, 총 개수(종료)

        System.out.println(minTime);
    }

    static int[] combRes;
    private static void comb(int cnt, int start, int size) {
        if (cnt == M) {
            //원소 다 뽑음 -> 바이러스 퍼트림
//            System.out.println(Arrays.toString(combRes));
            int[][] cloneMap = new int[N][N];
            for (int i = 0; i < N; i++){
                cloneMap[i] = map[i].clone();
            }
            for (int i : combRes){
                cloneMap[virus.get(i).r][virus.get(i).c] = 3; //활성 바이러스 - cloneMap으로 안함..
            }
            spread(cloneMap);
            return;
        }

        for (int i = start; i < size; i++){
            combRes[cnt] = i;
            comb(cnt+1, i+1, size); //start + 1이 아니라 i + 1
        }
    }

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    private static void spread(int[][] cloneMap) {
        Queue<Pos> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];

        //활성 바이러스 위치를 큐에 넣음
        for (int i : combRes){
//            visited[virus.get(i).r][virus.get(i).c] = true;
            q.offer(new Pos(virus.get(i).r, virus.get(i).c, 0));
        }

        //bfs 수행
        int lastDepth = 0;
        while(!q.isEmpty()){
            Pos cur = q.poll();

            boolean isEnd = true;
            //다 차면 : 즉 0이 없음
            for (int i = 0; i < N; i++){
                if (isEnd) break;
                for (int j = 0; j < N; j++){
                    if (cloneMap[i][j] == 0) {
                        isEnd = false;
                        break;
                    }
                }
            }
            if (isEnd){
                minTime = Math.min(minTime, lastDepth);
                break;
            }

            for (int di = 0; di < 4; di++){
                int nr = cur.r + dr[di];
                int nc = cur.c + dc[di];
                if (nr < 0 || nr >= N || nc < 0 || nc >= N || cloneMap[nr][nc] == 1) continue;

                //벽과 인덱스 넘어선 경우를 제외하고는 바이러스가 퍼짐
//                visited[nr][nc] = true;
                lastDepth = cur.depth;
                if (cloneMap[nr][nc] != 3){ //0, 2인 경우
                    cloneMap[nr][nc] = 3;
                    q.offer(new Pos(nr, nc, cur.depth + 1));
                }
            }
        }


        for (int i = 0; i < N; i++){
            System.out.println(Arrays.toString(cloneMap[i]));
        }
        System.out.println(lastDepth);
//        System.out.println(lastDepth);


        //다 차면 : 즉 0이 없음
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                if (cloneMap[i][j] == 0) {
                    return;
                }
            }
        }
        minTime = Math.min(minTime, lastDepth);
    }
}
