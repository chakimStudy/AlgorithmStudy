package ps.GraphSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G5_16928_뱀과사다리게임 {
    static class Pos{
        int no;
        int depth;
        public Pos(int no, int depth){
            this.no = no;
            this.depth = depth;
        }
    }
    static int upN, downN;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine());
        upN = Integer.parseInt(st.nextToken());
        downN = Integer.parseInt(st.nextToken());
        int[] map = new int[101];
        for (int i = 0; i < upN + downN; i++){
            st = new StringTokenizer(in.readLine());
            map[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
        }

        Queue<Pos> q = new ArrayDeque<>();
        boolean[] visited = new boolean[101];
        q.offer(new Pos(1, 0));
        visited[1] = true;
        while(!q.isEmpty()){
            Pos cur = q.poll();

            for (int dice = 1; dice <= 6; dice++){
                int next = cur.no + dice;
                if (next <= 100 && map[next] != 0) next = map[next];
                if (next == 100) {
                    System.out.println(cur.depth + 1);
                    return;
                }

                if (next < 1 || next > 100 || visited[next]) continue;

                visited[next] = true;
                q.offer(new Pos(next, cur.depth+1));
            }
        }
    }
}
