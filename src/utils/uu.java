package utils;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/9 14:19
 */
public class uu {
    static StringBuilder builder = new StringBuilder();

    /**
     * 功能: 数组替换<br>
     *
     * @param str 输入字符串
     */
    public static void replaceArray(int dimension, String str) {

        for (char val : str.toCharArray()) {
            if (val == '[') {
                builder.append('{');
            } else if (val == ']') {
                builder.append('}');
            } else builder.append(val);
        }

        if (dimension == 1) {
            builder.insert(0, "int[] nums = ");
            builder.append(";");
        } else if (dimension == 2) {
            builder.insert(0, "int[][] matrix = ");
            builder.append(";");
        }
        System.out.println(builder);
    }

    /**
     * 功能: 数组替换<br>
     *
     * @param str 输入字符串
     */
    public static void replaceChar(String str) {
        for (char val : str.toCharArray()) {
            if (val == '[') {
                builder.append('{');
            } else if (val == ']') {
                builder.append('}');
            } else if (val != 34 && val != 44)//不为双引号、逗号
                builder.append("'").append(val).append("'");
            else if (val != 34)
                builder.append(val);
        }


        builder.insert(0, "char[][] matrix = ");
        builder.append(";");

        System.out.println(builder);

    }


    //打印一维数组
    public static void print(int[] arr) {
        System.out.println();
        System.out.println(Arrays.toString(arr));
        System.out.println();
    }

    public static void print(boolean[] arr) {
        System.out.println();
        System.out.println(Arrays.toString(arr));
        System.out.println();
    }

    /**
     * 功能：打印一维数组
     *
     * @param
     */
    public static void print(String[] arr) {
        System.out.println();
        System.out.println(Arrays.toString(arr));
        System.out.println();
    }

    /**
     * 功能：打印二维数组
     *
     * @param arr
     */
    public static void print(int[][] arr) {
        System.out.println();
        System.out.println(Arrays.deepToString(arr));
        System.out.println();
    }

    /**
     * 功能：打印二维数组
     *
     * @param arr
     */
    public static void print(boolean[][] arr) {
        System.out.println();
        System.out.println(Arrays.deepToString(arr));
        System.out.println();
    }

    /**
     * 功能：打印二维数组
     *
     * @param arr
     */
    public static void print(char[][] arr) {
        System.out.println();
        System.out.println(Arrays.deepToString(arr));
        System.out.println();
    }

    /**
     * 功能：打印List
     *
     * @param list
     */
    public static void print(List<Integer> list) {
        System.out.println();
        System.out.println(list);
        System.out.println();
    }


    /**
     * 功能：打印力扣运行
     *
     * @param str
     */
    public static void print(String str) {

        builder.append("/").append("*").append("*").append("<br>").append("\n");
        for (char val : str.toCharArray()) {
            if (val == '内') {
                builder.append("<br>").append('\n').append(val);
            } else if (val != '\n') {
                builder.append(val);
            }
        }
        builder.append("<br>");
        builder.append("\n").append(date(new Date()));
        builder.append("*").append("/");

        System.out.println(builder);
    }

    /**
     * 功能：返回当前系统时间
     *
     * @param date 当前系统时间
     */
    private static String date(Date date) {
        String format = "yyyy年MM月dd日  HH:mm:ss";//时间格式
        return new SimpleDateFormat(format).format(date);
    }

}