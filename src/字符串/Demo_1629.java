package 字符串;

import java.util.Arrays;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/1/9 17:21
 */
//1629-按键持续时间最长的键
public class Demo_1629 {
    public static void main(String[] args) {
        int[] releaseTimes = {23, 34, 43, 59, 62, 80, 83, 92, 97};
        String keyPressed = "qgkzzihfc";
        System.out.println(slowestKey_1(releaseTimes, keyPressed));
        System.out.println(slowestKey_2(releaseTimes, keyPressed));
        System.out.println(slowestKey_3(releaseTimes, keyPressed));

    }

    /**
     * 简单模拟<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：38.4 MB, 在所有 Java 提交中击败了58.56%的用户
     */
    public static char slowestKey_1(int[] releaseTimes, String keysPressed) {
        int length = releaseTimes.length, index = 0, maxTime = releaseTimes[0];

        for (int i = 1; i < length; i++) {
            int curVal = releaseTimes[i] - releaseTimes[i - 1];

            if (curVal > maxTime) {
                index = i;
                maxTime = curVal;
            } else if (curVal == maxTime && (keysPressed.charAt(i) > keysPressed.charAt(index)))
                index = i;
        }
        return keysPressed.charAt(index);
    }

    /**
     * 简单模拟2<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%用户<br>
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了50.00%的用户<br>
     */
    public static char slowestKey_2(int[] releaseTimes, String keysPressed) {
        int length = releaseTimes.length, preTime = 0, resTime = 0;
        char resChar = 'a';

        for (int i = 0; i < length; i++) {
            int duration = releaseTimes[i] - preTime;

            if (duration > resTime || (duration == resTime && keysPressed.charAt(i) > resChar)) {
                resTime = duration;
                resChar = keysPressed.charAt(i);
            }

            preTime = releaseTimes[i];
        }

        return resChar;
    }


    /**Demo_3<br>
    执行用时：1 ms, 在所有 Java 提交中击败了90.07%的用户<br>
    内存消耗：38.6 MB, 在所有 Java 提交中击败了23.97%的用户
    */
    public static char slowestKey_3(int[] releaseTimes, String keysPressed) {
        int length = releaseTimes.length, maxTime = releaseTimes[0];
        char res = keysPressed.charAt(0);

        for (int i = 1; i < length; i++) {

            int duration = releaseTimes[i] - releaseTimes[i - 1];

            char curChar = keysPressed.charAt(i);

            if (duration > maxTime || (duration == maxTime && curChar > res))
                res = curChar;

            maxTime = Math.max(maxTime, duration);
        }
        return res;
    }
}
