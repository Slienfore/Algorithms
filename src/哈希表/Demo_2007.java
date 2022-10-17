package 哈希表;

import utils.uu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/4/1 9:23
 */
//2007-从双倍数组中还原原数组
public class Demo_2007 {
    public static void main(String[] args) {
//        int[] change = {1, 3, 4, 2, 6, 8};
        int[] change = {2, 1, 2, 4, 2, 4};
        uu.print(findOriginalArray(change));
    }

    /**
     * <br>
     * 执行用时：100 ms, 在所有 Java 提交中击败了31.74%的用户<br>
     * 内存消耗：57.2 MB, 在所有 Java 提交中击败了43.11%的用户<br>
     * 2022年04月01日  10:26:50
     */
    public static int[] findOriginalArray(int[] changed) {
        int length = changed.length;
        if (length % 2 != 0)//不能成对进行匹配
            return new int[]{};

        //排序，方便进行映射
        Arrays.sort(changed);
        HashMap<Integer, Integer> map = new HashMap<>();//进行对应数值，以及出现的个数进行匹配
        List<Integer> pair = new ArrayList<>();

        for (int val : changed) {
            if (map.getOrDefault(val, 0) > 0) {
                map.put(val, map.get(val) - 1);
                pair.add(val / 2);
            } else
                map.put(val * 2, map.getOrDefault(val * 2, 0) + 1);//从小到大排序，将其二倍关系进行映射
        }

        if (pair.size() != length / 2)//只能是偶数匹配对
            return new int[]{};

        int[] res = new int[pair.size()];
        int index = 0;

        for (int val : pair)
            res[index++] = val;

        return res;
    }
}