package ps.DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_S3_11727_2n타일링2 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(in.readLine());
        if (N == 1){
            System.out.println(1);
            return;
        }
        int[] dp = new int[N+1];
        dp[1] = 1;
        dp[2] = 3;
        int P = 10007;
        for (int i = 3; i <= N; i++){
            dp[i] = (dp[i-1] + 2*dp[i-2]) % P;
        }
        System.out.println(dp[N]);
    }
}
