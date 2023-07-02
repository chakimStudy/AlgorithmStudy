package ps.DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_S3_14501_퇴사 {

	/*
	 * 1일차
	 * - 하는 경우 - 1일차 돈 + (1+T)일차 이후의 최대 돈
	 * - 안 하는 경우 - (1+1)일차 이후의 최대 돈
	 * 
	 * 이렇게 하면 부분문제를 이용할 수가 없음...
	 * 뒤에서부터 해야함
	 * 
	 */
	
	static class Task{
		int day;
		int money;
		public Task(int day, int money) {
			super();
			this.day = day;
			this.money = money;
		}
		
	}
	
	static Task[] tasks;
	static int N;
	static int[] dp;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		tasks = new Task[N];
		for (int i = 0; i < N; i++) {
			String[] split = in.readLine().split(" ");
			tasks[i] = new Task(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
		}
		
		//dp
		dp = new int[N+1];
		Arrays.fill(dp, 0); //0으로 초기화
		
		for (int i = N-1; i >= 0; i--) {
			if (tasks[i].day + i > N) {
				//현재 값 포함 못 함
				dp[i] = dp[i+1];
			}
			else {
				//현재 값 포함 or 포함 안 하는 두 경우 중 최댓값 저장
				int curMax = (i + tasks[i].day >= N)? 0 : dp[i + tasks[i].day];
				dp[i] = Math.max(tasks[i].money + curMax, dp[i+1]); //마지막 원소의 i+1을 위해 dp[N+1]로 잡음
			}
		}
		
		System.out.println(dp[0]);
	}

}
