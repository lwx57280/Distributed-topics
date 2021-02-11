package com.rmi.learn.rpc;

import com.rmi.learn.zk.RegisterCenter;
import com.rmi.learn.zk.RegisterCenterImpl;

import java.io.IOException;

public class ServiceDemo2 {

    public static void main(String[] args) throws IOException {

        RegisterCenter register = new RegisterCenterImpl();
        // 初始化注册中心和服务端口信息
        RpcService service = new RpcService(register,   "127.0.0.1:8081");
        // 绑定服务
        service.bind(new UserServiceImpl2());
        // 发布并注册服务
        service.publisher();
        System.in.read();
    }
}
