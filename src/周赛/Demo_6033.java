package 周赛;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/4/2 22:38
 */
//6033-转换数字的最少位翻转次数
public class Demo_6033 {
    public static void main(String[] args) {
        int start = 10, goal = 7;
//        int start = 3, goal = 4;

        System.out.println(minBitFlips_1(start, goal));
        System.out.println(minBitFlips_2(start, goal));
    }

    /**
     * 模拟<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了100.00%的用户<br>
     * 2022年04月03日  08:19:03
     */
    public static int minBitFlips_2(int start, int goal) {
        int res = 0;

        while (start != 0 || goal != 0) {//模拟转换成 2 进制数，再统计不同的个数
            if (start % 2 != goal % 2)
                ++res;

            start /= 2;
            goal /= 2;
        }

        return res;
    }


    public static int minBitFlips_1(int start, int goal) {
        StringBuilder orin = new StringBuilder(Integer.toBinaryString(start)), end = new StringBuilder(Integer.toBinaryString(goal));
        int orinLen = orin.length(), endLen = end.length();

        if (orinLen < endLen) //转换的长度较小
            for (int cur = 0; cur < endLen - orinLen; ++cur)//往前补零
                orin.insert(0, "0");
        else if (orinLen > endLen)
            for (int cur = 0; cur < orinLen - endLen; ++cur)//往前补零
                end.insert(0, "0");

        int maxLen = Math.max(endLen, orinLen), res = 0;

        for (int cur = 0; cur < maxLen; ++cur)
            if (orin.charAt(cur) != end.charAt(cur))
                ++res;

        return res;
    }
}
