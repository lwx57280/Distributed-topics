package com.parent;

import java.io.*;

public class SuperDemo {

    public static void main(String[] args) {

        serializePerson();

        User user = deSerializePerson();
        System.out.println(user);

    }

    private static void serializePerson() {

        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(new File("user")));
            User user = new User();
            user.setAge(18);
            outputStream.writeObject(user);

            System.out.println("序列化成功");
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static User deSerializePerson() {

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("user")));
            User user = (User) ois.readObject();
            return user;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
