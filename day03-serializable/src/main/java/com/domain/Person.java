package com.domain;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import java.io.Serializable;

public class Person implements Serializable {


    private static final long serialVersionUID = 7944415800882856195L;

    @Protobuf(fieldType = FieldType.STRING,order = 1)
    private String name;
    // 序列化并不保存静态变量的状态
    public static int height = 2;
    @Protobuf(fieldType = FieldType.INT32,order = 2)
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static int getHeight() {
        return height;
    }

    public static void setHeight(int height) {
        Person.height = height;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age + ",height=" + height +
                '}';
    }
}
