package ps.DynamicProgramming;

import java.util.Scanner;

public class BOJ_2193_S3_이친수 {
	static int N;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		long[] num0 = new long[N+1];
		long[] num1 = new long[N+1];
		num0[0] = 0; 
		num0[1] = 0; 
		num1[0] = 0; 
		num1[1] = 1; //1
		for (int i = 2; i <= N; i++) {
			num0[i] = num0[i-1] + num1[i-1];
			num1[i] = num0[i-1];
		}
		
		System.out.println(num0[N] + num1[N]);
	}
}


/*
 * length 0
 * 1
 * 10
 * 101/100
 * 1: 1
 * 0: 1
 * 
 * len 4
 * 1 - 1010
 * 0 - 1001/1000
 * 1: 1
 * 0: 2
 * 
 * 1 - 10010
 * 0 - 10101, 10001
 *   - 10100, 10000
 * 1: 2
 * 0: 3
 * 
 * 
 * 1 - 101010, 100010
 * 0 - 100100, 101000, 100000
 * 	 - 100101, 101001, 100001
 * 1: 3 ( 이전의 0의 절반 )
 * 0: 5 ( 이전의 1+ 이전의 0)
 * 
 * 
 * 0 1 1 2 (1 + 1*2) (1 + 2*2)
 */
