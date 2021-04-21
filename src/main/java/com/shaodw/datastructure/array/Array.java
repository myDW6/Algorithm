package com.shaodw.datastructure.array;

public class Array <E>{

    private E[] arr;
    private int size;

    public Array(int capacity){
        arr = (E[])new Object[capacity];
        size = 0;
    }

    public Array(E[] es){
        arr = (E[]) new Object[es.length];
        for (int i = 0; i < es.length; i++) {
            arr[i] = es[i];
        }
        size = arr.length;
    }

    public Array(){
        this(10);//默认10个
    }

    public int capacity(){
        return arr.length;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    //O(1)
    public void addLast(E e){
        add(size, e);
    }

    ///O(n)
    public void addFirst(E e){
        add(0, e);
    }

    //O(n/2) -> O(n)
    public void add(int index, E e){
        if (index < 0 || index > size){
            throw new IllegalArgumentException("index is illegal");
        }
        if (size == arr.length){
            resize(2 * arr.length);
        }
        for (int i = size - 1; i >= index; i--){
            arr[i + 1] = arr[i];
        }
        arr[index] = e;
        size++;
    }

    public E get(int i){
        if (i < 0 || i >= size)
            throw new IllegalArgumentException("index is illegal");
        return this.arr[i];
    }

    public E getLast(){
        return get(size - 1);
    }

    public E getFirst(){
        return get(0);
    }

    public void set(int i, E e){
        if (i < 0 || i >= size)
            throw new IllegalArgumentException("index is illegal");
        this.arr[i] = e;
    }

    public boolean contains(E e){
        for (int i = 0; i < size; i++){
            if (arr[i].equals(e))
                return true;
        }
        return false;
    }

    public int find(E e){
        for (int i = 0; i < size; i++){
            if (arr[i].equals(e)){
                return i;
            }
        }
        return -1;
    }

    public E removeFirst(){
        return remove(0);
    }

    public E removeLast(){
        return remove(size - 1);
    }

    public E remove(int index){
        if (index < 0 || index > size){
            throw new IllegalArgumentException("index is illegal");
        }
        E tmp = arr[index];
        for (int i = index + 1; i < size; i++){
            arr[i - 1] = arr[i];
        }
        size--;
        arr[size] = null;//回收空间
        /**
         * 避免复杂度震荡
         */
        if (size == arr.length / 4 && arr.length / 2 != 0){
            resize(arr.length / 2);
        }
        return tmp;
    }

    public void removeElement(E e){
        int index = find(e);
        if (index != -1){
             remove(index);
        }
    }

    public void removeAllElements(E e){
        while (contains(e)){
            removeElement(e);
        }
    }

    private void resize(int capaicty){
        E[] newArray = (E[])new Object[capaicty];
        for (int i = 0; i < size; i++){
            newArray[i] = arr[i];
        }
        arr = newArray;
    }

    public void swap(int i, int j){
        if (i < 0 || i >= size ||j < 0 || j >= size){
            throw new IllegalArgumentException("Index is illeagl");
        }
        E tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Array: size = %d, capacity = %d\n", size, arr.length));
        builder.append("[");
        for (int i = 0; i < size; i++){
            builder.append(arr[i]);
            if (i != size - 1){
                builder.append(",");
            }
        }
        return builder.append("]").toString();

    }

    public static void main(String[] args) {
        Array<Integer> array = new Array<>();
        for (int i = 0; i < 10; i++){
            array.addLast(i);
        }
        System.out.println(array);
       array.add(1,1);

        System.out.println(array);
        System.out.println(array.remove(1));
        System.out.println(array);
        array.removeFirst();
        array.removeFirst();
        array.removeFirst();
        array.removeFirst();
        System.out.println(array);
    }
}
