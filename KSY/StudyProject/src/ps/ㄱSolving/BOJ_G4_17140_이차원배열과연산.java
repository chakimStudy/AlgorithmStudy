//package ps.ㄱSolving;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.*;
//
//public class BOJ_G4_17140_이차원배열과연산 {
//    /*
//    처음에는 이차원 리스트를 쓰려고 하다가
//    행 정렬은 괜찮은데 열 정렬이 힘들 거 같아서 별로일 거 같음
//     */
//
//    static int[][] A;
//    public static void main(String[] args) throws IOException {
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//        int r, c, k;
//        StringTokenizer st = new StringTokenizer(in.readLine());
//        r = Integer.parseInt(st.nextToken()) - 1;
//        c = Integer.parseInt(st.nextToken()) - 1;
//        k = Integer.parseInt(st.nextToken());
//
//        //초기 배열 입력
//        A = new int[100][100];
//        for (int i = 0; i < 3; i++){
//            st = new StringTokenizer(in.readLine());
//            for (int j = 0; j < 3; j++){
//                A[i][j] = Integer.parseInt(st.nextToken());
//            }
//        }
//
//        boolean isOk = false;
//        int time = 1;
//        int rowL = 3, colL = 3;
//        for (time = 1; time <= 100; time++){
//            if (A[r][c] == k){
//                isOk = true;
//                break;
//            }
//
//            if (rowL >= colL){
//                //R연산 : 모든 행에 대해 정렬
//                rowL = sortR(colL);
//            }
//            else{
//                //C연산
//
//            }
//
//        }
//
//        if (isOk) System.out.println(time);
//        else System.out.println(-1);
//    }
//
//    private static int sortR(int colL) {
//        int maxRowL = 0;
//        for (int r = 0; r < colL; r++){
//            //각 row를 돌면서 정렬
//            int[][] nums = new int[101][2]; //1~100
//            for (int i = 0; i <= 100; i++){
//                nums[i][0] = i; //0: 숫자, 1: 카운트
//            }
//            for (int c = 0; c < colL; c++){
//                if (A[r][c] == 0) break;
//                nums[A[r][c]][1]++; //카운트 증가
//            }
//            //카운트로 오름차순 정렬
//            Arrays.sort(nums, new Comparator<int[]>() {
//                @Override
//                public int compare(int[] o1, int[] o2) {
//                    return o1[1] - o2[1];
//                }
//            });
//
//
//
//        }
//    }
//
//}
