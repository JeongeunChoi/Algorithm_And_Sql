package algorithm.algorithm;

import java.io.*;
import java.util.*;

public class BJ_낚시왕 {

    private static int R, C, M, eatingSharkSizeSum = 0;
    private static int[][] map;
    private static Map<Integer, SharkInfo> sharkMap = new HashMap<>();
    private static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, 1, -1}; // 위, 아래, 오른쪽, 왼쪽

    private static void eatShark(int j) {
        for (int i = 1; i <= R; i++) {
            if (map[i][j] > 0) {
                eatingSharkSizeSum += sharkMap.get(map[i][j]).z;
                sharkMap.remove(map[i][j]);
                map[i][j] = 0;
                break;
            }
        }
    }

    private static void moveShark() {
        int[][] beforeMoveMap = new int[R + 1][C + 1];
        for (int i = 0; i <= R; i++) {
            beforeMoveMap[i] = Arrays.copyOf(map[i], map[i].length);
        }
        map = new int[R + 1][C + 1];

        for (int i = 1; i <= M; i++) {
            if (sharkMap.containsKey(i)) {
                SharkInfo shark = sharkMap.get(i);
                int dIdx = shark.d, nx = shark.r, ny = shark.c;
                for (int j = 0; j < shark.s; j++) {
                    if (nx + dx[dIdx] >= 1 && nx + dx[dIdx] <= R) {
                        nx += dx[dIdx];
                    } else {
                        dIdx = changeDirection(dIdx);
                        shark.updateD(dIdx);
                        nx += dx[dIdx];
                    }

                    if (ny + dy[dIdx] >= 1 && ny + dy[dIdx] <= C) {
                        ny += dy[dIdx];
                    } else {
                        dIdx = changeDirection(dIdx);
                        shark.updateD(dIdx);
                        ny += dy[dIdx];
                    }
                }

                if (map[nx][ny] == 0) {
                    map[nx][ny] = i;
                    shark.updateRAndC(nx, ny);
                } else {
                    SharkInfo otherShark = sharkMap.get(map[nx][ny]);
                    if (shark.z > otherShark.z) {
                        sharkMap.remove(map[nx][ny]);
                        map[nx][ny] = i;
                        shark.updateRAndC(nx, ny);
                    } else if (shark.z < otherShark.z) {
                        sharkMap.remove(i);
                    }
                }
            }
        }
    }

    private static int changeDirection(int dIdx) {
        switch (dIdx) {
            case 0:
                return 1;
            case 1:
                return 0;
            case 2:
                return 3;
            case 3:
                return 2;
        }

        return 0;
    }


    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[R + 1][C + 1];
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()), c = Integer.parseInt(
                    st.nextToken()), s = Integer.parseInt(st.nextToken()), d = Integer.parseInt(
                    st.nextToken()), z = Integer.parseInt(st.nextToken());
            sharkMap.put(i, new SharkInfo(r, c, s, d - 1, z));
            map[r][c] = i;
        }

        // 1. 낚시왕이 오른쪽으로 한 칸 이동
        for (int i = 1; i <= C; i++) {
            // 2. 낚시왕이 땅과 제일 가까운 상어를 잡아먹음.
            eatShark(i);

            // 3. 상어 이동
            moveShark();
        }

        System.out.println(eatingSharkSizeSum);
    }

    private static class SharkInfo {

        // r, c는 상어 위치
        // s는 속력
        // d는 이동 방향
        // z는 크기
        int r, c, s, d, z;

        SharkInfo(int r, int c, int s, int d, int z) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }

        public void updateRAndC(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public void updateD(int d) {
            this.d = d;
        }
    }

}
