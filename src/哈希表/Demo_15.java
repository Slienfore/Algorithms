package 哈希表;

import java.util.*;
//15-三数之和
public class Demo_15 {
    public static void main(String[] args) {
        int[] nums = {0, 2, 2, 3, 0, 1, 2, 3, -1, -4, 2};
        //双指针
        System.out.println(Solution_1(nums));
        //哈希表
        System.out.println(Solution_2(nums));
    }

    //返回的是一个三元组
    public static List<List<Integer>> Solution_1(int[] nums) {
        //创建一个List集合存储结果
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        //对数组进行排序，方便指针移动
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) return result;
            //如果首元素与前面的元素相同，则进行跳转进下一个元素
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            //定义双指针
            int left = i + 1;
            int right = nums.length - 1;
            while (right > left) {
                int sum = nums[i] + nums[left] + nums[right];
                //当它们的和值大于0时，缩小区间
                if (sum > 0) {
                    right--;
                } else if (sum < 0) {//当它们的和值小于0时，则进行增大区间
                    left++;
                } else {
                    //存放进集合中(先将各位值放进一个集合中后再放入List集合中)
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    //对下一轮循环查重
                    while (right > left && nums[right] == nums[right - 1]) right--;
                    while (right > left && nums[left] == nums[left + 1]) left++;
                    //移动指针
                    right--;
                    left++;
                }
            }
        }
        return result;
    }

    //哈希表
    public static List<List<Integer>> Solution_2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) return result;
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            //创建哈希表
            Set set = new HashSet();
            for (int j = i + 1; j < nums.length; j++) {
                //遇到连续相同的，只能存放进取一个数字
                if (j > i + 2 && nums[j] == nums[j - 1] && nums[j - 1] == nums[j - 2]) continue;
                int target = 0 - (nums[i] + nums[j]);
                if (set.contains(target)) {
                    //找到之后直接添加进去
                    result.add(Arrays.asList(nums[i], target, nums[j]));
                    //然后移除
                    set.remove(target);
                } else {//找不到
                    set.add(nums[j]);
                }
            }
        }
        return result;
    }
}
