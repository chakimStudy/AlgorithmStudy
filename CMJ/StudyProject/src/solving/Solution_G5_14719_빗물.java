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
		System.out.println(ans);
	}

	private static void check(int y) {
		int length = water.length;
		
		int check1 = -1;
		int check2 = -1;
		
		for(int i = y - 1; i >= 0; i--) {
			if(check1 == -1) {
				if(water[i] >= water[y]) {
					check1 = water[i];
				}
			}else {
				if(water[i] > check1) {
					check1 = water[i];
				}
			}
			
		}
		for(int i = y + 1; i < length; i++) {
			if(check2 == -1) {
				if(water[i] >= water[y]) {
					check2 = water[i];
				}
			}else {
				if(water[i] > check2) {
					check2 = water[i];
				}
			}
		}
		
		if(check1 != -1 && check2 != -1) {
			if(check1 >= check2) {
				ans += check2 - water[y];
				
			}else {
				ans += check1 - water[y];
			}
		}
	}
}
