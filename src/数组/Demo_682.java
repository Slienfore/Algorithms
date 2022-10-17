package 数组;

import utils.uu;

import java.sql.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/26 8:40
 */
//682-棒球比赛
public class Demo_682 {
    public static void main(String[] args) {
        String[] ops = {"5", "2", "C", "D", "+"};
        System.out.println(calPoints(ops));
    }

    /**
     * 模拟<br>
     * 执行用时：2 ms, 在所有 Java 提交中击败了88.66%的用户<br>
     * 内存消耗：39.8 MB, 在所有 Java 提交中击败了20.51%的用户
     */
    public static int calPoints(String[] ops) {
        List<Integer> list = new ArrayList<>();
        int sum = 0;
        for (String val : ops) {
            int cur;
            final int size = list.size();
            switch (val) {
                case "+" -> {//前两次得分相加
                    cur = list.get(size - 1) + list.get(size - 2);
                    list.add(cur);
                    sum += cur;
                }
                case "D" -> {//前一次得分两倍
                    cur = list.get(size - 1) * 2;
                    list.add(cur);
                    sum += cur;
                }
                case "C" -> {//移除前一次得分
                    cur = size - 1;
                    sum -= list.remove(cur);
                }
                default -> {
                    cur = Integer.parseInt(val);
                    list.add(cur);
                    sum += cur;
                }
            }
        }
        return sum;
    }
}
