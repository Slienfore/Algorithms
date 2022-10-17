package 回溯;

import utils.uu;

import java.util.*;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/11 8:56
 */
//2049-统计最高分的结点数目
public class Demo_2049 {
    public static void main(String[] args) {
        int[] nums = {-1, 2, 0, 2, 0};
//        int[] nums = {-1, 2, 0};
//        System.out.println(countHighestScoreNodes_2(nums));
        System.out.println(countHighestScoreNodes_1(nums));

    }

    static long maxScore = 0;
    static int res = 0;//最大得分以及得分的个数
    static List<Integer>[] mapping;

    /**
     * 递归<br>
     * 执行用时：59 ms, 在所有 Java 提交中击败了77.92%的用户<br>
     * 内存消耗：79.1 MB, 在所有 Java 提交中击败了16.23%的用户
     */
    public static int countHighestScoreNodes_2(int[] parents) {
        int length = parents.length;
        mapping = new List[length];

        for (int root = 0; root < length; ++root)
            mapping[root] = new ArrayList<>();

        for (int child = 0; child < length; ++child) {
            int root = parents[child];
            if (root != -1)
                mapping[root].add(child);
        }

        recur_2(0, length);
        return res;
    }

    private static int recur_2(int root, int total) {
        long score = 1;//自己结点得分

        int linking = 1;//相连部分长度

        for (int child : mapping[root]) {
            int length = recur_2(child, total);

            score *= length;//联通部分得分

            linking += length;
        }

        if (root != 0)
            score *= total - linking;//不连通部分得分

        if (score == maxScore)
            res++;
        else if (score > maxScore) {//重置
            maxScore = score;
            res = 1;
        }

        return linking;
    }


    static Map<Integer, List<Integer>> map = new HashMap<>();

    /**
     * 递归(Map)<br>
     * 执行用时：98 ms, 在所有 Java 提交中击败了46.10%的用户<br>
     * 内存消耗：79.3 MB, 在所有 Java 提交中击败了14.93%的用户
     */
    public static int countHighestScoreNodes_1(int[] parents) {
        int length = parents.length;

        for (int child = 0; child < length; child++) {
            int root = parents[child];
            if (root != -1)
                map.computeIfAbsent(root, o -> new ArrayList<>()).add(child);
        }
            /*
            删除一个节点过后分成 3 部分
            当前结点、连通的孩子结点、不连通的双亲结点
            根节点与这两部分相连(额外判断)
            */
        recur_1(0, length);
        return res;
    }


    private static int recur_1(int root, int total) {
        long score = 1;//自己结点得分

        int linking = 1;//相连部分长度

        if (map.get(root) != null)
            for (int child : map.get(root)) {
                int length = recur_1(child, total);

                score *= length;//联通部分得分

                linking += length;
            }


        score *= root == 0 ? 1 : (total - linking);//不连通部分得分

        if (score == maxScore)
            res++;
        else if (score > maxScore) {//重置
            maxScore = score;
            res = 1;
        }

        return linking;
    }
}
