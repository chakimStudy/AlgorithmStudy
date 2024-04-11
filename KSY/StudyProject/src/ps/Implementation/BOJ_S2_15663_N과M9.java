package ps.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_S2_15663_N과M9 {
    static int N, M;
    static int[] nums;
    static int[] selectNums;
    static boolean[] seleted;
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
        seleted = new boolean[N];
        dfs(0);

        result.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1.length != o2.length){
                    return o1.length - o2.length;
                }
                for (int i = 0; i < o1.length; i++){
                    if (o1[i] != o2[i]){
                        return o1[i] - o2[i];
                    }
                }
                return 0; //완전히 같은 경우
            }
        });

        StringBuilder sb = new StringBuilder();
        int[] preArr = null;
        for (int[] arr : result){
            if (preArr != null && isEqual(preArr, arr)) continue;

            for (int i = 0; i < arr.length; i++){
                sb.append(arr[i]).append(" ");
            }
            sb.append("\n");
            preArr = arr;
        }
        System.out.println(sb);
    }

    private static boolean isEqual(int[] preArr, int[] curArr) {
        for (int i = 0; i < preArr.length; i++){
            if (preArr[i] != curArr[i]) return false;
        }
        return true;
    }

    private static void dfs(int cnt) {
        if (cnt == M){
            result.add(selectNums.clone());
            return;
        }

        for (int i = 0; i < N; i++){
            if (seleted[i]) continue;

            seleted[i] = true;
            selectNums[cnt] = nums[i];
            dfs(cnt+1);
            seleted[i] = false;
        }
    }
}
