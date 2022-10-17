package 周赛;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/20 10:42
 */
//6027-
public class Demo_6027 {
    public static void main(String[] args) {
//        int[] nums = {2, 4, 1, 1, 6, 5};
        int[] nums = {6, 6, 5, 5, 4, 1};
        System.out.println(countHillValley(nums));
    }

    public static int countHillValley(int[] nums) {
        int length = nums.length, res = 0;

        for (int cur = 1; cur < length - 1; ++cur) {
            int val = nums[cur];

            if (val == nums[cur - 1])
                continue;

            int left = cur - 1, right = cur + 1;

            while (left >= 0 && nums[left] == val)//搜索左边不相等邻居
                --left;

            while (right < length - 1 && nums[right] == val)//搜索右边不相等邻居
                ++right;

            if (Math.min(nums[left], nums[right]) > val || Math.max(nums[left], nums[right]) < val)
                ++res;
        }

        return res;
    }

}
