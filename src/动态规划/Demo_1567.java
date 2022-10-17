package 动态规划;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/23 16:04
 */
//1567
public class Demo_1567 {
    public static void main(String[] args) {
//        int[] nums = {1, -2, -3, 4};
//        int[] nums = {0, 1, -2, -3, -4};
//        int[] nums = {-1, -2, -3, 0};
        int[] nums = {-1, -2, -3, 0, 1};
        System.out.println(getMaxLen(nums));

    }

    /**
     * 动规(分类讨论)<br>
     * 执行用时：3 ms, 在所有 Java 提交中击败了99.88%的用户<br>
     * 内存消耗：57.2 MB, 在所有 Java 提交中击败了19.77%的用户
     */
    public static int getMaxLen(int[] nums) {
        int posLen = 0, negLen = 0, max = 0;

        for (int val : nums) {
            if (val == 0) {
                posLen = negLen = 0;
            } else if (val > 0) {
                posLen++;
                negLen = (negLen > 0) ? (negLen + 1) : 0;//前面必须有 "负数" 乘以 "正数" 得负数

            } else if (posLen == 0 && negLen == 0) {
                negLen = 1;//当前数就是 "负数"

            } else if (posLen == 0 && negLen > 0) {
                posLen = negLen + 1;// "负数" + "负数"--->"正数"
                negLen = 1;//当前数就是 "负数"

            } else if (posLen > 0 && negLen == 0) {
                negLen = posLen + 1;// "正数" + "负数"--->"负数"
                posLen = 0;

            } else if (posLen > 0 && negLen > 0) {
                int temp = posLen;
                posLen = negLen + 1;// "负数" + "负数"--->"正数"
                negLen = temp + 1;// "正数" + "负数"--->"负数"

            }

            max = Math.max(posLen, max);
        }

        return max;
    }
}
