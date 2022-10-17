package 动态规划;

import java.util.Arrays;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/8 19:18
 */
public class 前缀和 {
    public static void main(String[] args) {
        int[][] query =
                {
                        {1, 2},
                        {3, 8},
                        {1, 7},
                        {3, 6}
                };

        int[] nums = {1, 2, 3, 4, 5, 6, 7, 9, 10};

        int[] sum = new int[nums.length];
        for (int i = 1; i < nums.length; i++)//前缀和初始化
            sum[i] = sum[i - 1] + nums[i - 1];
        System.out.println(Arrays.toString(sum));

        int length = query.length - 1;
        for (int[] ques : query) {
            int left = ques[0], right = ques[1];
            System.out.println("区间 [ " + left + " ~ " + right + " ] 的总和是:  " + (sum[right] - sum[left - 1]));
        }
    }
}
