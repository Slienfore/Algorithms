package 数据结构.搜索数据结构;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.ListIterator;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/11/1 15:44
 */
//图的--DFS与BFS算法
public class DFS_BFS_Graph {
    public static void main(String[] args) {

        ListGraph graph = new ListGraph(8);//图的存放结点的个数
        graph.addEdge(0, 1);//1-2
        graph.addEdge(0, 2);//1-2
        graph.addEdge(0, 3);//1-2
        graph.addEdge(2, 6);//3-7
        graph.addEdge(6, 5);//7-6
        graph.addEdge(2, 7);//3-8
        graph.addEdge(3, 4);//4-5
        graph.addEdge(4, 5);//5-6
        graph.addEdge(5, 6);//6-7

        DFS_BFS_Graph search = new DFS_BFS_Graph(graph);
        search.bfs();
        search.dfs();
    }

    /*链表创建有向图*/
    private static class ListGraph {
        ArrayList<ArrayList<Integer>> graph;//创建一大容器来存放 各个顶点与之相连的链表

        public ListGraph(int num) {//初始化图的顶点的数量
            graph = new ArrayList<>(num);
            for (int i = 0; i < num; i++) {
                graph.add(new ArrayList<>());//初始化各个结点对应的链表
            }
        }

        public void addEdge(int start, int end) {//获取指定的顶点然后添加进去对应的顶点
            graph.get(start).add(end);
        }

        public void removeEdge(int start, int end) {
            graph.get(start).remove((Integer) end);//强转包装类，防止抛出空指针
        }
    }

    //初始化链表
    ListGraph graph;
    boolean[] visited;//创建一个访问数组

    private DFS_BFS_Graph(ListGraph graph) {//初始化图
        this.graph = graph;
        visited = new boolean[graph.graph.size()];//图中的大容器的顶点容量
    }

    private void dfs() {
        for (int vertices = 0; vertices < graph.graph.size(); vertices++) {
            if (!visited[vertices]) {
                dfsTraverse(vertices);
            }
        }
    }

    private void bfs() {
        for (int vertices = 0; vertices < graph.graph.size(); vertices++) {
            if (!visited[vertices]) {
                bfsTraverse(vertices);
            }
        }
    }

    private void dfsTraverse(int vertices) {//需要遍历的顶点
        if (visited[vertices]) return;//若已经被访问过了，则不需要进行访问

        visited[vertices] = true;

        System.out.print(vertices + 1 + "->");
        //迭代各个结点
        for (Integer nextVertices : graph.graph.get(vertices)) {
            //若下一个结点还未被访问，则递归
            if (!visited[nextVertices]) {
                dfsTraverse(nextVertices);//一头扎进去，需要递归
            }
        }
    }

    private void bfsTraverse(int vertices) {
        Deque<Integer> deque = new ArrayDeque<>();
        visited[vertices] = true;

        deque.add(vertices);//将首顶点添加进入队头中
        while (!deque.isEmpty()) {

            Integer cur = deque.poll();

            System.out.print(cur + 1 + "->");

            //获得这一层结点的迭代器
            for (Integer nextVertices : graph.graph.get(cur)) {
                if (!visited[nextVertices]) {//将这一层所有结点的相邻结点添加入队列当中(即下一层)
                    deque.add(nextVertices);
                    visited[nextVertices] = true;
                }
            }
        }
    }
}
