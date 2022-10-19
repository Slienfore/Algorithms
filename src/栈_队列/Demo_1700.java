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
        System.out.println(countStudents(students, sandwiches));
    }

    /**
     * 暴力<br>
     * 执行用时：1 ms, 在所有 Java 提交中击败了46.51%的用户<br>
     * 内存消耗：39.8 MB, 在所有 Java 提交中击败了13.75%的用户<br>
     * 2022年10月19日  09:25:12
     */
    public static int countStudents(int[] students, int[] sandwiches) {
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
