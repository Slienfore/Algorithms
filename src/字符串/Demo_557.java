package 字符串;

import javax.swing.*;

//557-反转字符串中的单词
public class Demo_557 {
    public static void main(String[] args) {
        String s = "东京 爱情 故事";
/*
        System.out.println(reverseWords_1(s));
        System.out.println(reverseWords_2(s));
*/

        String str = "Let's take LeetCode contest";
        System.out.println(reverseWords_3(str));
        String s1 = "I love u";
        System.out.println(reverseWords_3(s1));
    }

    //Demo_1：分割
    public static String reverseWords_1(String s) {
        //对每一个单词进行分割
        String[] split = s.split(" ");
        int length = split.length;
        String result = "";
        for (int i = 0; i < length; i++) {
            String slight = split[i];
            if (i == length - 1)//当指针到达字符串的末尾的时候，不需要拼接空格
                result = result + new StringBuilder(slight).reverse();
            else
                result = result + new StringBuilder(slight).reverse() + " ";
        }
        return result;
    }

    //Demo_2：双指针
    public static String reverseWords_2(String s) {
        char[] chars = s.toCharArray();
        int preSave = 0, right = 0;
        int length = chars.length;
        for (int i = 0; i < length; i++) {
            //遇到空格的时候
            if (chars[i] == ' ') {
                right = i - 1;
                reverse(chars, preSave, right);
                preSave = i + 1;//用来标记下一个非空字符串的起始位置
            }
        }
        //还有最后一个
        reverse(chars, preSave, length - 1);
        return new String(chars);
    }

    public static void reverse(char[] chars, int left, int right) {
        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
    }

    /*快慢指针：
    执行用时：5 ms, 在所有 Java 提交中击败了68.51%的用户
    内存消耗：38.7 MB, 在所有 Java 提交中击败了91.83%的用户
    */
    public static String reverseWords_3(String s) {
        StringBuilder builder = new StringBuilder();
        char[] chars = s.toCharArray();
        int slow = 0;
        int length = chars.length;
        while (slow < length) {
            int fast = slow + 1;
            while (fast < length && chars[fast] != ' ') fast++;

            for (int i = fast - 1; i >= slow; i--) {//快指针总是探索到"空格"或者遇到"字符串最后一位"(不包含)此时的快指针已经指向了空格了
                builder.append(chars[i]);
            }

            if (fast < length) builder.append(" ");
            slow = fast + 1;//慢指针移动到空格的下一位
        }
        return builder.toString();
    }
}
