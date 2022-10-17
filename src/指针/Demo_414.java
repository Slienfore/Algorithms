package 指针;

import java.util.Arrays;
import java.util.TreeSet;

//414-第三大的数
public class Demo_414 {
    public static void main(String[] args) {
        int[] num = {1, 1, 1, 1, 2};
        System.out.println(thirdMax_1(num));
        System.out.println(thirdMax_2(num));
        System.out.println(thirdMax_3(num));
    }

    //Demo_1: 先排序然后再进行寻找
    public static int thirdMax_1(int[] nums) {
        int length = nums.length;
        int slow = length - 1;
        int fast = slow - 1;
        int index = 1;
        Arrays.sort(nums);
        //当第一和第二个数相等时,或者第二和第三个数相等时直接返回最大值
        if (length == 3 && (nums[0] == nums[1] || nums[1] == nums[slow])) return nums[slow];
        //当只有两个数的时候则返回最大值
        if (length < 3) return nums[slow];
        while (fast >= 0) {
            //当给定的数小于最大的数时,满指针移动到快指针的位置
            if (nums[fast] < nums[slow]) {
                slow = fast;
                if (++index >= 3) return nums[slow];
            }
            fast--;
        }
        //当只找到了两个的时候，直接返回最大值
        if (index == 2) return nums[length - 1];
        return nums[slow];
    }

    //Demo_2: TreeSet
    public static int thirdMax_2(int[] nums) {
        //用来维护一个有序集合，且不重复
        TreeSet<Integer> set = new TreeSet<>();
        for (int value : nums) {
            set.add(value);
            if (set.size() > 3) {
                //当TreeSet中的元素大于3的时候，则去除最小的元素
                set.remove(set.first());
            }
        }
        //只有当TreeSet中的元素个数达到三的时候才会返回最小值
        if (set.size() == 3) return set.first();
        return set.last();
    }

    //Demo_3
    public static int thirdMax_3(int[] nums) {
        long MIN = -Long.MAX_VALUE;
        long max = MIN, medium = MIN, min = MIN;
        for (int value : nums) {
            if (value > max) {//当该值大于最大的值时，则后面两个跟进
                min = medium;
                medium = max;
                max = value;
            } else if (max > value && value > medium) {
                min = medium;
                medium = value;
            } else if (medium > value && value > min) {
                min = value;
            }
        }
        return min == MIN ? (int) max : (int) min;
    }
}
