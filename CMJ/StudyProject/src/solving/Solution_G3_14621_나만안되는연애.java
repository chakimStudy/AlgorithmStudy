package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Solution_G3_14621_나만안되는연애 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] str = in.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int M = Integer.parseInt(str[1]);
		
		boolean[] gender = new boolean[N + 1];
		str = in.readLine().split(" ");
		for(int i = 1; i <= str.length; i++) {
			if(str[i].equals("M")) gender[i] = true;
		}
		List<List<int[]>> adj_list = new ArrayList<List<int[]>>();
		for(int i = 0; i <= N; i++) {
			adj_list.add(new ArrayList<int[]>());
		}
		for(int i = 0; i <= N; i++) {
			adj_list.add(new ArrayList<int[]>());
		}
		for(int i = 0; i < M; i++) {
			str = in.readLine().split(" ");
			int start = Integer.parseInt(str[0]);
			int end = Integer.parseInt(str[1]);
			int power = Integer.parseInt(str[3]);
			adj_list.get(start).add(new int[] {end, power});
		}
	}
}
