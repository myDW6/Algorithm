package com.shaodw.practice.unionfind;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * @Auther: shaodw
 * @Date: 2020/2/4 21:05
 * @Description: 并查集实现
 */
public class UnionFindSet {
    private class Node{
        //what ever you do
    }
    private HashMap<Node, Node> fatherMap;
    private HashMap<Node, Integer> sizeMap;

    UnionFindSet(){
        fatherMap = new HashMap<>();
        sizeMap = new HashMap<>();
    }

    //需要元素事先给定 不接受数据流
    private void makeSets(List<Node> lists){
        fatherMap.clear();
        sizeMap.clear();
        for (Node node : lists){
            fatherMap.put(node, node);
            sizeMap.put(node,1);
        }
    }

    //从当前节点出发 找到该集合的代表节点 并在找到后将该节点起直到代表节点之间的所有节点
    //直接连接到代表节点上 这是一步优化
    private Node findHead(Node node){
        Node father = fatherMap.get(node);
        if (node != father){
            father = findHead(father);//father 等于我father的findHead过程
        }
        fatherMap.put(node, father);
        return father;

        /**
         * 递归过程 当node == father只会 father为代表节点 返回 那么fatherMap.put(node, father)
         * 的意思即是最上面的father也就是代表节点被写成每个节点的father
         */
    }

    //上述方法的非递归实现
    private Node findHeadUnRecur(Node node){
        Node father = fatherMap.get(node);
        Stack<Node> stack = new Stack<>();
        while (father != node){
            stack.push(node);
            node = father;
            father = fatherMap.get(node);
        }
        while (!stack.isEmpty()){
            fatherMap.put(stack.pop(), father);
        }
        return father;
    }

    public boolean isSameSet(Node a, Node b){
        return findHead(a) == findHead(b);
    }

    public void union(Node a, Node b){
        if (a == null || b == null)
            return;
        Node aHead = findHead(a);
        Node bHead = findHead(b);
        if (aHead != bHead){
            int aSetSize = sizeMap.get(aHead);
            int bSetSize = sizeMap.get(bHead);
            if (aSetSize >= bSetSize){
                fatherMap.put(bHead, aHead);
                sizeMap.put(aHead, aSetSize + bSetSize);
            }else {
                fatherMap.put(aHead, bHead);
                sizeMap.put(bHead, aSetSize + bSetSize);
            }
        }
    }

}
