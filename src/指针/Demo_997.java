package 指针;

import java.util.Arrays;


//997-有序数组的平方
public class Demo_997 {
    public static void main(String[] args) {
        int[] num_1 = {-4, -1, 0, 3, 10};
        int[] num_2 = {-3, -3, -2, 1};
        System.out.println(Arrays.toString(sortedSquares_1(num_1)));
        System.out.println(Arrays.toString(sortedSquares_1(num_2)));
        int[] num_3 = {-3, -3, -2, 1};
        int[] num_4 = {-7, -3, 2, 3, 11};
        System.out.println(Arrays.toString(sortedSquares_2(num_3)));
        System.out.println(Arrays.toString(sortedSquares_2(num_4)));
    }

    //Demo_1
    public static int[] sortedSquares_1(int[] nums) {
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            nums[i] = (int) Math.pow(nums[i], 2);
        }
        Arrays.sort(nums);
        return nums;
    }

    //Demo_2
    private static int[] sortedSquares_2(int[] nums) {
        int length = nums.length;
        int left = 0;
        int right = length - 1;
        int[] result = new int[length];
        //写入数组的指针
        int index = length - 1;
        while (right >= left) {
            int leftValue = (int) Math.pow(nums[left], 2);
            int rightValue = (int) Math.pow(nums[right], 2);
            if (leftValue > rightValue) {
                result[index] = leftValue;
                left++;
                index--;
            } else {
                result[index] = rightValue;
                right--;
                index--;
            }
        }
        return result;
    }

}
