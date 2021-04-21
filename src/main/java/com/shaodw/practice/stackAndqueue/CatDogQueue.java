package com.shaodw.practice.stackAndqueue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Auther: shaodw
 * @Date: 2020/1/26 17:32
 * @Description:
 */

class Pet{
    private String type;

    Pet(String type){
        this.type = type;
    }

    public String getType(){
        return this.type;
    }
}

class Cat extends Pet{
    Cat(){
        super("cat");
    }
}

class Dog extends Pet{
    Dog(){
        super("dog");
    }
}

/**
 * 在已有上述宠物类 猫类 狗类的前提下 设计一种结构 实现如下需求：
 * add     将宠物添加进结构
 * pollDog 取出最先进的狗
 * pollCat 取出最先进的猫
 * pollAll 取出最先进的宠物
 * isEmpty 结构中是否还有宠物
 */
public class CatDogQueue {
    //思路:设计一个PetQEnterQueue的类 里面有一个宠物和计数器  设计两个队列 CatQueue DogQueue 分别将对应的宠物封装成宠物进队列类 扔进相应队列
    class PetEnterQueue{
        private Pet pet;
        private long count;

        PetEnterQueue(Pet pet, long count){
            this.pet = pet;
            this.count = count;
        }
    }

    private Queue<PetEnterQueue> catQueue;
    private Queue<PetEnterQueue> dogQueue;
    private long count;

    CatDogQueue(){
        catQueue = new LinkedList<>();
        dogQueue = new LinkedList<>();
        this.count = 0;
    }

    public void add(Pet pet){
        if (pet.getType().equals("cat")){
            catQueue.add(new PetEnterQueue(pet, this.count++));
        }else if (pet.getType().equals("dog")){
            dogQueue.add(new PetEnterQueue(pet, this.count++));
        }else {
            throw new RuntimeException("err, not dog or cat");
        }
    }

    public Cat pollCat(){
        if (catQueue.isEmpty()){
            throw new RuntimeException("not have cat here");
        }
        return (Cat) catQueue.poll().pet;
    }

    public Dog pollDog(){
        if (dogQueue.isEmpty()){
            throw new RuntimeException("not have dog here");
        }
        return (Dog) dogQueue.poll().pet;
    }

    public Pet pollAll(){
        if (dogQueue.isEmpty() && catQueue.isEmpty()){
            throw new RuntimeException("there is not any animal");
        }else if (dogQueue.isEmpty()){
            return catQueue.poll().pet;
        }else if (catQueue.isEmpty()){
            return dogQueue.poll().pet;
        }else if (catQueue.peek().count > dogQueue.peek().count){
            return dogQueue.poll().pet;
        }else return catQueue.poll().pet;
    }

    public boolean isEmpty(){
        return this.catQueue.isEmpty() && this.dogQueue.isEmpty();
    }

}

class Test{
    public static void main(String[] args) {
        CatDogQueue queue = new CatDogQueue();
        Cat cat1 = new Cat();
        Cat cat2 = new Cat();
        Cat cat3 = new Cat();
        Cat cat4 = new Cat();

        Dog dog1 = new Dog();
        Dog dog2 = new Dog();
        Dog dog3 = new Dog();
        Dog dog4 = new Dog();

        queue.add(cat1);
        queue.add(cat2);
        queue.add(dog1);
        queue.add(dog2);
        queue.add(dog3);
        queue.add(cat3);
        queue.add(dog4);

        while (!queue.isEmpty()){
            System.out.println(queue.pollAll().getType());
        }
    }
}
