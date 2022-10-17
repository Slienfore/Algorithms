package 二分查找;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/6/9 18:59
 */
//2064-分配给商店的最多商品的最小值
public class Demo_2064 {
    public static void main(String[] args) {
/*        int[] quantities = {11, 6};
        int all = 6;*/

        int[] quantities = {15, 10, 10};
        int all = 7;

        System.out.println(minimizedMaximum(all, quantities));
    }

    /**
     * 二分(极大化最小值)<br>
     * 执行用时：23 ms, 在所有 Java 提交中击败了91.57%的用户<br>
     * 内存消耗：50.2 MB, 在所有 Java 提交中击败了66.29%的用户<br>
     * 2022年06月09日  19:31:13
     */
    public static int minimizedMaximum(int all, int[] quantities) {
        int left = 1, right = 1;
        for (int num : quantities)
            right = Math.max(right, num);//一种商品只能分配给一个店铺, 上界就是最大值

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (cal(mid, quantities) > all) //说明过多分配了, 极大化 分配数量
                left = mid + 1;
            else
                right = mid;//恰好分配或者不足分配
        }

        return left;
    }

    private static int cal(int mid, int[] quantities) {
        int sum = 0;//能够分配商品的店铺数量

        for (int num : quantities)
            sum += (num + mid - 1) / mid;//向上取整

        return sum;//分配店铺的数量的越多、能够分配的商铺就会相应减少
    }
}
