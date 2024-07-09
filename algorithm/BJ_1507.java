package algorithm.algorithm;

import java.io.*;
import java.util.*;

/**
 * 플로이드워셜 알고리즘
 * 음수 순환 사이클이 없는 그래프에서, 모든 지점에서 다른 모든 지점까지의 최단 경로를 모두 구하는 알고리즘
 *
 * 한 점에서 이웃 노드를 탐색하며 최단 거리를 구하는 다익스트라 알고리즘과는 다르게 거쳐가는 중간 노드를 기준으로 최단 거리를 구한다.
 *
 * 1. 1번 노드를 거쳐 가는 경우를 고려하여 테이블을 갱신한다.
 * 2. 2번 노드를 거쳐 가는 경우를 고려하여 테이블을 갱신한다.
 * 3. 3번 노드를 거쳐 가는 경우를 고려하여 테이블을 갱신한다.
 * ...
 *
 * 시간복잡도: O(N^3)
 * 공간복잡도: O(N^2)
 */

public class BJ_1507 {

    private static void floydWarshall(int[][] distance, int cityCnt) {
        for (int i = 1; i <= cityCnt; i++) {
            for (int j = 1; j <= cityCnt; j++) {
                for (int k = 1; k <= cityCnt; k++) {
                    if (distance[j][i] != Integer.MAX_VALUE
                            && distance[i][k] != Integer.MAX_VALUE) {
                        distance[j][k] = Math.min(distance[j][k], distance[j][i] + distance[i][j]);
                    }
                }
            }
        }
    }

    // 플로이드와샬을 반대로 하는 문제
    // 다른 곳을 거쳐서 돌아가는 거랑 바로 연결된 거리랑 같으면 직접 연결된 다리는 불필요
    // 다른 최단경로가 존재하면 최소 이동시간이 아니므로 -1 출력
    private static int reverseFloydWarshall(int[][] distance, int cityCnt) {
        boolean[][] notRequiredRoad = new boolean[cityCnt + 1][cityCnt + 1];

        for (int i = 1; i <= cityCnt; i++) {
            for (int j = 1; j <= cityCnt; j++) {
                for (int k = 1; k <= cityCnt; k++) {
                    if (i != j && j != k && i != k) {
                        if (distance[j][k] == distance[j][i] + distance[i][k]) {
                            notRequiredRoad[j][k] = true;
                        } else if (distance[j][k] > distance[j][i] + distance[i][k]) {
                            return -1;
                        }
                    }
                }
            }
        }

        int roadSum = 0;
        for (int i = 1; i <= cityCnt; i++) {
            for (int j = 1; j <= cityCnt; j++) {
                if (!notRequiredRoad[i][j]) {
                    roadSum += distance[i][j];
                }
            }
        }
        roadSum /= 2;

        return roadSum;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int cityCnt = Integer.parseInt(br.readLine());
        int[][] distance = new int[cityCnt + 1][cityCnt + 1];
        for (int i = 1; i <= cityCnt; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= cityCnt; j++) {
                distance[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(reverseFloydWarshall(distance, cityCnt));
    }

}
