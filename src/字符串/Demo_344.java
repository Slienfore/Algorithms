package 字符串;

import java.util.ArrayList;
import java.util.Arrays;

//344-反转字符串
public class Demo_344 {
    public static void main(String[] args) {
        char[] chars_1 = {'h', 'e', 'l', 'l', 'o'};
        reverseString_1(chars_1);
        char[] chars_2 = {'h', 'e', 'l', 'l', 'o'};
        reverseString_2(chars_2);
    }

    //Demo_1:双指针
    public static void reverseString_1(char[] s) {
        int left = 0;
        int right = s.length - 1;
        while (left < right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
        System.out.println(Arrays.toString(s));
    }

    //Demo_2：异或运算
    public static void reverseString_2(char[] s) {
        int left = 0;
        int right = s.length - 1;
        while (left < right) {
            s[left] ^= s[right];// 1 ^ 4
            s[right] ^= s[left];//4 ^ 1 ^ 4 = 1
            s[left] ^= s[right];//1 ^ 4 ^ 1 = 4
            left++;
            right--;
        }
        System.out.println(Arrays.toString(s));
    }
}
