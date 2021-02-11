package com.rmi.learn.rpc;

import com.rmi.learn.zk.ServiceDiscovery;

import java.lang.reflect.Proxy;

/**
 * @Description: 创建代理对象
 * @Author: li cong zhi
 * @CreateDate: 2021/1/16 17:52
 * @UpdateUser: li cong zhi
 * @UpdateDate: 2021/1/16 17:52
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public class RpcClientProxy {

    private ServiceDiscovery serviceDiscovery;


    public RpcClientProxy(ServiceDiscovery serviceDiscovery) {
        this.serviceDiscovery = serviceDiscovery;
    }

    /*public <T> T clientProxy(final Class<T> interfaceCls,
                             final String host, final int port) {
        // 使用动态代理
        return (T) Proxy.newProxyInstance(interfaceCls.getClassLoader(), new Class[]{
                interfaceCls}, new RemoteInvokeHandler(host, port));
    }*/

    public <T> T clientProxy(final Class<T> interfaceCls, String version) {
        // 使用动态代理
        return (T) Proxy.newProxyInstance(interfaceCls.getClassLoader(), new Class[]{
                interfaceCls}, new RemoteInvokeHandler(serviceDiscovery, version));
    }
}
