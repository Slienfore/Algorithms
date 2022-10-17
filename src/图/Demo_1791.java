package 图;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/2/18 17:29
 */
//1791-找出星型图的中心结点
public class Demo_1791 {
    public static void main(String[] args) {
        int[][] edges = {{1, 2}, {2, 3}, {4, 2}};

        System.out.println(findCenter_1(edges));
        System.out.println(findCenter_2(edges));
        System.out.println(findCenter_3(edges));
        System.out.println(findCenter_4(edges));

    }

    /**
     * 规律<br>
     * 执行用时：0ms, 在所有 Java 提交中击败了100%的用户<br>
     * 内存消耗：63.5 MB, 在所有 Java 提交中击败了30%的用户
     */
    public static int findCenter_1(int[][] edges) {
        if (edges[0][0] == edges[1][0] || edges[0][0] == edges[1][1]) return edges[0][0];//中心结点一定出现在每一个pair中
        else return edges[0][1];
    }

    /**
     * 哈希数组<br>
     * 执行用时：2 ms, 在所有 Java 提交中击败了33.81%的用户<br>
     * 内存消耗：67.8 MB, 在所有 Java 提交中击败了14.05%的用户
     */
    public static int findCenter_2(int[][] edges) {
        //度为 N - 1 则为中心结点，且结点的个数均是按顺序开始的，使用数组作为哈希表
        int degree = edges.length;//度 数量
        int node = degree + 1;//结点个数

        int[] hash = new int[node];//结点个数

        for (int[] arr : edges) {//统计结点相连 度 数量
            hash[arr[0] - 1]++;
            hash[arr[1] - 1]++;
        }

        for (int i = 0; i < node; i++)//如果该结点 度 数量 等于规定则返回
            if (hash[i] == degree) return i + 1;

        return -1;

    }

    /**
     * 哈希Set<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：64.1 MB, 在所有 Java 提交中击败了23.01%的用户
     */
    public static int findCenter_3(int[][] edges) {
        HashSet<Integer> set = new HashSet<>();
        set.add(edges[0][0]);
        set.add(edges[0][1]);

        return set.contains(edges[1][0]) ? edges[1][0] : edges[1][1];//判断哈希Set中是否包含第二个节点的边
    }

    /**
     * 哈希Map<br>
     * 执行用时：29 ms, 在所有 Java 提交中击败了8.55%的用户<br>
     * 内存消耗：78.9 MB, 在所有 Java 提交中击败了5.09%的用户
     */
    public static int findCenter_4(int[][] edges) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int[] arr : edges) {
            map.put(arr[0], map.getOrDefault(arr[0], 0) + 1);
            map.put(arr[1], map.getOrDefault(arr[1], 0) + 1);
        }

        int degree = edges.length;//度的数量

        for (Integer key : map.keySet())
            if (map.get(key) == degree) return key;

        return -1;

    }

}



