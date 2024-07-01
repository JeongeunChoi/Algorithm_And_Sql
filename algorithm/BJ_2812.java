package algorithm.algorithm;

import java.io.*;
import java.util.*;

public class BJ_2812 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken()), removeCnt = 0;
        String numStr = br.readLine();
        Stack<Integer> numStack = new Stack<>(); // ** 스택 사용하기!
        for(int i=0; i<N; i++){ // 현재 수가 스택에서 꺼낸 수보다 클 때까지 pop하고 현재 수를 넣는다. pop 횟수는 K까지만 가능하므로 삭제 횟수 초과 시 pop 불가
            int num = numStr.charAt(i) - '0';
            while(!numStack.isEmpty() && numStack.peek() < num && removeCnt < K){
                numStack.pop();
                removeCnt++;
            }
            numStack.push(num);
        }

        // K개를 무조건 삭제해야 하므로, N - K개 초과 시 삭제해야 하는 개수만큼 pop 해주기
        while(numStack.size() > N - K){
            numStack.pop();
        }

        // 스택 이용해서 수 거꾸로 뒤집기
        Stack<Integer> reverseNumStack = new Stack<>();
        while(!numStack.isEmpty()){
            reverseNumStack.push(numStack.pop());
        }

        // 출력
        while(!reverseNumStack.isEmpty()){
            System.out.print(reverseNumStack.pop());
        }
    }
}
