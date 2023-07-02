package ps.브루트포스.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_D4_3124_최소스패닝트리2 {
	static class Vertex implements Comparable<Vertex>{
		int no;
		Vertex link;
		int w;
		
		public Vertex(int no, Vertex link, int w) {
			super();
			this.no = no;
			this.link = link;
			this.w = w;
		}
		@Override
		public String toString() {
			return "Vertex [no=" + no + ", link=" + link + ", w=" + w + "]";
		}
		@Override
		public int compareTo(Vertex o) {
			return w - o.w;
		}
		
	}

	static int V, E;
	static Vertex[] adjList;
	
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
			
			//Prim
			//간선의 정보를 인접리스트에 저장
			adjList = new Vertex[V+1];
			//입력
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(in.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				
				adjList[from] = new Vertex(to, adjList[from], w);
				adjList[to] = new Vertex(from, adjList[to], w);
			}

			long[] minEdge = new long[V+1]; //다른 정점에서 자신으로 연결하는 간선비용중 최소값
			Arrays.fill(minEdge, Integer.MAX_VALUE);
			
			//logic
			int vCnt = 0;
			long cost = 0L;
			minEdge[1] = 0; //임의의 시작점 비용 0
			
			Queue<Vertex> q = new PriorityQueue<>();
			for (Vertex adj = adjList[1]; adj != null; adj = adj.link) {
				q.offer(adj);
			}
			
			boolean[] visited = new boolean[V+1];
			visited[1] = true;
			vCnt++;
			
			while(!q.isEmpty()) {
				/**
				 *  1단계 (Priority Queue에서 최솟값을 뽑아야 하는 부분)
				 */
				// 신장트리에 연결되지 않은 정점 중 가장 유리한 비용의 정점을 선택
				Vertex minVertex = q.poll();
				if (visited[minVertex.no]) {  // 이미 신장트리에 포함되었다면
					continue;  // 다음 유리한 비용의 정점을 선택
				}

				// 선택된 정점을 신장트리에 포함시킴
				visited[minVertex.no] = true;

				// 비용 누적
				cost += minVertex.w;

				// (선택사항) 가지치기
				if (++vCnt == V) {
					break;
				}

				/**
				 * 2단계 (Priority Queue에 넣어야 되는 부분)
				 */
				// 선택된 정점 기준으로 신장트리에 포함되지 않은 다른 정점을 큐에 넣기
				for (Vertex temp = adjList[minVertex.no]; temp != null; temp = temp.link) {

					// 신장트리에 포함되지 않았고,
					// 선택된 정점과의 비용이 이전까지의 최소비용보다 작다면
					if (!visited[temp.no] && minEdge[temp.no] > temp.w) {
						minEdge[temp.no] = temp.w;  // 최소비용으로 갱신
						q.offer(temp);
					}
				}

			}
			
			sb.append(cost).append("\n");
		}
		System.out.println(sb);
	}
}
