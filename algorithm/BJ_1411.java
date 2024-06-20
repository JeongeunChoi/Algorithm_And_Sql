package algorithm.algorithm;

import java.io.*;
import java.util.*;

public class BJ_1411 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()), answer = 0;

        // 맵 이용하여 단어 패턴 저장
        ArrayList<ArrayList<Integer>> words = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            int num = 1;
            Map<Character, Integer> charMap = new HashMap<>();
            words.add(new ArrayList<>());
            for (int j = 0; j < word.length(); j++) {
                char ch = word.charAt(j);
                if (charMap.containsKey(ch)) {
                    words.get(i).add(charMap.get(ch));
                } else {
                    num++;
                    charMap.put(ch, num);
                    words.get(i).add(num);
                }
            }
        }

        // 몇 개의 쌍이 비슷한지 카운트
        for (int i = 0; i < words.size(); i++) {
            for (int j = i + 1; j < words.size(); j++) {
                boolean isEqual = true;
                for (int k = 0; k < words.get(i).size(); k++) {
                    if (words.get(i).get(k) != words.get(j).get(k)) {
                        isEqual = false;
                        break;
                    }
                }
                if (isEqual) {
                    answer++;
                }
            }
        }

        System.out.println(answer);
    }
}
