package 周赛;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/11/28 10:32
 */
//5938-
public class Demo_5938 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 5, 2, 3};
        int target = 5;
        System.out.println(targetIndices(nums, target));
    }

    public static List<Integer> targetIndices(int[] nums, int target) {
        Arrays.sort(nums);
        List<Integer> res = new ArrayList<>();
        if (Arrays.binarySearch(nums, target) < 0) return res;//找不到

        int length = nums.length;
        for (int i = 0; i < length; i++)
            if (nums[i] == target) res.add(i);

        return res;
    }
}
