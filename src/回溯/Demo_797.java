package 回溯;

import utils.uu;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/4/2 16:05
 */
//797-所有可能的路径
public class Demo_797 {
    public static void main(String[] args) {
//        int[][] matrix = {{1, 2}, {3}, {3}, {}};
        int[][] matrix = {{4, 3, 1}, {3, 2, 4}, {3}, {4}, {}};
        System.out.println(allPathsSourceTarget(matrix));
    }

    static List<List<Integer>> res = new ArrayList<>();
    static LinkedList<Integer> path = new LinkedList<>();
    /**
     * 回溯<br>
     * 执行用时：2 ms, 在所有 Java 提交中击败了68.01%的用户<br>
     * 内存消耗：42.8 MB, 在所有 Java 提交中击败了70.73%的用户<br>
     * 2022年04月02日  16:23:37
     */
    public static List<List<Integer>> allPathsSourceTarget(int[][] graph) {

        path.add(0);//起始节点

        backTrack(0, graph);

        return res;
    }

    private static void backTrack(int begin, int[][] matrix) {
        if (begin == matrix.length - 1) {//每一个顶点都可以连通最后一个结点
            res.add(new ArrayList<>(path));
            return;
        }

        for (int next : matrix[begin]) {//有向无环图，每一个结点在遍历路径中只会访问一次
            path.add(next);//进入下一个顶点

            backTrack(next, matrix);

            path.removeLast();
        }

    }
}
