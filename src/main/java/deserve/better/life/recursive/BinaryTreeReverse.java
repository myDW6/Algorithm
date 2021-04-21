package deserve.better.life.recursive;

/**
 * @author shaodw
 * @date 2021/1/12 13:59
 * @description
 */
public class BinaryTreeReverse {


    static void reverseTree(Node root){
        if (root == null){
            return;
        }
        Node tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        reverseTree(root.left);
        reverseTree(root.right);
    }

    static void reverseTreePost(Node root){
        if (root == null){
            return;
        }
        reverseTree(root.left);
        reverseTree(root.right);
        Node tmp = root.left;
        root.left = root.right;
        root.right = tmp;
    }


    static class Node{
        int val;
        Node left;
        Node right;
        Node(int val){
            this.val = val;
        }
    }

    public static void main(String[] args){
        Node root = new Node(1);
        root.left = new Node(2);
        root.left.left = new Node(3);
        root.left.right = new Node(4);
        root.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        printTree(root);
        reverseTree(root);
        System.out.println("reverse----->>>>");
        printTree(root);
        System.out.println("reverse----->>>>");
        reverseTreePost(root);
        printTree(root);
    }


    static void printTree(Node root){
        if (root == null){
            return;
        }
        System.out.println(root.val);
        printTree(root.left);
        printTree(root.right);
    }
}
