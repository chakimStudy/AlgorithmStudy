package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution_S4_1337_올바른배열 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		int N = Integer.parseInt(in.readLine());
		
		List<Integer> list = new ArrayList<>();
		
		
		for(int i = 0; i < N; i++) {
			list.add(Integer.parseInt(in.readLine()));
		}
		
		Collections.sort(list);
		
		int answer = Integer.MAX_VALUE;
		
		for(int i = 0; i < list.size(); i++) {
			int temp = list.get(i);
			int count = 4;
			for(int j = temp + 1; j < temp + 5; j++) {
				if(list.contains(j)) count--;
			}
			if(answer > count) answer = count;
			
		}
		sb.append(answer);
		System.out.print(sb);
	}
}
