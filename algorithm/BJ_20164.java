package algorithm.algorithm;

import java.io.*;

public class BJ_20164 {

    private static String num;
    private static int minOddCnt = Integer.MAX_VALUE, maxOddCnt = 0;

    private static void divideNum(String num, int oddCnt) {
        if (num.length() == 1) {
            minOddCnt = Math.min(minOddCnt, oddCnt);
            maxOddCnt = Math.max(maxOddCnt, oddCnt);
        } else if (num.length() == 2) {
            num = String.valueOf((num.charAt(0) - '0') + (num.charAt(1) - '0'));
            divideNum(num, oddCnt + countOdd(num));
        } else {
            divideTo3Num(num, 0, 0, oddCnt, new boolean[num.length()]);
        }
    }

    private static void divideTo3Num(String num, int numIdx, int divideCnt, int oddCnt,
            boolean[] isBoundary) {
        if (divideCnt == 2) { // 경계선이 2개 존재해야 숫자가 3개로 나누어짐.
            int divideNumSum = 0;
            String numStr = "";
            for (int i = 0; i < isBoundary.length; i++) {
                if (isBoundary[i] || i == isBoundary.length - 1) { // 선택한 2개의 경계선과 마지막 숫자에 대한 경계선 고려하기
                    numStr += num.charAt(i);
                    divideNumSum += Integer.parseInt(numStr);
                    numStr = "";
                } else {
                    numStr += num.charAt(i);
                }
            }
            divideNum(String.valueOf(divideNumSum),
                    oddCnt + countOdd(String.valueOf(divideNumSum)));
        } else {
            for (int i = numIdx; i < num.length() - 1; i++) { // 마지막 숫자 제외하고 경계선 정하기
                isBoundary[i] = true;
                divideTo3Num(num, i + 1, divideCnt + 1, oddCnt, isBoundary);
                isBoundary[i] = false;
            }
        }
    }

    private static int countOdd(String num) {
        int oddCnt = 0;
        for (int i = 0; i < num.length(); i++) {
            if ((num.charAt(i) - '0') % 2 == 1) {
                oddCnt++;
            }
        }

        return oddCnt;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        num = br.readLine();

        divideNum(num, countOdd(num));

        System.out.println(minOddCnt + " " + maxOddCnt);
    }
}
