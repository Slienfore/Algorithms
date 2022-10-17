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
        System.out.println(totalFruit1(fruits1));
        System.out.println(totalFruit1(fruits2));

        System.out.println(totalFruit2(fruits1));
        System.out.println(totalFruit2(fruits2));
    }

    /**
     * 双指针(两层遍历)<br>
     * 执行用时：6 ms, 在所有 Java 提交中击败了85.00%的用户<br>
     * 内存消耗：50.9 MB, 在所有 Java 提交中击败了80.00%的用户<br>
     * 2022年04月02日  22:10:58
     */
    public static int totalFruit1(int[] fruits) {// 水果类型
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

    /**滑动窗口(一次遍历)<br>
     执行用时：5 ms, 在所有 Java 提交中击败了97.47%的用户<br>
     内存消耗：50.6 MB, 在所有 Java 提交中击败了45.09%的用户<br>
     2022年10月17日  17:23:37*/
    public static int totalFruit2(int[] fruits) {// 水果类型
        int res = 0, len = fruits.length;

        // 最多只能够凑成两种类型
        if (len <= 2) return len;

        for (int preVar = fruits[0], newOrin = 0,// 下一个待匹配类型, 下次窗口起始位置
             left = 0, right = 1; right < len; ++right) {

            if (fruits[right] != fruits[right - 1]) {// 说明遇到不同中类型水果

                // 说明不是两种水果中的一种 => 1, 2, 1 (更新起点)
                if (fruits[right] != preVar) left = newOrin;

                // 更新下一个待匹配水果类型(两种中的一种)
                // 这次 fruits[right] 匹配 1, fruits[right - 1] 匹配 2
                // 那么下次 fruits[right] 为 2 的时候, 才能够不需要进行更新窗口
                preVar = fruits[right - 1];

                // 每次进行更新, 因为是不同类型了
                // 如果下次匹配的时候不是这两种类型的一种, 那么序列中不能夹杂其它类型
                newOrin = right;
            }

            res = Math.max(res, right - left + 1);
        }

        return res;
    }

}
