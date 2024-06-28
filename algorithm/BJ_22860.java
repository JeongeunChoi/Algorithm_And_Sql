package algorithm.algorithm;

import java.io.*;
import java.util.*;

public class BJ_22860 {

    private static BufferedReader br;
    private static StringTokenizer st;
    private static Map<String, ArrayList<FileFolderInfo>> folderFileMap = new HashMap<>();
    private static Set<String> fileSet;
    private static int containingFileCnt;

    private static void findFiles(ArrayList<FileFolderInfo> fileFolderInfos) {
        for (int j = 0; j < fileFolderInfos.size(); j++) {
            FileFolderInfo fileFolderInfo = fileFolderInfos.get(j);
            if (fileFolderInfo.isFolder) {
                findFiles(folderFileMap.get(fileFolderInfo.name));
            } else {
                fileSet.add(fileFolderInfo.name);
                containingFileCnt++;
            }
        }
    }

    private static void inputDirectoryStructure() throws Exception {
        st = new StringTokenizer(br.readLine());
        int folderCnt = Integer.parseInt(st.nextToken()), fileCnt = Integer.parseInt(
                st.nextToken());
        for (int i = 0; i < folderCnt + fileCnt; i++) {
            st = new StringTokenizer(br.readLine());
            String parent = st.nextToken(), child = st.nextToken();
            boolean isFolder = Integer.parseInt(st.nextToken()) == 1;
            if (folderFileMap.containsKey(parent)) {
                folderFileMap.get(parent).add(new FileFolderInfo(child, isFolder));
            } else {
                folderFileMap.put(parent,
                        new ArrayList<>(List.of(new FileFolderInfo(child, isFolder))));
            }
            if (isFolder && !folderFileMap.containsKey(child)) { // 폴더가 key 값으로 없으면 추가
                folderFileMap.put(child, new ArrayList<>());
            }
        }
    }

    private static String getChildFolder() throws Exception {
        st = new StringTokenizer(br.readLine());
        String folder = st.nextToken("/");
        while (st.hasMoreTokens()) {
            folder = st.nextToken("/");
        }

        return folder;
    }

    public static void main(String[] args) throws Exception {
         System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));

        inputDirectoryStructure();
        int queryCnt = Integer.parseInt(br.readLine());
        for (int i = 0; i < queryCnt; i++) {
            fileSet = new HashSet<>();
            containingFileCnt = 0;
            findFiles(folderFileMap.get(getChildFolder()));

            System.out.println(fileSet.size() + " " + containingFileCnt);
        }

    }

    private static class FileFolderInfo {

        String name;
        boolean isFolder;

        FileFolderInfo(String name, boolean isFolder) {
            this.name = name;
            this.isFolder = isFolder;
        }
    }
}
