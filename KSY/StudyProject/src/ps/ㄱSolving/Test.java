package ps.ㄱSolving;

import java.util.HashSet;
import java.util.Set;

public class Test {

	public static void main(String[] args) {
        int answer = 0;
        String s = "123";
        int N = 2;
        
        for (int i = 0; i < s.length() - N; i++){
            String sub = s.substring(i, i+N);

            Set<Character> set = new HashSet<>();
            for (char c : sub.toCharArray()){
                set.add(c);
            }
            if (set.size() == N){
                answer = Math.max(answer, Integer.parseInt(sub));
            }
        }
	}
}
