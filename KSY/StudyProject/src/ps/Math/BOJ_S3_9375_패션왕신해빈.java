package ps.Math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_S3_9375_패션왕신해빈 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(in.readLine());
        StringBuilder sb = new StringBuilder();
        while(T-- > 0){
            Map<String, Integer> map = new HashMap<>();
            int N = Integer.parseInt(in.readLine());
            while(N-- > 0){
                StringTokenizer st = new StringTokenizer(in.readLine());
                st.nextToken();
                String item = st.nextToken();
                map.put(item, map.getOrDefault(item, 0)+1);
            }

            int cnt = 1;
            for (String key : map.keySet()){
                cnt *= (map.get(key)+1);
            }
            sb.append(cnt-1).append("\n");
        }
        System.out.println(sb);
    }
}
