package 滑动窗口;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/10/17 10:11
 */
// 904-水果成蓝
public class Demo_904 {
    public static void main(String[] args) {
        int[] fruits1 = {0, 1, 2, 2};
        int[] fruits2 = {3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4};
        System.out.println(totalFruit(fruits1));
        System.out.println(totalFruit(fruits2));
    }

    // 滑动窗口
    public static int totalFruit(int[] fruits) {// 水果类型
        int res = 0, len = fruits.length;

        // 0 <= fruits[i] < fruits.length => 使用数字哈希统计窗口中果树数量
        int[] hash = new int[len];

        for (int left = 0, right = 0, cnt = 0; right < len; ++right) {

            ++hash[fruits[right]];// 统计窗口果树类型数量

            // 出现新种类
            if (hash[fruits[right]] == 1) ++cnt;

            // 新种类数量超过两种 => 收缩窗口
            while (cnt > 2) {
                // 水果种类减少
                if (--hash[fruits[left]] == 0) --cnt;

                ++left;// 收缩窗口
            }

            // 统计窗口果树数量
            res = Math.max(res, (right - left) + 1);
        }

        return res;
    }
}
