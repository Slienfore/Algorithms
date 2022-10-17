package 指针;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/11/20 9:33
 */
//594-最长和谐子序列
public class Demo_594 {
    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 1};
        System.out.println(findLHS(nums));
    }
    /**暴力<br>
    执行用时：1098 ms, 在所有 Java 提交中击败了5.06%的用户<br>
    内存消耗：38.8 MB, 在所有 Java 提交中击败了97.25%的用户
    */
    public static int findLHS(int[] nums) {
        Arrays.sort(nums);
        int res = 0;

        for (int i = 0; i < nums.length; i++) {
            int curVal = nums[i];
            int right = nums.length - 1;
            while (right > i) {

                if (Math.abs(curVal - nums[right]) == 1) {
                    res = Math.max(res, (right - i + 1));
                    break;
                }

                right--;
            }
        }
        return res;
    }
}
