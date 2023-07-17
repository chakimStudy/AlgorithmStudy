package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//2차원 배열 누적 
public class Solution_G4_1451_직사각형으로나누기 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] str = in.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int M = Integer.parseInt(str[1]);
		int[][] big = new int[N][M];
		
		int sum = 0;
		for(int i = 0; i < N; i++) {
			str = in.readLine().split("");
			for(int j = 0; j < M; j++) {
				sum += Integer.parseInt(str[j]);
				big[i][j] = Integer.parseInt(str[j]);
			}
		}
		
		int answer = 0;
		
		//세로 세 개
		
		for(int i = 0; i < N - 2; i++) {
			for(int j = i + 1; )
		}
		
		//가로 세 개
		
		//위쪽 두 개
		
		//아래 두 개
		
		//왼쪽 두 개
		
		//오른쪽 두 개
		
	}
}
