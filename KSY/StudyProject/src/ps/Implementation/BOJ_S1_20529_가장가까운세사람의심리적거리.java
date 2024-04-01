package ps.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_S1_20529_가장가까운세사람의심리적거리 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(in.readLine());
        StringBuilder sb = new StringBuilder();
        while(T-- > 0){
            int N = Integer.parseInt(in.readLine());
            if (N > 32) {
                sb.append(0).append("\n");
                in.readLine();
                continue;
            }

            String[] mbtis = new String[N];
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int i = 0; i < N; i++){
                mbtis[i] = st.nextToken();
            }

            int minCnt = Integer.MAX_VALUE;
            for (int i = 0; i < N-2; i++) {
                for (int j = i + 1; j < N - 1; j++) {
                    for (int k = j + 1; k < N; k++) {
                        int cnt = 0;
                        for (int l = 0; l < 4; l++) {
                            if (mbtis[i].charAt(l) != mbtis[j].charAt(l)) cnt++;
                        }
                        for (int l = 0; l < 4; l++) {
                            if (mbtis[i].charAt(l) != mbtis[k].charAt(l)) cnt++;
                        }
                        for (int l = 0; l < 4; l++) {
                            if (mbtis[j].charAt(l) != mbtis[k].charAt(l)) cnt++;
                        }
                        minCnt = Math.min(minCnt, cnt);
                    }
                }
            }
            sb.append(minCnt).append("\n");
        }
        System.out.println(sb);
    }
}
