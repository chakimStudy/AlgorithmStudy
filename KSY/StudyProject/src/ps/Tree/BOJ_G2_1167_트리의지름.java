package ps.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_G2_1167_트리의지름 {
    static class Node{
        int no;
        int cost;
        public Node(int no, int cost){
            this.no = no;
            this.cost = cost;
        }
    }
    static List<Node>[] graph;
    static int V;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        V = Integer.parseInt(in.readLine());
        graph = new ArrayList[V+1];
        for (int i = 0; i < V+1; i++){
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < V; i++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            int start = Integer.parseInt(st.nextToken());
            while(true){
                int end = Integer.parseInt(st.nextToken());
                if (end == -1) break;
                int cost = Integer.parseInt(st.nextToken());
                graph[start].add(new Node(end, cost));
            }
        }

        //dfs 수행. leaf 노드 찾기
        visited = new boolean[V+1];
        visited[1] = true;
        dfs(1, 0);

        Arrays.fill(visited, false);
        visited[leafNode] = true;
        maxSum = 0;
        dfs(leafNode, 0);
        System.out.println(maxSum);
    }

    static boolean[] visited;
    static long maxSum = 0;
    static int leafNode = 0;

    private static void dfs(int no, int sum) {
        if (maxSum < sum){
            maxSum = sum;
            leafNode = no;
        }

        for (Node adj : graph[no]){
            if (visited[adj.no]) continue;

            visited[adj.no] = true;
            dfs(adj.no, sum+adj.cost);
        }
    }
}
