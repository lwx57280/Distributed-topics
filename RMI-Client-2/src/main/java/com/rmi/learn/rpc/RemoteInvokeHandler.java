package com.rmi.learn.rpc;

import com.rmi.learn.zk.ServiceDiscovery;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class RemoteInvokeHandler implements InvocationHandler {

    private ServiceDiscovery serviceDiscovery;

    private String version;

    public RemoteInvokeHandler(ServiceDiscovery serviceDiscovery, String version) {
        this.serviceDiscovery = serviceDiscovery;
        this.version = version;
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
        request.setVersion(version);

        // 通过socket发送RPCRequest给服务端并获取结果返回 00:42
        // 根据接口名称得到对应的服务地址
        String serviceAddress = serviceDiscovery.discovery(request.getClassName());

        TcpTransport tcpTransport = new TcpTransport(serviceAddress);

        return tcpTransport.send(request);
    }
}
