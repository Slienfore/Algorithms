package 周赛;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/11/13 22:32
 */

public class Demo_5910 {

    public static void main(String[] args) {
//        String word1 = "abcdeef", word2 = "abaaacc";
//        String word1 = "aaaa", word2 = "bccb";
        String word1 = "cccddabba", word2 = "babababab";

        System.out.println(checkAlmostEquivalent(word1, word2));
    }


    public static boolean checkAlmostEquivalent(String word1, String word2) {

        if (word1.length() < 4) return true;

        //定义字符哈希
        int[] num_1 = new int[26], num_2 = new int[26];

        for (char str : word1.toCharArray())
            num_1[str - 'a'] += 1;

        for (char str : word2.toCharArray())
            num_2[str - 'a'] += 1;

        for (int index = 0; index < 26; index++)
            if (num_1[index] - num_2[index] > 3 || num_1[index] - num_2[index] < -3)
                return false;

        return true;
    }
}
