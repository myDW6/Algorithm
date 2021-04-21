package com.shaodw.datastructure.linkedlist;

public class Main {
    public static void main(String[] args) {
        DummyHeadLinkedList<Integer> linkedList = new DummyHeadLinkedList<>();
        for (int i = 0; i < 5; i++){
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }
        linkedList.add(2,666);
        System.out.println(linkedList);
        System.out.println(linkedList.contains(5));
        System.out.println(linkedList.getSize());
        System.out.println(linkedList.get(3));
        System.out.println(linkedList.remove(2));
        System.out.println(linkedList);
        linkedList.removeFirst();
        System.out.println(linkedList);
        linkedList.removeLast();
        System.out.println(linkedList);
    }
}
