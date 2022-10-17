package 周赛;

import utils.uu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/4/3 13:37
 */
//5235-找出输掉零场或一场比赛的玩家
public class Demo_5235 {
    public static void main(String[] args) {
        int[][] matrix = {{1, 3}, {2, 3}, {3, 6}, {5, 6}, {5, 7}, {4, 5}, {4, 8}, {4, 9}, {10, 4}, {10, 9}};
        System.out.println(findWinners(matrix));
    }

    /**
     * 哈希<br>
     * 执行用时：362 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：90.8 MB, 在所有 Java 提交中击败了100.00%的用户<br>
     * 2022年04月03日  13:53:27
     */
    public static List<List<Integer>> findWinners(int[][] matches) {
        //映射: 人名、比赛败场次数 (维护(人名)升序排序)
        TreeMap<Integer, Integer> map = new TreeMap<>();

        for (int[] arr : matches) {
            int win = arr[0], lose = arr[1];

            map.put(win, map.getOrDefault(win, 0));//赢的话就不用增加

            map.put(lose, map.getOrDefault(lose, 0) + 1);//输掉了
        }

        //一个是没有输掉比赛的(0) 一个是恰好输掉一场比赛的(1)
        List<List<Integer>> res = List.of(new ArrayList<>(), new ArrayList<>());

        for (Integer key : map.keySet()) {
            int loseTime = map.get(key);

            if (loseTime == 0)//若没有输掉
                res.get(loseTime).add(key);

            else if (loseTime == 1)//若输掉了一场
                res.get(loseTime).add(key);
        }

        return res;
    }
}
