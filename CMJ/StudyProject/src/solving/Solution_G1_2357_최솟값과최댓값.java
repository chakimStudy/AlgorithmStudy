package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_G1_2357_최솟값과최댓값 {
	
	static class MaxSegmentTree{
		long[] tree;
		
		MaxSegmentTree(int n){
			double height = Math.ceil(Math.log(n) / Math.log(2)) + 1;
			tree = new long[(int)Math.pow(2, height) + 1];
		}
		
		long init(long[] array, int node, int start, int end) {
			if(start == end) return tree[node] = array[start];
			else return tree[node] = Math.max(init(array, 2*node, start, (start + end)/2), init(array, 2*node + 1, (start + end) / 2 + 1, end));
		}
		
		long getMax(int node, int start, int end, int left, int right) {
			if(start > right || end < left) return -1;
			else if(start >= left && end <= right) return tree[node];
			else {
				return Math.max(getMax(2*node, start, (start + end) / 2, left, right), getMax(2 * node + 1, (start + end) / 2 + 1, end, left, right));
			}
		}
	}
	
	static class MinSegmentTree{
		long[] tree;
		
		MinSegmentTree(int n){
			double height = Math.ceil(Math.log(n) / Math.log(2)) + 1;
			tree = new long[(int)Math.pow(2, height) + 1];
		}
		
		long init(long[] array, int node, int start, int end) {
			if(start == end) return tree[node] = array[start];
			else return tree[node] = Math.min(init(array, 2*node, start, (start + end)/2), init(array, 2*node + 1, (start + end) / 2 + 1, end));
		}
		
		long getMin(int node, int start, int end, int left, int right) {
			if(start > right || end < left) return 1000000001;
			else if(start >= left && end <= right) return tree[node];
			else {
				return Math.min(getMin(2*node, start, (start + end) / 2, left, right), getMin(2 * node + 1, (start + end) / 2 + 1, end, left, right));
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] str = in.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int M = Integer.parseInt(str[1]);
		
		long[] array = new long[N + 1];
		for(int i = 1; i < N + 1; i++) {
			array[i] = Integer.parseInt(in.readLine());
		}
		
		MaxSegmentTree max_Segment_tree = new MaxSegmentTree(N);
		max_Segment_tree.init(array, 1, 1, N);
		
		MinSegmentTree min_segment_tree = new MinSegmentTree(N);
		min_segment_tree.init(array, 1, 1, N);
		
		for(int i = 0; i < M ; i++) {
			str = in.readLine().split(" ");
			int start = Integer.parseInt(str[0]);
			int end = Integer.parseInt(str[1]);
			sb.append(min_segment_tree.getMin(1, 1, N, start, end) + " ");
			sb.append(max_Segment_tree.getMax(1, 1, N, start, end) + "\n");
		}
		System.out.println(sb);
	}
}
