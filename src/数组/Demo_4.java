package 数组;

import java.util.Arrays;

//4 -寻找正序字符串的中位数
public class Demo_4 {
    public static void main(String[] args) {
/*        int[] nums1 = {1, 3, 4, 9};
        int[] nums2 = {1, 2, 3, 4, 5, 6, 7, 8, 9};*/
        int[] nums1 = {1, 3};
        int[] nums2 = {2};
/*      int[] nums1 = {1, 3, 4, 5};
        int[] nums2 = {3, 4};*/
        long start = System.currentTimeMillis();

        System.out.println(findMedianSortedArrays_1(nums1, nums2));

        long end = System.currentTimeMillis();
        System.out.println("耗时为: " + (end - start));


    }

    public static double findMedianSortedArrays_2(int[] nums1, int[] nums2) {
        int length_1 = nums1.length, length_2 = nums2.length;
        int allLength = length_1 + length_2;
        boolean odd = allLength % 2 != 0;//判断中位数是否是奇数
        int mid = allLength / 2;

        int orinLeft = length_1 / 2, orinRihgt = (length_1 - 1) - orinLeft;//获取原数组左右两边端点的位置
        int pivot = nums1[orinLeft];//获取中间结点

        //在第二个数组中进行分割
        int middle = binarySearch(0, length_2 - 1, pivot, nums2);

        int splitLeft = middle, splitRight = (length_2 - 1) - middle + 1;//因为是第一个大于等于它的数所以包含自身


        if (odd) {//如果是奇数
            if (mid > splitLeft) {//说明在右边数组中

                int begin = binarySearch(orinLeft + 1, length_1, nums2[middle], nums1);//搜索起始位置
                int end = binarySearch(orinLeft + 1, length_1, nums2[length_2 - 1], nums1);//搜索结束位置

                if (mid < begin) {//如果在原来数组中


                }


            } else {


            }
        } else {

            int mid_1 = mid - 1;//偶数需要两个数


        }


        return 0;
    }


    private static int binarySearch(int left, int right, int target, int[] nums) {//搜寻第一个出现的元素
        while (left < right) {

            int mid = left + (right - left) / 2;//查找第一个大于等于pivot的数

            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] == target) {
                right = mid;
            } else if (nums[mid] > target) {
                right = mid;
            }

        }

        return left;
    }


    public static double findMedianSortedArrays_1(int[] nums1, int[] nums2) {
        //存放进大数组中然后再进行排序
        double result = 0;
        int index = 0;
        int[] num = new int[(nums1.length + nums2.length)];
        //分别将两数组存放进大数组
        for (int i = 0; i < nums1.length; i++) {
            num[i] = nums1[i];
            index++;
        }

        for (int j = 0; j < nums2.length; j++) {
            num[index] = nums2[j];
            index++;
        }
        //进行排序
        Arrays.sort(num);

        int mid = num.length / 2;
        if (num.length % 2 == 0) {//长度为偶数
            result = ((double) (num[mid] + num[mid - 1])) / 2;
        } else
            result = num[mid];
        return result;
    }
}
