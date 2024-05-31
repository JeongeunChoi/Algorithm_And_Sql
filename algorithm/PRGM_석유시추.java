package algorithm.algorithm;

import java.util.*;

public class PRGM_석유시추 {

    private int h, w;
    private int[][] copyLand;
    private int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};
    private boolean[][] visited;
    private final Map<Integer, Integer> areaOilMap = new HashMap<>();

    private int countOilInArea(Location startLoc, int areaNum) {
        int oilCnt = 0;
        Queue<Location> q = new LinkedList<>();
        q.add(startLoc);
        oilCnt++;
        copyLand[startLoc.x][startLoc.y] = areaNum;
        visited[startLoc.x][startLoc.y] = true;
        while (!q.isEmpty()) {
            Location loc = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = loc.x + dx[i], ny = loc.y + dy[i];
                if (!outOfBound(nx, ny) && !visited[nx][ny] && copyLand[nx][ny] == 1) {
                    oilCnt++;
                    copyLand[nx][ny] = areaNum;
                    visited[nx][ny] = true;
                    q.add(new Location(nx, ny));
                }
            }
        }

        return oilCnt;
    }

    private boolean outOfBound(int x, int y) {
        return !(x >= 0 && x < h && y >= 0 && y < w);
    }

    public int solution(int[][] land) {
        int answer = 0;
        copyLand = land;

        h = land.length;
        w = land[0].length;

        int areaNum = 2;
        visited = new boolean[h][w];
        // 시추관을 뚫는 세로 방향으로 탐색
        for (int i = 0; i < w; i++) {
            Set<Integer> areaSet = new HashSet<>(); // 석유 추출할 수 있는 구역 번호 저장
            for (int j = 0; j < h; j++) {
                if (copyLand[j][i] == 1 && !visited[j][i]) { // 새로운 구역 발견 시, 해당 구역에서 얻을 수 있는 석유 양 세고 구역 번호 추가
                    areaOilMap.put(areaNum, countOilInArea(new Location(j, i), areaNum));
                    areaSet.add(copyLand[j][i]);
                    areaNum++;
                } else if (copyLand[j][i] >= 2) { // 이미 석유 양이 계산된 구역인 경우 구역 번호 추가
                    areaSet.add(copyLand[j][i]);
                }
            }
            Iterator<Integer> it = areaSet.iterator();
            int oilSumCnt = 0;
            while (it.hasNext()) {
                oilSumCnt += areaOilMap.get(it.next());
            }
            answer = Math.max(answer, oilSumCnt);
        }

        return answer;
    }

    private class Location {

        int x, y;

        Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
