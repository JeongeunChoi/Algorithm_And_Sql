package algorithm.algorithm;

import java.io.*;
import java.util.*;

public class BJ_17420 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int gifticonCnt = Integer.parseInt(br.readLine());
        long totalExtensionCnt = 0;
        StringTokenizer remainDays = new StringTokenizer(br.readLine());
        StringTokenizer usingDays = new StringTokenizer(br.readLine());
        Gifticon[] gifticons = new Gifticon[gifticonCnt];
        for (int i = 0; i < gifticonCnt; i++) {
            gifticons[i] = new Gifticon(Integer.parseInt(remainDays.nextToken()), Integer.parseInt(usingDays.nextToken()));
        }

        Arrays.sort(gifticons); // 사용일 기준 내림차순, 같으면 남은 기한 기준 내림차순 정렬

        int maxRemainDayBeforeCurrentDay = gifticons[0].remainDay, maxRemainDay = gifticons[0].remainDay;
        for (int i = 0; i < gifticonCnt; i++) {
            if (gifticons[i].remainDay < gifticons[i].usingDay) { // 사용일 보다 남은 기한이 적으면 연장
                int extensionCnt = (int) Math.ceil((gifticons[i].usingDay - gifticons[i].remainDay) / 30.0);
                gifticons[i].extendRemainDay(extensionCnt);
                totalExtensionCnt += extensionCnt;
            }
            if (gifticons[i].remainDay < maxRemainDayBeforeCurrentDay) { // 오늘 이전 날까지 최대 남은 기한보다 남은 기한이 적으면 연장 -> 강박증 해결하기
                int extensionCnt = (int) Math.ceil((maxRemainDayBeforeCurrentDay - gifticons[i].remainDay) / 30.0);
                gifticons[i].extendRemainDay(extensionCnt);
                totalExtensionCnt += extensionCnt;
            }
            maxRemainDay = Math.max(maxRemainDay, gifticons[i].remainDay);
            if (i < gifticonCnt - 1 && gifticons[i].usingDay != gifticons[i + 1].usingDay) { // 오늘 날짜와 다음날 날짜가 다르면 최대 남은 기한 지금까지 남은 기한 중 제일 큰 값으로 설정
                maxRemainDayBeforeCurrentDay = Math.max(maxRemainDayBeforeCurrentDay, maxRemainDay);
            }
        }
        System.out.println(totalExtensionCnt);
    }

    static class Gifticon implements Comparable<Gifticon> {

        int remainDay, usingDay;

        Gifticon(int remainDay, int usingDay) {
            this.remainDay = remainDay;
            this.usingDay = usingDay;
        }

        public int compareTo(Gifticon other) {
            if (this.usingDay != other.usingDay) {
                return this.usingDay - other.usingDay;
            } else {
                return this.remainDay - other.remainDay;
            }
        }

        public void extendRemainDay(int cnt) {
            this.remainDay += (30 * cnt);
        }
    }
}
