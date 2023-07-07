package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Solution_G4_1967_트리의지름 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(in.readLine());
		List<List<int[]>> adj_list = new ArrayList<>();
		for(int i = 0; i < N + 1; i++) {
			adj_list.add(new ArrayList<int[]>());
		}
		String[] str;
		for(int i = 0; i < N - 1; i++) {
			str = in.readLine().split(" ");
			adj_list.get(Integer.parseInt(str[0])).add(new int[] {Integer.parseInt(str[1]), Integer.parseInt(str[2])});
		}
		
	}
}
