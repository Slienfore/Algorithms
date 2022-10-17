package 动态规划;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/25 17:03
 */
//740-删除并获得点数
public class Demo_740 {
    public static void main(String[] args) {
//        int[] nums = {3, 4, 2};
        int[] nums = {2, 2, 3, 3, 3, 4};

        System.out.println(deleteAndEarn(nums));
    }

    /**
     * 动规<br>
     * 执行用时：2 ms, 在所有 Java 提交中击败了58.99%的用户<br>
     * 内存消耗：40.9 MB, 在所有 Java 提交中击败了33.41%的用户-
     */
    public static int deleteAndEarn(int[] nums) {//打家劫舍。。。
        int maxAward = Integer.MIN_VALUE;//删除获得的最大点数

        for (int val : nums)
            maxAward = Math.max(maxAward, val);

        int[] grades = new int[maxAward + 1];

        for (int record : nums)//下标记录的是得分(grades[cur - 1], grades[cur + 1])
            grades[record] += record;//记录得分相同的所有元素之和

        int preNeigh = grades[0], neigh = Math.max(grades[0], grades[1]), length = grades.length;

        for (int cur = 2; cur < length; ++cur) {
            int temp = neigh;

            neigh = Math.max(neigh, preNeigh + grades[cur]);//删除当前点就不能获取到【得分相差 "1" 的点数得分】

            preNeigh = temp;
        }

        return neigh;
    }
}
