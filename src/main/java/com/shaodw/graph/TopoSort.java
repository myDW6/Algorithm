package com.shaodw.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Auther: shaodw
 * @Date: 2019-12-12 12:12
 * @Description:拓扑排序
 */
public class TopoSort {
    static class Node
    {
        int value;
        List<Integer> next;
        public Node(int value) {
            this.value=value;
            next=new ArrayList<Integer>();
        }
        public void setnext(List<Integer> list) {
            this.next=list;
        }
    }

    public static void main(String[] args) {
        Node[] nodes=new Node[9];//储存节点
        int a[]=new int[9];//储存入度
        List<Integer>[] list=new ArrayList[10];//临时空间，为了存储指向的集合
        for(int i=1;i<9;i++)
        {
            nodes[i]=new Node(i);
            list[i]=new ArrayList<Integer>();
        }
        initmap(nodes,list,a);

        //主要流程
        //Queue<TreeNode>q1=new ArrayDeque<TreeNode>();
        Stack<Node> s1=new Stack<Node>();
        for(int i=1;i<9;i++)
        {
            //System.out.print(nodes[i].next.size()+" 55 ");
            //System.out.println(a[i]);
            if(a[i]==0) {s1.add(nodes[i]);}

        }
        while(!s1.isEmpty())
        {
            Node n1=s1.pop();//抛出输出

            System.out.print(n1.value+" ");

            List<Integer>next=n1.next;
            for(int i=0;i<next.size();i++)
            {
                a[next.get(i)]--;//入度减一
                if(a[next.get(i)]==0)//如果入度为0
                {
                    s1.add(nodes[next.get(i)]);
                }
            }
        }
    }

    private static void initmap(Node[] nodes, List<Integer>[] list, int[] a) {
        list[1].add(3);
        nodes[1].setnext(list[1]);
        a[3]++;
        list[2].add(4);list[2].add(6);
        nodes[2].setnext(list[2]);
        a[4]++;a[6]++;
        list[3].add(5);
        nodes[3].setnext(list[3]);
        a[5]++;
        list[4].add(5);list[4].add(6);
        nodes[4].setnext(list[4]);
        a[5]++;a[6]++;
        list[5].add(7);
        nodes[5].setnext(list[5]);
        a[7]++;
        list[6].add(8);
        nodes[6].setnext(list[6]);
        a[8]++;
        list[7].add(8);
        nodes[7].setnext(list[7]);
        a[8]++;

    }
}
