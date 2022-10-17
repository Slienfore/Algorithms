package 字符串;

import java.util.Arrays;
//434-字符串的单词数
public class Demo_434 {
    public static void main(String[] args) {
        String s = ", , , ,        a, eaefa";
        System.out.println(countSegments_1(s));
        System.out.println(countSegments_2(s));
    }

    //Demo_1：将字符串按空格进行分割，如果字符串为空则不会增加单词数
    public static int countSegments_1(String s) {
        int result = 0;
        String[] str = s.split(" ");
        for (String value : str) {
            if (!value.equals("")) result++;//排除了分  割字符串为空的情况
        }
        return result;
    }

    //Demo_2
    public static int countSegments_2(String s) {
        //对字符串的长度进行过滤
        return (int) Arrays.stream((s.split(" "))).filter(str -> str.length() > 0).count();
    }
}
