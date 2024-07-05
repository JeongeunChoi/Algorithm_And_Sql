package algorithm.algorithm;

import java.io.*;
import java.util.*;

public class BJ_3109 {

    // 최대한 위쪽 방향으로 설치해야 다음 파이프를 놓을 가능성이 높다.
    // 파이프를 놓는다면 해당 점은 접근을 못한다.
    // 한 점에서 끝까지 도달하는 것을 실패했다면 해당 지점은 다시 가볼 필요가 없다.

    private static int[] dx = {-1, 0, 1};
    private static int R, C, answer = 0;
    private static char[][] map;

    private static boolean dfs(int x, int y) {
        map[x][y] = 'x'; // 파이프 놓으면 지나갈 수 없게 됨.
        if (y == C - 1) { // 빵집 도착 시, answer 증가 후 true 반환
            answer++;
            return true;
        } else {
            for (int i = 0; i < 3; i++) { // 빵집 미도착 시, 오른쪽 위 대각선, 오른쪽, 오른쪽 아래 대각선 순으로 탐색
                int nx = x + dx[i], ny = y + 1;
                if (!outOfBound(nx, ny) && map[nx][ny] == '.') { // 파이프 놓을 수 있으면 dfs 호출
                    if (dfs(nx, ny)) { // dfs 반환 값이 true 라면 빵집에 도착한 것 이므로, 탐색을 멈추고 true 반환
                        return true;
                    }
                }
            }
        }

        return false; // 다음 지점으로 가는 dfs의 모든 반환값이 false 라면 빵집 도착 불가하므로 false 반환
    }

    private static boolean outOfBound(int x, int y) {
        return !(x >= 0 && x < R && y >= 0 && y < C);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        // (0, 0) ~ (0, R-1) 순으로 파이프를 놓는다.
        for (int i = 0; i < R; i++) {
            dfs(i, 0);
        }

        System.out.println(answer);
    }

}
