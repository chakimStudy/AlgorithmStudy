package ps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class test {

    static char[][] map;
    static boolean[][] visited;
    
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    
    static int N, M;
    
    static int wolfCount, sheepCount;
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String str[] = in.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        map = new char[N][];
        visited = new boolean[N][M];
        for(int i = 0; i < N; i++) {
            map[i] = in.readLine().toCharArray();
        }
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] == 'v') {
                    wolfCount++;
                }
                if(map[i][j] == 'o') {
                    sheepCount++;
                }
            }
        }
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(!visited[i][j]) bfs(i, j);
            }
        }

        System.out.println(sheepCount + " " + wolfCount);
    }

    private static void bfs(int x, int y) {
        Queue<int[]> queue = new ArrayDeque<int[]>();
        List<int[]> wolfList = new ArrayList<>();
        List<int[]> sheepList = new ArrayList<>();
        int sheep = 0;
        int wolf = 0;
        queue.offer(new int[] {x, y});
        visited[x][y] = true;
        while(!queue.isEmpty()) {
            int[] now = queue.poll();
            for(int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if(!visited[nx][ny] && !(map[nx][ny] == '#')) {
                    queue.offer(new int[] {nx, ny});
                    visited[nx][ny] = true;
                    if(map[nx][ny]== 'v') {
                        wolf++;
                        wolfList.add(new int[] {nx, ny});
                    }
                    else if(map[nx][ny] == 'o') {
                        sheep++;
                        sheepList.add(new int[] {nx, ny});
                    }
                }
            }
        }
        if(sheep > wolf) {
            wolfCount -= wolf;
        }else {
            sheepCount -= sheep;
        }
    }
}