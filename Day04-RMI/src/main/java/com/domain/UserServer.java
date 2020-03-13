package com.domain;

public class UserServer extends User {

    public static void main(String[] args) {
        UserServer userServer = new UserServer();
        userServer.setAge(18);

        User_Skeleton user_skeleton = new User_Skeleton(userServer);
        user_skeleton.start();
    }
}
