package 周赛;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/2/27 10:34
 */
//6008-统计包含前缀的字符串
public class Demo_6008 {
    public static void main(String[] args) {
        String[] words = {"pay", "attention", "practice", "attend"};
//        String[] words = {"leetcode", "win", "loops", "success"};
        String pref = "at";
        System.out.println(prefixCount(words, pref));
    }


    public static int prefixCount(String[] words, String pref) {

        return (int) Arrays.stream(words).filter(o -> o.startsWith(pref)).count();

    }
}
