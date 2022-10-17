package 数组;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/1/13 11:38
 */
//747-至少是其他数字两倍的最大数
public class Demo_747 {
    public static void main(String[] args) {
        int[] nums = {0, 0, 3, 2};

        System.out.println(dominantIndex_1(nums));
        System.out.println(dominantIndex_2(nums));
    }

    /**
     * 暴力美学<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：36.3 MB, 在所有 Java 提交中击败了35.54%的用户
     */
    public static int dominantIndex_1(int[] nums) {
        int max = Integer.MIN_VALUE;
        int index = -1;

        for (int i = 0; i < nums.length; i++) {
            if (max <= nums[i]) {
                max = nums[i];
                index = i;
            }
        }

        for (int i = 0; i < nums.length; i++)
            if (i != index && 2 * nums[i] > max) return -1;

        return index;
    }

    /**寻找第一、二大数字<br>
    执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
    内存消耗：36.1 MB, 在所有 Java 提交中击败了74.88%的用户
    */
    private static int dominantIndex_2(int[] nums) {
        int max_1 = Integer.MIN_VALUE, max_2 = Integer.MIN_VALUE;
        int index = -1;

        for (int i = 0; i < nums.length; i++) {
            int val = nums[i];
            if (val > max_1) {//寻找第一大
                max_2 = max_1;
                max_1 = val;
                index = i;
            } else if (val > max_2) {//寻找第二大
                max_2 = val;
            }
        }

        return max_1 >= (2 * max_2) ? index : -1;
    }
}
