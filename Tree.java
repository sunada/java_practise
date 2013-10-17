import java.util.LinkedList;

class Node{
    int data;
    Node lchild;
    Node rchild;

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

    public void build_tree(int min, int max){
        for (int i = min; i < max; i++){ 
            Node node = new Node(i);
            LinkedList<Node> list = new LinkedList<Node>();
            list.add(root);
        
            while (true){
                Node tmp = list.removeFirst();
                if (tmp.lchild == null){
                    tmp.lchild = node;
                    break;
                }else if (tmp.rchild == null){
                    tmp.rchild = node;
                    break;
                }else{
                    list.add(tmp.lchild);
                    list.add(tmp.rchild);
                }
            }
        }
    }

    public void pre_order(Node node){
        if (node != null){
            node.print();
            pre_order(node.lchild);
            pre_order(node.rchild);
        }
    }

    public void pre_order_loop(Node node){
        LinkedList<Node> list = new LinkedList<Node>();
        list.add(node);

        Node tmp = list.removeFirst();
        while (tmp != null){
            tmp.print();
            if (tmp.rchild != null){
                list.addFirst(tmp.rchild);
            }
            if (tmp.lchild != null){
                list.addFirst(tmp.lchild);
            }
            if (list.isEmpty()){
                System.out.println();
                return;
            }
            tmp = list.removeFirst();
        }
    }

    public void mid_order(Node node){
        if (node != null){
            mid_order(node.lchild);
            node.print();
            mid_order(node.rchild);
        }
    }

    public void mid_order_loop(Node node){
        LinkedList<Node> list = new LinkedList<Node>();

        while (true){
            while (node.lchild != null){
                list.addFirst(node);
                node = node.lchild;
            }
            node.print();
            if (list.isEmpty()){
                System.out.println();
                return;
            }
            node = list.removeFirst();
            node.print();
            node = node.rchild;
        }
    }

    public void post_order(Node node){
        if (node != null){
            post_order(node.lchild);
            post_order(node.rchild);
            node.print();
        }
    }

    public void post_order_loop(Node node){
        LinkedList<Node> list = new LinkedList<Node>();
        Node pre = null;
        
        while (true){
            if (node.lchild == null && node.rchild == null){
                node.print();
                pre = node;
                if (list.isEmpty()){
                    System.out.println();
                    return;
                }
                node = list.removeFirst();
            }else if (node.lchild != null && node.rchild != null && pre == node.rchild){
                node.print();
                pre = node;
                if (list.isEmpty()){
                    System.out.println();
                    return;
                }
                node = list.removeFirst();
            }else if (node.lchild != null && node.rchild != null){
                list.addFirst(node);
                list.addFirst(node.rchild);
                list.addFirst(node.lchild);
                node = list.removeFirst();
                pre = null;
            }
        }
    }            

    public static void main(String[] args){
        Tree bitree = new Tree();
        bitree.pre_order(bitree.root);
        System.out.println();

        bitree.build_tree(1, 11);
        System.out.println("pre_order: ");
        bitree.pre_order(bitree.root);
        System.out.println();
        bitree.pre_order_loop(bitree.root);
        System.out.println("mid_order: ");
        bitree.mid_order(bitree.root);
        System.out.println();
        bitree.mid_order_loop(bitree.root);
        System.out.println("post_order: ");
        bitree.post_order(bitree.root);
        System.out.println();
        bitree.post_order_loop(bitree.root);
    }
}

 
