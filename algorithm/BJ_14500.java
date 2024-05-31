package algorithm.algorithm;

import java.io.*;
import java.util.*;

public class BJ_14500 {

    private static int N, M, answer = 0;
    private static int[][] map;

    private static ArrayList<ArrayList<Integer>> tetrominoes = new ArrayList<>();
    private static int[] dx = {0, -1, 0, 1}, dy = {1, 0, -1, 0}; // 오른쪽, 아래쪽, 왼쪽, 위쪽

    private static void saveTetrominoes() {
        for (int i = 0; i < 5; i++) {
            tetrominoes.add(new ArrayList<>());
        }
        // 방향 그대로면 0, 오른쪽 1, 왼쪽 -1, 반대편 2

        // 0번 테트로미노
        tetrominoes.get(0).addAll(Arrays.asList(0, 0, 0));

        // 1번 테트로미노
        tetrominoes.get(1).addAll(Arrays.asList(0, 1, 1));

        // 2번 테트로미노
        tetrominoes.get(2).addAll(Arrays.asList(0, 0, -1));

        // 3번 테트로미노
        tetrominoes.get(3).addAll(Arrays.asList(0, -1, 1));

        // 4번 테트로미노
        tetrominoes.get(4).addAll(Arrays.asList(0, 0, 2, -1));
    }


    private static void putTetromino(int x, int y, int tIdx) {
        for (int i = 0; i < 4; i++) { // 시작점으로부터 동서남북 4방향으로
            int sum = map[x][y], dIdx = i, nx = x, ny = y;
            boolean canPut = true;
            for (int j = 0; j < tetrominoes.get(tIdx).size(); j++) {
                int dir = tetrominoes.get(tIdx).get(j);
                dIdx += dir;
                dIdx = (dIdx == -1 ? 3 : dIdx % 4);
                if (outOfBound(nx + dx[dIdx], ny + dy[dIdx])) {
                    canPut = false;
                    break;
                } else {
                    nx += dx[dIdx];
                    ny += dy[dIdx];
                    if (!(tIdx == 4 && dir == 2)) { // 테트로미노 4번은 다시 되돌아갈때 중복 방문하므로 이 경우는 더하지 않도록 하기
                        sum += map[nx][ny];
                    }
                }
            }
            if (canPut) {
                answer = Math.max(answer, sum);
            }
        }

        // 대칭하는 경우
        for (int i = 0; i < 4; i++) { // 시작점으로부터 동서남북 4방향으로
            int sum = map[x][y], dIdx = i, nx = x, ny = y;
            boolean canPut = true;
            for (int j = 0; j < tetrominoes.get(tIdx).size(); j++) {
                int dir = tetrominoes.get(tIdx).get(j);
                dIdx += (dir != 2 ? dir * -1 : dir);
                dIdx = (dIdx == -1 ? 3 : dIdx % 4);
                if (outOfBound(nx + dx[dIdx], ny + dy[dIdx])) {
                    canPut = false;
                    break;
                } else {
                    nx += dx[dIdx];
                    ny += dy[dIdx];
                    if (!(tIdx == 4 && dir == 2)) { // 테트로미노 4번은 다시 되돌아갈때 중복 방문하므로 이 경우는 더하지 않도록 하기
                        sum += map[nx][ny];
                    }
                }
            }
            if (canPut) {
                answer = Math.max(answer, sum);
            }
        }
    }

    private static boolean outOfBound(int x, int y) {
        return !(x >= 0 && x < N && y >= 0 && y < M);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        saveTetrominoes();

        for (int i = 0; i < tetrominoes.size(); i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    putTetromino(j, k, i);
                }
            }
        }

        System.out.println(answer);
    }
}
