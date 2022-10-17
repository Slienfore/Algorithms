package 二分查找;

//278-第一个错误版本
public class Demo_278 {
    public static void main(String[] args) {
        int bad = 2126753390;
        System.out.println(firstBadVersion(bad));
    }

    public static int firstBadVersion(int n) {
        int left = 1;
        int right = n;
        while (left < right) {
            //注意边界
            int mid = left + (right - left) / 2;
            //是坏版本的话，就从前面找
            if (isBadVersion(mid)) right = mid;
                //不是坏版本的话，就往后边找
            else left = mid + 1;
        }
        return left;
    }

    public static int firstBadVersion_1(int n) {
        int left = 1, right = n;
        while(left < right) {
            int mid = (right + left) >>> 1;

            if (!isBadVersion(mid)) {//如果不是错误版本，说明当前以及前面的版本都是错误的
                left = mid + 1;
            } else {//如果是错误的说明可能是第一个也可能不是
                right = mid;
            }
        }

        return left;

    }

    public static boolean isBadVersion(int value) {
        if (value == 30 || value == 31 || value == 32 || value == 33) {
            return true;
        }
        return false;
    }
}
