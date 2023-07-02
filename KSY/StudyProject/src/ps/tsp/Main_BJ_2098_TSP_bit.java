package ps.tsp;

import java.util.Arrays;
import java.util.Scanner;

public class Main_BJ_2098_TSP_bit {

	static int N;
	static int [][] dist;
	static int [][] dp;
	static int min;
	static int INF=Integer.MAX_VALUE/100;
	public static void main(String[] args) {
		
		Scanner scann=new Scanner(System.in);
		N=scann.nextInt();
		dist=new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <N; j++) {
				dist[i][j]=scann.nextInt();
			}
		}
		dp=new int[1<<N][N];
		for (int i =0; i < 1<<N ; i++) {
			Arrays.fill(dp[i], -1);
		}
		min=tsp(1,0); //1:n개의 도시 방문여부(처음에는) / 0:집. (방금 방문한 도시). 이제 여기서 출발.
		
		System.out.println(min);
		
	}
	private static int tsp(int visited,int city) {
//		for (int i = 0; i < 1<<N; i++) {
//			System.out.println(Arrays.toString(dp[i]));
//		}
//		System.out.println();
		// 모두 방문했니 그러면 마지막 도시와 집과의 거리는?
		if(visited==((1<<N)-1)){ //다 1
			//만약 연결이 안되어 있다면 
			if(dist[city][0]==0) return INF;
			return dist[city][0];// 마지막 도시와 집까지 거리
		}
		// 도시 방문순서를 고려포함
		// 방문도시 순서에(이 도시 방문하려고 함) 같은 순서가 있어
		if(dp[visited][city]!=-1){ //처음에 -1로 초기화 했으니까. 이 말은 :"이미 업데이트 된 적이 있다면" 이다.
			return dp[visited][city]; //이미 전에 업뎃됐으면 그 값을 리턴
		}
		// 방문 표시
		dp[visited][city]=INF;
		for (int i = 0; i < N; i++) { //그 다음 도시 방문
			// 중복방문x
			if((visited &(1<<i))!=0)continue;
			// 도시가 연결안됨
			if(dist[city][i]==0)continue;
			
			int res=tsp(visited |(1<<i),i)+dist[city][i];
			dp[visited][city]=Math.min(res, dp[visited][city]); //갱신
		}
		return dp[visited][city];
	}
}