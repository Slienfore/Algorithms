package One;

import java.util.Stack;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/2/21 21:51
 */
// 汉诺塔递归
public class Hanoi {
    public static void main(String[] args) {
        recur('A', 'B', 'C', 3);
    }

    public static void recur(char A, char B, char C, int diskNum) {
        if (diskNum == 1) {
            System.out.println(A + "  移动到   " + C);
            return;
        }

        recur(A, C, B, diskNum - 1);//最大的盘子(第N个)滞留在 A 柱中， 然后将 (N - 1)个盘子依次递归到最后一个然后将其移动到 B 柱中

        System.out.println(A + "  移动到   " + C);//此时最大的盘子留在了 A 柱，将其直接移动到 C 柱

        recur(B, A, C, diskNum - 1);//然后将 B 柱中的盘子递归到最后一个将其移动到 C 柱
    }


    //将 3 根柱子抽象成为三个 栈
    private static void recur_1(Stack stack_A, Stack stack_B, Stack stack_C, int diskNum) {
        if (diskNum == 1) {
            stack_C.push(stack_A.pop());//《自底向上》递归到顶端最小的元素，将其移动到 C 柱
            return;
        }

        recur_1(stack_A, stack_C, stack_B, diskNum - 1);//将顶端的下一个元素移动到 B 柱

        stack_C.push(stack_A.pop());

        recur_1(stack_B, stack_A, stack_C, diskNum - 1);


    }


}
