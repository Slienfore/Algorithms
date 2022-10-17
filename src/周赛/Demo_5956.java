package 周赛;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/12/19 10:38
 */
//5956-找出数组中的第一个回文字符串
public class Demo_5956 {
    public static void main(String[] args) {
//        String[] string = {"abc", "car", "ada", "racecar", "cool"};
//        String[] string = {"def", "ghi"};
        String[] string = {"notapalindrome", "racecar"};
        System.out.println(firstPalindrome(string));

    }

    public static String firstPalindrome(String[] words) {
        for (String word : words)
            if (isPalindrome(word)) return word;

        return "";
    }

    //判断回文字符串
    private static boolean isPalindrome(String str) {
        int left = 0, right = str.length() - 1;
        char[] chars = str.toCharArray();

        while (left < right) {
            if (chars[left] != chars[right]) return false;

            left++;
            right--;
        }

        return true;
    }
}
