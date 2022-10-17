package 二分查找;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/27 9:06
 */
//367-有效的完全平方数
public class Demo_367 {
    public static void main(String[] args) {
        int num = 16;
//        int num = 5;
//        int num = 1;
        System.out.println(isPerfectSquare(num));
    }


    /**
     * 二分<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：38.1 MB, 在所有 Java 提交中击败了37.59%的用户
     */
    public static boolean isPerfectSquare(int num) {

        int left = 1, right = (num + 1) / 2;//最大值不会超过其一半

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (mid < num / mid)//说明小了
                left = mid + 1;
            else
                right = mid;
        }

        return left * right == num;
    }

}
