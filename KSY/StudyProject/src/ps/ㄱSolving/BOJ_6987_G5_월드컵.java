package ps.ㄱSolving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_6987_G5_월드컵 {

	static class Score{
		int win, same, lose;

		public Score() {
			win = 0;
			same = 0;
			lose = 0;
		}
		public Score(int win, int same, int lose) {
			super();
			this.win = win;
			this.same = same;
			this.lose = lose;
		}

		@Override
		public String toString() {
			return "Score [win=" + win + ", same=" + same + ", lose=" + lose + "]";
		}
		
	}
	static Score[] scores = new Score[6];
	static int N = 6;
	static StringBuffer sb = new StringBuffer();
	
	static int answer = 0;
	
	static boolean[][] isPlayed;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int T = 4;
		while(T-- > 0){
			//init
			isPlayed = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j <= i; j++) {
					isPlayed[i][i] = true; //이미 경기를 치룸
				}
			}
			//input
			StringTokenizer st = new StringTokenizer(in.readLine());
			for (int i = 0; i < N; i++) {
				int win = Integer.parseInt(st.nextToken());
				int same = Integer.parseInt(st.nextToken());
				int lose = Integer.parseInt(st.nextToken());
				
				scores[i] = new Score(win, same, lose);
			}
			for (int i = 0; i < N; i++) {
				System.out.println(scores[i]);
			}
			System.out.println();
			/*
			 * 각 나라마다 나머지 나라에 대해 승, 무, 패 집합으로 나누기
			 */
			
			play(0, 0);
			sb.append(answer).append(" ");
			
			
		}
		System.out.println(sb);
	}
	private static void play(int homeTeam, int awayTeam) {
		
		
		
		if (isPlayed[homeTeam][awayTeam]) {
			play(homeTeam, awayTeam + 1); //이미 붙은 애는 다른 팀이랑
			return;
		}
		
		//경기 시작
		
		isPlayed[homeTeam][awayTeam] = true;
		
		//승 - 무승부 - 패 : 순서로 dfs
		
		//홈 팀이 승
		scores[homeTeam].win += 1;
		
	}
	
}
