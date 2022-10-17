package 二分查找;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/2 10:16
 */
public class binarySearch {
    public static void main(String[] args) {
        int[] nums = {1, 3, 4, 6};
//        int[] nums = {1, 3, 5, 6};
//        int[] nums = {1};
        int target = 7;
        System.out.println(binarySearch_1(target, nums));
        System.out.println(binarySearch_2(target, nums));
        System.out.println(binarySearch_3(target, nums));
    }

    /**
     * 搜索区间: [0, length]<br>
     * 退出循环条件: left == right<br>
     * 搜索第一个插入的位置: 进行左边界收缩
     */
    private static int binarySearch_1(int target, int[] nums) {
        int length = nums.length;

        int left = 0, right = length;

        while (left < right) {//搜索区间不在数组范围内
            int mid = (right + left) >>> 1;

            if (nums[mid] == target) {//搜索的值与target相等，进行边界收缩[left， mid](收缩左边界)
                right = mid;
            } else if (nums[mid] > target) {//搜索的值比target要大，说明可能是插入位置
                right = mid;
            } else if (nums[mid] < target) {//搜索的值比target还小，说明不可能是插入位置
                left = mid + 1;
            }
        }

        return left;
    }

    private static int binarySearch_2(int target, int[] nums) {
        int length = nums.length;

        int left = 0, right = length - 1;

        //插入的长度只能是数组里面的元素，如果需要插入到数组外面还需要额外判断
        if (target > nums[length - 1])
            return length;//左边界不能收缩至数组长度

        while (left < right) {//搜索区间在数组范围内
            int mid = (right + left) >>> 1;

            if (nums[mid] == target) {//搜索的值与target相等，进行边界收缩[left， mid](收缩左边界)
                right = mid;
            } else if (nums[mid] > target) {//搜索的值比target要大，说明可能是插入位置
                right = mid;
            } else if (nums[mid] < target) {//搜索的值比target还小，说明不可能是插入位置
                left = mid + 1;
            }

        }

        return left;
    }

    /**
     * right >= left<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：41.1 MB, 在所有 Java 提交中击败了9.27%的用户
     */
    private static int binarySearch_3(int target, int[] nums) {
        int length = nums.length;

        int left = 0, right = length - 1;

        while (left <= right) {//搜索区间在数组范围内(最终停下来的位置：left = right + 1)
            int mid = (right + left) >>> 1;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("left = " + left + "  right = " + right + " mid = " + mid);

            if (nums[mid] == target) {//搜索的值与target相等，进行边界收缩[left， mid](收缩左边界)
                right = mid - 1;//要么直接返回，要么收缩左边界
            } else if (nums[mid] > target) {//搜索的值比target要大，说明可能是插入位置
                right = mid - 1;
            } else if (nums[mid] < target) {//搜索的值比target还小，说明不可能是插入位置
                left = mid + 1;
            }

        }

        return left;//最后指针停下来的位置是right + 1
    }
}
