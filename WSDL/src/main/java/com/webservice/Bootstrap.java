package com.webservice;

import javax.xml.ws.Endpoint;

/**
 * WebService服务端发布
 */
public class Bootstrap {

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8888/vip/hello", new SayHelloImpl());
        System.out.println("publish success");
    }
}
