package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_G4_1707_이분그래프 {

    static List<List<Integer>> adj_list;
    static int visited[];

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int K = Integer.parseInt(in.readLine());

        for(int t_c = 0; t_c < K; t_c++){
            adj_list = new ArrayList<>();
            String[] str = in.readLine().split(" ");
            int V = Integer.parseInt(str[0]);
            int E = Integer.parseInt(str[1]);
            answer = "YES";
            for(int i = 0; i < V + 1; i++){
                adj_list.add(new ArrayList<>());
            }
            for(int i = 0; i < E; i++){
                str = in.readLine().split(" ");
                int start = Integer.parseInt(str[0]);
                int end = Integer.parseInt(str[1]);
                adj_list.get(start).add(end);
                adj_list.get(end).add(start);
            }
            visited = new int[V + 1];

            for(int i = 1; i < V + 1; i++){
                if(visited[i] == 0){
                    dfs(i, 1);
                }
            }
            System.out.println(answer);
        }
    }

    static String answer;
    private static void dfs(int now, int color) {
        visited[now] = color;
        for(int vertex:adj_list.get(now)){
            if(visited[vertex] == 0){
                dfs(vertex, -color);
            }
            else if(color == visited[vertex]){
                answer = "NO";
            }
        }
    }
}
