package 数组;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//1-两数之和
public class Demo_1 {
    public static void main(String[] args) {
        int[] num = {2, 7, 11, 15};
        int target = 9;
        System.out.println(Arrays.toString(twoSum_2(num, target)));
        System.out.println(Arrays.toString(twoSum_3(num, target)));
        System.out.println(Arrays.toString(twoSum_1(num, target)));

    }

    //Demo_2：哈希表(键找值)
    public static int[] twoSum_2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            //目标值减去被减数后存放进去
            int targetNum = target - nums[i];
            //搜寻目标值
            if (map.get(targetNum) == null) {
                //存放数组下标
                map.put(nums[i], i);
            } else {
                return new int[]{map.get(targetNum), i};
            }
        }
        return null;
    }

    //Demo_3：哈希表(移除差值优化空间)
    public static int[] twoSum_3(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            if (map.containsKey(nums[i])) {
                //存在的话直接返回下标
                return new int[]{i, map.get(nums[i])};
            }
            //找不到的话直接放进哈希表中等待查询
            map.put(target - nums[i], i);
        }
        return null;
    }

    //Demo_1：暴力破解
    public static int[] twoSum_1(int[] nums, int target) {
        int length = nums.length;
        for (int i = 0; i < length; i++)
            for (int j = 1; j < length; j++) {
                if (nums[i] + nums[j] == target && (i != j)) {//不能重复出现
                    return new int[]{i, j};
                }
            }
        return null;
    }
}
