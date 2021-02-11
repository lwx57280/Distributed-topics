package com.rmi.learn.rpc;

import com.google.common.collect.Maps;
import com.rmi.learn.anno.RpcAnnotation;
import com.rmi.learn.zk.RegisterCenter;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 远程调用的服务端入口，使用socket监听
 *
 * @Author: cong zhi
 * @CreateDate: 2021/1/17 11:29
 * @UpdateUser: cong zhi
 * @UpdateDate: 2021/1/17 11:29
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public class RpcService {
    /**
     * 定义个线程池
     */
    private static final ExecutorService executorService = Executors.newCachedThreadPool();

    /**
     * 注册中心
     */
    private RegisterCenter registerCenter;
    /**
     * 服务发布地址
     */
    private String serviceAddress;

    /**
     * 存放服务名称和对象之间的关系
     */
    Map<String, Object> handleMap = Maps.newHashMap();

    public RpcService(RegisterCenter registerCenter, String serviceAddress) {
        this.registerCenter = registerCenter;
        this.serviceAddress = serviceAddress;
    }

    /**
     * 绑定服务名称和服务对象
     *
     * @return * @return : null
     * @throws
     * @author cong zhi
     * @params * @param null
     * @date 2021/2/7 10:45
     */
    public void bind(Object... services) {
        for (Object service : services) {
            RpcAnnotation annotation = service.getClass().getAnnotation(RpcAnnotation.class);
            String serviceName = annotation.value().getName();
            String version = annotation.version();
            if (null != version && !version.equals("")) {
                serviceName = serviceName + "-" + version;
            }
            // 绑定接口名称对应的服务名
            handleMap.put(serviceName, service);
        }
    }

    /**
     * 注册服务实例，服务注册后，其他客户端通过接口调用就可以调用服务端的实现
     *
     * @return
     * @throws
     * @author cong zhi
     * @date 2021/1/17 12:14
     */
    public void publisher() {

        ServerSocket serverSocket = null;
        try {
            String[] addrs = serviceAddress.split(":");

            // 启动一个服务监听
            serverSocket = new ServerSocket(Integer.parseInt(addrs[1]));
            for (String interfaceName : handleMap.keySet()) {
                registerCenter.register(interfaceName, serviceAddress);
                System.out.println("注册服务成功: " + interfaceName + "->" + serviceAddress);
            }
            while (true) {
                // 监听端口，是个阻塞的方法
                Socket socket = serverSocket.accept();
                // 处理rpc请求，这里使用线程池来处理
                executorService.execute(new ProcessorHandler(socket, handleMap));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
