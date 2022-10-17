package 二分查找;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/31 10:34
 */
//744-寻找比目标字母大的最小字母
public class Demo_744 {
    public static void main(String[] args) {
        char[] letters = {'c', 'f', 'j'};
        char target = 'f';
        System.out.println(nextGreatestLetter(letters, target));
    }

    /**
     * 二分<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：41.9 MB, 在所有 Java 提交中击败了10.29%的用户*2022年03月31日  10:43:16
     */
    public static char nextGreatestLetter(char[] letters, char target) {
        int left = 0, right = letters.length;//若不存在比其大的，则说明成环

        while (left < right) {//ceiling
            int mid = left + (right - left) / 2;

            if (letters[mid] <= target)
                left = mid + 1;
            else
                right = mid;
        }

        return right == letters.length ? letters[0] : letters[right];
    }
}
