package algorithm.algorithm;

import java.util.*;

public class PRGM_택배상자 {

    public int solution(int[] order) {
        int answer = 0;

        Stack<Integer> subBelt = new Stack<>();

        int orderIdx = 0;
        boolean[] inSubBelt = new boolean[order.length + 1];
        for (int i = 1; i <= order.length; i++) {
            if (order[orderIdx] == i) {
                orderIdx++;
                answer++;
            } else {
                subBelt.push(i);
            }

            while (!subBelt.isEmpty() && order[orderIdx] == subBelt.peek()) {
                subBelt.pop();
                orderIdx++;
                answer++;
            }
        }

        return answer;
    }
}
