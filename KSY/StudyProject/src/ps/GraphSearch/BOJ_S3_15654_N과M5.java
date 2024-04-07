package ps.GraphSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_S3_15654_N과M5 {
    static int N, M;
    static int[] nums;
    static int[] selectNums;
    static boolean[] selected;
    static List<int[]> result = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nums = new int[N];
        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < N; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }

        selectNums = new int[M];
        selected = new boolean[N];
        dfs(0);
        Collections.sort(result, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                for (int i = 0; i < M; i++){
                    if (o1[i] != o2[i]){
                        return o1[i] - o2[i]; //오름차순
                    }
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

    private static void dfs(int cnt) {
        if (cnt == M){
            result.add(selectNums.clone());
            return;
        }

        for (int i = 0; i < N; i++){
            if (selected[i]) continue;

            selected[i] = true;
            selectNums[cnt] = nums[i];
            dfs(cnt+1);
            selected[i] = false;
        }
    }
}
