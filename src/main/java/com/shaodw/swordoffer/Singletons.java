package com.shaodw.swordoffer;

import java.lang.reflect.Constructor;

/**
 * @Auther: shaodw
 * @Date: 2020-01-15 12:12
 * @Description: 实现单例模式
 *  1.由于单例模式只生成一个实例，减少了系统性能开销，当一个对象的产生需要比较多的资源时，如读取配置、产生其他依赖对象时，
 *  则可以通过在应用启动时直接产生一个单例对象，然后永久驻留内存的方式来解决
 *  2.单例模式可以在系统设置全局的访问点，优化环共享资源访问，例如可以设计一个单例类，负责所有数据表的映射处理。
 */

public class Singletons {

}

/**
 *  懒汉式单例
 */
//使用于单线程环境 不推荐
class Singleton1{
    private static Singleton1 instance = null;
    private Singleton1(){
    }

    public static Singleton1 getInstance(){
        if (instance == null){
            instance = new Singleton1();
        }
        return instance;
    }
}



//适用于多线程环境 但效率低
class Singleton2{
    private static Singleton2 instance = null;
    private Singleton2(){}

    public synchronized static Singleton2 getInstance(){
        if (instance == null){
            instance = new Singleton2();
        }
        return instance;
    }
}



//双重检验锁 Double check lock
class Singleton3{
    private static volatile Singleton3 instance = null;//需要volatile关键字
    private Singleton3(){}

    public static Singleton3 getInstance(){
        if (instance == null){
            synchronized (Singleton3.class){
                if (instance == null){
                    instance = new Singleton3();
                }
            }
        }
        return instance;
    }
}

//静态内部类 推荐
//加载一个类时，其内部类不会同时被加载。一个类被加载，当且仅当其某个静态成员（静态域、构造器、静态方法等）被调用时发生。
// 由于在调用 StaticSingleton.getInstance() 的时候，才会对单例进行初始化，而且通过反射，是不能从外部类获取内部类的属性的；
// 由于静态内部类的特性，只有在其被第一次引用的时候才会被加载，所以可以保证其线程安全性。
//优势：兼顾了懒汉模式的内存优化（使用时才初始化）以及饿汉模式的安全性（不会被反射入侵）。
//劣势：需要两个类去做到这一点，虽然不会创建静态内部类的对象，但是其 Class 对象还是会被创建，而且是属于永久带的对象。
class Singleton4{
    private Singleton4(){}

    public static Singleton4 getInstance(){
        return Holder.instance;
    }

    //一个私有的静态内部类 用于初始化一个静态final实例
    private static class Holder{
        private static final Singleton4 instance = new Singleton4();
    }
}

/**
 * 饿汉式单例:在类初始化时，已经自行实例化。
 */
class Singleton5{
    private static Singleton5 instance = new Singleton5();

    private Singleton5(){}

    public static Singleton5 getInstance(){
        return instance;
    }
}

//枚举 推荐
enum  Singleton6{
    INSTANCE;
    // 枚举元素，本身就是单例对象，但是没有延时加载。定义一个枚举的元素，它就代表了Singleton的一个实例

    //单例可以有自己的操作
    public void singletonOperation(){}

}
