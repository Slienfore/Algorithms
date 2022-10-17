package One;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/12/22 8:30
 */
// 686-重复叠加字符串的匹配
public class Demo_686 {
    public static void main(String[] args) {
        String s = "a", b = "aa";
        System.out.println(repeatedStringMatch(s, b));
    }

    public static int repeatedStringMatch(String a, String b) {        //字母哈希
        boolean[] hash = new boolean[26];
        for (char val : a.toCharArray())
            hash[val - 'a'] = true;

        //如果母串中不包含子串的字母，则直接返回
        for (char val : b.toCharArray())
            if (!hash[val - 'a'])
                return -1;

        //查看需要折叠的次数
        int split = b.length() / a.length();

        //进行拼接
        StringBuilder builder = new StringBuilder(a.repeat(split));
        for (int i = 0; i < 3; i++) {
            if (builder.toString().contains(b)) return split + i;

            builder.append(a);
        }
        return -1;

    }
}
