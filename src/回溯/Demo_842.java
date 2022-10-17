package 回溯;

import utils.uu;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/4/3 12:17
 */
//842-将数组拆分成斐波那契序列
public class Demo_842 {
    public static void main(String[] args) {
//        String num = "1101111";
//        String num = "112358130";
//        String num = "0123";
        String num = "5511816597";
        System.out.println(splitIntoFibonacci(num));
    }

    /**
     * 回溯<br>
     * 执行用时：2 ms, 在所有 Java 提交中击败了57.93%的用户<br>
     * 内存消耗：41.4 MB, 在所有 Java 提交中击败了16.37%的用户<br>
     * 2022年04月03日  13:01:55
     */
    public static List<Integer> splitIntoFibonacci(String num) {
        backTrack(0, num);
        return res;
    }
    static List<Integer> res = new ArrayList<>();

    private static boolean backTrack(int orin, String str) {
        if (orin == str.length() && res.size() >= 3)//只有将整个字符串进行拆分才能分解完成
            return true;

        for (int cur = orin; cur < str.length(); ++cur) {

            if (cur > orin && str.charAt(orin) == '0')//不能包含前导 0
                return false;

            long sub = Long.parseLong(str.substring(orin, cur + 1));

            //防止截取越界或者截取的数值过大
            if (sub > Integer.MAX_VALUE)
                return false;

            //若不满足斐波那契数列性质
            if (res.size() >= 2 && sub != res.get(res.size() - 1) + res.get(res.size() - 2))
                continue;

            res.add((int) sub);

            if (backTrack(cur + 1, str))
                return true;

            res.remove(res.size() - 1);
        }

        return false;
    }
}