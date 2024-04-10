package algorithm;

import java.io.*;
import java.util.*;

public class BJ_3190 {

    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};
    private static int N, K, L;
    private static int hx = 1, hy = 1, hdir = 0;
    private static int tx = 1, ty = 1;
    private static int time = 1;
    private static int[][] board;
    private static Map<Integer, String> changeDirectionMap;
    private static Queue<Integer> xQ, yQ; // 뱀 꼬리 좌표 찾기 위함

    private static boolean outOfBound(int x, int y) {
        return !(x >= 1 && x <= N && y >= 1 && y <= N);
    }

    private static boolean conflictHeadAndBody(int hx, int hy) {
        return board[hx][hy] == 2;
    }

    private static void changeHeadDirection() {
        if (changeDirectionMap.containsKey(time)) {
            String changeDirection = changeDirectionMap.get(time);
            if (changeDirection.equals("L")) {
                hdir -= 1;
                if (hdir == -1) {
                    hdir = 3;
                }
            } else if (changeDirection.equals("D")) {
                hdir += 1;
                hdir %= 4;
            }
        }
    }

    private static void moveHead() {
        board[hx][hy] = 2;
        xQ.add(hx);
        yQ.add(hy);
    }

    private static void moveTail() {
        board[tx][ty] = 0;
        xQ.poll();
        yQ.poll();
        tx = xQ.peek();
        ty = yQ.peek();
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        board = new int[N + 1][N + 1];
        K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken());
            board[x][y] = 1;
        }
        L = Integer.parseInt(br.readLine());
        changeDirectionMap = new HashMap<>();
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            String d = st.nextToken();
            changeDirectionMap.put(t, d);
        }

        board[hx][hy] = 2;
        xQ = new LinkedList<>();
        yQ = new LinkedList<>();
        xQ.add(1);
        yQ.add(1);
        while (true) {
            int nhx = hx + dx[hdir], nhy = hy + dy[hdir];
            if (outOfBound(nhx, nhy) || conflictHeadAndBody(nhx, nhy)) {
                break;
            }
            if (board[nhx][nhy] == 1) { // 사과가 있는 경우
                hx = nhx;
                hy = nhy;
                moveHead();
            } else { // 사과가 없는 경우
                hx = nhx;
                hy = nhy;
                moveHead();
                moveTail();
            }
            changeHeadDirection();
            time++;
        }

        System.out.println(time);
    }
}
