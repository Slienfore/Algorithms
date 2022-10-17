package 二分查找;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/6 21:24
 */
//852-山脉数组的峰顶索引
public class Demo_852 {
    public static void main(String[] args) {
        int[] arr = {24, 69, 100, 99, 79, 78, 67, 36, 26, 19};
//        int[] arr = {0, 1, 0};
//        int[] arr = {0, 10, 5, 2};
//        int[] arr = {0, 2, 1, 0};
//        int[] arr = {3, 4, 5, 1};
//        int[] arr = {5, 7, 4};
        System.out.println(peakIndexInMountainArray_1(arr));
        System.out.println(peakIndexInMountainArray_2(arr));
    }


    /**
     * 二分<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：41.8 MB, 在所有 Java 提交中击败了12.03%的用户
     */
    public static int peakIndexInMountainArray_2(int[] arr) {
        if (arr.length == 3)
            return 1;

        int left = 0, right = arr.length - 1;

        while (left < right) {//左递增、右递减
            int mid = left + (right - left) / 2;

            //左边上坡、右边下坡
            if (arr[mid - 1] < arr[mid] && arr[mid] < arr[mid + 1]) {//峰顶左边<递增>

                left = mid + 1;

            } else if (arr[mid - 1] > arr[mid] && arr[mid] > arr[mid + 1]) {//峰顶右边<递减>

                right = mid - 1;

            } else if (arr[mid - 1] < arr[mid] && arr[mid] > arr[mid + 1]) {//登顶了
                return mid;
            }

        }

        return left;
    }


    /**
     * 前后比较<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：41.7 MB, 在所有 Java 提交中击败了12.44%的用户
     */
    public static int peakIndexInMountainArray_1(int[] arr) {
        for (int i = 1; i < arr.length - 1; i++)
            if (arr[i] > arr[i - 1] && arr[i] > arr[i + 1])
                return i;

        return -1;
    }
}
