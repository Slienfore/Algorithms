package 回溯;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/4/1 19:12
 */
//547-省份的数量
public class Demo_547 {
    public static void main(String[] args) {
        int[][] matrix = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
//        int[][] matrix = {{1, 0, 0, 1}, {0, 1, 1, 0}, {0, 1, 1, 1}, {1, 0, 1, 1}};
        System.out.println(findCircleNum(matrix));
    }

    private static int CITIES;//城市的数量
    /**
     * DFS<br>
     * 执行用时：1 ms, 在所有 Java 提交中击败了87.68%的用户<br>
     * 内存消耗：42 MB, 在所有 Java 提交中击败了49.49%的用户<br>
     * 2022年04月01日  20:06:16
     */
    public static int findCircleNum(int[][] isConnected) {
        CITIES = isConnected.length;

        boolean[] connect = new boolean[CITIES];//访问城市的数量
        int res = 0;
        for (int cur = 0; cur < CITIES; ++cur)
            if (!connect[cur]) {//若该城市尚未连通
                dfs(cur, connect, isConnected);
                ++res;
            }

        return res;
    }

    private static void dfs(int cur, boolean[] connect, int[][] matrix) {

        for (int next = 0; next < CITIES; ++next) {
            if (matrix[cur][next] == 0 || connect[next])//若不是城市或者已经联通了的城市
                continue;

            connect[next] = true;//联通了

            dfs(next, connect, matrix);//搜索与该城市相连的下一个城市
        }

    }
}
