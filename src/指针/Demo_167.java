package 指针;

import java.util.Arrays;
import java.util.HashMap;

//167-两数之和II - 有序数组
public class Demo_167 {
    public static void main(String[] args) {
        int[] num = {0, 0, 0, 0};
        int target = 9;
        System.out.println(Arrays.toString(twoSum_1(num, target)));
        System.out.println(Arrays.toString(twoSum_2(num, target)));
    }

    //Demo_1:
    public static int[] twoSum_1(int[] numbers, int target) {
        var map = new HashMap<Integer, Integer>();
        int length = numbers.length;
        for (int i = 0; i < length; i++) {
            if (map.containsKey(numbers[i]) && (map.get(numbers[i]) != i)) {
                return new int[]{map.get(numbers[i]) + 1, i + 1};
            } else {
                map.put(target - numbers[i], i);
            }
        }
        return null;
    }

    /*双指针
    执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：38.6 MB, 在所有 Java 提交中击败了63.68%的用户
    */
    public static int[] twoSum_2(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        while (left <= right) {
            if (numbers[left] + numbers[right] > target) right--;
            else if (numbers[left] + numbers[right] < target) left++;
            else if (numbers[left] + numbers[right] == target) return new int[]{++left, ++right};
        }
        return null;
    }
}
