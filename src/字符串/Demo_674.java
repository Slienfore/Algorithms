package 字符串;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/2/20 11:28
 */
public class Demo_674 {
    public static void main(String[] args) {
        String str = "aaa";
        System.out.println(countSubstrings(str));
    }


    /**中心扩散法<br>
    执行用时：3 ms, 在所有 Java 提交中击败了86.88%的用户<br>
    内存消耗：39.2 MB, 在所有 Java 提交中击败了25.99%的用户
     */
    public static int countSubstrings(String s) {
        int count = 0;

        int length = s.length();
        int single = length, pair = length - 1;
        int total = single + pair;//中心点个数

        for (int center = 0; center < total; center++) {

            //设定左右指针个数，当中心点为《偶数》的时候，右指针则在中心点右边，为《奇数》的时候则与左指针重叠
            int left = center / 2, right = center / 2 + center % 2;

            while (left >= 0 && right < length && s.charAt(left) == s.charAt(right)) {
                count++;
                left--;
                right++;
            }
        }

        return count;
    }
}
