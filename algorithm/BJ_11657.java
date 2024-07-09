package algorithm.algorithm;

import java.io.*;
import java.util.*;

/**
 * 벨만포드 알고리즘 한 노드에서 다른 노드까지의 최단 거리를 구하는 알고리즘
 * 간선의 가중치가 음수일 때도 최단 거리를 구할 수 있다.
 * -> 다익스트라의 경우 매번 방문하지 않은 노드 중에서 최단 거리가 가장 짧은 노드를 선택하므로 가중치가 음수인 경우 최단 거리를 찾을 수 없는 상황이 발생할 수 있다.
 * 모든 간선의 비용이 양수일 때는 다익스트라를, 음수 간선이 포함되어 있으면 벨만-포드를 사용하면 된다.
 *
 * 매 단계마다 모든 간선을 전부 확인하면서 모든 노드간의 최단 거리를 구해나간다.
 * 1. 출발 노드를 설정한다.
 * 2. 최단 거리 테이블을 초기화한다.
 * 3. 다음 과정을 (정점 -1)번 반복한다.
 * 3-1. 모든 간선 E개를 하나씩 확인한다.
 * 3-2. 각 간선을 거쳐 다른 노드로 가는 비용을 계산하여 최단 거리 테이블을 갱신한다.
 *
 * 시간복잡도: O(VE) V번 반복에 대해서 해당 정점과 연결되어 있는 모든 간선을 탐색해준다.
 */


public class BJ_11657 {

    private static boolean hasMinusCycle = false;

    private static long[] bellmanFord(ArrayList<Node> roads, int cityCnt) {
        long[] distance = new long[cityCnt + 1];
        for (int i = 0; i <= cityCnt; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        distance[1] = 0;
        for (int i = 1; i <= cityCnt; i++) {
            for (int j = 0; j < roads.size(); j++) {
                Node road = roads.get(j);
                if (distance[road.startCityNum] != Integer.MAX_VALUE) {
                    if (distance[road.startCityNum] + road.time < distance[road.endCityNum]) {
                        distance[road.endCityNum] = distance[road.startCityNum] + road.time;
                        if (i == cityCnt) {
                            hasMinusCycle = true; // cityCnt - 1번 반복하면 모든 에지를 확인한 것이므로 i가 cityCnt인 경우 거리 갱신이 생기면 음수 사이클이 존재한다는 것이다.
                        }
                    }
                }
            }
        }

        return distance;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int cityCnt = Integer.parseInt(st.nextToken()), busRoadCnt = Integer.parseInt(
                st.nextToken());
        ArrayList<Node> roads = new ArrayList<>();
        for (int i = 0; i < busRoadCnt; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()), e = Integer.parseInt(
                    st.nextToken()), t = Integer.parseInt(st.nextToken());
            roads.add(new Node(s, e, t));
        }

        long[] distance = bellmanFord(roads, cityCnt);

        if (hasMinusCycle) {
            System.out.println(-1);
        } else {
            for (int i = 2; i <= cityCnt; i++) {
                System.out.println(distance[i] == Integer.MAX_VALUE ? -1 : distance[i]);
            }
        }
    }

    static class Node {

        int startCityNum, endCityNum, time;

        Node(int startCityNum, int endCityNum, int time) {
            this.startCityNum = startCityNum;
            this.endCityNum = endCityNum;
            this.time = time;
        }
    }

}
