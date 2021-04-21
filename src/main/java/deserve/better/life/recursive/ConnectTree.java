package deserve.better.life.recursive;


/**
 * @author shaodw
 * @date 2021/1/12 14:15
 * @description
 */
public class ConnectTree {

    static class Node{
        int val;
        Node left;
        Node right;
        Node next; //相邻右节点
    }

    static Node connect(Node root){
        if (root == null){
            return null;
        }
        connectTwoNode(root.left, root.right);
        return root;
    }

    static void connectTwoNode(Node node1, Node node2){
        if (node1 == null || node2 == null){
            return;
        }
        node1.next = node2;
        connectTwoNode(node1.left, node1.right);
        connectTwoNode(node2.left, node2.right);
        connectTwoNode(node1.right, node2.left);
    }
}
