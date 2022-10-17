package 周赛;

import java.util.Arrays;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/11/28 10:42
 */
public class Demo_5939 {
    public static void main(String[] args) {
        int[] nums = {7, 4, 3, 9, 1, 8, 5, 2, 6};
        int k = 3;
        System.out.println(Arrays.toString(getAverages(nums, k)));
    }

    /**
    执行用时：8 ms, 在所有 Java 提交中击败了100.00%的用户<br>
    内存消耗：57.8 MB, 在所有 Java 提交中击败了100.00%的用户
    */
    public static int[] getAverages(int[] nums, int k) {
        int length = nums.length;
        int[] avg = new int[length];
        Arrays.fill(avg, -1);

        if (length <= k) return avg;//不满足左
        else if ((2 * k) >= length) return avg;//不满足右
        else if (k == 0) return nums;

        double num = (2 * k + 1) * 1.0, sum = 0;

        for (int i = 0; i <= (2 * k); i++)
            sum += nums[i];

        for (int i = k; i < (length - k); i++) {//移动区间
            avg[i] = (int) (sum / num);

            sum -= nums[i - k];//左区间
            if (i < (length - k - 1))
                sum += nums[i + k + 1];//右区间
        }
        return avg;
    }
}
