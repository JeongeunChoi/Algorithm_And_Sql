package algorithm.algorithm;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 다익스트라 알고리즘 음의 가중치가 없는 그래프의 한 노드에서 각 모든 노드까지의 최단거리를 구하는 알고리즘
 * 1. 방문하지 않은 노드 중에서 가장 비용이 적은 노드를 선택한다.
 * 2. 해당 노드로부터 갈 수 있는 노드들의 비용을 갱신한다.
 *
 * 두 가지 방법으로 구현 가능
 * 1. 순차 탐색
 * 2. 우선순위 큐
 */
public class BJ_1238 {

    private static int villageCnt, roadCnt, party;

    // 1. 순차 탐색 O(V^2)
    private static int[] dijkstra1(ArrayList<ArrayList<Node>> villageRoads, int startVillageNum) {
        int[] distance = new int[villageCnt + 1];
        boolean[] visited = new boolean[villageCnt + 1];
        for (int i = 0; i <= villageCnt; i++) {
            distance[i] = Integer.MAX_VALUE;
        }

        distance[startVillageNum] = 0;
        for (int i = 1; i <= villageCnt; i++) {
            int idx = 0;
            for (int j = 1; j <= villageCnt; j++) {
                if (!visited[j] && distance[j] < distance[idx]) {
                    idx = j;
                }
            }
            visited[idx] = true;

            for (int j = 0; j < villageRoads.get(idx).size(); j++) {
                Node nextVillage = villageRoads.get(idx).get(j);
                distance[nextVillage.villageNum] = Math.min(distance[nextVillage.villageNum],
                        distance[idx] + nextVillage.time);
            }
        }

        return distance;
    }

    // 2. 우선순위 큐 O(ElogV)
    private static int[] dijkstra2(ArrayList<ArrayList<Node>> villageRoads, int startVillageNum) {
        int[] distance = new int[villageCnt + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(startVillageNum, 0));
        for (int i = 0; i <= villageCnt; i++) {
            distance[i] = Integer.MAX_VALUE;
        }

        distance[startVillageNum] = 0;
        while (!pq.isEmpty()) {
            int idx = pq.poll().villageNum;

            for (int j = 0; j < villageRoads.get(idx).size(); j++) {
                Node nextVillage = villageRoads.get(idx).get(j);
                if (distance[idx] + nextVillage.time < distance[nextVillage.villageNum]) {
                    distance[nextVillage.villageNum] = distance[idx] + nextVillage.time;
                    pq.add(new Node(nextVillage.villageNum, distance[nextVillage.villageNum]));
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
        villageCnt = Integer.parseInt(st.nextToken());
        roadCnt = Integer.parseInt(st.nextToken());
        party = Integer.parseInt(st.nextToken());
        ArrayList<ArrayList<Node>> villageRoads = new ArrayList<>();
        for (int i = 0; i <= villageCnt; i++) {
            villageRoads.add(new ArrayList<>());
        }

        for (int i = 0; i < roadCnt; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()), e = Integer.parseInt(
                    st.nextToken()), t = Integer.parseInt(st.nextToken());
            villageRoads.get(s).add(new Node(e, t));
        }

        int maxDistance = 0;
        int[] returnDistance = dijkstra2(villageRoads, party); // 파티에서 돌아오는 시간
        for (int i = 1; i <= villageCnt; i++) {
            int[] goDistance = dijkstra2(villageRoads, i); // 파티로 가는 시간
            maxDistance = Math.max(maxDistance, goDistance[party] + returnDistance[i]); // 파티를 오고 가는데 소요한 시간
        }

        System.out.println(maxDistance);
    }

    static class Node implements Comparable<Node> {

        int villageNum, time;

        Node(int villageNum, int time) {
            this.villageNum = villageNum;
            this.time = time;
        }

        public int compareTo(Node other) {
            return this.time - other.time;
        }
    }

}
