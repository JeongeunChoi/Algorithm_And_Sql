package algorithm.algorithm;

import java.io.*;
import java.util.*;

public class BJ_16960 {

    private static boolean canTurnOnLamp(ArrayList<ArrayList<Integer>> switches, int lampCnt) {
        for (int i = 0; i < switches.size(); i++) {
            Set<Integer> turnOnSwitchSet = new HashSet<>();
            for (int j = 0; j < switches.size(); j++) {
                if (j == i) {
                    continue;
                } else {
                    turnOnSwitchSet.addAll(switches.get(j));
                }
            }
            if (turnOnSwitchSet.size() == lampCnt) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int switchCnt = Integer.parseInt(st.nextToken()), lampCnt = Integer.parseInt(
                st.nextToken());
        ArrayList<ArrayList<Integer>> switches = new ArrayList<>();
        for (int i = 0; i < switchCnt; i++) {
            switches.add(new ArrayList<>());
        }

        for (int i = 0; i < switchCnt; i++) {
            st = new StringTokenizer(br.readLine());
            int linkedLampCnt = Integer.parseInt(st.nextToken());
            for (int j = 0; j < linkedLampCnt; j++) {
                switches.get(i).add(Integer.parseInt(st.nextToken()));
            }
        }

        if (canTurnOnLamp(switches, lampCnt)) { // N-1개의 스위치를 눌러서 모든 램프를 켤 수 있는지 확인하기
            System.out.println("1");
        } else {
            System.out.println("0");
        }
    }

}
