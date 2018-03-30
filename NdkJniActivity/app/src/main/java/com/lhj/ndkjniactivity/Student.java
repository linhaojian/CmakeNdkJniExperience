package com.lhj.ndkjniactivity;

/**
 * Created by Administrator on 2018/3/30.
 */

public class Student {
    private int age;
    private String sex;

    public Student() {
    }

    public Student(int age) {
        this.age = age;
    }

    public Student(String sex) {
        this.sex = sex;
    }

    public Student(int age, String sex) {
        this.age = age;
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }
}
