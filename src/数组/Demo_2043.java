package 数组;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/18 16:02
 */
//2043-简易银行系统
public class Demo_2043 {
    /**
     * 模拟<br>
     * 执行用时：102 ms, 在所有 Java 提交中击败了39.72%的用户<br>
     * 内存消耗：78.6 MB, 在所有 Java 提交中击败了38.06%的用户
     */
    public static void main(String[] args) {
        Demo_2043 bank = new Demo_2043(new long[]{10, 100, 20, 50, 30});

        System.out.println(bank.withdraw(3, 10));    // 返回 true ，账户 3 的余额是 $20 ，所以可以取款 $10 。
        // 账户 3 余额为 $20 - $10 = $10 。
        System.out.println(bank.transfer(5, 1, 20)); // 返回 true ，账户 5 的余额是 $30 ，所以可以转账 $20 。
        // 账户 5 的余额为 $30 - $20 = $10 ，账户 1 的余额为 $10 + $20 = $30 。
        System.out.println(bank.deposit(5, 20));    // 返回 true ，可以向账户 5 存款 $20 。
        // 账户 5 的余额为 $10 + $20 = $30 。
        System.out.println(bank.transfer(3, 4, 15)); // 返回 false ，账户 3 的当前余额是 $10 。
        // 所以无法转账 $15 。
        System.out.println(bank.withdraw(10, 50));   // 返回 false ，交易无效，因为账户 10 并不存在。
    }

    long[] balance;

    Demo_2043(long[] balance) {
        this.balance = balance;
    }

    //转账
    public boolean transfer(int account1, int account2, long money) {
        if (!check(account1) || !check(account2) || !check(account1, money))
            return false;

        balance[account1 - 1] -= money;
        balance[account2 - 1] += money;

        return true;
    }

    //存款
    public boolean deposit(int account, long money) {
        if (!check(account))
            return false;

        balance[account - 1] += money;

        return true;
    }

    //取款
    public boolean withdraw(int account, long money) {
        if (!check(account) || !check(account, money))
            return false;

        balance[account - 1] -= money;

        return true;
    }

    //指定账户数量在《 1 和 n 》之间、取款或者转账需要的钱《小于等于》用户越
    public boolean check(int account) {
        return account >= 1 && account <= balance.length;
    }

    public boolean check(int account, long money) {
        return balance[account - 1] >= money;
    }

}