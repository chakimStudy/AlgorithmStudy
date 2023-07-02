package ps.브루트포스.sequence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_G3_14890_경사로 {
	static class Elem{
		int num;
		int len;
		public Elem(int num, int len) {
			super();
			this.num = num;
			this.len = len;
		}
		@Override
		public String toString() {
			return "Elem [num=" + num + ", len=" + len + "]";
		}
		
	}

	static int N, L;
	static int[][] map;
	
	static List<Elem>[] row;
	static List<Elem>[] col;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		//logic
		row = new ArrayList[N];
		col= new ArrayList[N];
		
	
		//row count
		for (int i = 0; i < N; i++) {
			row[i] = new ArrayList<>();
			int num = map[i][0];
			int len = 1;
			for (int j = 1; j < N; j++) {
				if (num == map[i][j]) {
					len++;
				}
				else {
					row[i].add(new Elem(num, len));
					num = map[i][j];
					len = 1;
				}
			}
			row[i].add(new Elem(num, len));
		}
		
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			boolean ok = true;
			Elem pre = row[i].get(0);
			for (int j = 1; j < row[i].size(); j++) {
				Elem cur = row[i].get(j);
//				System.out.println("pre: " + pre + " cur: " +cur);
				if (pre.num-1 == cur.num) {
					//수가 하나 작아짐 -> cur len이 L 이상 !
					cur.len -= L;
					if (cur.len < 0) {
						ok = false;
						break;//안됨
					}
				}
				else if (pre.num+1 == cur.num) {
					//수가 하나 커짐
					pre.len -= L;
					if (pre.len < 0) {
						ok = false;
						break;//안됨
					}
				}
				else {
					ok = false;
					break;//안됨
				}
				pre = cur;
			}
			if (ok) {
//				System.out.println("ok");
				cnt++;
			}
		}
		
		//col
		for (int j = 0; j < N; j++) {
			col[j] = new ArrayList<>();
			int num = map[0][j];
			int len = 1;
			for (int i = 1; i < N; i++) {
				if (num == map[i][j]) {
					len++;
				}
				else {
					col[j].add(new Elem(num, len));
					num = map[i][j];
					len = 1;
				}
			}
			col[j].add(new Elem(num, len));
		}
		
		for (int i = 0; i < N; i++) {
			boolean ok = true;
			Elem pre = col[i].get(0);
			for (int j = 1; j < col[i].size(); j++) {
				Elem cur = col[i].get(j);
//				System.out.println("pre: " + pre + " cur: " +cur);
				if (pre.num-1 == cur.num) {
					//수가 하나 작아짐 -> cur len이 L 이상 !
					cur.len -= L;
					if (cur.len < 0) {
						ok = false;
						break;//안됨
					}
				}
				else if (pre.num+1 == cur.num) {
					//수가 하나 커짐
					pre.len -= L;
					if (pre.len < 0) {
						ok = false;
						break;//안됨
					}
				}
				else {
					ok = false;
					break;//안됨
				}
				pre = cur;
			}
			if (ok) {
//				System.out.println("ok");
				cnt++;
			}
		}
		
		System.out.println(cnt);
		
	}
}
