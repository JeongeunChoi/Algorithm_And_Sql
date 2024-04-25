package algorithm;

import java.util.*;

public class PRGM_방문길이 {

    private ArrayList<ArrayList<Direction>> visitedPaths = new ArrayList<>();
    private int[] dx = {-1, 1, 0, 0};
    private int[] dy = {0, 0, 1, -1}; // U, D, R, L

    private int getDir(char dir) {
        if (dir == 'U') {
            return 0;
        } else if (dir == 'D') {
            return 1;
        } else if (dir == 'R') {
            return 2;
        } else if (dir == 'L') {
            return 3;
        }

        return -1;
    }

    private int getReverseDir(char dir) {
        if (dir == 'U') {
            return 1;
        } else if (dir == 'D') {
            return 0;
        } else if (dir == 'R') {
            return 3;
        } else if (dir == 'L') {
            return 2;
        }

        return -1;
    }

    private boolean outOfBound(int x, int y) {
        return !(x >= 0 && x <= 10 && y >= 0 && y <= 10);
    }

    public int solution(String dirs) {
        int answer = 0;
        int x = 5, y = 5;

        for (int i = 0; i <= 10; i++) {
            visitedPaths.add(new ArrayList<>());
            for (int j = 0; j <= 10; j++) {
                visitedPaths.get(i).add(new Direction());
            }
        }

        for (int i = 0; i < dirs.length(); i++) {
            int dir = getDir(dirs.charAt(i));
            int reverseDir = getReverseDir(dirs.charAt(i));
            if (!outOfBound(x + dx[dir], y + dy[dir])) {
                if (!visitedPaths.get(x).get(y).visited[dir]) {
                    answer++;
                    visitedPaths.get(x).get(y).visited[dir] = true;
                    visitedPaths.get(x + dx[dir]).get(y + dy[dir]).visited[reverseDir] = true;
                }
                x += dx[dir];
                y += dy[dir];
            }
        }

        return answer;
    }

    private class Direction {

        boolean[] visited = new boolean[4];
    }

}
