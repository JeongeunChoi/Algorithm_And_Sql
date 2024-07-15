package algorithm.algorithm;

import java.io.*;
import java.util.*;

public class BJ_22862 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());
        int[] sequence = new int[N + 1], oddCntSum = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            sequence[i] = Integer.parseInt(st.nextToken());
            oddCntSum[i] = sequence[i] % 2 != 0 ? oddCntSum[i - 1] + 1 : oddCntSum[i - 1];
        }

        int lt = 0, rt = 1, answer = 0;
        while (lt < rt && rt <= N) {
            int oddCnt = oddCntSum[rt] - oddCntSum[lt]; // lt와 rt 사이의 홀수 개수 계산
            if (oddCnt <= K) { // 홀수 개수가 K보다 작거나 같으면
                answer = Math.max(answer, rt - lt - oddCnt); // 홀수 삭제 시의 연속된 짝수 개수 계산 후 기존 answer 값과 비교해서 더 큰 값 저장
                rt++; // rt 증가하여 범위 늘려보기
            } else { // 홀수 개수가 K보다 크면
                lt++; // lt 증가하여 범위 좁혀보기
            }
        }

        System.out.println(answer);
    }

}
