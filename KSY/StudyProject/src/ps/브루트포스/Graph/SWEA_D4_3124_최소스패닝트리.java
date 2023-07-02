package ps.브루트포스.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class SWEA_D4_3124_최소스패닝트리 {

	static class Edge{
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		
	}
	static int V, E;
	static int[] set;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		
		int T = Integer.parseInt(in.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			
			StringTokenizer st;
			st = new StringTokenizer(in.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			//kruskal
			
			Edge[] edges = new Edge[E];
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(in.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				edges[i] = new Edge(a, b, w);
			}

			Arrays.sort(edges, new Comparator<Edge>() {

				@Override
				public int compare(Edge o1, Edge o2) {
					return o1.weight - o2.weight; //오름차순
				}
				
			});
			
			makeSet();
			
			long wSum = 0;
			int vCnt = 0;
			for (Edge edge : edges) {
				if (findSet(edge.from) != findSet(edge.to)) {
					union(edge.from, edge.to);
					wSum += edge.weight;
					vCnt++;
					if (vCnt == V-1) break;
				}
			}
			sb.append(wSum).append("\n");
		}
		System.out.println(sb);
	}
	private static void union(int from, int to) {
		int root1 = findSet(from);
		int root2 = findSet(to);
		set[root2] = root1;
	}
	private static int findSet(int v) {
		if (v == set[v]) return v;
		
		return set[v] = findSet(set[v]);
	}
	private static void makeSet() {
		set = new int[V+1];
		for (int i = 1; i <= V; i++) {
			set[i] = i;
		}
	}
}
