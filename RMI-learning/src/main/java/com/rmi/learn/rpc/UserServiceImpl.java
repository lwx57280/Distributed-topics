package com.rmi.learn.rpc;

public class UserServiceImpl implements IUserService {
    @Override
    public String sayHello(String msg) {
        return "Hello ,"+msg;
    }
}
