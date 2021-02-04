package com.rmi.learn;

import com.rmi.learn.rpc.IUserService;
import com.rmi.learn.rpc.RpcClientProxy;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ClientDemo {

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        // HelloServiceImpl实例（HelloServiceImpl_stub）代理对象
//        IHelloService helloService =(IHelloService) Naming.lookup("rmi://127.0.0.1/Hello");
//        System.out.println(helloService.sayHello("lisa"));

        RpcClientProxy proxy = new RpcClientProxy();
        IUserService service = proxy.clientProxy(IUserService.class, "localhost", 8888);
        System.out.println(service.sayHello("lisa"));
    }
}
