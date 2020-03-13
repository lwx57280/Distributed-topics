package com.domain;

import java.io.IOException;

public class UserClient  {
    public static void main(String[] args) throws IOException {
        User userStub = new User_Stub();
        int age = userStub.getAge();
        System.out.println("age=:"+age);
    }
}
