package 数位DP;

import java.util.Arrays;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/10/18 8:38
 */
// 233-数字 1 的个数
public class Demo_233 {
    public static void main(String[] args) {
        // int tar = 13;
        //int tar = 0;
        // int tar = 10;
        int tar = 13;
        // int tar = 824883294;
        System.out.println(countDigitOne4(tar));
        System.out.println(countDigitOne3(tar));

        System.out.println(countDigitOne1(tar));
        System.out.println(countDigitOne2(tar));
    }

    private static char[] chars;// 切割字符串
    private static int[][] dp;// 当前位置之前包含 cnt 个 1, 组合形成的数字个数(自顶向下递推)

    /**
     * 数位DP+记忆化搜索<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：38 MB, 在所有 Java 提交中击败了95.37%的用户<br>
     * 2022年10月18日  21:35:41
     */
    public static int countDigitOne4(int tar) {
        // 转换成为字符串
        chars = String.valueOf(tar).toCharArray();

        // 自左向右遍历的位, 不会超过数字的长度
        dp = new int[chars.length][chars.length];

        // 初始化记忆化
        for (int[] tmp : dp) Arrays.fill(tmp, -1);

        return dfs(0,
                0,// 根据前面有多少个 1, 获取后面重复子状态
                true);
    }

    private static int dfs(int idx, int cnt, boolean limit) {
        // 扫描到最后一个数字
        if (idx == chars.length) return cnt;

        // 需要添加约束条件 => 只有没有受到限制的时候, 才能够填入,
        // limit 表示当前是否受到了 n 的约束。若为真，则第 i 位填入的数字至多为 s[i]s[i], 否则可以是 9
        // 如果在受到约束的情况下填了 s[i]s[i], 那么后续填入的数字仍会受到 nn 的约束
        if (!limit && dp[idx][cnt] > 0) return dp[idx][cnt];

        int res = 0;
        for (int ceiling = limit ? chars[idx] - '0' : 9,// 受限 => 最高位 只能是 当前位数最高位
             num = 0; num <= ceiling; ++num) {// 遍历当前位数能够取值的所有数字

            res += dfs(idx + 1,// 统计下一个位置
                    cnt + (num == 1 ? 1 : 0),// 当前位为 1 时, 才会统计当前位数上 1 的个数
                    limit && num == ceiling);// 只有高位首先, 而且当前位置达到最高位时候 => 受限
        }

        if (!limit) dp[idx][cnt] = res;

        return res;
    }

    /**
     * 数位动规<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：37.9 MB, 在所有 Java 提交中击败了96.87%的用户<br>
     * 2022年10月18日  18:39:30
     */
    public static int countDigitOne3(int tar) {
        // 个位数只有一个
        if (tar < 10) return tar == 0 ? 0 : 1;

        // 将数字转换切割成为字符
        char[] chars = String.valueOf(tar).toCharArray();

        int len = chars.length;// 位数

        // 表示 0 ~ 10^(i+1) - 1 的所有 (i + 1) 位数里 1 的总数
        int[] memo = new int[len];
        memo[0] = 1;// 0 ~ 9 中 1 的个数只有 1个

        for (int nth = 1; nth < len; ++nth) {
            memo[nth] = memo[nth - 1] * 10 + // 不看高位, 低位拼接
                    (int) Math.pow(10, nth);// 高位为 1, 与后面所有的低位拼接都会包含
        }

        // dp[nth]: 低位 向 高位 进行截取的 nth + 1 位中所有数 1 的总数
        int[] dp = new int[len];
        // 初始化动规 => 如果个位为 0, 则说明个位不会包含 1
        dp[0] = chars[len - 1] == '0' ? 0 : 1;

        for (int nth = 1; nth < len; ++nth) {
            int idx = (len - 1) - nth;// 高位下标

            // 低位 向 高位 递推
            int num = chars[idx] - '0';

            if (num == 0) {// 高位为 0, 高位不能包含前导 0
                dp[nth] = dp[nth - 1];

            } else if (num == 1) {// 高位为 1
                // 截取低位(从 0 开始)
                int low = Integer.parseInt(new String(chars,
                        idx + 1, len - (idx + 1))) + 1;

                dp[nth] = low + // 高位为 1 时组合个数
                        dp[nth - 1] + // 低位(包含 1 的总数)与高位 个数
                        memo[nth - 1];// 不满足 (nth + 1)位的这一块中, 包含 1 的个数 => 168(0 - 99)

            } else {// 高位 > 1

                dp[nth] = (int) Math.pow(10, nth) +// 高位由于 > 1 => 包含了 1, 所以能够与 全部低位 进行组合
                        dp[nth - 1] +// 低位(包含 1 的总数)与高位 个数
                        memo[nth - 1] * num;// [0 ~ nth] 区间中, 不满足 (nth + 1)位的这一块中 1的数量
            }
        }

        return dp[len - 1];// 最高位就是答案
    }


    // 动规(超内存)
    public static int countDigitOne2(int tar) {
        if (tar == 0) return 0;// 防止初始化越界

        int cnt = 0;
        // dp[nth] = dp[nth % 10] + dp[nth / 10]
        // 获取数字切割后 1 的个数
        int[] dp = new int[tar + 1];

        dp[1] = 1;// 初始化

        for (int nth = 1; nth <= tar; ++nth) {
            // 将数字切割成为两部分, 然后获取这两部分中 1 的个数
            dp[nth] = dp[nth % 10] + dp[nth / 10];

            cnt += dp[nth];
        }

        return cnt;
    }

    // 暴力(超时)
    public static int countDigitOne1(int tar) {
        int res = 0;

        for (int cur = 1; cur <= tar; ++cur) {
            int cnt = 0, num = cur;

            while (num != 0) {
                // 个位开始一一遍历比较
                if (num % 10 == 1) ++cnt;

                num /= 10;
            }

            res += cnt;
        }

        return res;
    }
}
