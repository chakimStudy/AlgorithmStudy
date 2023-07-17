package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_G4_15685_드래곤커브 {
	
	static class dragon {
		List<int[]> list;

		public dragon() {
			super();
			this.list = new ArrayList<>();
		}
		
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		
		dragon[] dragon_array = new dragon[N];
		for(int i = 0; i < N; i++) dragon_array[i] = new dragon();
		int[][] map = new int[250][250];
		for(int i = 0; i < N; i++) {
			String[] str = in.readLine().split(" ");
			int y = Integer.parseInt(str[0]);
			int x = Integer.parseInt(str[1]);
			dragon_array[i].list.add(new int[] {x, y});
			if(Integer.parseInt(str[2]) == 0) {
				dragon_array[i].list.add(new int[] {x, y + 1});
			}
			if(Integer.parseInt(str[2]) == 1) {
				dragon_array[i].list.add(new int[] {x - 1, y});			
			}
			if(Integer.parseInt(str[2]) == 2) {
				dragon_array[i].list.add(new int[] {x, y - 1});
			}
			if(Integer.parseInt(str[2]) == 3) {
				dragon_array[i].list.add(new int[] {x + 1, y});
			}
			curve(dragon_array[i], Integer.parseInt(str[3]));
			for(int j = 0; j < dragon_array[i].list.size(); j++) {
				int[] temp = dragon_array[i].list.get(j);
				map[temp[0]][temp[1]] = 1;
			}
		}
		int answer = 0;
		for(int i = 0; i < 101; i++) {
			for(int j = 0; j < 101; j++) {
				if(map[i][j] == 1 && map[i + 1][j] == 1 && map[i][j + 1] == 1 && map[i + 1][j + 1] == 1) answer++;
			}
		}
		System.out.println(answer);
	}

	private static void curve(dragon dragon, int generation) {
		for(int asd = 0; asd < generation; asd++) {
			int[] last = dragon.list.get(dragon.list.size() - 1);
			int size = dragon.list.size();
			for(int i = size - 2; i >= 0; i--) {
				int[] now = new int[] {dragon.list.get(i)[0], dragon.list.get(i)[1]};
				now[0] = now[0] - last[0];
				now[1] = now[1] - last[1];
				
				int temp = now[0];
				now[0] = now[1];
				now[1] = -temp;
				
				now[0] = now[0] + last[0];
				now[1] = now[1] + last[1];
				dragon.list.add(now);
			}
		}
	}
}
