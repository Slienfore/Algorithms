package 哈希表;

import utils.uu;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/14 8:01
 */
//599-两个列表的最小索引和
public class Demo_599 {
    public static void main(String[] args) {
        String[] list_1 = {"Shogun", "Hungry Hunter Steakhouse", "Tapioca Express", "Burger King", "KFC"},
                list_2 = {"Piatti", "Shogun"};
        System.out.println(Arrays.toString(findRestaurant_1(list_1, list_2)));
        System.out.println(Arrays.toString(findRestaurant_2(list_1, list_2)));

    }

    /**
     * 模拟<br>
     * 执行用时：36 ms, 在所有 Java 提交中击败了36.06%的用户<br>
     * 内存消耗：42 MB, 在所有 Java 提交中击败了21.84%的用户
     */
    public static String[] findRestaurant_2(String[] list1, String[] list2) {
        int length_1 = list1.length, length_2 = list2.length;

        int min = Integer.MAX_VALUE;


        List<String> res = new ArrayList<>();

        for (int first = 0; first < length_1; ++first) {
            String val = list1[first];

            for (int second = 0; second < length_2; ++second) {

                if (first + second > min)//距离太大了
                    break;

                if (!list2[second].equals(val))//不匹配则放弃
                    continue;

                if (first + second < min) {//距离较小,添加
                    res.clear();
                    min = first + second;
                }

                res.add(val);

            }
        }

        return res.toArray(new String[0]);
    }


    /**
     * 哈希表+剪枝<br>
     * 执行用时：5 ms, 在所有 Java 提交中击败了99.63%的用户<br>
     * 内存消耗：41.8 MB, 在所有 Java 提交中击败了33.77%的用户
     */
    public static String[] findRestaurant_1(String[] list1, String[] list2) {
        int length_1 = list1.length, length_2 = list2.length;
        if (length_1 > length_2)//使用长度较小节约空间
            return findRestaurant_1(list2, list1);

        List<String> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();

        for (int index = 0; index < length_1; ++index)//映射每个字符串的值
            map.put(list1[index], index);

        int min = Integer.MAX_VALUE;
        for (int second = 0; second < length_2; ++second) {
            String val = list2[second];

            if (!map.containsKey(val))
                continue;

            int dis = map.get(val) + second;//获取距离

            if (dis > min)//距离过大
                break;

            if (dis < min) {//重置
                min = dis;
                res.clear();
            }

            res.add(val);
        }

        return res.toArray(new String[0]);
    }

}