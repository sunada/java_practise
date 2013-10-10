class Node{
    int data;
    Node lchild;
    Node rchild;

    public Node(){
        this.data = -1;
        lchild = null;
        rchild = null;
    }

    public Node(int data){
        this.data = data;
        lchild = null;
        rchild = null;
    }

    public void set(int data){
        this.data = data;
    }

    public void print(){
        System.out.print(data);
        System.out.print(" ");
    }

}

public class Tree{
    Node root;

    public Tree(){
        root = new Node(0);
    }
    
    public Node find_last(Node node){
        if (node == null){
            node = new Node();
            return node;
        }else if (node.lchild == null){
            node.lchild = new Node();
            return node.lchild;
        }else if (node.rchild == null){
            node.rchild = new Node();
            return node.rchild;
        }else{
            node = node.lchild;
            return find_last(node);
        }
    }

    public void build_tree(int cnt){
        for (int i = 1; i <= cnt; i++){
            //Node node = new Node(i);
            Node last = find_last(root);
            last.set(i);
        }
        return;
    }

    public void pre_order(Node node){
        if (node != null){
            node.print();
            pre_order(node.lchild);
            pre_order(node.rchild);
        }
    }

    public void mid_order(Node node){
        if (node != null){
            mid_order(node.lchild);
            node.print();
            mid_order(node.rchild);
        }
    }

    public void post_order(Node node){
        if (node != null){
            post_order(node.lchild);
            post_order(node.rchild);
            node.print();
        }
    }

    public static void main(String[] args){
        Tree bitree = new Tree();
        bitree.pre_order(bitree.root);
        System.out.println();

        bitree.build_tree(10);
        System.out.println("pre_order: ");
        bitree.pre_order(bitree.root);
        System.out.println();
        System.out.println("mid_order: ");
        bitree.mid_order(bitree.root);
        System.out.println();
        System.out.println("post_order: ");
        bitree.post_order(bitree.root);
        System.out.println();
    }
}

 
