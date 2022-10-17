package 数据结构.搜索数据结构;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/11/2 14:38
 */
//DFS——BFS邻接表实现
public class DFS_BFS_Vertices {
    public static void main(String[] args) {
        Graph graph = new Graph();
        DFS_BFS_Vertices search = new DFS_BFS_Vertices();
        //初始化图
        search.init(graph);
        //打印各顶点的链表
        search.printGraph(graph);

        System.out.println("DFS搜索路径是:(递归) ");
        search.dfsRecursion(graph);

        System.out.println("DFS搜索路径(非递归)");
        search.dfsTraverse(graph);

        System.out.println("BFS搜索路径: ");
        search.bfsQueue(graph);
    }


    //顶点类
    private static class Vertices {
        String verName;
        String color;//遍历的结点的颜色
        Vertices next;//下一个结点
    }

    //图类
    private static class Graph {//初始化图
        //顶点数组-->即各个顶点的链表
        Vertices[] verArray = new Vertices[100];
        int verNum = 0;//顶点数量
        int edgeNum = 0;//边的数量
    }

    Stack<Vertices> stack = new Stack<>();//顶点栈

    /**
     * 根据用户的输入边并返回该结点(查找顶点)
     *
     * @param graph   创建好的图
     * @param verName 顶点名字
     * @return 返回一个顶点
     */
    private Vertices getVertex(Graph graph, String verName) {
        for (int pos = 0; pos < graph.verNum; pos++) {
            if (graph.verArray[pos].verName.equals(verName)) return graph.verArray[pos];
        }
        return null;
    }

    /**
     * 直接用数组进行初始化
     * @param graph 图*/
    private void init(Graph graph) {
 /*
        graph.verNum = 6;
        System.out.println("顶点数量: " + graph.verNum);
        graph.edgeNum = 7;
        System.out.println("边的数量: " + graph.edgeNum);


        //创建有向图
        String[] vertex = {"v1", "v2", "v3", "v4", "v5", "v6"};
        for (int num = 0; num < graph.verNum; num++) {//初始化各个顶点
            Vertices vertices = new Vertices();
            vertices.verName = vertex[num];
            vertices.color = "white";
            vertices.next = null;
            graph.verArray[num] = vertices;
        }


        String[] edge = {"v1->v2", "v1->v3", "v2->v3", "v3->v4", "v4->v2", "v5->v4", "v5->v6"};
        for (int num = 0; num < graph.edgeNum; num++) {
            //将边分割
            String[] saveEdge = edge[num].split("->");
            String start = saveEdge[0], end = saveEdge[1];
            Vertices pre_1 = getVertex(graph, start);
            Vertices preEnd_1 = new Vertices();
            preEnd_1.verName = end;
            preEnd_1.next = pre_1.next;
            pre_1.next = preEnd_1;
        }
*/

        graph.verNum = 8;
        System.out.println("顶点数量: " + graph.verNum);
        graph.edgeNum = 10;
        System.out.println("边的数量: " + graph.edgeNum);
        //创建无向图
        String[] vertex = {"v1", "v2", "v3", "v4", "v5", "v6", "v7", "v8"};
        for (int num = 0; num < graph.verNum; num++) {//初始化各个顶点
            Vertices vertices = new Vertices();
            vertices.verName = vertex[num];
            vertices.color = "white";
            vertices.next = null;
            graph.verArray[num] = vertices;
        }

        String[] wideEdge = {"v1-v2", "v1-v4", "v2-v3", "v4-v5", "v4-v8", "v5-v6", "v5-v7", "v5-v8", "v6-v7", "v7-v8"};
        for (int num = 0; num < graph.edgeNum; num++) {

            String[] saveEdge = wideEdge[num].split("-");
            String start = saveEdge[0], end = saveEdge[1];

            Vertices pre_1 = getVertex(graph, start);
            Vertices preEnd_1 = new Vertices();
            preEnd_1.verName = end;
            preEnd_1.next = pre_1.next;
            pre_1.next = preEnd_1;

            //两条边需要相连
            Vertices pre_2 = getVertex(graph, end);
            Vertices preEnd_2 = new Vertices();
            preEnd_2.verName = start;

            preEnd_2.next = pre_2.next;
            pre_2.next = preEnd_2;
        }
    }

    /**
     * 根据用户输入创建一个图(邻接表的形式创建)
     *
     * @param graph 生成的图
     */
    private void initGraph(Graph graph) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入顶点的数量: ");
        graph.verNum = scanner.nextInt();
        System.out.println("请输入边的数量: ");
        graph.edgeNum = scanner.nextInt();

        System.out.println("请依次输入您的顶点的名称: ");
        for (int ver = 0; ver < graph.verNum; ver++) {
            //新建一个顶点
            Vertices vertex = new Vertices();
            vertex.verName = scanner.next();
            vertex.color = "white";//该顶点还未被访问到
            vertex.next = null;//该节点的相邻结点为空

            //将该顶点存放进该图的链表当中
            graph.verArray[ver] = vertex;
        }

        System.out.println("请输入图中的各条相邻的边: ");
        for (int num = 0; num < graph.edgeNum; num++) {
            String start = scanner.next(), end = scanner.next();

            Vertices pre_1 = getVertex(graph, start);

            if (pre_1 == null) System.out.println("输入的入度边不存在! ");

            Vertices end_1 = new Vertices();
            end_1.verName = end;

            end_1.next = pre_1.next;//入度边后面也还有元素

            pre_1.next = end_1;//将该顶点插入其后面入度边的后面

/*在出出渡边中也添加入度边则会形成无向图(不添加的话就只需要添加到一个链表当中)
            Vertices pre_2 = getVertex(graph, end);
            if (pre_2 == null) System.out.println("输入的出度边不存在! ");

            Vertices end_2 = new Vertices();
            end_2.verName = start;

            end_2.next = pre_2.next;

            pre_2.next = end_2;
 */
        }
    }

    /**
     * 输出各个顶点的邻接链表
     *
     * @param graph 待输出的图
     */
    private void printGraph(Graph graph) {
        System.out.println("输出邻接链表: ");
        for (int num = 0; num < graph.verNum; num++) {
            Vertices curVer = graph.verArray[num];
            System.out.print(curVer.verName);

            Vertices neighbor = curVer.next;
            while (neighbor != null) {//若当前结点的邻接点不为空的话
                System.out.print("->" + neighbor.verName);

                neighbor = neighbor.next;//转移到下一个结点
            }
            System.out.println();//输出完一个结点链表后换行
        }
    }

    /**
     * DFS深度递归遍历图
     *
     * @param graph 待搜索的图
     */
    private void dfsRecursion(Graph graph) {
        for (int num = 0; num < graph.verNum; num++) {
            Vertices vertex = graph.verArray[num];

            if (vertex.color.equals("white")) {
                dfsRecur(graph, vertex);
                System.out.println("null");
            }

        }
    }

    /**
     * 递归实现DFS搜索
     *
     * @param graph  待搜索的图
     * @param vertex 顶点
     */
    private void dfsRecur(Graph graph, Vertices vertex) {
        vertex.color = "gray";
        System.out.print(vertex.verName + "->");

        Vertices nextVer = vertex.next;
        while (nextVer != null) {
            Vertices next = getVertex(graph, nextVer.verName);//移动到下一个顶点链表的顶点

            if (next.color.equals("white")) dfsRecur(graph, next);

            nextVer = nextVer.next;
        }
        vertex.color = "black";
    }

    /**
     * 搜索该定顶点的相邻结点
     *
     * @param graph  图
     * @param vertex 顶点
     */
    private Vertices getAdj(Graph graph, Vertices vertex) {
//        Vertices current = getVertex(graph, vertex.verName).next;//搜索该顶点的所在的链表

        if (vertex.next != null) {
            Vertices cur = getVertex(graph, vertex.next.verName);//获取该节点所在的链表
/*

            while (current != null && cur.color.equals("gray")) {
                current = current.next;//搜索下一个结点
            }
*/
            if (cur != null && cur.color.equals("white")) {
                return getVertex(graph, cur.verName);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * 通过栈实现DFS非递归搜索
     *
     * @param graph  待搜索的图
     * @param vertex 顶点
     */
    private void dfsStack(Graph graph, Vertices vertex) {
        vertex.color = "gray";
        stack.push(vertex);//将首元结点压入栈中
        System.out.print(vertex.verName + "->");

        while (!stack.isEmpty()) {
            Vertices adj = getAdj(graph, stack.peek());//获取该节点的相邻结点

            if (adj != null) {//若该节点不为空，则将该节点放入栈中，继续搜索下一个结点(一层一层深入)
                stack.push(adj);//一头扎进去，而是马上遍历
                adj.color = "gray";
                System.out.print(adj.verName + "->");
            } else {
                stack.pop();
            }
        }
    }

    /**
     * DFS非递归调用
     *
     * @param graph 遍历的图
     */
    private void dfsTraverse(Graph graph) {
        for (int num = 0; num < graph.verNum; num++) {
            if (graph.verArray[num].color.equals("white")) {
                dfsStack(graph, graph.verArray[num]);
                System.out.println("null");
            }
        }
    }

    private void bfsQueue(Graph graph) {
        Vertices current = graph.verArray[0];
        current.color = "gray";

        Queue<Vertices> queue = new LinkedList<>();
        queue.add(current);

        while (queue.peek() != null) {
            Vertices peekVer = queue.poll();
            System.out.print(peekVer.verName + "->");

            Vertices nextVer = peekVer.next;
            while (nextVer != null) {
                Vertices curNow = getVertex(graph, nextVer.verName);
                if (curNow.color.equals("white")) {
                    curNow.color = "gray";
                    queue.add(curNow);
                }
                nextVer = nextVer.next;
            }
        }
        System.out.println("null");
    }
}
