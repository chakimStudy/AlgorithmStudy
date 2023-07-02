package ps.Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2512_S3_예산 {
	static int areaN;
	static long[] money;
	static long M;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		//input
		areaN = Integer.parseInt(in.readLine());
		money = new long[areaN];
		String[] line = in.readLine().split(" ");
		for (int i = 0; i < areaN; i++) {
			money[i] = Long.parseLong(line[i]);
		}	
		M = Integer.parseInt(in.readLine());
		
		//logic
		//오름차순 정렬
		Arrays.sort(money); 
		long saveMax = money[areaN-1];
		
		//예산 분배
		for (int i = 0; i < areaN; i++) {
			//예산으로 커버 가능
			if (money[i] <= M/(areaN-i)) {
				moneySub(i, money[i]);
			}
			//예산 부족하면 예산 n빵 가능한 만큼 일단 다 넣음. 예산 다 씀. 끝.
			else {
				moneySub(i, M/(areaN-i));
				break;
			}
		}
		
		
		//출력
		System.out.println(saveMax - money[areaN-1]);
	}

	private static void moneySub(int start, long val) {
		//money
		for (int i = start; i < areaN; i++) {
			money[i] -= val;
		}
		//남은예산
		M -= (val * (areaN-start));
	}



	/*
	 * 음..
	 * 120 110 140 150
	 * 485
	 * 가장 최소인 것
	 * 
	 * 10 0 30 40
	 * 485-440 = 45
	 * 
	 * 0 0 20 30
	 * 45-30 = 15
	 * 
	 * 0 0 13 23
	 * 15-14 = 1
	 * 
	 * 140-13 = 127..!!!
	 * 
	 * 정리해보면
	 * 1. 입력받은 돈 정렬 (인덱스 필요없으니까 정렬해도 상관없음)
	 * 2. money중 최솟값 골라서 (남은 예산/n=예산요청당 가능한 예산)과 비교
	 * 	2.1 money가 더 작다면 -> money 배열의 모든 값에 -money, 남은예산 계산
	 *  2.2 n빵한 예산이 더 작다면 -> money 배열의 모든 값에 -(남은예산/n), 남은예산 계산
	 * 	2번 과정을 반복하다가(남은예산/n) = 0이 되면 멈춤.
	 * 3. money 원래의 최댓값 - money[-1] = 해주면 답.
	 * 	
	 */
}
