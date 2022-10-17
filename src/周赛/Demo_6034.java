package 周赛;

import java.util.Arrays;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/4/2 22:56
 */
//6034-数组的三角和
public class Demo_6034 {
    public static void main(String[] args) {
//        int[] nums = {1, 2, 3, 4, 5};
        int[] nums = {5};
        System.out.println(triangularSum(nums));
    }

    public static int triangularSum(int[] nums) {
        int layer = nums.length;

        for (int curLayer = 0; curLayer < layer - 1; ++curLayer)  //三角形一共有多少层
            for (int cur = 0; cur < layer - curLayer - 1; ++cur) //每一层衰减一个数
                nums[cur] = (nums[cur] + nums[cur + 1]) % 10;

        return nums[0];

    }
}
