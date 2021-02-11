package com.rmi.learn.rpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Map;

/**
 * 处理RPC请求的线程
 *
 * @Author: cong zhi
 * @CreateDate: 2021/1/17 12:08
 * @UpdateUser: cong zhi
 * @UpdateDate: 2021/1/17 12:08
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public class ProcessorHandler implements Runnable {

    private Socket socket;
    /**
     * 服务端发布的服务
     */
    private Map<String, Object> handlerMap;

    public ProcessorHandler(Socket socket, Map<String, Object> handlerMap) {
        this.socket = socket;
        this.handlerMap = handlerMap;
    }

    @Override
    public void run() {
        ObjectInputStream inputStream = null;

        try {
            // 从socket中获取RPC请求
            inputStream = new ObjectInputStream(socket.getInputStream());
            RpcRequest request = (RpcRequest) inputStream.readObject();
            Object result = invoke(request);

            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(result);
            outputStream.flush();
            outputStream.close();
            inputStream.close();

        } catch (IOException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }


    }

    private Object invoke(RpcRequest request) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object[] args = request.getParameters();
        Class[] types = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            types[i] = args[i].getClass();
        }
        String serviceName = request.getClassName();
        String version = request.getVersion();
        if (null != version && !"".equals(version)) {
            serviceName = serviceName + "-" + version;
        }
        // 从handlerMap中,根据客户端请求地址,去拿到响应地址，通过反射发起调用
        Object service = handlerMap.get(serviceName);
        Method method = service.getClass().getMethod(request.getMethodName(), types);
        return method.invoke(service, args);
    }
}
