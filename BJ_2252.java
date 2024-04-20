package algorithm;

import java.io.*;
import java.util.*;

public class BJ_2252 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N, M;
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[] inDegree = new int[N + 1];
        boolean[] visited = new boolean[N + 1];

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            int a, b;
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            inDegree[b]++;
        }

        // 위상 정렬 알고리즘
        // 진입 차수가 0이 되면 큐에 넣는다.
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                q.add(i);
                visited[i] = true;
            }
        }

        while (!q.isEmpty()) {
            int current = q.poll();
            System.out.print(current + " ");
            for (int i = 0; i < graph.get(current).size(); i++) {
                int next = graph.get(current).get(i);
                if (!visited[next]) {
                    inDegree[next]--;
                    if (inDegree[next] == 0) { // 진입 차수가 0인 경우 큐에 넣기
                        q.add(next);
                        visited[next] = true;
                    }
                }
            }
        }
    }

}
