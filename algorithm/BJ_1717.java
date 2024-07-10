package algorithm.algorithm;

import java.io.*;
import java.util.*;

public class BJ_1717 {

    private static int[] parent;

    private static void union(int a, int b) {
        a = getParent(a);
        b = getParent(b);
        if (a != b) { // 부모가 다르면 합친다.
            parent[b] = a;
        }
    }

    private static int getParent(int num) {
        if (parent[num] == num) {
            return parent[num];
        } else {
            return parent[num] = getParent(parent[num]);
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
        parent = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int isUnion = Integer.parseInt(st.nextToken()), a = Integer.parseInt(
                    st.nextToken()), b = Integer.parseInt(st.nextToken());
            if (isUnion == 0) {
                union(a, b);
            } else {
                System.out.println(getParent(a) == getParent(b) ? "YES" : "NO");
            }
        }
    }

}
