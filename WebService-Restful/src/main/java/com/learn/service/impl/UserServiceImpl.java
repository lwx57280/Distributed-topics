package com.learn.service.impl;

import com.learn.Response;
import com.learn.service.UserService;
import com.learn.Storage;
import com.learn.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public List<User> getUsers() {
        return Storage.userList;
    }

    @Override
    public Response delete(int id) {
        Storage.userList.remove(id);
        Response body = new Response();
        body.setCode("100");
        body.setMsg("success");
        return body;
    }

    @Override
    public Response insert(User user) {

        return null;
    }

    @Override
    public Response updateUser(User user) {
        return null;
    }

    @Override
    public User getUser(int id) {
        return Storage.userList.get(id);
    }

}
