package algorithm.algorithm;

import java.io.*;
import java.util.*;

public class BJ_15683 {

    private static int N, M;
    private static ArrayList<Location> cctvs = new ArrayList<>();
    private static int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};
    private static ArrayList<ArrayList<Integer>> dirForCctv = new ArrayList<>();
    private static int minBlindSpot = Integer.MAX_VALUE;

    private static void watchingCctv(int cctvIdx, int[][] office, int spaceForWatching) { // cctv 별 감시 가능한 방향에 대한 모든 경우의 수 탐색
        if (cctvIdx == cctvs.size()) {
            minBlindSpot = Math.min(minBlindSpot, spaceForWatching);
        } else {
            Location cctv = cctvs.get(cctvIdx);
            int cctvNum = office[cctv.x][cctv.y];

            for (int i = 0; i < dirForCctv.get(cctvNum).size(); i++) {
                int[][] copiedOffice = new int[N][M];
                int dirIdx = dirForCctv.get(cctvNum)
                        .get(i), copiedSpaceForWatching = spaceForWatching;
                for (int j = 0; j < N; j++) {
                    copiedOffice[j] = Arrays.copyOf(office[j], office[j].length);
                }

                boolean[] dir = selectDir(cctvNum, dirIdx);

                for (int j = 0; j < 4; j++) {
                    if (dir[j]) {
                        int nx = cctv.x, ny = cctv.y;
                        while (true) {
                            nx += dx[j];
                            ny += dy[j];
                            if (outOfBound(nx, ny) || copiedOffice[nx][ny] == 6) {
                                break;
                            } else if (copiedOffice[nx][ny] == 0) {
                                copiedOffice[nx][ny] = 7;
                                copiedSpaceForWatching--;
                            }
                        }
                    }
                }

                watchingCctv(cctvIdx + 1, copiedOffice, copiedSpaceForWatching);
            }
        }
    }

    private static boolean[] selectDir(int cctvNum, int dirIdx) { // cctv 번호별 감시 가능 방향 체크
        boolean[] dir = new boolean[4];

        switch (cctvNum) {
            case 1:
                dir[dirIdx] = true;
                break;
            case 2:
                dir[dirIdx] = true;
                dir[(dirIdx + 2) % 4] = true;
                break;
            case 3:
                dir[dirIdx] = true;
                dir[(dirIdx + 1) % 4] = true;
                break;
            case 4:
                dir[dirIdx] = true;
                dir[(dirIdx + 1) % 4] = true;
                dir[(dirIdx + 2) % 4] = true;
                break;
            case 5:
                dir[dirIdx] = true;
                dir[(dirIdx + 1) % 4] = true;
                dir[(dirIdx + 2) % 4] = true;
                dir[(dirIdx + 3) % 4] = true;
                break;
        }

        return dir;
    }

    private static boolean outOfBound(int x, int y) {
        return !(x >= 0 && x < N && y >= 0 && y < M);
    }

    private static void initDirForCctv() { // cctv 번호 별 감시 시작 방향 저장
        for (int i = 0; i < 6; i++) {
            dirForCctv.add(new ArrayList<>());
        }
        dirForCctv.get(1).addAll(Arrays.asList(0, 1, 2, 3));
        dirForCctv.get(2).addAll(Arrays.asList(0, 1));
        dirForCctv.get(3).addAll(Arrays.asList(0, 1, 2, 3));
        dirForCctv.get(4).addAll(Arrays.asList(0, 1, 2, 3));
        dirForCctv.get(5).addAll(List.of(0));
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][] office = new int[N][M];
        int spaceForWatching = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                office[i][j] = Integer.parseInt(st.nextToken());
                if (office[i][j] >= 1 && office[i][j] <= 5) {
                    cctvs.add(new Location(i, j));
                } else if (office[i][j] == 0) {
                    spaceForWatching++;
                }
            }
        }
        initDirForCctv();

        watchingCctv(0, office, spaceForWatching);

        System.out.println(minBlindSpot);
    }

    private static class Location {

        int x, y;

        Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
