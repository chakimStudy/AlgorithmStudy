package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_G5_14719_빗물 {
	
	static int[] water;
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] str = in.readLine().split(" ");
		int H = Integer.parseInt(str[0]);
		int W = Integer.parseInt(str[1]);
		
		water = new int[W];
		ans = 0;
		
		str = in.readLine().split(" ");
		for(int i = 0; i < W; i++) {
			water[i] = Integer.parseInt(str[i]);
		}
		
		for(int i = 1; i < W - 1; i++) {
			check(i);
		}
	}

	private static void check(int y) {
		int length = water.length;
		for(int i = 0; i < y; i++) {
			
		}
		for(int i = y; i < length; i++) {
			
		}
	}
}
