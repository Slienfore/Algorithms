package 周赛;

import utils.uu;

import java.util.Arrays;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/4/2 23:29
 */
//6035-选择建筑的方案数
public class Demo_6035 {
    public static void main(String[] args) {
        String str = "001101";
//        String str = "0001100100";
        System.out.println(numberOfWays(str));
    }

    /**
     * 前缀和<br>
     * 执行用时：75 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：48.6 MB, 在所有 Java 提交中击败了100.00%的用户<br>
     * 2022年04月03日  09:25:24
     */
    public static long numberOfWays(String s) {
        int length = s.length();
        //统计当前位置 "之前" 和 "之后" 前后缀 0、1 的前后缀和
        int[] pre0 = new int[length], pre1 = new int[length],
                suf0 = new int[length], suf1 = new int[length];

        for (int cur = 1; cur < length; ++cur) {//统计当前 <位置之前> 的 "前后缀和"
            pre0[cur] = pre0[cur - 1] + (s.charAt(cur - 1) == '0' ? 1 : 0);

            pre1[cur] = pre1[cur - 1] + (s.charAt(cur - 1) == '1' ? 1 : 0);
        }

        for (int cur = length - 2; cur >= 0; --cur) {//统计当前 <位置之后> 的 "前后缀和"
            suf0[cur] = suf0[cur + 1] + (s.charAt(cur + 1) == '0' ? 1 : 0);

            suf1[cur] = suf1[cur + 1] + (s.charAt(cur + 1) == '1' ? 1 : 0);
        }

        long res = 0;
        for (int cur = 1; cur < length; ++cur)
            if (s.charAt(cur) == '0')//方案：[1,0,1],使用前缀 1， 后缀 1
                res += (long) pre1[cur] * suf1[cur];
            else if (s.charAt(cur) == '1')//方案：[0,1,0],使用前缀 0， 后缀 0
                res += (long) pre0[cur] * suf0[cur];

        return res;
    }
}
