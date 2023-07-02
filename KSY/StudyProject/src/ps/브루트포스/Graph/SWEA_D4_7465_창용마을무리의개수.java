package ps.브루트포스.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class SWEA_D4_7465_창용마을무리의개수 {

    static int N, M;
    static int[] people;
    
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        
        int T = Integer.parseInt(in.readLine());
        int tc = 1;
        while(T-- > 0) {
            sb.append("#").append(tc).append(" ");
            
            StringTokenizer st = new StringTokenizer(in.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            
            makeSet();
            while(M-- > 0) {
                st = new StringTokenizer(in.readLine());
                int p1 = Integer.parseInt(st.nextToken());
                int p2 = Integer.parseInt(st.nextToken());
                
                union(p1, p2);
            }
            //set 이용
            sb.append(countRepresentative()).append("\n");
            
            tc++;
        }
        System.out.println(sb);
    }

    private static int countRepresentative() {
    	int cnt = 0;
        for (int i = 1; i <= N; i++) {
        	if (people[i] < 0) cnt++;
        }
        return cnt;
    }

    private static boolean union(int p1, int p2) {
        int pR1 = findSet(p1);
        int pR2 = findSet(p2);
        
        if (pR1 == pR2) return false;
        
        int min = Math.min(pR1, pR2);
        int max = Math.max(pR1, pR2);
        people[min] += people[max];
        people[max] = min;
        return true;
    }

    private static int findSet(int p) {
        if (people[p] < 0) return p;
        
        return people[p] = findSet(people[p]);
    }

    private static void makeSet() {
        people = new int[N+1];
        for (int i = 1; i <= N; i++) {
            people[i] = -1;
        }
    }
}