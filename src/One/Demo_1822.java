package One;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/10/27 15:04
 */
// 1822-数组元素积的符号
public class Demo_1822 {

    public static void main(String[] args) {
        int[] nums = {-1, -2, -3, -4, 3, 2, 1};
        System.out.println(arraySign(nums));
    }

    public static int arraySign(int[] nums) {
        int res = 1;

        for (int val : nums) {
            if (val == 0)// 所有项相乘都为 0
                return 0;
            else if (val < 0)// 负数时候 改变符号
                res *= -1;
        }

        return res;
    }

}
