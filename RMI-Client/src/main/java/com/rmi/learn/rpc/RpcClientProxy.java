package com.rmi.learn.rpc;

import java.lang.reflect.Proxy;

/**
 * @Description:    创建代理对象
 * @Author:         li cong zhi
 * @CreateDate:     2021/1/16 17:52
 * @UpdateUser:     li cong zhi
 * @UpdateDate:     2021/1/16 17:52
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
public class RpcClientProxy {

    public <T> T clientProxy(final Class<T> interfaceCls,
                             final String host, final int port) {
        return (T) Proxy.newProxyInstance(interfaceCls.getClassLoader(), new Class[]{
                interfaceCls}, new RemoteInvokeHandler(host, port));
    }
}
