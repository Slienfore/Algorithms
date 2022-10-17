package 前缀和与差分;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/8 19:43
 */
//303-区域搜索，数组不可变
public class Demo_303 {
    int[] sum;

    /**
    执行用时：11 ms, 在所有 Java 提交中击败了23.31%的用户<br>
    内存消耗：44.5 MB, 在所有 Java 提交中击败了5.17%的用户
    */
    public static void main(String[] args) {
        int[] nums = {-2, 0, 3, -5, 2, -1};
        Demo_303 ob = new Demo_303(nums);
        System.out.println(ob.sumRange(0, 5));
    }

    public Demo_303(int[] nums) {
        int length = nums.length;
        this.sum = new int[length + 1];

        for (int i = 1; i <= length; i++)//预处理前缀和(保留第一位)
            sum[i] = sum[i - 1] + nums[i - 1];

    }

    public int sumRange(int left, int right) {
        return sum[right + 1] - sum[left];//下标是从 0 开始的，所以需要向上提
    }
}
