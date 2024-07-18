package algorithm.algorithm;

import java.io.*;
import java.util.*;

public class BJ_15591 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()), Q = Integer.parseInt(st.nextToken());
        int[][] usado = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                usado[i][j] = Integer.MAX_VALUE;
            }
        }
        // 인접그래프 만들기
        ArrayList<ArrayList<Node>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken()), q = Integer.parseInt(st.nextToken()), r = Integer.parseInt(st.nextToken());
            graph.get(p).add(new Node(q, r));
            graph.get(q).add(new Node(p, r));
        }

        // 각 영상에 대한 유사도 계산
        for (int i = 1; i <= N; i++) {
            boolean[] visited = new boolean[N + 1];
            visited[i] = true;
            Queue<Node> q = new LinkedList<>();
            q.add(new Node(i, Integer.MAX_VALUE)); // 처음에는 유사도 없으므로 MAX_VALUE 값으로 넣기
            while (!q.isEmpty()) {
                Node current = q.poll();
                for (int j = 0; j < graph.get(current.n).size(); j++) { // 인접 그래프로부터 현재 노드에서 갈 수 있는 노드 탐색
                    Node next = graph.get(current.n).get(j);
                    if (!visited[next.n]) { // 방문하지 않은 경우
                        int minUsado = Math.min(current.w, next.w);
                        usado[i][next.n] = minUsado; // 유사도가 더 낮은 값으로 업데이트
                        q.add(new Node(next.n, minUsado)); // 다음 노드 번호와 현재까지의 최소 유사도를 큐에 넣기
                        visited[next.n] = true;
                    }
                }
            }
        }
        
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken()), v = Integer.parseInt(st.nextToken()), cnt = 0;
            for (int j = 1; j <= N; j++) {
                if (j != v && usado[v][j] >= k) {
                    cnt++;
                }
            }
            System.out.println(cnt);
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
