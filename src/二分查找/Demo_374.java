package 二分查找;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/26 21:14
 */
//374-猜数字大小
public class Demo_374 {
    public static void main(String[] args) {


    }

    /**
     * 二分<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：42.3 MB, 在所有 Java 提交中击败了5.35%的用户
     */
    public static int guessNumber(int n) {
        int left = 1;
        for (int right = n; left < right; ) {
            int mid = left + (right - left) / 2;

            if (guess(mid) == 1)//当前值小了
                left = mid + 1;
            else
                right = mid;
        }

        return left;
    }

    private static int guess(int num) {
        int target = 9;
        if (num == target) return 0;
        return num > target ? 1 : -1;
    }
}
