package 哈希表;

import java.util.HashMap;
import java.util.Map;

//454-四数之和
public class Demo_454 {
    public static void main(String[] args) {
        int[] nums1 = {-1, -1};
        int[] nums2 = {-1, 1};
        int[] nums3 = {-1, 1};
        int[] nums4 = {1, -1};
        System.out.println(fourSumCount(nums1, nums2, nums3, nums4));
    }

    //Demo_1：哈希表
    public static int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        //哈希表法
        Map<Integer, Integer> map = new HashMap<>();
        int sum;
        int conunt = 0;
        //先统计两个数组之和
        for (int i : nums1)
            for (int j : nums2) {
                sum = i + j;
                if (map.containsKey(sum)) {
                    //统计两个数组中出现的各个和出现的次数
                    map.put(sum, map.get(sum) + 1);
                } else {
                    //若还没有出现
                    map.put(sum, 1);
                }
            }
        //统计另外两个数组在哈希表中的取值
        for (int i : nums3)
            for (int j : nums4) {
                sum = i + j;
                if (map.containsKey(0 - sum)) {
                    //若存在四个数组相加等于 0 的数组则进行递增
                    conunt += map.get(0 - sum);
                }
            }
        return conunt;
    }
}
