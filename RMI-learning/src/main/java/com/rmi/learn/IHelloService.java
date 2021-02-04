package com.rmi.learn;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IHelloService extends Remote {

    String sayHello(String msg) throws RemoteException;
}
