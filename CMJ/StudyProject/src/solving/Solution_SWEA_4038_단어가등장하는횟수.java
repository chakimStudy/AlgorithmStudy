package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_SWEA_4038_단어가등장하는횟수 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int exp1 = 31;
		int exp2 = 41;
		int exp3 = 47;
		
		int t = Integer.parseInt(in.readLine());
		for(int t_c = 1; t_c <= t; t_c++)
		{
			char[] B = in.readLine().toCharArray();
			char[] S = in.readLine().toCharArray();
			
			int B_hash1 = 0;
			int S_hash1= 0;
			int B_hash2 = 0;
			int S_hash2 = 0;
			int B_hash3 = 0;
			int S_hash3 = 0;
			
			int S_length = S.length;
			int B_length = B.length;
			
			int power1 = 1;
			int power2 = 1;
			int power3 = 1;
			
			int ans = 0;
			
			for(int i = S_length - 1; i >= 0; i--) {
				S_hash1 += S[i] * power1;
				B_hash1 += B[i] * power1;
				S_hash2 += S[i] * power2;
				B_hash2 += B[i] * power2;
				S_hash3 += S[i] * power3;
				B_hash3 += B[i] * power3;
				if(i > 0) {
					power1 *= exp1;
					power2 *= exp2;
					power3 *= exp3;
				}
			}
			
			if(S_hash1 == B_hash1 && S_hash2 == B_hash2 && S_hash3 == B_hash3) {
				ans = 1;
			}else {
				ans = 0;
			}
			
			
			for(int i = 1; i <= B_length - S_length; i++) {
				B_hash1 = exp1 * (B_hash1 - hash(B[i - 1], power1)) + B[i + S_length - 1];
				B_hash2 = exp2 * (B_hash2 - hash(B[i - 1], power2)) + B[i + S_length - 1];
				B_hash3 = exp3 * (B_hash3 - hash(B[i - 1], power3)) + B[i + S_length - 1];


				if(S_hash1 == B_hash1 && S_hash2 == B_hash2 && S_hash3 == B_hash3) {
					ans++;
				}
			}
			
			sb.append("#" + t_c + " " + ans + "\n");
		}
		System.out.println(sb);
	}
    private static int hash(int value, int power) {
        return value * power;
    }
}
