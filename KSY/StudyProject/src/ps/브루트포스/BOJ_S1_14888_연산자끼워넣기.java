package ps.브루트포스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S1_14888_연산자끼워넣기 {

	static int N;
	static int[] nums;
	static int[] operator;
	
	static int[] operLimit = new int[4];
	static int minRes = Integer.MAX_VALUE;
	static int maxRes = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		nums = new int[N];
		operator = new int[N-1];
		StringTokenizer st = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(in.readLine());
		operLimit[0] = Integer.parseInt(st.nextToken());
		operLimit[1] = Integer.parseInt(st.nextToken());
		operLimit[2] = Integer.parseInt(st.nextToken());
		operLimit[3] = Integer.parseInt(st.nextToken());
		
		permutation(0, new int[4]);
		
		System.out.println(maxRes);
		System.out.println(minRes);
	}


	private static void permutation(int cnt, int[] oper) {
		if (cnt == N-1) { //연산자는 N-1개(0~N-2)
			//다 고름
			int res = calculate();
			minRes = Math.min(minRes, res);
			maxRes = Math.max(maxRes, res);
		}
		
		for (int i = 0; i < 4; i++) {
			if (oper[i] + 1 > operLimit[i]) continue;
			
			//가능하면
			oper[i]++;
			operator[cnt] = i;
			permutation(cnt+1, oper);
			
			oper[i]--; //원래대로
		}
		
	}


	private static int calculate() {
		int res = nums[0]; // 첫 번째 숫자
		for (int i = 0; i < N-1; i++) {
			int num = nums[i+1];
			switch(operator[i]) {
			case 0: res += num; break;
			case 1: res -= num; break;
			case 2: res *= num; break;
			case 3: res /= num; break;
			}
		}
		return res;
	}
}
