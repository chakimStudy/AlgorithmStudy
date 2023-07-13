package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Solution_S1_12761_돌다리 {

    static int[] array;
    static int A, B;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String[] str = in.readLine().split(" ");
        A = Integer.parseInt(str[0]);
        B = Integer.parseInt(str[1]);
        int N = Integer.parseInt(str[2]);
        int M = Integer.parseInt(str[3]);
        array = new int[100001];
        array[N] = 0;
        int answer = bfs(N, M);
        sb.append(answer);
        System.out.print(sb);
    }

    private static int bfs(int N, int M) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{N, 0});
        while(!queue.isEmpty()){
            int[] temp = queue.poll();
            if(temp[0] == M){
                return temp[1];
            }
            if(temp[0] + 1 < 100001 && array[temp[0] + 1] == 0){
                array[temp[0] + 1] = temp[1] + 1;
                queue.add(new int[]{temp[0] + 1, temp[1] + 1});
            }
            if(temp[0] - 1 >= 0 && array[temp[0] - 1] == 0){
                array[temp[0] - 1] = temp[1] + 1;
                queue.add(new int[]{temp[0] - 1, temp[1] + 1});
            }
            if(temp[0] * A < 100001 && array[temp[0] * A] == 0){
                array[temp[0] * A] = temp[1] + 1;
                queue.add(new int[]{temp[0] * A, temp[1] + 1});
            }
            if(temp[0] * B < 100001 && array[temp[0] * B] == 0){
                array[temp[0] * B] = temp[1] + 1;
                queue.add(new int[]{temp[0] * B, temp[1] + 1});
            }
            if(temp[0] + A < 100001 && array[temp[0] + A] == 0){
                array[temp[0] + A] = temp[1] + 1;
                queue.add(new int[]{temp[0] + A, temp[1] + 1});
            }
            if(temp[0] - A >= 0 && array[temp[0] - A] == 0){
                array[temp[0] - A] = temp[1] + 1;
                queue.add(new int[]{temp[0] - A, temp[1] + 1});
            }
            if(temp[0] + B < 100001 && array[temp[0] + B] == 0){
                array[temp[0] + B] = temp[1] + 1;
                queue.add(new int[]{temp[0] + B, temp[1] + 1});
            }
            if(temp[0] - B >= 0 && array[temp[0] - B] == 0){
                array[temp[0] - B] = temp[1] + 1;
                queue.add(new int[]{temp[0] - B, temp[1] + 1});
            }
        }
        return 0;
    }
}