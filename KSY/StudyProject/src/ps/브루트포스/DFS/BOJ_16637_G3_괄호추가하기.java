package ps.브루트포스.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_16637_G3_괄호추가하기 {
	static boolean[] selected;
	static int N;
	static int maxVal = Integer.MIN_VALUE;
	static String[] expression;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		expression = in.readLine().split("");
		selected = new boolean[N];
		
		//N = 1부터니까..! - 이거 하니까 90퍼에서 통과됨 ㅠㅠ - 문제를 잘 읽자
		if (expression.length == 1) maxVal = Integer.parseInt(expression[0]);
		else search(1);
		
		System.out.println(maxVal);
	}

	private static void search(int start) {
		if (start >= N) {
			return;
		}
		
		for (int i = start; i < N; i+=2) {
			if (!selected[i-1] && !selected[i+1] && !selected[i]){ //선택가능한 연산자 - 이 조건 바꾸니까 맞음...
				selected[i] = true;
				selected[i-1] = true;
				selected[i+1] = true;

				//괄호를 적절히 추가..
				int val = getMax(selected.clone());
				if (val > maxVal) maxVal = val;
				
				search(i + 2);
				
				selected[i] = false;
				selected[i-1] = false;
				selected[i+1] = false;
			}
		}
	}

	private static int getMax(boolean[] select) {
		List<String> rest = new ArrayList<>();
		
		for (int i = 0; i < expression.length; i++) {
			if (i % 2 == 0) { //숫자. 선택되지 않은.
				if (!select[i]) rest.add(expression[i]);
			}
			else {			//연산자
				if (select[i]) {
					int num1 = Integer.parseInt(expression[i-1]);
					int num2 = Integer.parseInt(expression[i+1]);
					int res = compute(num1, num2, expression[i]);
					rest.add(Integer.toString(res));
					i++; ///
				}
				else rest.add(expression[i]);
			}
		}

//		for (String re : rest) {
//			System.out.print(re + " ");
//		}
		//나머지 차례로 계산
		int num1 = 0;
		int num2 = 0;
		for (int i = 0; i < rest.size(); i++) {
			if (i % 2 == 0) { //숫자
				num1 = Integer.parseInt(rest.get(i));
			}
			else {
				num2 = Integer.parseInt(rest.get(i+1));
				int res = compute(num1, num2, rest.get(i));
				rest.set(i+1, Integer.toString(res));
			}
		}
//		System.out.println(" = " +num1);
		return num1;
	}

	private static int compute(int num1, int num2, String cmd) {
		int res = 0;
		switch(cmd) {
		case "+": res = num1 + num2; break;
		case "-": res = num1 - num2; break;
		case "*": res = num1 * num2; break;
		//default : System.out.println("error : " + cmd);
		}
		return res;
	}
}

/*
 * 완탐..?
 * 
 * 입력받은 line을 배열로 만들어서 하나씩 읽는다.
 * 
 * 3+8*7-9*2
 * 
 * 연산자가 나오면 순서대로
 * 괄호를 쓴다 -> 표시 -> 다음 연산자는 괄호 못 씀
 * 이런거 이용해서 재귀?
 * 
 * 표시할 때 처음에 연산자에만 표시했어서 인덱스 에러나고.. 그랬는데
 * 연산자와 괄호 안에 든 숫자 다 표시하니까
 * 에러도 안나고 정답 나옴. 
 * 
 * 아나 근데 80퍼에서 멈춤
 */
