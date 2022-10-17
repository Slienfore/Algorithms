package 指针;

import java.util.Arrays;
import java.util.Comparator;

//283-移动零
public class Demo_283 {
    public static void main(String[] args) {
        int[] num_1 = {0, 1, 0, 1, 0, 1, 0, 1, 1, 0};
        moveZeroes_1(num_1);
        int[] num_2 = {0, 1, 0, 1, 0, 1, 0, 1, 1, 0};
        moveZeroes_2(num_2);
    }

    //Demo_1:快慢指针
    public static void moveZeroes_1(int[] nums) {
        int length = nums.length;
        int slow = -1;
        for (int fast = 0; fast < length; fast++) {
            if (nums[fast] != 0) {
                //存储fast当前的值
                int temp = nums[fast];
                //将元素移动过去时需要抹去当前数据
                nums[fast] = 0;
                //当快指针不为零的时候，慢指针和快指针一起移动获得快指针的值
                nums[++slow] = temp;
            }
        }
        System.out.println(Arrays.toString(nums));
    }

    /*双指针：
    执行用时：1 ms, 在所有 Java 提交中击败了39.75%的用户
    内存消耗：39.8 MB, 在所有 Java 提交中击败了5.17%的用户
    */
    public static void moveZeroes_2(int[] nums) {
        if (nums == null || nums.length == 1) return;

        int length = nums.length;
        int slow = 0;

        for (int fast = 0; fast < length; fast++) {
            if (nums[fast] != 0) {
                nums[slow] = nums[fast];
                slow++;
            }
        }

        while (slow < length) {//将 index 以后的元素都置为 0
            nums[slow] = 0;
            slow++;
        }

        System.out.println(Arrays.toString(nums));
    }
}
