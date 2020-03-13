package com.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * 分布式通信框架RMI
 *
 * 实现一个RMI程序
 */
public class SayHelloImpl extends UnicastRemoteObject implements ISayHello {


    public SayHelloImpl() throws RemoteException {
    }

    public String sayHello(String name) throws RemoteException {
        return "Hello Mic->"+name;
    }
}
