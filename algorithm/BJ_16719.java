package algorithm.algorithm;

import java.io.*;

public class BJ_16719 {

    private static char[] word;
    private static int[] wordNum;

    private static void makeLexicalOrderWord(int cnt, int wordIdx) {
        if (cnt == 0) { // 선택해야하는 갯수가 0이면 탐색 종료
            System.out.println();
        } else {
            int minWordNum = 'Z' + 1, minWordIdx = 0;
            // 현재 필요한 알파벳 갯수가 남는 범위까지 탐색하며, 가장 번호가 작은 알파벳을 선택하여 출력한다.
            for (int i = wordIdx + 1; i <= word.length - cnt; i++) {
                if (minWordNum > wordNum[i]) {
                    minWordNum = wordNum[i];
                    minWordIdx = i;
                }
            }
            System.out.print(word[minWordIdx]);
            makeLexicalOrderWord(cnt - 1, minWordIdx);
        }
    }

    private static void input() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        word = br.readLine().toCharArray();
        wordNum = new int[word.length];
    }

    public static void main(String[] args) throws Exception {
        input();

        for (int i = 0; i < word.length; i++) {
            wordNum[i] = word[i] - '0';
        }

        for (int i = 1; i <= word.length; i++) {
            makeLexicalOrderWord(i, -1);
        }
    }
}
