package 周赛;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/12/5 10:35
 */
public class Demo_5942 {
    public static void main(String[] args) {
        int[] digits = {0, 2, 0, 0};
        System.out.println(Arrays.toString(findEvenNumbers(digits)));
    }

    public static int[] findEvenNumbers(int[] digits) {
        List<Integer> list = new ArrayList<>();
        int length = digits.length;
/*        for (int digit : digits) {
            if (digit != 0) {
                for (int val_1 : digits)
                    if (val_1 != digit) {
                        for (int val_2 : digits)
                            if (val_2 != digit && val_2 != val_1) {
                                int temp = (digit * 100 + val_1 * 10 + val_2);
                                if (temp % 2 == 0) list.add(temp);
                            }
                    }
            }
        }*/
        Arrays.sort(digits);
        for (int i = 0; i < length; i++) {
            int val_1 = digits[i];
            if (val_1 != 0) {
                for (int j = 0; j < length; j++) {
                    if (j != i) {
                        for (int k = 0; k < length; k++) {
                            if (k != j && k != i) {
                                int temp = (val_1 * 100 + digits[j] * 10 + digits[j]);
                                if (temp % 2 == 0) list.add(temp);
                            }
                        }
                    }
                }
            }
        }


        int[] res = new int[list.size()];
        int index = 0;
        for (int val : list)
            res[index++] = val;

        return res;
    }

    private static List<Integer> list;

    private static void helper(int num, int[] digits) {
    }

}
