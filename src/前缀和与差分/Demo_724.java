package 前缀和与差分;

import java.util.Arrays;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/8 20:19
 */
//724-寻找数组的中心下标
public class Demo_724 {
    public static void main(String[] args) {
        int[] nums = {1, 7, 3, 6, 5, 6};
//        int[] nums = {1, 2, 3};
//        int[] nums = {2, 1, -1};
        System.out.println(pivotIndex_1(nums));
        System.out.println(pivotIndex_2(nums));
    }


    /**数学<br>
    执行用时：3 ms, 在所有 Java 提交中击败了34.07%的用户<br>
    内存消耗：41.6 MB, 在所有 Java 提交中击败了43.38%的用户
    */
    public static int pivotIndex_2(int[] nums) {

        int sum = Arrays.stream(nums).sum();

        //preSum = sum - nums[i] - preSum -->2 * preSum + nums[i] == sum

        int preSum = 0, length = nums.length;
        for (int i = 0; i < length; i++) {
            if (preSum == (sum - nums[i]) - preSum)//前半段之和等于后半段之和
                return i;

            preSum += nums[i];
        }

        return -1;
    }


    public static int pivotIndex_1(int[] nums) {
        int length = nums.length;
        int[] sum = new int[length + 1];
        for (int i = 0; i < length; i++)
            sum[i + 1] = sum[i] + nums[i];


        //preSum[i] == sum - preSum[i] - nums[i]
        int max = sum[length];
        for (int i = 1; i <= length; i++)
            if (max - sum[i] == sum[i - 1])//前面的和等于后面的和，找一个分割点
                return i - 1;


        return -1;
    }
}
