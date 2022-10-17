package 指针;

import java.util.Arrays;

//27-移除元素
public class Demo_27 {
    public static void main(String[] args) {
        int[] nums = {0, 1, 2, 2, 3, 0, 4, 2};
        int[] nums_1 = {0, 1, 2, 2, 3, 0, 4, 2};
        int[] nums_2 = {0, 1, 2, 2, 3, 0, 4, 2};
//        int[] nums = {3, 2, 2, 3};
//        int[] nums = {0, 0, 0};
//        int[] nums = {0, 0, 0, 0, 0, 0, 0, 0};
//        int[] nums = {0, 0, 0, 2, 2, 2, 2, 2};
        int target = 2;
        System.out.println(removeElement_1(nums, target));
        System.out.println(removeElement_2(nums_1, target));
        System.out.println(removeElement_3(nums_2, target));
    }

    //Demo_2-快慢指针
    public static int removeElement_2(int[] nums, int val) {
        //定义快慢指针
        int quick = 0, slow = -1;
        while (quick < nums.length) {
            if (nums[quick] != val) nums[++slow] = nums[quick];
            quick++;
        }
        return slow + 1;
    }

    //Demo_1- 双指针
    public static int removeElement_1(int[] nums, int val) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            if (nums[left] != val) {
                if (nums[right] == val) {
                    right--;
                }
                left++;
            } else {
                if (nums[right] != val) {
                    nums[left] = nums[right];
                    left++;
                }
                right--;
            }
        }
        return left;
    }

    /*暴力破解
    执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：37.2 MB, 在所有 Java 提交中击败了11.07%的用户
     */
    public static int removeElement_3(int[] nums, int val) {
        int length = nums.length;
        for (int i = 0; i < length; i++) {

            if (nums[i] == val) {
                for (int j = i + 1; j < length; j++) {//将后面的元素往前面移动
                    nums[j - 1] = nums[j];
                }
                i--;//移除后需要将数组动态变化
                length--;
            }
        }
        return length;
    }
}
