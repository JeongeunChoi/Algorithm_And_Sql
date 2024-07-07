package algorithm.algorithm;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 냅색 알고리즘
 * 배낭에 담을 수 있는 무게의 최댓값이 정해져 있고,
 * 일정한 가치의 무게가 정해진 짐들을 배낭에 담을 때,
 * 가치의 합이 최대가 되는 조합을 찾는 알고리즘
 *
 * "제한된 자원"으로 "최적(최대 or 최소)의 이득"을 얻는 문제
 *
 * dp[k][w] 배낭의 무게 한도가 w이고, 1부터 k번째 짐까지 배낭에 넣을 수 있을 때 얻을 수 있는 최고 가치
 */

public class BJ_1535 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int power = 99;
        int peopleCnt = Integer.parseInt(br.readLine());
        int[][] dp = new int[peopleCnt + 1][power + 1];
        int[] losePower = new int[peopleCnt + 1];
        int[] getJoy = new int[peopleCnt + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= peopleCnt; i++) {
            losePower[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= peopleCnt; i++) {
            getJoy[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= peopleCnt; i++) {
            for (int j = 1; j <= power; j++) {
                if (j - losePower[i] >= 0) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - losePower[i]] + getJoy[i]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        System.out.println(dp[peopleCnt][power]);
    }
}
