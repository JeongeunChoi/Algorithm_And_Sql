package algorithm.algorithm;

import java.io.*;
import java.util.*;

public class BJ_14501 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine()), answer = 0;
        int[] dp = new int[N + 1]; // 해당 시간까지 얻은 최대 이익

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken()), p = Integer.parseInt(st.nextToken());
            if (i + t - 1 <= N) {
                dp[i + t - 1] = Math.max(dp[i + t - 1], dp[i - 1] + p); // (i+t-1일까지 얻은 이익)과 (i-1일까지 얻은 이익 + 오늘 일하여 얻은 이익) 중 더 큰 값으로 넣기
                answer = Math.max(answer, dp[i + t - 1]); // 최대이익 구하기
            }
            dp[i] = Math.max(dp[i - 1], dp[i]); // i일까지 얻은 최대 이익 업데이트
        }

        System.out.println(dp[N]);
    }
}
