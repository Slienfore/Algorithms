package 二分查找;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/4 17:18
 */
//658-找到 k 个最接近的元素
public class Demo_658 {

    public static void main(String[] args) {
        int[][] arr =
                {
                        {1, 2, 3, 4, 5},
                        {0, 0, 1, 2, 3, 3, 4, 7, 7, 8}
                };

        int[] target = {3, -1, 4, 5, 6};
        int[] num = {3, 4, 5};

        System.out.println(findClosestElements(arr[1], num[2], target[3]));
        System.out.println(findClosestElements_1(arr[1], num[2], target[3]));
        System.out.println(findClosestElements_2(arr[1], num[2], target[3]));
    }

    /**
     * 二分<br>
     * 执行用时：16 ms, 在所有 Java 提交中击败了19.29%的用户<br>
     * 内存消耗：43.3 MB, 在所有 Java 提交中击败了16.88%的用户
     */
    public static List<Integer> findClosestElements(int[] arr, int k, int x) {
        int length = arr.length;

        if (k == length)
            return Arrays.stream(arr).boxed().collect(Collectors.toList());//转换成 List


        LinkedList<Integer> res = new LinkedList<>();

        int left = 0, right = length - 1;

        while (left < right) {//搜索最接近 指定值的下标
            int mid = left + (right - left + 1) / 2;//记得向上取整

            if (arr[mid] > x) {//如果比目标值小收缩右边界
                right = mid - 1;
            } else if (arr[mid] == x) {//相等则说明可能是目标
                left = mid;
            } else if (arr[mid] < x) {//该值肯能是目标值
                left = mid;
            }

        }
        right++;//防止指针重叠

        for (int i = 0; i < k && i < length; i++) {//取 k 个数
            if (left >= 0 && right < length) {//如果双方都没有越界
                int leftDiff = Math.abs(x - arr[left]), rightDiff = Math.abs(x - arr[right]);

                if (leftDiff < rightDiff) {//如果左边的相差的值小

                    res.add(arr[left--]);

                } else if (leftDiff == rightDiff) {//如果相差的值相等，添加小的那一个

                    res.add(arr[left] > arr[right] ? arr[right++] : arr[left--]);

                } else {

                    res.add(arr[right++]);

                }

            } else if (left >= 0) {
                res.add(arr[left--]);
            } else {
                res.add(arr[right++]);
            }
        }

        Collections.sort(res);
        return res;
    }


    /**
     * 双指针删除法<br>
     * 执行用时：4 ms, 在所有 Java 提交中击败了76.97%的用户<br>
     * 内存消耗：43 MB, 在所有 Java 提交中击败了30.70%的用户
     */
    public static List<Integer> findClosestElements_1(int[] arr, int k, int x) {
        int length = arr.length;
        if (k == length)
            return Arrays.stream(arr).boxed().collect(Collectors.toList());

        int left = 0, right = length - 1;

        while (right - left > k - 1) {//当删除后还剩下多少个元素

            int leftDiff = x - arr[left], rightDiff = arr[right] - x;//有序数组(两者都是边界值)

            if (leftDiff <= rightDiff) {//因为右边的值大所以只删除右边的
                right--;//删除右边元素
            } else {
                left++;//删除左边
            }

        }

        ArrayList<Integer> res = new ArrayList<>();
        while (left <= right)
            res.add(arr[left++]);

        return res;
    }

    /**
     * 二分查找最优子区间<br>
     * 执行用时：4 ms, 在所有 Java 提交中击败了76.97%的用户<br>
     * 内存消耗：43.8 MB, 在所有 Java 提交中击败了5.20%的用户
     */
    public static List<Integer> findClosestElements_2(int[] arr, int k, int x) {
        int length = arr.length;

        //收缩区间是[0, length - need]  left最多只能取到 (length - k)
        int left = 0, right = length - k;

        //一个数[左右对称]的元素相差值最小(尽量使区间中的 mid 离 x 最近)
        while (left < right) {
            int mid = left + (right - left) / 2;

            if (x - arr[mid] > arr[mid + k] - x) {//左边相差太大则

                left = mid + 1;//收缩左边界

            } else if (x - arr[mid] == arr[mid + k] - x) {//左右边界相同则收缩右边界
                right = mid;
            } else if (x - arr[mid] < arr[mid + k] - x) {
                right = mid;
            }

        }

        LinkedList<Integer> res = new LinkedList<>();

        while (left < right + k)//最终left == right + k(length)否则越界
            res.add(arr[left++]);

        return res;

    }
}
