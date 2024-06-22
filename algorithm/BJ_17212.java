package algorithm.algorithm;

import java.io.*;

public class BJ_17212 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int moneyToPay = Integer.parseInt(br.readLine());
        int[] dp = new int[moneyToPay + 1]; // dp[i]는 i원을 지불한 최소 동전 개수

        for (int i = 1; i <= moneyToPay; i++) {
            if (i >= 1) {
                dp[i] = dp[i - 1] + 1;
            }
            if (i >= 2) {
                dp[i] = Math.min(dp[i], dp[i - 2] + 1);
            }
            if (i >= 5) {
                dp[i] = Math.min(dp[i], dp[i - 5] + 1);
            }
            if (i >= 7) {
                dp[i] = Math.min(dp[i], dp[i - 7] + 1);
            }
        }

        System.out.println(dp[moneyToPay]);
    }

}
