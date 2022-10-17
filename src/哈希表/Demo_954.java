package 哈希表;

import utils.uu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/4/1 10:58
 */
//954-二倍成对数组
public class Demo_954 {
    public static void main(String[] args) {
//        int[] arr = {3, 1, 3, 6};
        int[] arr = {4, -2, 2, -4};
        System.out.println(canReorderDoubled(arr));
    }


    /**
     * 哈希<br>
     * 执行用时：22 ms, 在所有 Java 提交中击败了92.78%的用户<br>
     * 内存消耗：49.1 MB, 在所有 Java 提交中击败了7.73%的用户<br>
     * 2022年04月01日  11:11:57
     */
    public static boolean canReorderDoubled(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int val : arr)//映射键值对的数量
            map.put(val, map.getOrDefault(val, 0) + 1);

        List<Integer> list = new ArrayList<>(map.keySet());//存放各个 "键"

        list.sort(((o1, o2) -> Math.abs(o1) - Math.abs(o2)));//存在负数(将其绝对值之差进行排序)
        System.out.println(list);

        for (int key : list) {
            //若没有足够的 (2 * KEY) 键值对
            if (map.get(key) > map.getOrDefault(2 * key, 0))
                return false;

            map.put(2 * key, map.getOrDefault(2 * key, 0) - map.get(key));//将匹配的键值对进行消除
        }

        return true;
    }
}
