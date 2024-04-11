package algorithm;

import java.io.*;
import java.util.*;

public class BJ_14499 {

    private static int N, M, x, y, K;
    private static int[][] map;
    private static int[] dice = new int[7];
    private static int[] dx = {0, 0, -1, 1};
    private static int[] dy = {1, -1, 0, 0};

    private static boolean outOfBound(int x, int y) {
        return !(x >= 0 && x < N && y >= 0 && y < M);
    }

    private static void moveMapXY(int dir) {
        x += dx[dir];
        y += dy[dir];
    }

    // 주사위 굴리면 전개도 기준으로 값 바꾸기
    private static void moveDice(int dir) {
        if (dir == 0) { // 동
            int temp = dice[1];
            dice[1] = dice[3];
            dice[3] = dice[6];
            dice[6] = dice[4];
            dice[4] = temp;
        } else if (dir == 1) { // 서
            int temp = dice[1];
            dice[1] = dice[4];
            dice[4] = dice[6];
            dice[6] = dice[3];
            dice[3] = temp;
        } else if (dir == 2) { // 북
            int temp = dice[1];
            dice[1] = dice[2];
            dice[2] = dice[6];
            dice[6] = dice[5];
            dice[5] = temp;
        } else if (dir == 3) { // 남
            int temp = dice[1];
            dice[1] = dice[5];
            dice[5] = dice[6];
            dice[6] = dice[2];
            dice[2] = temp;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int dir = Integer.parseInt(st.nextToken()) - 1;
            if (outOfBound(x + dx[dir], y + dy[dir])) {
                continue;
            }
            moveMapXY(dir);
            moveDice(dir);
            if (map[x][y] == 0) {
                map[x][y] = dice[1];
            } else if (map[x][y] > 0) {
                dice[1] = map[x][y];
                map[x][y] = 0;
            }

            System.out.println(dice[6]);
        }
    }
}
