package 动态规划;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/26 9:28
 */
//Offer-46-把数字翻译成字符串
public class Offer_46 {
    public static void main(String[] args) {
        int num = 12258;
//        int num = 26;

        System.out.println(translateNum(num));

    }
    /**
     * 动规<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：37.9 MB, 在所有 Java 提交中击败了62.61%的用户
     */
    public static int translateNum(int num) {
        String str = String.valueOf(num);//转换成字符串
        int length = str.length();

        int[] dp = new int[length + 1];
        dp[0] = 1;
        dp[1] = ((str.charAt(0) - '0') >= 0 && (str.charAt(0) - '0') <= 9) ? 1 : 0;//第一个数

        for (int cur = 2; cur <= length; ++cur) {//只有组成字符只与前两项有关
            int curVal = str.charAt(cur - 1) - '0', compo = (str.charAt(cur - 2) - '0') * 10 + curVal;//与前一个数组成的数字

            if (curVal >= 0 && curVal <= 9)//自成一个字符
                dp[cur] += dp[cur - 1];

            if (compo >= 10 && compo <= 25)//与前两个字符形成数字(排除了前导 0)<当前组成为两位数>
                dp[cur] += dp[cur - 2];
        }

        return dp[length];
    }
}
