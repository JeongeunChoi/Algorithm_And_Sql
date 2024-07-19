package algorithm.algorithm;

import java.io.*;
import java.util.*;

public class BJ_17780 {

    private static int N, K;
    private static int[][] chessBoard;
    private static ArrayList<Pair>[][] horses;
    private static Map<Integer, Pair> horseMap;
    private static int[] dx = {0, 0, -1, 1}, dy = {1, -1, 0, 0};
    private static int WHITE = 0, RED = 1, BLUE = 2;

    private static int getGameOverTurn() {
        int turn = 1;
        while (turn <= 1000) {
            if (!playAndCompleteTurn()) {
                return turn;
            }
            turn++;
        }

        return -1;
    }

    private static boolean playAndCompleteTurn() {
        for (int i = 1; i <= K; i++) {
            Pair currentHorseLocation = horseMap.get(i);
            ArrayList<Pair> horsesToMove = horses[currentHorseLocation.a][currentHorseLocation.b];
            Pair bottomHorse = horsesToMove.get(0);

            // 이동할 말이 가장 아래에 위치하는지 확인
            if (i != bottomHorse.a) {
                continue;
            }

            int nx = currentHorseLocation.a + dx[bottomHorse.b], ny = currentHorseLocation.b + dy[bottomHorse.b];
            if (outOfBound(nx, ny) || chessBoard[nx][ny] == BLUE) { // 범위를 벗어나거나 파란색인 경우
                bottomHorse.b = getReverseDir(bottomHorse.b);
                nx = currentHorseLocation.a + dx[bottomHorse.b];
                ny = currentHorseLocation.b + dy[bottomHorse.b];
                if (!outOfBound(nx, ny) && chessBoard[nx][ny] != BLUE) { // 범위를 벗어나지 않고, 다음칸이 파란색이 아닌 흰색 또는 빨간색인 경우 말들 이동
                    moveHorses(nx, ny, horsesToMove, currentHorseLocation);
                }
            } else { // 범위를 벗어나지 않고, 다음칸이 파란색이 아닌 흰색 또는 빨간색인 경우 말들 이동
                moveHorses(nx, ny, horsesToMove, currentHorseLocation);
            }

            // 이동한 위치에 말이 4개 이상 쌓이면 false 반환
            if (!outOfBound(nx, ny) && horses[nx][ny].size() >= 4) {
                return false;
            }
        }

        return true;
    }

    private static void moveHorses(int nx, int ny, ArrayList<Pair> horsesToMove, Pair currentHorseLocation) {
        if (chessBoard[nx][ny] == WHITE) {
            horses[nx][ny].addAll(horsesToMove);
            updateHorsesLocation(horsesToMove, nx, ny);
            horses[currentHorseLocation.a][currentHorseLocation.b].clear();
        } else if (chessBoard[nx][ny] == RED) {
            Collections.reverse(horsesToMove);
            horses[nx][ny].addAll(horsesToMove);
            updateHorsesLocation(horsesToMove, nx, ny);
            horses[currentHorseLocation.a][currentHorseLocation.b].clear();
        }
    }

    private static void updateHorsesLocation(ArrayList<Pair> horsesToMove, int x, int y) {
        for (int j = 0; j < horsesToMove.size(); j++) {
            horseMap.put(horsesToMove.get(j).a, new Pair(x, y));
        }
    }

    private static boolean outOfBound(int x, int y) {
        return !(x >= 1 && x <= N && y >= 1 && y <= N);
    }

    private static int getReverseDir(int dir) {
        if (dir == 0) {
            return 1;
        } else if (dir == 1) {
            return 0;
        } else if (dir == 2) {
            return 3;
        } else if (dir == 3) {
            return 2;
        }

        return 4;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        chessBoard = new int[N + 1][N + 1];
        horses = new ArrayList[N + 1][N + 1];
        horseMap = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                chessBoard[i][j] = Integer.parseInt(st.nextToken());
                horses[i][j] = new ArrayList<>();
            }
        }
        for (int i = 1; i <= K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(
                    st.nextToken()), d = Integer.parseInt(st.nextToken());
            horses[x][y].add(new Pair(i, d - 1));
            horseMap.put(i, new Pair(x, y));
        }

        System.out.println(getGameOverTurn());

    }

    static class Pair {

        int a, b;

        Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

}
