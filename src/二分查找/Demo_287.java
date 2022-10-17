package 二分查找;

import utils.uu;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/2/28 20:14
 */

//287-寻找重复数
public class Demo_287 {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 4, 2, 2};
        System.out.println(findDuplicate_1(nums));
        System.out.println(findDuplicate_2(nums));
        uu.print("执行用时：\n" +
                "25 ms\n" +
                ", 在所有 Java 提交中击败了\n" +
                "35.87%\n" +
                "的用户\n" +
                "内存消耗：\n" +
                "58.5 MB\n" +
                ", 在所有 Java 提交中击败了\n" +
                "35.94%\n" +
                "的用户");
    }

    /**
     * 二分<br>
     * 执行用时：25 ms, 在所有 Java 提交中击败了35.87%的用户<br>
     * 内存消耗：58.5 MB, 在所有 Java 提交中击败了35.94%的用户
     */
    public static int findDuplicate_2(int[] nums) {
        //出现的数取值在数组长度之内
        int left = 1, right = nums.length - 1;

        while (left < right) {//在取值范围内随机猜一个数
            int mid = left + (right - left) / 2;

            int count = 0;
            for (int val : nums)
                if (val <= mid)
                    ++count;

            //抽屉原理：把 10 个苹果放进 9 个抽屉，一定存在某个抽屉放至少 2 个苹果
            if (count > mid) {//严格大于(说明取值范围内装不下这些数)
                right = mid;
            } else if (count <= mid)
                left = mid + 1;//说明装得下这些数
        }

        return left;
    }

    /**
     * 哈希<br>
     * 执行用时：16 ms, 在所有 Java 提交中击败了42.68%的用户<br>
     * 内存消耗：59.9 MB, 在所有 Java 提交中击败了5.05%的用户
     */
    public static int findDuplicate_1(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int val : nums)
            if (!set.add(val))
                return val;

        return -1;
    }
}
