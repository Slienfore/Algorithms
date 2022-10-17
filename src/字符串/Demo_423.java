package 字符串;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/11/24 7:42
 */
//423-从英文中重建数字
public class Demo_423 {
    public static void main(String[] args) {
        String str = "owoztneoer";
        System.out.println(originalDigits(str));
    }


    /**
     * 执行用时：15 ms, 在所有 Java 提交中击败了15.25%的用户<br>
     * 内存消耗：39.3 MB, 在所有 Java 提交中击败了11.30%的用户
     */
    public static String originalDigits(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char chs : s.toCharArray())
            map.put(chs, map.getOrDefault(chs, 0) + 1);

        int[] hash = new int[10];
        hash[0] = map.getOrDefault('z', 0);//确定"zero"
        hash[2] = map.getOrDefault('w', 0);//确定"two"
        hash[4] = map.getOrDefault('u', 0);//确定"four"
        hash[6] = map.getOrDefault('x', 0);//确定"six"
        hash[8] = map.getOrDefault('g', 0);//确定"eight"

        hash[3] = map.getOrDefault('h', 0) - hash[8];//确定"three"
        hash[5] = map.getOrDefault('f', 0) - hash[4];//确定"five"
        hash[7] = map.getOrDefault('s', 0) - hash[6];//确定"seven"

        hash[1] = map.getOrDefault('o', 0) - hash[0] - hash[2] - hash[4];//确定"one"
        hash[9] = map.getOrDefault('i', 0) - hash[5] - hash[6] - hash[8];//确定"nine"

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < hash.length; i++)
            for (int nums = 0; nums < hash[i]; nums++)//统计出现的数字个数
                builder.append((char) (i + '0'));

        return builder.toString();
    }
}
