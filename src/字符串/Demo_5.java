package 字符串;

//5-最长回文字串
public class Demo_5 {
    public static void main(String[] args) {
        String s = " babad";
        System.out.println(longestPalindrome(s));
    }

    public static String longestPalindrome(String s) {
        int length = s.length(), point = 0, max_length = 1;
        if (length < 2) {
            return s;
        }
        for (int i = 0; i < (length - 1); i++)
            for (int j = i + 1; j < length; j++) {
                //如果该字符串的长度比max大的话，还有匹配的话则修改
                if (max_length < (j - i + 1) && isMatch(s, i, j)) {
                    //若该字符串的长度比max_length的大和匹配的话，则修改指针max_length切换
                    max_length = j - i + 1;
                    point = i;
                }
            }
        //String substring(int beginIndex, int endIndex) 返回一个字符串，该字符串是此字符串的子字符串
        return s.substring(point, (point + max_length));
    }

    private static boolean isMatch(String s, int i, int j) {//匹配中间字符
        while (i < j) {
            if (s.charAt(i) == s.charAt(j)) {
                i++;
                j--;
            } else {
                return false;
            }
        }
        return true;
    }
}
