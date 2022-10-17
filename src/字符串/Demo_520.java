package 字符串;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/11/13 18:39
 */
public class Demo_520 {

    public static void main(String[] args) {
        String str = "USA";
        System.out.println(detectCapitalUse_1(str));
    }

    /**
     执行用时：2 ms, 在所有 Java 提交中击败了13.68%的用户<br>
    内存消耗：37 MB, 在所有 Java 提交中击败了8.06%的用户<br>
    */
    public static boolean detectCapitalUse_1(String word) {
        //全是大写后者第二位开始全部是小写
        return (word.toUpperCase().equals(word) || word.substring(1).toLowerCase().equals(word.substring(1)));
    }
}
