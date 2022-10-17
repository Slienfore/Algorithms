package 栈_队列;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/10/31 17:39
 */
//347-前  k 个高频元素
public class Demo_347 {

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 1, 1, 2, 2, 3, 3, 3, 3, 3, 3, 3, 6, 6, 6, 6, 6, 6, 6, 6, 6, 9, 7, 7, 7, 7};
        int k = 2;
        System.out.println(Arrays.toString(topKFrequent_1(nums, k)));
        System.out.println(Arrays.toString(topKFrequent_2(nums, k)));
        System.out.println(Arrays.toString(topKFrequent_3(nums, k)));
    }

    /*优先队列(堆)
    执行用时：13 ms, 在所有 Java 提交中击败了67.24%的用户
    内存消耗：40.6 MB, 在所有 Java 提交中击败了96.38%的用户
     */
    public static int[] topKFrequent_2(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        //字典获取出现的频率
        for (int val : nums) {
            map.put(val, map.getOrDefault(val, 0) + 1);
        }

        //维护小顶堆(即正序 --- 小的永远在队头)
        PriorityQueue<Integer> queue = new PriorityQueue<>(((o1, o2) -> map.get(o1) - map.get(o2)));

        for (Integer key : map.keySet()) {
            queue.add(key);
            if (queue.size() > k) queue.poll();
        }

        int[] res = new int[k];
        for (int i = k - 1; i >= 0; i--) {//频率高的再最前面
            res[i] = queue.remove();
        }
        return res;
    }


    /*大顶堆
    执行用时：12 ms, 在所有 Java 提交中击败了89.53%的用户
    内存消耗：40.6 MB, 在所有 Java 提交中击败了96.38%的用户
     */
    public static int[] topKFrequent_1(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int val : nums) {
            map.put(val, map.getOrDefault(val, 0) + 1);
        }
        //维护的是大顶堆(存放键值对)<逆序>
        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>(((o1, o2) -> o2.getValue() - o1.getValue()));

        for (Map.Entry entry : map.entrySet()) {
            queue.add(entry);
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {//返回频率最大的那一个
            res[i] = queue.poll().getKey();
        }
        return res;
    }

    /*桶排序:
    执行用时：9 ms, 在所有 Java 提交中击败了97.47%的用户
    内存消耗：41 MB, 在所有 Java 提交中击败了47.77%的用户
    */
    public static int[] topKFrequent_3(int[] nums, int k) {
        Map<Integer, Integer> map = IntStream.of(nums).boxed().collect(Collectors.toMap(o -> o, o -> 1, Integer::sum));

        List<Integer>[] list = new List[nums.length + 1];
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {//创建一个List数组桶,频率做下标,数字存放进去
            int key = entry.getKey(), val = entry.getValue();

            if (!(list[val] == null)) {//若存在该频率桶
                list[val].add(key);
            } else {
                //不存在频率桶的时候则需要创造一个频率桶
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(key);
                list[val] = temp;
            }
        }
        int[] res = new int[k];
        //按照出现的频率,从大到小进行排列
        for (int right = list.length - 1, count = 0; right > 0 && count < k; right--)
            while (list[right] != null && list[right].size() > 0 && count < k) {

                res[count++] = list[right].remove(0);
            }
        return res;
    }
}
