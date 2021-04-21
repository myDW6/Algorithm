package com.shaodw.practice.subarray;



import java.util.*;

/**
 * @Auther: shaodw
 * @Date: 2020/2/29 10:09
 * @Description:
 */
public class Test {
    private static class Node{
        Node left;
        Node right;
        int val;

        Node(int val){
            this.val = val;
        }
    }


    public static void getPreTreal(Node head, List<Integer> list){
        if (head == null){
            return;
        }
        list.add(head.val);
        getPreTreal(head.left, list);
        getPreTreal(head.right, list);
    }

    public static int getSum(Node head, List<Integer> list){
        List<Integer> res = new ArrayList<>();
        getPreTreal(head, res);
        int sum = 0;
        for (int i : list) {
            sum += res.get(i);
        }
        return sum;
    }

    public static Node constructTreeByLevel(String serial){
        if (serial == null)
            return null;
        String[] str = serial.split(",");
        int s = str.length;
        int height = 1;
        while (s > 0){
            height++;
            s/= 2;
        }
        String[] newStr = new String[2 << (height + 1)];
        for (int i = 0; i < str.length; i++) {
            newStr[i] = str[i];
        }
        for (int i = str.length; i < newStr.length; i++){
            newStr[i] = "null";
        }

        int index = 0;
        Node root = generateNodeByString(newStr[index++]);
        Queue<Node> queue = new LinkedList<>();
        if (root != null){
            queue.offer(root);
        }
        Node node;
        while (!queue.isEmpty()){
            node = queue.poll();
            node.left = generateNodeByString(newStr[index++]);
            node.right = generateNodeByString(newStr[index++]);
            if (node.left != null){
                queue.offer(node.left);
            }
            if (node.right != null){
                queue.offer(node.right);
            }
        }
        return root;
    }
    private static Node generateNodeByString(String s){
        return s.equals("null") ? null : new Node(Integer.valueOf(s));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String next1 = scanner.nextLine();
        String next2 = scanner.nextLine();
        Node node = constructTreeByLevel(next1);
        List<String> list = Arrays.asList(next2.split(","));
        List<Integer> res = new ArrayList<>();
        for (String s : list){
            res.add(Integer.parseInt(s));
        }
        int sum = getSum(node, res);
        System.out.println(sum);
    }

}
