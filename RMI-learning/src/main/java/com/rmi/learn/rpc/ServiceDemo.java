package com.rmi.learn.rpc;

public class ServiceDemo {

    public static void main(String[] args) {

        IUserService userService = new UserServiceImpl();

        RpcService service = new RpcService();
        service.publisher(userService, 8888);
    }
}
