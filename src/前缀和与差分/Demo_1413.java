package 前缀和与差分;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/8 21:02
 */
//1413-逐步求和得到正数的最小值
public class Demo_1413 {
    public static void main(String[] args) {
//        int[] nums = {-3, 2, -3, 4, 2};
//        int[] nums = {1, 2};
        int[] nums = {1, -2, -3};
        System.out.println(minStartValue(nums));
    }

    /**
     * 前缀和<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了36.43%的用户
     */
    public static int minStartValue(int[] nums) {
        int min = Integer.MAX_VALUE, sum = 0;

        //找出前缀和最小
        for (int num : nums) {
            sum += num;
            min = Math.min(sum, min);
        }

        return min >= 0 ? 1 : Math.abs(min) + 1;
    }
}
