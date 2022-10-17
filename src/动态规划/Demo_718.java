package 动态规划;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/27 21:13
 */
//718-最长重复子数组
public class Demo_718 {
    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 2, 1}, nums2 = {3, 2, 1, 4, 7};
        System.out.println(findLength_1(nums1, nums2));
        System.out.println(findLength_2(nums1, nums2));
    }

    /**动规(滚动数组)<br>
     执行用时：25 ms, 在所有 Java 提交中击败了97.95%的用户<br>
     内存消耗：40.7 MB, 在所有 Java 提交中击败了89.81%的用户*/
    public static int findLength_2(int[] nums1, int[] nums2) {
        int len1 = nums1.length, len2 = nums2.length;

        //表示以下标 (one - 1) 结尾的序列 A， 和 以下标 (two - 1)结尾的 B 形成的最长重复子数组
        int[] dp = new int[len2 + 1];
        //连续则说明只有相等才会连续
        int maxLen = 0;
        for (int one = 1; one <= len1; ++one)
            for (int two = len2; two > 0; --two)//从后向前遍历防止覆盖上层左上角数组[one - 1, two - 1]
                if (nums1[one - 1] == nums2[two - 1]) {
                    dp[two] = dp[two - 1] + 1;//两个数组前一个位置组成的最长连续子数组
                    maxLen = Math.max(maxLen, dp[two]);
                } else
                    dp[two] = 0;//不相等则重置为 0

        return maxLen;
    }


    /**
     * 动规<br>
     * 执行用时：27 ms, 在所有 Java 提交中击败了89.82%的用户<br>
     * 内存消耗：50.5 MB, 在所有 Java 提交中击败了5.09%的用户
     */
    public static int findLength_1(int[] nums1, int[] nums2) {
        int len1 = nums1.length, len2 = nums2.length;

        //表示以下标 (one - 1) 结尾的序列 A， 和 以下标 (two - 1)结尾的 B 形成的最长重复子数组
        int[][] dp = new int[len1 + 1][len2 + 1];
        //连续则说明只有相等才会连续
        int maxLen = 0;
        for (int one = 1; one <= len1; ++one)
            for (int two = 1; two <= len2; ++two)
                if (nums1[one - 1] == nums2[two - 1]) {//重复才会连续
                    dp[one][two] = dp[one - 1][two - 1] + 1;//两个数组前一个位置组成的最长连续子数组
                    maxLen = Math.max(maxLen, dp[one][two]);
                }

        return maxLen;
    }
}
