package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_G1_2042_구간합구하기 {

	static long[] segment_tree;
	
	static class SegmentTree{
		private long[] tree;
		
		SegmentTree(int n){
            double treeHeight = Math.ceil(Math.log(n)/Math.log(2))+1;
            // 트리의 노드 수 계산
            long treeNodeCount = Math.round(Math.pow(2, treeHeight));
            // 트리의 길이 설정
            tree = new long[Math.toIntExact(treeNodeCount)];
		}
		
		public long init(long[] arr, int node, int start, int end) {
			if(start == end) {
				return tree[node] = arr[start];
			}else {
				return tree[node] = init(arr, 2*node, start, (start + end) / 2) + init(arr, 2*node+1, (start+end) / 2 + 1, end);
			}
		}
		
		public long sum(int node, int start, int end, int left, int right) {
			if(start > right || end < left) return 0;
			else if(left <= start && right >= end) return tree[node];
			else return sum(2 * node, start, (start + end) / 2, left, right) + sum(2 * node + 1, (start + end) / 2 + 1, end, left, right);
		}
		
		public void update(int node, int start, int end, int idx, long diff) {
			if(idx < start || idx > end) return;
			else if(start == idx && end == idx) tree[node] = tree[node] + diff;
			else if(node >= start || node <= end) {
				tree[node] = tree[node] + diff;
				update(2 * node, start, (start + end) / 2, idx, diff);
				update(2 * node + 1, (start + end) / 2 + 1, end, idx, diff);
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] str = in.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int M = Integer.parseInt(str[1]);
		int K = Integer.parseInt(str[2]);
		
		SegmentTree segment_tree = new SegmentTree(N);
		
		long[] array = new long[N + 1];
		for(int i = 1; i <= N; i++) {
			array[i] = Long.parseLong(in.readLine());
		}
		
		segment_tree.init(array, 1, 1, N);
		
		for(int i = 0; i < M + K; i++) {
			str = in.readLine().split(" ");
			int cmd = Integer.parseInt(str[0]);
			if(cmd == 1) { // 바꾸는 연산
				long diff  = Long.parseLong(str[2]) - array[Integer.parseInt(str[1])];
				array[Integer.parseInt(str[1])] = Long.parseLong(str[2]);
				segment_tree.update(1, 1, N, Integer.parseInt(str[1]), diff);
			}else { // 합 구하는 연산
				sb.append(segment_tree.sum(1, 1, N, Integer.parseInt(str[1]), Integer.parseInt(str[2])) + "\n");
			}
		}
		System.out.println(sb);
		
	}

}
