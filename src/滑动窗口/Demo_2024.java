package 滑动窗口;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/29 21:21
 */
//2024-考试的最大难度
public class Demo_2024 {
    public static void main(String[] args) {
/*        String str = "TTFF";
        int num = 2;*/
        String str = "TFFT";
        int num = 1;
        System.out.println(maxConsecutiveAnswers(str, num));
    }

    public static int maxConsecutiveAnswers(String answerKey, int k) {
        return Math.max(maxLen(answerKey, 'T', k), maxLen(answerKey, 'F', k));
    }

    private static int maxLen(String str, char tar, int limit) {
        int length = str.length(), max = Integer.MIN_VALUE;

        for (int left = 0, right = 0, count = 0; right < length; ++right) {
            count += (str.charAt(right) == tar) ? 1 : 0;//统计目标字符出现的次数

            while (count > limit) { //超过了限制次数
                if (str.charAt(left) == tar) //窗口中出现字符
                    --count;//复原

                ++left;
            }
            max = Math.max(max, right - left + 1);
        }
        return max;
    }
}
