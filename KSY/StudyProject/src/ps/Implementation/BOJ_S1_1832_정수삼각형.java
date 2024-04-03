package ps.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_S1_1832_정수삼각형 {
    static int N;
    static int[][] triangle;
    static int[][] sumArr;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        triangle = new int[N][N];
        for (int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int j = 0; j < i+1; j++){
                triangle[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        sumArr = new int[N][N];
        for (int i = 0; i < N; i++){
            Arrays.fill(sumArr[i], -1);
        }

        dfs(0, 0, 0); //행, 열, sum
        int maxVal = 0;
        for (int j = 0; j < N; j++){
            maxVal = Math.max(maxVal, sumArr[N-1][j]);
        }
        System.out.println(maxVal);
    }

    private static void dfs(int r, int c, int sum) {
        //현재 위치 값을 더했을 때 업데이트 되는 지 확인(가장 큰 지)
        sum += triangle[r][c];
        if (sumArr[r][c] < sum){
            sumArr[r][c] = sum;

            //현재 위치에서 왼쪽과 오른쪽 대각선으로 내려감
            if (r+1 < N){
                dfs(r+1, c, sum);
                if (c+1 < N) dfs(r+1, c+1, sum);
            }
        }
    }
}
