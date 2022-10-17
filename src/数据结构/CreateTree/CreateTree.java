package 数据结构.CreateTree;

public class CreateTree {
    //创建结点的数据域
    private Object data;
    //创建左右结点
    private CreateTree lChild, rChild;

    public CreateTree(Object data, CreateTree lChild, CreateTree rChild) {
        this.data = data;
        this.lChild = lChild;
        this.rChild = rChild;
    }

    //构造一个左孩子、右孩子为空的结点
    public CreateTree(Object data) {
        this(data, null, null);
    }

    //构建一个空结点
    public CreateTree() {
        this(null);
    }

    //获取结点
    public Object getData() {
        return data;
    }

    public CreateTree getlChild() {
        return lChild;
    }

    public CreateTree getrChild() {
        return rChild;
    }

    //传值
    public void setData() {
        this.data = data;
    }

    public void setlChild(CreateTree lChild) {
        this.lChild = lChild;
    }

    public void setrChild(CreateTree rChild) {
        this.rChild = rChild;
    }
}

class Traverse {
    //获取根节点
    private CreateTree root;

    //构造一棵空树
    public Traverse() {
        this.root = root;
    }

    //构造一棵树
    public Traverse(CreateTree root) {
        this.root = root;
    }

    //先根遍历(中-左-右)
    public void preTraverse(CreateTree root) {
        if (root != null) {
            System.out.print(root.getData() + " - ");
            //递归遍历
            preTraverse(root.getlChild());
            preTraverse(root.getrChild());
        }
    }

    //后根遍历(左-右-中)
    public void postTraverse(CreateTree root) {
        if (root != null) {
            postTraverse(root.getlChild());
            System.out.print(root.getData() + " - ");
            postTraverse(root.getrChild());
        }
    }

    //创建树
    public Traverse createTree() {
        CreateTree k = new CreateTree('k', null, null);
        CreateTree f = new CreateTree('f', k, null);
        CreateTree e = new CreateTree('e', null, f);
        CreateTree g = new CreateTree('g', null, null);
        CreateTree l = new CreateTree('l', null, null);
        CreateTree j = new CreateTree('j', null, null);
        CreateTree i = new CreateTree('i', l, j);
        CreateTree h = new CreateTree('h', null, i);
        CreateTree d = new CreateTree('d', h, null);
        CreateTree c = new CreateTree('c', g, d);
        CreateTree b = new CreateTree('b', e, c);
        CreateTree a = new CreateTree('a', b, null);
        //创建根节点为a的树
        return new Traverse(a);
    }

    public static void main(String[] args) {
        Traverse traverse = new Traverse();
        Traverse tree = traverse.createTree();
        //获得树的根结点
        CreateTree root = tree.root;
        System.out.println("树的先序遍历");
        tree.preTraverse(root);
        System.out.println();
        System.out.println("树的后序遍历");
        tree.postTraverse(root);
    }

}

