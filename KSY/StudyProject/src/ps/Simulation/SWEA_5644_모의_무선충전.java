package ps.Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_5644_모의_무선충전 {
	static class Pos{
		int r, c;

		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

	}
	static class BC{
		int r, c;
		int range;
		int perform;
		public BC(int r, int c, int range, int perform) {
			super();
			this.r = r;
			this.c = c;
			this.range = range;
			this.perform = perform;
		}

	}

	static int T, bcN;
	static int[] moveA, moveB;
	static BC[] bc;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();

		int TC = Integer.parseInt(in.readLine());
		int tc = 1;
		while(TC-- > 0) {
			sb.append("#").append(tc).append(" ");

			//input--------
			StringTokenizer st = new StringTokenizer(in.readLine());
			T = Integer.parseInt(st.nextToken());
			bcN = Integer.parseInt(st.nextToken());

			//in move
			moveA = new int[T];
			moveB = new int[T];
			st = new StringTokenizer(in.readLine());
			for (int i = 0; i < T; i++) {
				moveA[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(in.readLine());
			for (int i = 0; i < T; i++) {
				moveB[i] = Integer.parseInt(st.nextToken());
			}

			//in bc
			bc = new BC[bcN];
			for (int i = 0; i < bcN; i++) {
				st = new StringTokenizer(in.readLine());
				int r = Integer.parseInt(st.nextToken())-1;
				int c = Integer.parseInt(st.nextToken())-1;
				int range = Integer.parseInt(st.nextToken());
				int perf = Integer.parseInt(st.nextToken());

				bc[i] = new BC(c, r, range, perf);
			}

			//logic----------
			/*
			 * 시간을 1씩 증가시키면서 
			 * 
			 * 1. A, B현 위치에서 어디꺼 쓸 수 있는지 검사->각각 list
			 * 2. 모든 조합의 경우의 수를 검사하여, 가장 충전이 잘 되는거 선택
			 * 3. A, B충전량 더해주기
			 * 4. 위치 업뎃
			 */
			Pos curA = new Pos(0, 0);
			Pos curB = new Pos(9, 9);
			int chargeTotal = 0;
			List<Integer> useA, useB;
			for (int time = 0; time <= T; time++) {
				//검사
				useA = search(curA);
				useB = search(curB);

				//모든 조합
				combination(useA, useB);

				//충전량 더함
				chargeTotal += bestChar;
//				System.out.println(bestChar);
				
				if (time == T) break;
				//위치 업뎃
				curA = posUpdate(curA, moveA[time]);
				curB = posUpdate(curB, moveB[time]);
			}
			sb.append(chargeTotal).append("\n");
//			System.out.println("-------------");
			tc++;
		}
		System.out.println(sb);
	}
	private static Pos posUpdate(Pos pos, int i) {
		Pos newPos = new Pos(pos.r, pos.c);
		switch(i) {
		case 0: break;
		case 1: newPos.r--; break;
		case 2: newPos.c++; break;
		case 3: newPos.r++; break;
		case 4: newPos.c--; break;
		}
		return newPos;
	}
	static int bestIdxA, bestIdxB;
	static int bestChar;

	private static void combination(List<Integer> useA, List<Integer> useB) {
		bestChar = 0;
//		System.out.println(useA);
//		System.out.println(useB);
		
		if (useA.isEmpty() && useB.isEmpty()) return;
		else if (useA.isEmpty() && !useB.isEmpty()) {
			for (int i = 0; i < useB.size(); i++) {
				int chargeB = bc[useB.get(i)].perform;
				bestChar = Math.max(bestChar, chargeB);
			}
		}
		else if (!useA.isEmpty() && useB.isEmpty()) {
			for (int i = 0; i < useA.size(); i++) {
				int chargeA = bc[useA.get(i)].perform;
				bestChar = Math.max(bestChar, chargeA);
			}
		}
		else {
			//모든 조합
			for (int i = 0; i < useA.size(); i++) {
				for (int j = 0; j < useB.size(); j++) {
					int chargeA = 0;
					int chargeB = 0;
					if (useA.get(i) == useB.get(j)) {
						chargeA = bc[useA.get(i)].perform/2;
						chargeB = bc[useB.get(j)].perform/2;
					}
					else {
						chargeA = bc[useA.get(i)].perform;
						chargeB = bc[useB.get(j)].perform;
					}
					bestChar = Math.max(bestChar, chargeA + chargeB);
				}
			}
		}

	}
	private static List<Integer> search(Pos p) {
		List<Integer> ls = new ArrayList<>();

		for (int i = 0; i < bcN; i++) {
			int dest = getDist(p, i);
			if (dest <= bc[i].range) {
				//범위 이내이면 리스트에 추가
				ls.add(i);
			}
		}
		return ls;
	}
	private static int getDist(Pos p, int i) {
		return Math.abs(p.r - bc[i].r) + Math.abs(p.c - bc[i].c);
	}
}
