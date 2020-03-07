package com;

import com.domain.Person;

import java.io.*;

/**
 * 分布式通信 —序列化
 */
public class SerializeDemo {
    public static void main(String[] args) {
        // 序列化操作
        serializePerson();
        Person.height = 5;
        // 反序列化
        Person person = deSerializePerson();
        int height = person.height;
        System.out.println(person);
    }

    private static void serializePerson() {

        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(new File("person")));
            Person person = new Person();
            person.setAge(18);
            person.setName("李四");
            outputStream.writeObject(person);
            outputStream.flush();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("person")));
            Person person1 = (Person) ois.readObject();
            outputStream.writeObject(person);
            outputStream.flush();
            System.out.println("序列化成功" + new File("person").length());

            Person person2 = (Person) ois.readObject();
            System.out.println(person1.equals(person2));
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Person deSerializePerson() {

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("person")));
            Person person = (Person) ois.readObject();

            System.out.println(person);
            return person;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
