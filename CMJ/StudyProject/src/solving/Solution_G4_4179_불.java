package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;


public class Solution_G4_4179_불 {

    static int R, C;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] str = in.readLine().split(" ");
        R = Integer.parseInt(str[0]);
        C = Integer.parseInt(str[1]);
        char[][] map = new char[R][C];
        for(int i = 0; i < R; i++){
            map[i] = in.readLine().toCharArray();
        }
        int[] jihoon = new int[2];
    	Queue<int[]> queue1 = new ArrayDeque<>();

        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                if(map[i][j] == 'J'){
                    jihoon = new int[] {i, j, 0};
                }
                if(map[i][j] == 'F'){
                    queue1.offer(new int[] {i, j, 0});
                } 
            }
        }
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(jihoon);

        boolean[][] visited = new boolean[R][C];
        visited[jihoon[0]][jihoon[1]] = true;
        
        int depth = 0;
        int dx[] = {1, -1, 0, 0};
        int dy[] = {0, 0, 1, -1};
        while (!queue.isEmpty()){
            int[] temp = queue.poll();
            if(map[temp[0]][temp[1]] == 'F' ) {
            	continue;
            }
            for(int i = 0; i < 4; i++){
                int nx = temp[0] + dx[i];
                int ny = temp[1] + dy[i];
                if(nx < 0 || nx >= R || ny < 0 || ny >= C) {
                	System.out.println(temp[2] + 1);
                	System.exit(0);
                }
                if(visited[nx][ny] || map[nx][ny] == 'F' || map[nx][ny] == '#') continue;
                queue.offer(new int[]{nx, ny, temp[2] + 1});
                visited[nx][ny] = true;
            }
            if(depth == temp[2]) {

            	while(!queue1.isEmpty()) {
            		int[] temp2 = queue1.poll();
            		if(temp2[2] != depth) {
            			queue1.offer(temp2);
            			break;
            		}
            		for(int k = 0; k < 4; k++){
                        int nx = temp2[0] + dx[k];
                        int ny = temp2[1] + dy[k];
                        if(nx < 0 || nx >= R || ny < 0 || ny >= C || map[nx][ny] == '#') continue;
                        if(map[nx][ny] == '.') {
                        	map[nx][ny] = 'F';
                        	queue1.offer(new int[] {nx, ny, temp[2] + 1});
                        }
                    }
            	}
            	depth++;
            }
        }
        System.out.println("IMPOSSIBLE");
    }
}