package algorithm.algorithm;

import java.io.*;
import java.util.*;

public class BJ_14890 {

    private static int N, L, answer = 0;
    private static int[][] map;
    private static boolean[][] runway;

    private static void countRowRoad(int dx, int dy) {
        int x = 0, y = 0;
        for (int i = 0; i < N; i++) {
            boolean canPass = true;
            for (int j = 1; j < N; j++) {
                int nx = x + dx, ny = y + dy;
                int difference = map[nx][ny] - map[x][y];

                if (Math.abs(difference) > 1) {
                    canPass = false;
                    break;
                } else if (Math.abs(difference) == 1) {
                    boolean canMake = true;
                    if (difference > 0) { // 오르는 경사
                        for (int k = 0; k < L; k++) {
                            if (y - k < 0 || map[x][y - k] != map[x][y] || runway[x][y - k]) {
                                canMake = false;
                                break;
                            }
                        }
                        if (canMake) {
                            for (int k = 0; k < L; k++) {
                                runway[x][y - k] = true;
                            }
                        } else {
                            canPass = false;
                            break;
                        }
                    } else if (difference < 0) { // 내리는 경사
                        for (int k = 0; k < L; k++) {
                            if (ny + k >= N || map[nx][ny + k] != map[nx][ny] || runway[nx][ny + k]) {
                                canMake = false;
                                break;
                            }
                        }
                        if (canMake) {
                            for (int k = 0; k < L; k++) {
                                runway[nx][ny + k] = true;
                            }
                        } else {
                            canPass = false;
                            break;
                        }
                    }
                }
                x = nx;
                y = ny;
            }
            x++;
            y = 0;
            if (canPass) {
                answer++;
            }
        }
    }

    private static void countColumnRoad(int dx, int dy) {
        int x = 0, y = 0;
        for (int i = 0; i < N; i++) {
            boolean canPass = true;
            for (int j = 1; j < N; j++) {
                int nx = x + dx, ny = y + dy;
                int difference = map[nx][ny] - map[x][y];

                if (Math.abs(difference) > 1) {
                    canPass = false;
                    break;
                } else if (Math.abs(difference) == 1) {
                    boolean canMake = true;
                    if (difference > 0) { // 오르는 경사
                        for (int k = 0; k < L; k++) {
                            if (x - k < 0 || map[x - k][y] != map[x][y] || runway[x - k][y]) {
                                canMake = false;
                                break;
                            }
                        }
                        if (canMake) {
                            for (int k = 0; k < L; k++) {
                                runway[x - k][y] = true;
                            }
                        } else {
                            canPass = false;
                            break;
                        }
                    } else if (difference < 0) { // 내리는 경사
                        for (int k = 0; k < L; k++) {
                            if (nx + k >= N || map[nx + k][ny] != map[nx][ny] || runway[nx + k][ny]) {
                                canMake = false;
                                break;
                            }
                        }
                        if (canMake) {
                            for (int k = 0; k < L; k++) {
                                runway[nx + k][ny] = true;
                            }
                        } else {
                            canPass = false;
                            break;
                        }
                    }
                }
                x = nx;
                y = ny;
            }
            x = 0;
            y++;
            if (canPass) {
                answer++;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        runway = new boolean[N][N];
        countRowRoad(0, 1);
        runway = new boolean[N][N];
        countColumnRoad(1, 0);

        System.out.println(answer);
    }
}
