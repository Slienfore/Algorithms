package 指针;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//18-四数之和
public class Demo_18 {
    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        int target = -1;
        System.out.println(fourSum_1(nums, target));
        System.out.println(fourSum_2(nums, target));
    }

    public static List<List<Integer>> fourSum_1(int[] nums, int target) {
        //创建一个List集合存储结果
        List<List<Integer>> result = new ArrayList<>();
        //将数组元素进行排序
        Arrays.sort(nums);
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            if (nums[i] > target) return result;
            //进行查重
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            //四数之和不能包含自己本身
            for (int j = i + 1; j < length; j++) {
                //进行查重
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                int left = j + 1;
                int right = length - 1;
                while (right > left) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum > target) {
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        //查找到了后
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        //进行指针移动
                        while (right > left && nums[right] == nums[right - 1]) right--;
                        while (right > left && nums[left] == nums[left + 1]) left++;
                        //进行指针移动
                        right--;
                        left++;
                    }
                }
            }
        }
        return result;
    }

    //Demo_2-剪枝
    public static List<List<Integer>> fourSum_2(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        //当其为空或者小于4个时，直接返回
        if (nums == null || nums.length < 4) {
            return result;
        }
        //进行排序
        Arrays.sort(nums);
        int length = nums.length;
        //只要比较倒数第四个就行
        for (int i = 0; i < length - 3; i++) {
            //查重
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            //获得当前最小指
            int min_1 = nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3];
            //如果前三个的值的和比target大的话直接退出
            if (min_1 > target) break;
            //获得最大值
            int max_1 = nums[i] + nums[length - 1] + nums[length - 2] + nums[length - 3];
            //如果最大值比target小的话，直接换成下一位
            if (max_1 < target) continue;
            //只需要比较倒数三个就行
            for (int j = i + 1; j < length - 2; j++) {
                //排重
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                //定义指针
                int left = j + 1;
                int right = length - 1;
                //获得最小值
                int min_2 = nums[i] + nums[j] + nums[left] + nums[left + 1];
                if (min_2 > target) break;
                //获得最大值
                int max_2 = nums[i] + nums[j] + nums[right] + nums[right - 1];
                //连最大值都比target小，直接退出来
                if (max_2 < target) continue;
                //指针开始比较
                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        //查重
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;
                        left++;
                        right--;
                    } else if (sum > target) {
                        right--;
                    } else {
                        left++;
                    }
                }
            }
        }
        return result;
    }
}
