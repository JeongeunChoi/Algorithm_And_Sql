package algorithm;

import java.io.*;
import java.util.*;

public class BJ_13458 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] testCenter = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            testCenter[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        int B = Integer.parseInt(st.nextToken()), C = Integer.parseInt(st.nextToken());

        long answer = 0; // 자료형 long 사용하기!
        for (int i = 0; i < N; i++) {
            answer++;
            testCenter[i] -= B;
            if (testCenter[i] > 0) { // 총감독관만 필요한 경우 주의
                answer += testCenter[i] / C;
                if (testCenter[i] % C > 0) {
                    answer++;
                }
            }
        }

        System.out.println(answer);
    }
}
