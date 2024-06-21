package algorithm.algorithm;

import java.io.*;
import java.util.*;

public class BJ_16434 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int roomCnt = Integer.parseInt(st.nextToken());
        long power = Integer.parseInt(st.nextToken()), requiredVitality = 1, minRequiredVitality = 0;
        for (int i = 0; i < roomCnt; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken()), a = Integer.parseInt(st.nextToken()), h = Integer.parseInt(st.nextToken());
            if (t == 1) { // 몬스터 물리치기
                long attackedCnt = h / power;
                if (h % power > 0) {
                    attackedCnt++;
                }
                requiredVitality += a * (attackedCnt - 1);
//                아래와 같이 while 문을 통해 공격 횟수를 세아리면 비효율적
//                h -= power;
//                while (h > 0) {
//                    requiredVitality += a;
//                    h -= power;
//                }
            } else if (t == 2) { // 생명력 획득하기
                power += a;
                minRequiredVitality = Math.max(minRequiredVitality, requiredVitality);
                requiredVitality -= h;
                if (requiredVitality < 1) {
                    requiredVitality = 1;
                }
            }
        }
        minRequiredVitality = Math.max(minRequiredVitality, requiredVitality);

        System.out.println(minRequiredVitality);
    }
}
