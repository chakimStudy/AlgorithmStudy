package ps.DataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ_S2_1927_최소힙 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        StringBuilder sb = new StringBuilder();
        while(N-- > 0){
            int num = Integer.parseInt(in.readLine());
            if (num > 0){
                //값을 추가
                pq.add(num);
            }
            else{
                //값을 뺌
                int n = 0;
                if (!pq.isEmpty()) n = pq.poll();
                sb.append(n).append("\n");
            }
        }
        System.out.println(sb);
    }
}
