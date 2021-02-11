package com.rmi.learn;

import com.rmi.learn.rpc.IUserService;
import com.rmi.learn.rpc.RpcClientProxy;
import com.rmi.learn.zk.ServiceDiscovery;
import com.rmi.learn.zk.ServiceDiscoveryImpl;
import com.rmi.learn.zk.ZkConfig;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ClientDemo {

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException, InterruptedException {
        // HelloServiceImpl实例（HelloServiceImpl_stub）代理对象
//        IHelloService helloService =(IHelloService) Naming.lookup("rmi://127.0.0.1/Hello");
//        System.out.println(helloService.sayHello("lisa"));
        ServiceDiscovery serviceDiscovery = new ServiceDiscoveryImpl(ZkConfig.CONNECTION_STR);
        RpcClientProxy proxy = new RpcClientProxy(serviceDiscovery);
        for (int i = 0; i < 10; i++) {
            IUserService service = proxy.clientProxy(IUserService.class,null);
            System.out.println(service.sayHello("lisa"));
            Thread.sleep(1000);
        }

    }
}
