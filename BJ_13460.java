package algorithm;

import java.io.*;
import java.util.*;

public class BJ_13460 {
    private static final int[] dx = {0, 1, 0, -1};
    private static final int[] dy = {1, 0, -1, 0}; // 오른쪽, 아래쪽, 왼쪽, 위쪽
    private static int N, M, answer = 11;
    private static char[][] board;

    private static void dfs(int dir, int cnt, int rx, int ry, int bx, int by) {
        boolean redOut = false, blueOut = false;
        int crx = rx, cry = ry, cbx = bx, cby = by;
        while (true) {
            if (moveRFirst(dir, crx, cry, cbx, cby)) {
                int nrx = crx + dx[dir], nry = cry + dy[dir];
                if (nextIsHole(nrx, nry)) {
                    redOut = true;
                    crx = nrx;
                    cry = nry;
                }
                boolean canMoveR = canMoveR(nrx, nry, cbx, cby, redOut);
                if (canMoveR) {
                    crx = nrx;
                    cry = nry;
                }
                int nbx = cbx + dx[dir], nby = cby + dy[dir];
                if (nextIsHole(nbx, nby)) {
                    blueOut = true;
                    cbx = nbx;
                    cby = nby;
                }
                boolean canMoveB = canMoveB(crx, cry, nbx, nby, blueOut);
                if (canMoveB) {
                    cbx = nbx;
                    cby = nby;
                }
                if (!canMoveR && !canMoveB) {
                    break;
                }
            } else {
                int nbx = cbx + dx[dir], nby = cby + dy[dir];
                if (nextIsHole(nbx, nby)) {
                    blueOut = true;
                    cbx = nbx;
                    cby = nby;
                }
                boolean canMoveB = canMoveB(crx, cry, nbx, nby, blueOut);
                if (canMoveB) {
                    cbx = nbx;
                    cby = nby;
                }
                int nrx = crx + dx[dir], nry = cry + dy[dir];
                if (nextIsHole(nrx, nry)) {
                    redOut = true;
                    crx = nrx;
                    cry = nry;
                }
                boolean canMoveR = canMoveR(nrx, nry, cbx, cby, redOut);
                if (canMoveR) {
                    crx = nrx;
                    cry = nry;
                }
                if (!canMoveR && !canMoveB) {
                    break;
                }
            }
        }

        boolean isMoved = isMoved(rx, ry, bx, by, crx, cry, cbx, cby);
        if (redOut && !blueOut) {
            answer = Math.min(answer, cnt + 1);
        } else if (blueOut|| cnt + 1 == 10) {
            return;
        } else {
            for (int i = 0; i < 4; i++) {
                if (!isMoved && dir == i) {
                    continue;
                } else {
                    dfs(i, cnt + 1, crx, cry, cbx, cby);
                }
            }
        }
    }

    private static boolean outOfBound(int x, int y) {
        return !(x >= 0 && x < N && y >= 0 && y < M);
    }

    private static boolean nextIsBall(int rx, int ry, int bx, int by) {
        return (rx == bx && ry == by);
    }

    private static boolean nextIsHole(int nx, int ny) {
        return board[nx][ny] == 'O';
    }

    private static boolean canMoveR(int nrx, int nry, int bx, int by, boolean isOut) {
        return (!outOfBound(nrx, nry) && board[nrx][nry] == '.' && !nextIsBall(nrx, nry, bx, by)
                && !isOut);
    }

    private static boolean canMoveB(int rx, int ry, int nbx, int nby, boolean isOut) {
        return (!outOfBound(nbx, nby) && board[nbx][nby] == '.' && !nextIsBall(rx, ry, nbx, nby)
                && !isOut);
    }

    private static boolean moveRFirst(int dir, int rx, int ry, int bx, int by) {
        if (dir == 0 && ry > by) {
            return true;
        } else if (dir == 1 && rx > bx) {
            return true;
        } else if (dir == 2 && ry < by) {
            return true;
        } else {
            return dir == 3 && rx < bx;
        }
    }

    private static boolean isMoved(int rx, int ry, int bx, int by, int crx, int cry, int cbx,
            int cby) {
        return (rx != crx || ry != cry || bx != cbx || by != cby);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        int rx = 0, ry = 0, bx = 0, by = 0;

        for (int i = 0; i < N; i++) {
            String boardStr = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = boardStr.charAt(j);
                if (board[i][j] == 'R') {
                    rx = i;
                    ry = j;
                    board[i][j] = '.';
                } else if (board[i][j] == 'B') {
                    bx = i;
                    by = j;
                    board[i][j] = '.';
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            dfs(i, 0, rx, ry, bx, by);
        }

        if (answer == 11) {
            answer = -1;
        }

        System.out.println(answer);
    }
}
