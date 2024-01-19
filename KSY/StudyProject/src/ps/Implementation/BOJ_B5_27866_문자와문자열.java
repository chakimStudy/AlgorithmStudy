package ps.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_B5_27866_문자와문자열 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String word = in.readLine();
        int i = Integer.parseInt(in.readLine());
        System.out.println(word.charAt(i-1));
    }
}
