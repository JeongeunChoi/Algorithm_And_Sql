package algorithm.algorithm;

import java.io.*;
import java.util.*;

/**
 * 위상 정렬 알고리즘
 * 진입차수: 특정한 노드로 들어오는 간선의 개수
 * 진출차수: 특정한 노드에서 나가는 간선의 개수
 *
 * 1. 진입차수가 0인 노드를 큐에 넣는다.
 * 2. 큐가 빌 때까지 다음의 과정을 반복한다.
 * 2-1. 큐에서 원소를 꺼내 해당 노드에서 나가는 간선을 그래프에서 제거
 * 2-2. 새롭게 진입차수가 된 0이 된 노드를 큐에 삽입
 */

public class BJ_2637 {

    private static int[][] topologicalSorting(ArrayList<ArrayList<Node>> graph, int[] indegree, int productNum) {
        int[][] dp = new int[productNum + 1][productNum + 1]; // dp[i][j] i번째 물건을 만들기 위해 필요한 j 개수
        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i <= productNum; i++) {
            if (indegree[i] == 0) {
                q.add(i);
                dp[i][i] = 1;
            }
        }

        while (!q.isEmpty()) {
            int n = q.poll();
            for (int j = 0; j < graph.get(n).size(); j++) {
                Node node = graph.get(n).get(j);
                indegree[node.n]--;
                for (int k = 1; k <= productNum; k++) {
                    dp[node.n][k] += dp[n][k] * node.w;
                }
                if (indegree[node.n] == 0) { // 진입차수 0 되면 큐에 넣기
                    q.add(node.n);
                }
            }
        }

        return dp;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int productNum = Integer.parseInt(br.readLine()), edgeCnt = Integer.parseInt(br.readLine());
        int[] indegree = new int[productNum + 1];
        ArrayList<ArrayList<Node>> graph = new ArrayList<>();
        for (int i = 0; i <= productNum; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < edgeCnt; i++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken()), Y = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());
            graph.get(Y).add(new Node(X, K)); // X를 만들기 위해 Y가 K개 필요하므로 Y -> X 이렇게 방향이 되어야 함.
            indegree[X]++;
        }

        int[][] dp = topologicalSorting(graph, indegree, productNum);
        for (int i = 1; i < productNum; i++) {
            if (dp[productNum][i] > 0) {
                System.out.println(i + " " + dp[productNum][i]);
            }
        }
    }

    static class Node {

        int n, w;

        Node(int n, int w) {
            this.n = n;
            this.w = w;
        }
    }

}
