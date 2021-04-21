package com.shaodw.datastructure.hash;

public class Stu {
    int grade;
    int cls;
    String firstName;
    String lastName;

    Stu(int grade, int cls, String firstName, String lastName){
        this.grade = grade;
        this.cls = cls;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    //Object的hashcode()将对象的地址映射成整型
    //覆盖hashcode方法 用于产生哈希函数的值
    @Override
    public int hashCode(){
        int B = 31;//随便指定
        int hash = 0;
        hash = hash * B + grade;
        hash = hash * B + cls;
        hash = hash * B + firstName.toLowerCase().hashCode();
        hash = hash * B + lastName.toLowerCase().hashCode();
        return hash;
    }

    //覆盖equals方法 当产生哈希冲突时需要比较两个对象是否真正相等
    @Override
    public boolean equals(Object o){
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (getClass() != o.getClass())
            return false;
        Stu another = (Stu)o;
        return this.grade == another.grade &&
                this.cls == another.cls &&
                this.firstName.toLowerCase().equals(another.firstName.toLowerCase()) &&
                this.lastName.toLowerCase().equals(another.lastName.toLowerCase());
    }
    /**
     * 只有重写了hashcode()和equals()后 将类作为哈希表的键才算真正放心 即使产生hash冲突 依然可以使用equals方法区分类对象的不同
     */
}
