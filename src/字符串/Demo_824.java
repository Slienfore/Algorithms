package 字符串;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/4/22 12:35
 */
//824-山羊拉丁文
public class Demo_824 {
    public static void main(String[] args) {
        String str = "I speak Goat Latin";
    }

    public static String toGoatLatin(String sentence) {
        String[] arr = sentence.split(" ");//将字母分割开来
        StringBuilder sb = new StringBuilder();

        int num = arr.length;
        for (int cur = 0; cur < num; ++cur) {
            String word;

            if (isValid(arr[cur])) {
                word = arr[cur];
            } else {
                word = arr[cur].substring(1) + arr[cur].charAt(0);
            }
            sb.append(word);
            sb.append("ma");
            sb.append("a".repeat(cur + 1));

            if (cur != num - 1)
                sb.append(" ");
        }
        return sb.toString();
    }

    static boolean isValid(String word) {
        String temp = word.toUpperCase();

        return temp.startsWith("A") || temp.startsWith("E") || temp.startsWith("I") || temp.startsWith("O") || temp.startsWith("U");
    }
}

