package ps.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_S2_11725_트리의부모찾기 {
    static List<Integer>[] graph;
    static int N;
    static boolean[] visited;
    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        graph = new ArrayList[N+1];
        for (int i = 0; i < N+1; i++){
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N-1; i++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        visited = new boolean[N+1];
        visited[1] = true;
        parents = new int[N+1];
        dfs(1);

        StringBuilder sb = new StringBuilder();
        for (int i = 2; i < N+1; i++){
            sb.append(parents[i]).append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int parent) {
        for (int child : graph[parent]){
            if (visited[child]) continue;

            visited[child] = true;
            parents[child] = parent;
            dfs(child);
        }
    }
}
