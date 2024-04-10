package algorithm;

import java.io.*;
import java.util.*;

public class BJ_12100 {

    private static int N, answer = 0;
    private static final int[] dx = {0, 1, 0, -1};
    private static final int[] dy = {1, 0, -1, 0};

    private static void move(int dir, int[][] board, int cnt) {
        if (cnt == 5) {
            findMaxNum(board);
        } else {
            boolean[][] hasMerged = new boolean[N][N];
            if (dir == 0) { // 오른쪽
                for (int x = 0; x < N; x++) {
                    for (int y = N - 2; y >= 0; y--) {
                        if (board[x][y] == 0) {
                            continue;
                        }
                        int yy = y;
                        while (yy <= N - 2) {
                            int nx = x + dx[dir], ny = yy + dy[dir];
                            if (board[x][yy] == board[nx][ny] && !hasMerged[nx][ny]
                                    && !hasMerged[x][yy]) {
                                board[nx][ny] *= 2;
                                board[x][yy] = 0;
                                hasMerged[nx][ny] = true;
                                hasMerged[x][yy] = false;
                            } else if (board[nx][ny] == 0) {
                                board[nx][ny] = board[x][yy];
                                board[x][yy] = 0;
                                hasMerged[nx][ny] = hasMerged[x][yy];
                                hasMerged[x][yy] = false;
                            } else {
                                break;
                            }
                            yy++;
                        }
                    }
                }
            } else if (dir == 1) { // 아래쪽
                for (int y = 0; y < N; y++) {
                    for (int x = N - 2; x >= 0; x--) {
                        if (board[x][y] == 0) {
                            continue;
                        }
                        int xx = x;
                        while (xx <= N - 2) {
                            int nx = xx + dx[dir], ny = y + dy[dir];
                            if (board[xx][y] == board[nx][ny] && !hasMerged[nx][ny]
                                    && !hasMerged[xx][y]) {
                                board[nx][ny] *= 2;
                                board[xx][y] = 0;
                                hasMerged[nx][ny] = true;
                                hasMerged[xx][y] = false;
                            } else if (board[nx][ny] == 0) {
                                board[nx][ny] = board[xx][y];
                                board[xx][y] = 0;
                                hasMerged[nx][ny] = hasMerged[xx][y];
                                hasMerged[xx][y] = false;
                            } else {
                                break;
                            }
                            xx++;
                        }
                    }
                }
            } else if (dir == 2) { // 왼쪽
                for (int x = 0; x < N; x++) {
                    for (int y = 1; y < N; y++) {
                        if (board[x][y] == 0) {
                            continue;
                        }
                        int yy = y;
                        while (yy >= 1) {
                            int nx = x + dx[dir], ny = yy + dy[dir];
                            if (board[x][yy] == board[nx][ny] && !hasMerged[nx][ny]
                                    && !hasMerged[x][yy]) {
                                board[nx][ny] *= 2;
                                board[x][yy] = 0;
                                hasMerged[nx][ny] = true;
                                hasMerged[x][yy] = false;
                            } else if (board[nx][ny] == 0) {
                                board[nx][ny] = board[x][yy];
                                board[x][yy] = 0;
                                hasMerged[nx][ny] = hasMerged[x][yy];
                                hasMerged[x][yy] = false;
                            } else {
                                break;
                            }
                            yy--;
                        }
                    }
                }
            } else if (dir == 3) { // 위쪽
                for (int y = 0; y < N; y++) {
                    for (int x = 1; x < N; x++) {
                        if (board[x][y] == 0) {
                            continue;
                        }
                        int xx = x;
                        while (xx >= 1) {
                            int nx = xx + dx[dir], ny = y + dy[dir];
                            if (board[xx][y] == board[nx][ny] && !hasMerged[nx][ny]
                                    && !hasMerged[xx][y]) {
                                board[nx][ny] *= 2;
                                board[xx][y] = 0;
                                hasMerged[nx][ny] = true;
                                hasMerged[xx][y] = false;
                            } else if (board[nx][ny] == 0) {
                                board[nx][ny] = board[xx][y];
                                board[xx][y] = 0;
                                hasMerged[nx][ny] = hasMerged[xx][y];
                                hasMerged[xx][y] = false;
                            } else {
                                break;
                            }
                            xx--;
                        }
                    }
                }
            }

            for (int i = 0; i < 4; i++) {
                move(i, copyBoard(board), cnt + 1);
            }
        }
    }

    private static int[][] copyBoard(int[][] origin) {
        int[][] copy = new int[N][N];

        for (int i = 0; i < N; i++) {
            System.arraycopy(origin[i], 0, copy[i], 0, N);
        }

        return copy;
    }

    private static void findMaxNum(int[][] board) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                answer = Math.max(answer, board[i][j]);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        int[][] board = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < 4; i++) {
            move(i, copyBoard(board), 0);
        }

        System.out.println(answer);
    }
}
