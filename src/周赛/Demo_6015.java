package 周赛;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/2/20 11:03
 */
public class Demo_6015 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        int k = 2;
        System.out.println(countPairs(nums, k));
    }

    public static long countPairs(int[] nums, int k) {
        int length = nums.length;

        long count = 0;
        for (int val : nums) {
            if (val % k == 0) count++;
        }

        return count * length;

    }
}
