package ps.브루트포스.sequence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SWEA_6808_D3_규영이와인영이의카드게임 {

	static int N = 9;
	static List<Integer> num1 = new ArrayList<>();
	static int[] num2 = new int[N];
	static int sum1, sum2;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		
		int T = Integer.parseInt(in.readLine());
		int tc = 1;
		while(T-- > 0) {
			String[] line = in.readLine().split(" ");
			for (int i = 0; i < N; i++) {
				num1.add(Integer.parseInt(line[i]));
			}

			int idx = 0;
			for (int i = 1; i <= 18; i++) {
				if (!num1.contains(i)) num2[idx++] = i;
			}
			
			//logic
			//1. 오름차순 정렬된 형태임
			
			int win = 0;
			int lose = 0;
			do {
//				System.out.println(Arrays.toString(num2));
				
				score();
				if (sum1 > sum2) win++;
				else if (sum1 < sum2) lose++;
				//init
				sum1 = 0;
				sum2 = 0;
			}while(np());
			sb.append("#" + tc + " ").append(win).append(" ").append(lose).append("\n");
			tc++;
			
			//init
			num1.clear();
		}
		System.out.println(sb);
	}

	private static void score() {
		for (int i = 0; i < N; i++) {
			int sum = num1.get(i) + num2[i];
			if (num1.get(i) > num2[i]) sum1 += sum;
			else sum2 += sum;
		}
	}

	private static boolean np() {
		//2. 꼭대기(i) 찾고, i-1 찾는다.
		int i = N-1;
		while(i > 0 && num2[i-1] >= num2[i]) i--; //작아지는 순간 멈춤 -- i > 0이다!!
		//-----------return false;
		if (i == 0) return false; //이게 가장 큰 순열임.
		
		//3. 뒤에서부터 num2[i-1]보다 큰 값을 찾는다.
		int j = N-1;
		while(num2[i-1] >= num2[j]) j--;
		
		
		//4. swap
		swap(i-1, j);
		
		//5. 꼭대기 이후 자리들 오름차순 정렬
		int k = N-1;
		while(i < k) {
			swap(i++, k--);
		}

		return true;
	}

	private static void swap(int i, int j) {
		int temp = num2[i];
		num2[i] = num2[j];
		num2[j] = temp;
	}
}
