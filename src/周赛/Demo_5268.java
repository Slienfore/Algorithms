package 周赛;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/27 10:40
 */
//5268-找出两数组的不同
public class Demo_5268 {
    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3}, nums2 = {2, 4, 6};

//        int[] nums1 = {-80, -15, -81, -28, -61, 63, 14, -45, -35, -10}, nums2 = {-1, -40, -44, 41, 10, -43, 69, 10, 2};

        System.out.println(findDifference(nums1, nums2));
    }

    public static List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<>();
        for (int val : nums1)
            set1.add(val);

        HashSet<Integer> set2 = new HashSet<>();
        for (int val : nums2)
            set2.add(val);

        List<Integer> temp1 = new ArrayList<>();
        for (int val : nums1)
            if (!set2.contains(val) && !temp1.contains(val))
                temp1.add(val);

        List<Integer> temp2 = new ArrayList<>();
        for (int val : nums2)
            if (!set1.contains(val) && !temp2.contains(val))
                temp2.add(val);

        return new ArrayList<>() {{
            add(temp1);
            add(temp2);
        }};
    }
}
