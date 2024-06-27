package algorithm.algorithm;

import java.io.*;
import java.util.*;

public class BJ_1477 {

    private static int installRestArea(int[] restAreas, int distance) {
        int cnt = 0;

        // distance 거리 간격으로 설치할 수 있는 편의점 개수 구하기
        for (int i = 1; i < restAreas.length; i++) {
            int interval = restAreas[i] - restAreas[i - 1];
            cnt += interval / distance;
            if (interval % distance == 0) { // 같은 위치에 중복 설치 제거
                cnt--;
            }
        }

        return cnt;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int restAreaCnt = Integer.parseInt(st.nextToken()), restAreaCntToInstall = Integer.parseInt(st.nextToken()), highwayDistance = Integer.parseInt(st.nextToken()), answer = Integer.MAX_VALUE;
        int[] restAreas = new int[restAreaCnt + 2];
        st = new StringTokenizer(br.readLine());
        restAreas[0] = 0;
        for (int i = 1; i <= restAreaCnt; i++) {
            restAreas[i] = Integer.parseInt(st.nextToken());
        }
        restAreas[restAreaCnt + 1] = highwayDistance;
        Arrays.sort(restAreas);

        int l = 1, r = 0;
        // r 값은 현재 설치된 편의점 사이의 거리 중 가장 큰 값으로 설정!
        for (int i = 1; i <= restAreaCnt + 1; i++) {
            r = Math.max(r, restAreas[i] - restAreas[i - 1]);
        }

        while (l <= r) {
            int mid = (l + r) / 2;
            int restAreaCntCanInstall = installRestArea(restAreas, mid);
            if (restAreaCntCanInstall <= restAreaCntToInstall) {
                answer = Math.min(answer, mid);
                r = mid - 1;
            } else if (restAreaCntCanInstall > restAreaCntToInstall) {
                l = mid + 1;
            }
        }

        System.out.println(answer);
    }
}
