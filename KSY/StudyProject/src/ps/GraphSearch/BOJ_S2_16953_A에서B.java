package ps.GraphSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_S2_16953_A에서B {
    static class Num{
        long num;
        int cnt;
        public Num(long num, int cnt){
            this.num = num;
            this.cnt = cnt;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        Queue<Num> q = new ArrayDeque<>();
        q.offer(new Num(A, 0));
        while(!q.isEmpty()){
            Num number = q.poll();

            if (number.num == B) {
                System.out.println(number.cnt + 1);
                return;
            }
            if (number.num > B) continue;

            long mul = number.num*2;
            if (mul >= 0 && mul <= 1000000000){
                q.offer(new Num(mul, number.cnt+1));
            }

            long plus = number.num*10 + 1;
            if (plus >= 0 && plus <= 1000000000){
                q.offer(new Num(plus, number.cnt+1));
            }
        }
        System.out.println(-1);
    }
}
