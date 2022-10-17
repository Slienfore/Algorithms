package One;

import java.util.Arrays;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/11/21 10:56
 */
public class RangeFreqQuery {
    public static void main(String[] args) {
        int[] res = {12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56};
        RangeFreqQuery tes = new RangeFreqQuery(res);

        System.out.println(tes.query(1, 2, 4));
        System.out.println(tes.query(0, 11, 33));
    }

    private int[] nums;

    public RangeFreqQuery(int[] arr) {
        this.nums = arr;
    }

    public int query(int left, int right, int value) {
        int res = 0;
        Arrays.sort(nums, left, right);
        System.out.println(Arrays.toString(nums));
        return res;
    }
}
