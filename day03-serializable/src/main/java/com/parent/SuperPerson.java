package com.parent;

import java.io.Serializable;

public class SuperPerson implements Serializable {

    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "SuperPerson{" +
                "age=" + age +
                '}';
    }
}
