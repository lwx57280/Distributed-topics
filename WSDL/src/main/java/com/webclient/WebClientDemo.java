package com.webclient;



import webclient.SayHelloImpl;
import webclient.SayHelloImplService;

/**
 * webService客户端调用
 */
public class WebClientDemo {

    public static void main(String[] args) {
        SayHelloImplService service = new SayHelloImplService();
        SayHelloImpl sayHello = service.getSayHelloImplPort();
        System.out.println(sayHello.sayHello("lisa "));


    }
}
