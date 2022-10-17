package 回溯;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/11/11 12:11
 */
//17-电话号码的组合
public class Demo_17 {

    public static void main(String[] args) {
        String digits = "23";
        List<String> strings = letterCombinations_1(digits);
        System.out.println(strings);

        System.out.println();

    }


    static List<String> res = new ArrayList<>();
    //字符缓冲区
    static StringBuilder builder = new StringBuilder();


    public static List<String> letterCombinations(String digits) {

        if (digits == null || digits.length() == 0) return res;//无效直接返回

        //用来存放键盘映射
        String[] keyMap = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

        backTracking(digits, keyMap, 0);//刚开始是从第零个元素开始的

        return res;
    }

    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：37.2 MB, 在所有 Java 提交中击败了52.12%的用户
     */
    private static void backTracking(String digits, String[] keyMap, int num) {
        //若达到了键盘映射的长度
        if (num == digits.length()) {
            res.add(builder.toString());
            return;
        }

        int part = digits.charAt(num) - '0';//获取键盘映射位置

        String key = keyMap[part];//获取当前的键盘映射字符串(键盘所代表的映射相当于)

        for (int i = 0; i < key.length(); i++) {//起始位置是从 0 开始的， 因为需要拼接的是另一个区域的首位
            builder.append(key.charAt(i));//拼接首位元素

            backTracking(digits, keyMap, num + 1);//获取下一个电话键盘映射

            builder.deleteCharAt(builder.length() - 1);//删除最后一位元素
        }
    }

    static Map<Character, String> map = new HashMap<>() {
        {
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }
    };

    public static List<String> letterCombinations_1(String digits) {
        if (digits.equals("")) return res;
        String path = "";
        recur(digits, 0, path);

        return res;
    }

    /**
     * Map(键值队进行映射)<br>
     * 执行用时：6 ms, 在所有 Java 提交中击败了5.77%的用户<br>
     * 内存消耗：41.4 MB, 在所有 Java 提交中击败了5.05%的用户
     */
    private static void recur(String digit, int index, String path) {
        if (path.length() == digit.length()) {
            res.add(path);
            return;
        }

        String key = map.get(digit.charAt(index));//获取当前的键值

        for (int i = 0; i < key.length(); i++)
            recur(digit, index + 1, path + key.charAt(i));//将回溯藏在实参中

    }
}
