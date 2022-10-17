package 周赛;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/6 11:12
 */
//6017-
public class Demo_6017 {
    public static void main(String[] args) {
        int[] nums = {1, 4, 25, 10, 25};
//        int[] nums = {5, 6};
        int k = 2;
        System.out.println(minimalKSum(nums, k));
    }

    /**排序 + 移除元素<br>
    执行用时：17 ms, 在所有 Java 提交中击败了100.00%的用户<br>
    内存消耗：51.9 MB, 在所有 Java 提交中击败了100.00%的用户
    */
    public static long minimalKSum(int[] nums, int k) {
        Arrays.sort(nums);//去重
        int length = nums.length;

        long res = k * (1L + k) / 2;//先将 k 之前的数进行累加，若在 k 的范围内进行提出

        for (int i = 0; i < length; i++)
            if (k >= nums[i] && (i == 0 || (nums[i - 1] != nums[i])))//如果 k 包含当前数,需从总和中剔除
                res += ++k - nums[i];//那么 k 需要增加一位然后将其从剔除

        return res;

    }
}