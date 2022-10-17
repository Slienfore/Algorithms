package 指针;

import utils.uu;

import java.util.*;

//15-三数之和
public class Demo_15 {
    public static void main(String[] args) {
        int[] nums = {0, 2, 2, 3, 0, 1, 2, 3, -1, -4, 2};
//        int[] nums = {0, 0, 0};
//        int[] nums = {-1, 0, 1, 2, -1, -1};
//        int[] nums = {-1, 0, 1, 2, -1, -4};
//        int[] nums = {-2, 0, 0, 2, 2};
        //双指针
        System.out.println(Solution_1(nums));
        //哈希表
        System.out.println(Solution_2(nums));

        System.out.println(threeSum_4(nums));

    }

    /**
     * 双指针<br>
     * 执行用时：38 ms, 在所有 Java 提交中击败了14.23%的用户<br>
     * 内存消耗：45.7 MB, 在所有 Java 提交中击败了14.03%的用户
     */
    public static List<List<Integer>> threeSum_4(int[] nums) {
        int length = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (length < 3) return res;

        Arrays.sort(nums);//将重复数字排到一起
        if (nums[length - 1] < 0 || nums[0] > 0)//最后一位 小于 0、或者第一位 大于 0
            return res;

        for (int orin = 0; orin < length - 2 && nums[orin] <= 0; ++orin) {
            int one = nums[orin];
            if (orin > 0 && one == nums[orin - 1])//去重
                continue;

            int left = orin + 1, right = length - 1;//左指针记录适合的最小值、右指针记录适合的最大值
            while (left < right) {
                int sum = nums[left] + nums[right] + one;//三数之和

                if (sum == 0) {
                    res.add(Arrays.asList(one, nums[left], nums[right]));
                    ++left;//更新状态
                    --right;
                } else if (sum < 0) {//说明取值过小
                    ++left;
                } else if (sum > 0) {//说明取值过大
                    --right;
                }

                while (right < (length - 1) && (right > left) && nums[right] == nums[right + 1]) //向左移动<去右边的重>
                    --right;

                while (left > (orin + 1) && (left < right) && nums[left] == nums[left - 1])//向右移动<去左边的重>
                    ++left;

            }
        }

        return res;
    }

    //返回的是一个三元组
    public static List<List<Integer>> Solution_1(int[] nums) {
        //创建一个List集合存储结果
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        //对数组进行排序，方便指针移动
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) return result;//第一大的数大于 0 说明不能选了

            //如果首元素与前面的元素相同，则进行跳转进下一个元素
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            //定义双指针
            int left = i + 1, right = nums.length - 1;

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
