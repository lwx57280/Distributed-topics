package com.rmi.learn.rpc;

import com.rmi.learn.anno.RpcAnnotation;

@RpcAnnotation(value = IUserService.class,version = "2.0")
public class UserServiceImpl2 implements IUserService {
    @Override
    public String sayHello(String msg) {
        return "[I'M 8081] node : "+msg;
    }
}
