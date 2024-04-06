package ps.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_G4_1967_트리의지름 {
    static class Node{
        int no, cost;
        public Node(int no, int cost){
            this.no = no;
            this.cost = cost;
        }
    }
    static ArrayList<Node>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        if (N == 1){
            System.out.println(0);
            return;
        }

        graph = new ArrayList[N+1];
        for (int i = 0; i < N+1; i++){
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < N-1; i++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[parent].add(new Node(child, cost));
            graph[child].add(new Node(parent, cost));
        }

        boolean[] visited = new boolean[N+1];
        visited[1] = true;
        dfs(1, 0, visited);
//        System.out.println(leaf);
        Arrays.fill(visited, false);
        visited[leaf] = true;
        maxSum = 0;
        dfs(leaf, 0, visited);
        System.out.println(maxSum);
    }

    static int maxSum = 0;
    static int leaf = -1;
    private static void dfs(int no, int sum, boolean[] visited) {
        if (maxSum < sum){
            maxSum = sum;
            leaf = no;
        }
        for (Node child : graph[no]){
            if (visited[child.no]) continue;
            visited[child.no] = true;
            dfs(child.no, sum + child.cost, visited);
        }
    }

}
