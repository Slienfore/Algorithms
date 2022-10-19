package 栈_队列;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/10/19 9:05
 */
// 1700-无法吃午餐的学生数量
public class Demo_1700 {
    public static void main(String[] args) {
        // int[] students = {1, 1, 0, 0};
        // int[] sandwiches = {0, 1, 0, 1};
        int[] students = {1, 1, 1, 0, 0, 1};
        int[] sandwiches = {1, 0, 0, 0, 1, 1};
        System.out.println(countStudents1(students, sandwiches));
        System.out.println(countStudents2(students, sandwiches));
    }

    /**
     * 规律<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：39.6 MB, 在所有 Java 提交中击败了38.06%的用户<br>
     * 2022年10月19日  09:52:48
     */
    public static int countStudents2(int[] students, int[] sandwiches) {
        int[] cnt = new int[2];

        // 统计学生对两种三明治的需求量
        for (int val : students) ++cnt[val];

        for (int val : sandwiches) {// 遍历栈顶三明治
            // 只要找到一个三明治所有学生都不喜欢(即此时 学生们不对该三明治有需求了 )
            // 那么该三明治无论学生怎么排队, 都不会有人吃掉
            if (cnt[val] > 0) --cnt[val];
            else
                break;
        }

        return cnt[0] + cnt[1];
    }

    /**
     * 暴力<br>
     * 执行用时：1 ms, 在所有 Java 提交中击败了46.51%的用户<br>
     * 内存消耗：39.8 MB, 在所有 Java 提交中击败了13.75%的用户<br>
     * 2022年10月19日  09:25:12
     */
    public static int countStudents1(int[] students, int[] sandwiches) {
        Queue<Integer> queue = new LinkedList<>();

        // 模拟学生队列
        for (int val : students) queue.add(val);

        int idx = 0;// 控制三明治栈顶元素(等待学生匹配)
        while (true) {
            int size = queue.size();

            for (int cur = 0; cur < size && queue.size() > 0; ++cur) {// 遍历学生, 直到某次学生人数不会减少为止
                int val = queue.poll();// 队头学生出队

                if (val == sandwiches[idx])// 如果能够满足, 不必入队列
                    ++idx;// 移动下一个进行匹配三明治
                else
                    queue.add(val);
            }

            // 说明不能进行更换了
            if (size == queue.size()) return size;
        }
    }
}
