package One;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/12/19 18:49
 */
// 997-找到小镇的法官
public class Demo_997 {
    public static void main(String[] args) {
        int[][] trust =
                {
                        {1, 3},
                        {1, 4},
                        {2, 3},
                        {2, 4},
                        {4, 3}
                };
        System.out.println(findJudge(4, trust));

    }

    public static int findJudge(int n, int[][] trust) {
        int[][] arr = new int[n + 1][2];

        for (int[] val : trust) {
            int trustOther = val[0], wasTrusted = val[1];

            arr[trustOther][0]++;//信任别人
            arr[wasTrusted][1]++;//被人所信任
        }

        for (int i = 1; i <= n; i++)
            if (arr[i][0] == 0 && arr[i][1] == n - 1) return i;

        return -1;
    }
}
