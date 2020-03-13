package com.rmi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * 服务端
 */
public class HelloServer {

    public static void main(String[] args) {
        try {
            ISayHello hello = new SayHelloImpl();
            // createRegistry方法注册远程对象
            LocateRegistry.createRegistry(8888);
            Naming.bind("rmi://localhost:8888/sayHello",hello);
            System.out.println("server start success");
        } catch (RemoteException e) {
            e.printStackTrace();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}
