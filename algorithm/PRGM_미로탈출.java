package algorithm.algorithm;

import java.util.*;

public class PRGM_미로탈출 {

    private final char START = 'S', EXIT = 'E', LEVER = 'L', PASSAGE = 'O', WALL = 'X';
    private int h, w;
    private int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};
    private String[] mapsCopy;

    private int moveTo(char destination, Location startLoc) {
        boolean[][] visited = new boolean[h][w];

        Queue<Location> q = new LinkedList<>();
        visited[startLoc.x][startLoc.y] = true;
        q.add(startLoc);
        while (!q.isEmpty()) {
            Location loc = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = loc.x + dx[i], ny = loc.y + dy[i];
                if (!outOfBound(nx, ny) && !visited[nx][ny] && mapsCopy[nx].charAt(ny) != 'X') {
                    if (mapsCopy[nx].charAt(ny) == destination) {
                        return loc.dis + 1;
                    } else {
                        visited[nx][ny] = true;
                        q.add(new Location(nx, ny, loc.dis + 1));
                    }
                }
            }
        }

        return -1;
    }

    private boolean outOfBound(int x, int y) {
        return !(x >= 0 && x < h && y >= 0 && y < w);
    }

    public int solution(String[] maps) {
        int answer = 0;

        int sx = 0, sy = 0, lx = 0, ly = 0;
        h = maps.length;
        w = maps[0].length();
        mapsCopy = maps;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (maps[i].charAt(j) == START) {
                    sx = i;
                    sy = j;
                } else if (maps[i].charAt(j) == LEVER) {
                    lx = i;
                    ly = j;
                }
            }
        }

        // 레버까지 이동하는 최소 시간
        int timeToLever = moveTo(LEVER, new Location(sx, sy, 0));
        if (timeToLever == -1) {
            answer = -1;
        } else {
            answer += timeToLever;
            // 레버에서 출구로 이동하는 최소 시간
            int timeToExit = moveTo(EXIT, new Location(lx, ly, 0));
            if (timeToExit == -1) {
                answer = -1;
            } else {
                answer += timeToExit;
            }
        }

        return answer;
    }

    class Location {

        int x, y, dis;

        Location(int x, int y, int dis) {
            this.x = x;
            this.y = y;
            this.dis = dis;
        }
    }

}