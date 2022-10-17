package 字符串;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/11/23 15:06
 */
//859-亲密字符串
public class Demo_859 {
    public static void main(String[] args) {
        String s = "aaaaaaabc";
        String goal = "aaaaaaacb";
        System.out.println(buddyStrings(s, goal));
    }

    /**
     * 迭代、字符哈希<br>
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.45%的用户<br>
     * 内存消耗：38.3 MB, 在所有 Java 提交中击败了69.34%的用户
     */
    public static boolean buddyStrings(String s, String goal) {
        int len_1 = s.length(), len_2 = goal.length();

        if (len_1 != len_2) return false;
        int one = -1, two = -1;

        if (s.equals(goal)) {
            int[] hash = new int[26];//判断是否具有两个相同的元素进行交换
            for (int i = 0; i < len_1; i++) {
                int pos = s.charAt(i) - 'a';
                hash[pos]++;

                if (hash[pos] > 1) return true;//两个相同元素那么说明可以交换一次
            }
            return false;
        } else {//找出只需要交换一次的两个位置
            for (int i = 0; i < len_1; i++)
                if (s.charAt(i) != goal.charAt(i)) {
                    if (one == -1) one = i;
                    else if (two == -1) two = i;
                    else return false;
                }
        }
        return (one != -1 && two != -1 && s.charAt(one) == goal.charAt(two) && s.charAt(two) == goal.charAt(one));
    }
}
