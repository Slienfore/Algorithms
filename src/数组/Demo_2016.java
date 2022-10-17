package 数组;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/5 8:56
 */
//增量元素之间的最大差值
public class Demo_2016 {
    public static void main(String[] args) {
//        int[] nums = {7, 1, 5, 4};
//        int[] nums = {9, 4, 3, 2};
        int[] nums = {1, 5, 2, 10};
        System.out.println(maximumDifference(nums));
    }


    /**动态维护最小值<br>
    执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
    内存消耗：40.8 MB, 在所有 Java 提交中击败了19.38%的用户
    */
    public static int maximumDifference(int[] nums) {
        int length = nums.length;
        if (length == 1)
            return -1;

        int min = nums[0], res = -1;

        for (int i = 1; i < length; i++) {
            if (nums[i] > min)//排除不满足preVal > curVal
                res = Math.max(nums[i] - min, res);//获取最大差值

            min = Math.min(min, nums[i]);//动态更新最小元素
        }

        return res;
    }
}
