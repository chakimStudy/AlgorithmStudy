package ps.Bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_S3_15652_N과M4 {
    static int N, M;
    static int[] nums;
    static List<int[]> result;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nums = new int[M];
        result = new ArrayList<>();
        permutation(0, 1);
        Collections.sort(result, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                for (int i = 0; i < o1.length; i++){
                    if (o1[i] != o2[i])
                        return o1[i] - o2[i];
                }
                return 0;
            }
        });

        StringBuilder sb = new StringBuilder();
        for (int[] arr : result){
            for (int num : arr){
                sb.append(num).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void permutation(int cnt, int idx) {
        if (cnt == M){
            result.add(nums.clone());
            return;
        }

        for (int i = idx; i <= N; i++){
            nums[cnt] = i;
            permutation(cnt+1, i);
        }
    }
}
