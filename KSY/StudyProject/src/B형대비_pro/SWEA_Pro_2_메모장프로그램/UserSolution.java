package B형대비_pro.SWEA_Pro_2_메모장프로그램;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

class UserSolution
{
	static List<Character> memo;
	static int cursor;
	static ListIterator<Character> iter;
	int H;
	int W;
	
	static int[][] count;
	
	void init(int H, int W, char mStr[])
	{
		//메모장 높이, 너비, 초기 문자열. \0으로 끝남
		memo = new ArrayList<>();
		cursor = 0;
		this.H = H;
		this.W = W;

		count = new int[28][H*W+1];
		//메모장 초기 값 저장
		for (int i = 0; mStr[i] != '\0'; i++) {
			memo.add(mStr[i]);
		}
		memo.add('\0'); //끝
		iter = memo.listIterator();
		
		for (int i = H*W-1; i >= 0 ;i--) {
			for (int ch = 0; ch < 26; ch++) {
				count[ch][i] = count[ch][i+1];
				if ((mStr[i]-'a') == ch) {
					count[ch][i]++;
				}
			}
		}
//		for (int i = 0; i < 28; i++) {
//			for (int j = 0; j < H*W; j++) {
//				System.out.print(count[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
//		System.out.println(memo);
		
	}
	
	void insert(char mChar)
	{
		//커서 위치에 문자 mChar 입력. 입력 후 커서는 새로 입력된 문자의 오른쪽. 메모장 초과할 일은 없음
//		memo.add(cursor, mChar);
		iter.add(mChar);
//		System.out.println(memo);
//		iter.next(); //가능? - 자동으로 감
		
		//카운트 업뎃

//		Arrays.fill(count[memo.get(memo.size()-1)-'a'], 0);
		
//		for (int i = memo.size()-2; i >= 0 ;i--) {
//			for (int ch = 0; ch < 26; ch++) {
//				count[ch][i] = count[ch][i+1];
//				if ((memo.get(i)-'a') == ch) {
//					count[ch][i]++;
//				}
//			}
//		}
		
		

//		for (int i = 0; i < 28; i++) {
//			for (int j = 0; j < H*W; j++) {
//				System.out.print(count[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
		cursor++;
	}

	char moveCursor(int mRow, int mCol)
	{
		/*
		 * 1. 커서의 위치를 r, c로 이동
		 * 	1. (r,c) 문자있음 : 해당 문자의 왼쪽으로 이동 - (r,c) 할당
		 * 	2. (r,c) 비어있음 : 문자열의 마지막 문자 오른쪽으로 이동 - 마지막 문자 다음 좌표 할당
		 * 2. 커서의 다음 문자를 리턴
		 * 	1. 다음 문자 있음 : 리턴
		 * 	2. 없음 : $ 리턴
		 */
		int nextCursor = (mRow-1) * W + (mCol-1);
		if (nextCursor >= memo.size()-1) {
			nextCursor = memo.size()-1; //마지막으로
			//move
			while (cursor < nextCursor) {
				cursor++;
				iter.next();
			}
			while (cursor > nextCursor) {
				cursor--;
				iter.previous();
			}
			
//			iter = memo.listIterator(cursor);
//			System.out.println(memo);
//			System.out.println('$');
			
			return '$';
		}
		else {
			//move
			while (cursor < nextCursor) {
				cursor++;
				iter.next();
			}
			while (cursor > nextCursor) {
				cursor--;
				iter.previous();
			}
			
//			iter = memo.listIterator(cursor);
//			System.out.println(memo);
			char ret = iter.next();
			iter.previous();
//			System.out.println(ret);
			return ret;
		}
		
	}

	int countCharacter(char mChar)
	{
		/*
		 * 커서 뒤쪽 문자열 중 mChar의 개수 리턴
		 */
//		int goCnt = 0;
//		int cnt = 0;
//		
//		while(iter.hasNext()) {
//			goCnt++;
//			if (iter.next() == mChar) cnt++;
//		}
//		while(goCnt-- > 0) iter.previous();
//		System.out.println(memo);
//		System.out.println(cnt);
//		System.out.println("cursor : " + cursor);
//		System.out.println(count[mChar-'a'][cursor]);
		return count[mChar-'a'][cursor];
	}
}