package algorithm;

import java.io.*;
import java.util.*;

public class BJ_14889 {

    private static int N, answer = 20000;
    private static int[][] abilities;
    private static int[] people;

    private static void divideTeam(int pIdx, int cnt) {
        if (cnt == N / 2) {
            ArrayList<Integer> team0People = new ArrayList<>();
            ArrayList<Integer> team1People = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                if (people[i] == 0) {
                    team0People.add(i);
                } else if (people[i] == 1) {
                    team1People.add(i);
                }
            }
            answer = Math.min(answer, Math.abs(
                    calculateTeamAbility(team0People) - calculateTeamAbility(team1People)));
        } else {
            people[pIdx] = 1;
            for (int i = pIdx + 1; i < N; i++) {
                divideTeam(i, cnt + 1);
            }
            people[pIdx] = 0;
        }
    }

    private static int calculateTeamAbility(ArrayList<Integer> people) {
        int abilitySum = 0;
        for (int i = 0; i < people.size(); i++) {
            for (int j = i + 1; j < people.size(); j++) {
                abilitySum += abilities[people.get(i)][people.get(j)];
                abilitySum += abilities[people.get(j)][people.get(i)];
            }
        }

        return abilitySum;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        abilities = new int[N][N];
        people = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                abilities[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            divideTeam(i, 0);
        }

        System.out.println(answer);
    }
}
