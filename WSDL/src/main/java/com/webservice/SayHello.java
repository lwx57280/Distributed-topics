package com.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * SE和SEI的实现类
 */
@WebService
public interface SayHello {

    @WebMethod
    String sayHello(String name);
}
