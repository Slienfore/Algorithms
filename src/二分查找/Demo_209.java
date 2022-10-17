package 二分查找;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/6/8 10:14
 */
//209-长度最小的子数组
public class Demo_209 {
    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 2, 4, 3};
        int target = 7;

/*        int[] nums =  {1,1,1,1,1,1,1,1};
        int target = 11;*/

        System.out.println(minSubArrayLen(target, nums));
    }

    /**
     * 二分+前缀和<br>
     * 执行用时：4 ms, 在所有 Java 提交中击败了20.44%的用户<br>
     * 内存消耗：41.4 MB, 在所有 Java 提交中击败了35.43%的用户<br>
     * 2022年06月08日  10:32:24
     */
    public static int minSubArrayLen(int target, int[] nums) {
        int length = nums.length;
        int[] preSum = new int[length + 1];//记录区间和

        for (int cur = 1; cur <= length; ++cur)
            preSum[cur] = preSum[cur - 1] + nums[cur - 1];

        int res = Integer.MAX_VALUE;
        for (int cur = 1; cur <= length; ++cur) {

            int sum = preSum[cur], left = 0, right = cur;

            while (left < right) {//搜索以当前下标结尾的满足条件的最小距离
                int mid = left + (right - left + 1) / 2;

                if (sum - preSum[mid] >= target)//逐渐缩小距离
                    left = mid;
                else
                    right = mid - 1;
            }

            if (sum - preSum[left] >= target)
                res = Math.min(res, cur - left);//前缀和数组下标从 1 开始，所以不需加 1
        }

        return res == Integer.MAX_VALUE ? 0 : res;
    }
}
