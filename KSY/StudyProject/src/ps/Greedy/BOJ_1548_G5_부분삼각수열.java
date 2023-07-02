package ps.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1548_G5_부분삼각수열 {

	static int N;
	static long[] nums;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(in.readLine());
		nums = new long[N];
		String[] line = in.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			nums[i] = Long.parseLong(line[i]);
		}

		//logic
		Arrays.sort(nums);
		
		//wow
		int maxLen = (N == 1)? 1 : 2;
		for (int i = 0; i < N-2; i++) {
			int j = i + 1;
			int len = 2; //기본값 2
			for (int k = j + 1; k < N; k++) {
				if (nums[i] + nums[j] > nums[k]) len++;
				else break;
			}
			maxLen = Math.max(maxLen, len);
		}
		
		System.out.println(maxLen);
	}
}

//문제가 모호해서 질문게시판 찾아봄 : N<3이면 i<j<k인 i,j,k가 없으니, i<j<k인 쌍에 대한 모든 명제를 자동으로 만족합니다.
/*
 * subset을 계속 뽑고, 선택된 subset이 삼각수열이 맞는지 확인하고, 길이 갱신.(유도조건)
 * 만약 선택된 subset이 삼각수열이 아니면 원소를 더 선택해봤자니까 그냥 리턴.(기저조건)
 * 
 * 흠 시간초과 나옴..
 * 생각해보면
 * N이 최대 50인데 그러면 최악 2^50인데
 * 그러면 너무 깊은거같음
 * 흠 그러면 ! 지금 이걸 bottom - up이라 하면 (맞는지 모르겠음)
 * top - down 형식으로!
 * 빼는걸 선택한다고 생각하면 길이 이용해서 가지치기 더 많이 할 수 있을 것 같다.
 * 
 * 움 근데 이것도 시간초과 나옴..
 * 해보니까 이렇게 하면 check에서 못걸러서.. 다른 게 더 필요할 거 같은데 잘 모르겠당.
 * 
 * 모르곘다!!
 * 
 * 그냥 찾아봄
 * 대박이당.
 * 정리하자면
 * 1. 입력받은 값들 정렬
 * 2. 두 개의 원소를 연속된 작은 수로 고정하고, 나머지 큰 수 하나를 가져와서..
 *  i + j > k를 만족하는 곳까지의 길이를 구함.
 *  최대길이를 구함 !
 *  답!
 *  
 *  그니까 나는 원래 조합에 조합을 썼는데
 *  그냥 정렬해서 이중for문ㄴ만 써서 풀어버릴 수 있다.
 *  
 *  <다시 볼 부분>
 *  삼각형의 조건 : 작은 두 수의 합 > 큰 수
 *  좀 아이디어를 내라..
 *  N 크기 보고 무지성 재귀 쓰지 마랏 
 */