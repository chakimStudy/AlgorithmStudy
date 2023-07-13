package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Solution_S5_2161_카드1 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(in.readLine());
        Queue<Integer> queue = new ArrayDeque<>();
        for(int i = 1; i <= N; i++){
            queue.add(i);
        }
        while(queue.size() != 1){
            System.out.print(queue.poll() + " ");
            int temp = queue.poll();
            queue.offer(temp);
        }
        System.out.print(queue.poll());
    }
}