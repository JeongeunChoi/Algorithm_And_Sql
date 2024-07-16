package algorithm.algorithm;

import java.io.*;
import java.util.*;

public class BJ_1300 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // B[K] = x -> x보다 작거나 같은 수가 K개
        // 이분 탐색을 통해 x의 값을 조정해가며 답을 찾는다.
        int N = Integer.parseInt(br.readLine()), K = Integer.parseInt(br.readLine());
        int low = 1, high = K, answer = 0;
        while(low <= high){
            int mid = (low + high) / 2;
            int cnt = 0;
            for(int i=1; i<=N; i++){
                cnt += Math.min(mid/i, N);
            }

            // B[k]보다 작은 수는 반드시 cnt가 B[k]보다 작고,
            // B[k]보다 큰 수는 반드시 cnt가 B[k]보다 크거나 같다.
            if(cnt < K){
                low = mid + 1;
            } else {
                answer = mid;
                high = mid - 1; // cnt가 k개인 것 중에 가장 작은 수는 항상 배열 안에 존재하는 수
            }
        }

        System.out.println(answer);
    }

}
