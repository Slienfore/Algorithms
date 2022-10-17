package 二分查找;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/31 14:17
 */
public class Demo_167 {
    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int tar = 9;

/*        int[] nums = {2, 3, 4};
        int tar = 6;*/
        uu.print(twoSum(nums, tar));
    }

    /**
     * 二分<br>
     * 执行用时：4 ms, 在所有 Java 提交中击败了21.19%的用户<br>
     * 内存消耗：44.4 MB, 在所有 Java 提交中击败了5.01%的用户<br>
     * 2022年03月31日  14:40:55
     */
    public static int[] twoSum(int[] numbers, int target) {
        int length = numbers.length;

        for (int cur = 0; cur < length - 1; ++cur) {
            int curVal = numbers[cur];

            for (int left = cur + 1, right = length - 1; left <= right; ) {
                int mid = left + (right - left) / 2;

                if (numbers[mid] + curVal == target)
                    return new int[]{++cur, ++mid};
                else if (numbers[mid] + curVal > target)//大了
                    right = mid - 1;
                else if (numbers[mid] + curVal < target)//小了
                    left = mid + 1;

            }
        }

        return new int[]{};
    }
}
