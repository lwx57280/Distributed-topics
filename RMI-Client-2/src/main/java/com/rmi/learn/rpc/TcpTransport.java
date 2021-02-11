package com.rmi.learn.rpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @Description: socket传输
 * https://dongguabai.blog.csdn.net/article/details/83625362?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.control&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.control
 * @Author:     cong zhi
 * @CreateDate: 2021/1/16 18:09
 * @UpdateUser: cong zhi
 * @UpdateDate: 2021/1/16 18:09
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public class TcpTransport {

    private String serviceAddress;


    public TcpTransport(String serviceAddress) {
        this.serviceAddress = serviceAddress;
    }

    public Socket newSocket() {
        System.out.println("准备创建Socket连接");
        Socket socket;
        try {
            String[] arrs = serviceAddress.split(":");
            socket = new Socket(arrs[0], Integer.parseInt(arrs[1]));
            return socket;
        } catch (IOException e) {
            throw new RuntimeException("Socket连接建立失败！");
        }
    }

    public Object send(RpcRequest request) {
        Socket socket = null;
        try {
            socket = newSocket();
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            // 通过 ObjectOutputStream 将当前request传给服务端
            outputStream.writeObject(request);
            outputStream.flush();

            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            Object result = inputStream.readObject();
            inputStream.close();
            outputStream.close();
            return result;
        } catch (Exception e) {
            throw new RuntimeException("发起远程调用异常");
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
    }
}
