package com.webservice;

import javax.jws.WebService;

@WebService
public class SayHelloImpl implements SayHello{


    @Override
    public String sayHello(String name) {
        System.out.println("call sayHello");
        return "Hello,"+name+"I'am 菲菲";
    }
}
