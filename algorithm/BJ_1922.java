package algorithm.algorithm;

import java.io.*;
import java.util.*;

/**
 * 최소 신장 트리
 * 그래프 내에 있는 모든 정점을 연결하고 가중치 합이 최소인 사이클이 없는 그래프
 *
 * 크루스칼 알고리즘
 * 1. 모든 간선들의 가중치를 오름차 순으로 정렬
 * 2. 가중치가 가장 작은 간선을 선택
 * 3. 위에서 선택한 간선이 연결하려는 2개의 노드가 서로 연결되지 않은 상태라면, 2개의 노드를 서로 연결한다.
 * 4. 이 과정을 반복
 *
 * 프림 알고리즘
 * 1. 임의의 간선을 선택
 * 2. 선택한 간선의 정점으로부터 가장 낮은 가중치를 갖는 정점을 선택
 * 3. 모든 정점이 선택될 때까지 반복
 *
 * 프림의 경우, 최소 거리의 정점을 찾는 부분에서 자료 구조의 성능에 영향을 받는다.
 * 크루스칼은 간선을 기준으로 정렬하는 과정이 오래 걸린다.
 * 간선의 개수가 작은 경우에는 크루스칼, 간선의 개수가 많은 경우에는 프림
 */

public class BJ_1922 {

    private static int[] parent;

    private static void union(int c1, int c2) {
        c1 = getParent(c1);
        c2 = getParent(c2);
        if (c1 != c2) {
            parent[c2] = parent[c1];
        }
    }

    private static int getParent(int c) {
        if (parent[c] == c) {
            return parent[c];
        } else {
            return parent[c] = getParent(parent[c]);
        }
    }

    private static int kruskal(ArrayList<Cable> cables) {
        int weightSum = 0;
        for (int i = 0; i < cables.size(); i++) {
            Cable cable = cables.get(i);
            if (getParent(cable.s) != getParent(cable.e)) {
                weightSum += cable.w;
                union(cable.s, cable.e);
            }
        }

        return weightSum;
    }

    private static int prim(ArrayList<ArrayList<Cable>> cables2, int computerCnt) {
        boolean[] visited = new boolean[computerCnt + 1];
        PriorityQueue<Cable> cablePQ = new PriorityQueue<>();
        cablePQ.add(new Cable(0, 1, 0));
        int weightSum = 0;
        while (!cablePQ.isEmpty()) {
            Cable cable = cablePQ.poll();
            if (!visited[cable.e]) {
                visited[cable.e] = true;
                weightSum += cable.w;
                for (int i = 0; i < cables2.get(cable.e).size(); i++) {
                    if (!visited[cables2.get(cable.e).get(i).e]) {
                        cablePQ.add(cables2.get(cable.e).get(i));
                    }
                }
            }
        }

        return weightSum;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int computerCnt = Integer.parseInt(br.readLine()), cableCnt = Integer.parseInt(br.readLine());
        parent = new int[computerCnt + 1];
        for (int i = 0; i <= computerCnt; i++) {
            parent[i] = i;
        }
        ArrayList<Cable> cablesForKruskal = new ArrayList<>();
        ArrayList<ArrayList<Cable>> cablesForPrim = new ArrayList<>();
        for (int i = 0; i <= computerCnt; i++) {
            cablesForPrim.add(new ArrayList<>());
        }
        for (int i = 0; i < cableCnt; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()), e = Integer.parseInt(st.nextToken()), w = Integer.parseInt(st.nextToken());
            cablesForKruskal.add(new Cable(s, e, w));
            cablesForPrim.get(s).add(new Cable(s, e, w));
            cablesForPrim.get(e).add(new Cable(e, s, w));
        }
        Collections.sort(cablesForKruskal);

        // System.out.println(kruskal(cablesForKruskal));
        System.out.println(prim(cablesForPrim, computerCnt));

    }

    static class Cable implements Comparable<Cable> {

        int s, e, w;

        Cable(int s, int e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }

        public int compareTo(Cable other) {
            return this.w - other.w;
        }
    }

}
