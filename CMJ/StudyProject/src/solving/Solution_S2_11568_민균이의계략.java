package solving;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_S2_11568_민균이의계략 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		
		String[] str = in.readLine().split(" ");
		int[] numbers = new int[N];
		for(int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(str[i]);
		}		
		int[] dp = new int[N];
		
		for(int i = 0; i < N; i++) {
			dp[i] = 1;
		}
		
		int answer = 1;
		
		for(int i = 1; i < N; i++) {
			for(int j = 0; j < i; j++) {
				if(numbers[j] < numbers[i]) {
					if(dp[i] < dp[j] + 1) dp[i] = dp[j] + 1;
					if(answer < dp[i]) answer = dp[i];
				}
			}
		}
		System.out.println(answer);
	}
}
