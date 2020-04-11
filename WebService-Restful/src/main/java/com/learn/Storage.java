package com.learn;

import com.learn.domain.User;

import java.util.Arrays;
import java.util.List;

public class Storage {

    public static List<User> userList = Arrays.asList(
            new User(1, "james"),
            new User(2, "mic"),
            new User(3, "sam")
    );


}
