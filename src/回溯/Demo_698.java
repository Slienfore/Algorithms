package 回溯;

import utils.uu;

import java.util.Arrays;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/11 17:07
 */
//698-划分为 K 个相等的子集
public class Demo_698 {
    public static void main(String[] args) {
//        int[] nums = {4, 3, 2, 3, 5, 2, 1};
//        int[] nums = {1, 2, 3, 4};
//        int[] nums = {3, 3, 10, 2, 6, 5, 10, 6, 8, 3, 2, 1, 6, 10, 7, 2};
//        int[] nums = {3, 3, 3, 3, 4};
        int[] nums = {5, 5, 5, 5, 4, 4, 4, 4, 3, 3, 3, 3};
        int num = 4;

        System.out.println(canPartitionKSubsets_1(nums, num));

    }

    public static boolean canPartitionKSubsets_1(int[] nums, int k) {
        int sum = Arrays.stream(nums).sum();

        if (sum % k != 0)//若不能整除说明不能够进行划分
            return false;

        Arrays.sort(nums);
        boolean[] branch = new boolean[nums.length];

        int tar = sum / k;//分成 k 部分的值

        if (nums[nums.length - 1] > tar)//说明这个数绝对大于
            return false;

        return backTrack(0, tar, k, branch, nums);
    }

    /**
     * 回溯<br>
     * 执行用时：430 ms, 在所有 Java 提交中击败了34.16%的用户<br>
     * 内存消耗：39.1 MB, 在所有 Java 提交中击败了28.60%的用户
     */
    private static boolean backTrack(int curSum, int tar, int arrNum, boolean[] branch, int[] nums) {
        if (arrNum == 1)//还剩下的元素必定能够组成排列
            return true;

        if (curSum == tar)//找完一个再找下一个，进行重置
            return backTrack(0, tar, arrNum - 1, branch, nums);//说明已经找到了该元素了

        for (int val = nums.length - 1; val >= 0; --val) {//从后往前找，优先选择最大元素
            if (branch[val] || curSum + nums[val] > tar)//树枝去重、防止值过大
                continue;

            branch[val] = true;//使用该值

            if (backTrack(curSum + nums[val], tar, arrNum, branch, nums))
                return true;

            branch[val] = false;//说明该值不能用了，再次重复使用该值无意义
            while (val > 0 && nums[val] == nums[val - 1])
                val--;
        }

        return false;
    }
}
