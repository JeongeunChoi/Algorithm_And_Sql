package algorithm.algorithm;

import java.io.*;
import java.util.*;

public class BJ_2470 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int solutionCnt = Integer.parseInt(br.readLine());
        int[] solutions = new int[solutionCnt];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < solutionCnt; i++) {
            solutions[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(solutions);

        int lt = 0, rt = solutions.length - 1, target = 0, newSolution = 2000000000;
        int[] answer = new int[2];
        while (lt < rt) {
            int sum = solutions[lt] + solutions[rt];
            if(newSolution > Math.abs(target - sum)){
                answer[0] = solutions[lt];
                answer[1] = solutions[rt];
                newSolution = Math.abs(target - sum);
            }
            if (sum > target) {
                rt--;
            } else if (sum < target) {
                lt++;
            } else if (sum == target) {
                break;
            }
        }

        System.out.println(answer[0] + " " + answer[1]);
    }

}
