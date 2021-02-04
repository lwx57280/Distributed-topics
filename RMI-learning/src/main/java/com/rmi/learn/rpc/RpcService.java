package com.rmi.learn.rpc;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 远程调用的服务端入口，使用socket监听
 * @Author:         cong zhi
 * @CreateDate:     2021/1/17 11:29
 * @UpdateUser:     cong zhi
 * @UpdateDate:     2021/1/17 11:29
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
public class RpcService {
    // 定义个线程池
    private static final ExecutorService executorService = Executors.newCachedThreadPool();
    /**
     * 注册服务实例，服务注册后，其他客户端通过接口调用就可以调用服务端的实现
     * @author      cong zhi
     * @param       service
     * @return
     * @exception
     * @date        2021/1/17 12:14
     */
    public void publisher(final Object service, int port) {

        ServerSocket serverSocket = null;
        try {
            // 启动一个服务监听
            serverSocket = new ServerSocket(port);
            while (true) {
                // 监听端口，是个阻塞的方法
                Socket socket =serverSocket.accept();
                // 处理rpc请求，这里使用线程池来处理
                executorService.execute(new ProcessorHandler(socket,service));
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
