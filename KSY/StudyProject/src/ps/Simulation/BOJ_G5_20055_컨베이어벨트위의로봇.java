package ps.Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_G5_20055_컨베이어벨트위의로봇 {

    static int[] line;
    static int N, K;


    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        line = new int[2*N];
        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < 2*N; i++){
            line[i] = Integer.parseInt(st.nextToken());
        }

        int upIdx = 0; //시작 idx
        int downIdx = N-1;
        List<Integer> robot = new ArrayList<>();
        int zeroCnt = 0; //내구도 0인 것의 개수
        int cnt = 0;
//        System.out.println("upIdx " + upIdx);
//        System.out.println("downIdx " + downIdx);
        while(zeroCnt < K){
            cnt++;
            //1. 회전 : 인덱스 변환. 로봇과 함께 회전
            upIdx = ((upIdx-1)+(2*N)) % (2*N);
            downIdx = ((downIdx-1)+(2*N)) % (2*N);
//            System.out.println("upIdx " + upIdx);
//            System.out.println("downIdx " + downIdx);

            //로봇이 끝에 도달하면.. 제거됨 : 연속적으로 제거해야 하므로 반대로 순회
            for (int i = robot.size()-1; i >= 0; i--){
                if (robot.get(i) == downIdx){
                    robot.remove(i);
                }
            }

            //2. 로봇만 한 칸 앞으로 이동 or 정지
            int preIdx = -2;
            for (int i = 0; i < robot.size(); i++){
                int curIdx = robot.get(i); //현재 로봇 위치
                int nextIdx = ((curIdx+1) % (2*N)); //한칸 이동 위치
                if (line[nextIdx] > 0 && preIdx != nextIdx){
                    //이동 가능하면 이동 + 내구도 감소
                    robot.set(i, nextIdx);
                    curIdx = nextIdx;
                    line[nextIdx]--;
                    if (line[nextIdx] == 0) zeroCnt++;
                }
                preIdx = curIdx;
            }
            //로봇이 끝에 도달하면.. 제거됨 : 연속적으로 제거해야 하므로 반대로 순회
            for (int i = robot.size()-1; i >= 0; i--){
                if (robot.get(i) == downIdx){
                    robot.remove(i);
                }
            }

            //3. 로봇을 올리는 위치에 하나 추가
            if (line[upIdx] > 0){
                robot.add(upIdx);
                line[upIdx]--;
                if (line[upIdx] == 0) zeroCnt++;
            }
        }
        System.out.println(cnt);

    }

}