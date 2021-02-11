package com.rmi.learn.rpc;

import com.rmi.learn.anno.RpcAnnotation;

@RpcAnnotation(value = IUserService.class,version = "1.0")
public class UserServiceImpl implements IUserService {
    @Override
    public String sayHello(String msg) {
        return "[I'M 8080] node : "+msg;
    }
}
