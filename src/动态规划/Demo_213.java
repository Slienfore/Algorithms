package 动态规划;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/15 20:56
 */
//213-打家劫舍-II
public class Demo_213 {
    public static void main(String[] args) {
        int[] nums = {2, 3, 2};
//        int[] nums = {1, 2, 3, 1};
//        int[] nums = {0, 0};
        System.out.println(rob_1(nums));
        System.out.println(rob_2(nums));

    }

    /**
     * 动规(优化)<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：39.1 MB, 在所有 Java 提交中击败了6.00%的用户
     */
    public static int rob_2(int[] nums) {
        int length = nums.length;

        if (length == 1)
            return nums[0];
        else if (length == 2)
            return Math.max(nums[0], nums[1]);

        //去头
        int preFirst = nums[1], neighFirst = Math.max(nums[1], nums[2]);
        //去尾
        int preLast = nums[0], neighLast = Math.max(nums[0], nums[1]);

        for (int hose = 2; hose < length; ++hose) {
            int temp;

            if (hose <= length - 2) {//去尾
                temp = neighLast;
                neighLast = Math.max(neighLast, preLast + nums[hose]);
                preLast = temp;
            }

            if (hose >= 3) {//去头
                temp = neighFirst;
                neighFirst = Math.max(neighFirst, preFirst + nums[hose]);
                preFirst = temp;
            }

        }

        return Math.max(neighFirst, neighLast);
    }

    /**
     * 动规<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：38.6 MB, 在所有 Java 提交中击败了49.75%的用户
     */
    public static int rob_1(int[] nums) {
        int length = nums.length;
        if (length == 1)
            return nums[0];

        int removeFirst = count(1, length - 1, nums);//去头
        int removeLast = count(0, length - 2, nums);//去尾

        return Math.max(removeFirst, removeLast);
    }

    private static int count(int begin, int end, int[] nums) {
        if (begin == end)
            return nums[begin];

        int preNeigh = nums[begin], neigh = Math.max(preNeigh, nums[begin + 1]);

        for (int hose = begin + 2; hose <= end; ++hose) {
            int temp = neigh;

            neigh = Math.max(preNeigh + nums[hose], neigh);

            preNeigh = temp;
        }

        return neigh;
    }

}
