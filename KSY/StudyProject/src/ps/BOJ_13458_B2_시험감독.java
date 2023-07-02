package ps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_13458_B2_시험감독 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(in.readLine());
		String[] line = in.readLine().split(" ");
		long[] students = new long[N];
		for (int i = 0; i < N; i++) {
			students[i] = Integer.parseInt(line[i]);		
		}

		line = in.readLine().split(" ");
		long B = Integer.parseInt(line[0]);
		long C = Integer.parseInt(line[1]);


		//logic
		long bCnt = 0;
		long cCnt = 0;
		for (int i = 0; i < N; i++) {
			long rest = students[i];
			if (students[i] - B >= 0) {
				bCnt++;
				rest -= B;
			}
			cCnt += (rest / C);
			if (rest % C != 0) cCnt++;
		}
		System.out.println(bCnt + cCnt);
	}
}
