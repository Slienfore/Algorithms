package 反转;

import java.util.Arrays;

//189-旋转数组
public class Demo_189 {
    public static void main(String[] args) {
        int[] num_1 = {1, 2, 3, 4, 5, 6, 7};
        rotate_1(num_1, 3);
        int[] num_2 = {1, 2, 3, 4, 5, 6, 7};
        rotate_2(num_2, 3);
        int[] num_3 = {1, 2, 3, 4, 5, 6, 7};
        rotate_3(num_3, 3);
    }

    //Demo_1： 超时
    public static void rotate_1(int[] nums, int k) {
        int length = nums.length;
        if (length < 2) {
            System.out.println(Arrays.toString(nums));
            return;
        }
        int temp = nums[0];
        for (int i = 0; i < k; i++) {
            for (int j = 1; j < length; j++) {
                //进行交换
                int origin = nums[j];
                nums[j] = temp;
                temp = origin;
            }
            //一次移动一个，刚开时的位置就是末位的位置
            nums[0] = temp;
        }
        System.out.println(Arrays.toString(nums));
    }

    //Demo_2：取余
    public static void rotate_2(int[] nums, int k) {
        int length = nums.length;
        int[] temp = new int[length];
        for (int i = 0; i < length; i++) {
            //每个元素移动 k 次， 那么就是以 k 为一个周期
            temp[(i + k) % length] = nums[i];
        }
        //将一个数组从指定位置复制到指定的位置
        System.arraycopy(temp, 0, nums, 0, length);
        System.out.println(Arrays.toString(nums));
    }

    //Demo_3：反转
    public static void rotate_3(int[] nums, int k) {
        int length = nums.length;
        //得到 k 的反转分割区间
        k %= length;
        //整串反转
        reverse(nums, 0, length - 1);
        //反转左边
        reverse(nums, 0, k - 1);
        //反转右边
        reverse(nums, k, length - 1);
        System.out.println(Arrays.toString(nums));
    }

    public static void reverse(int[] nums, int start, int end) {
        while (end > start) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
