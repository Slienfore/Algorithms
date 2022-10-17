package 滑动窗口;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/4/1 12:09
 */
//11-盛水最多的容器
public class Demo_11 {
    public static void main(String[] args) {
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(maxArea(height));
    }

    /**
     * 双指针<br>
     * 执行用时：3 ms, 在所有 Java 提交中击败了93.12%的用户<br>
     * 内存消耗：50.7 MB, 在所有 Java 提交中击败了95.85%的用户<br>
     * 2022年04月01日  12:17:17
     */
    public static int maxArea(int[] height) {
        int max = 0;

        for (int left = 0, right = height.length - 1; left < right; ) {
            if (height[left] == height[right])//左、右高度相等(随意移动一个)
                max = Math.max(max, (right - left) * height[left++]);

            else if (height[left] > height[right])//右边短(移动右边)
                max = Math.max(max, (right - left) * height[right--]);

            else if (height[left] < height[right])//左边短(移动左边)
                max = Math.max(max, (right - left) * height[left++]);
        }

        return max;
    }
}
