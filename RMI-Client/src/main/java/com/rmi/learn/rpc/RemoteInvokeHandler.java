package com.rmi.learn.rpc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class RemoteInvokeHandler implements InvocationHandler {
    private String host;

    private int port;


    public RemoteInvokeHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /**
     * 增强的InvocationHandler,接口调用方法的时候实际是调用socket进行传输
     */

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 将远程调用需要的接口类、方法名、参数信息封装成RPCRequest
        RpcRequest request = new RpcRequest();
        request.setClassName(method.getDeclaringClass().getName());
        request.setMethodName(method.getName());
        request.setParameters(args);

        // 通过socket发送RPCRequest给服务端并获取结果返回 00:42


        TcpTransport tcpTransport = new TcpTransport(this.host, this.port);

        return tcpTransport.send(request);
    }
}
