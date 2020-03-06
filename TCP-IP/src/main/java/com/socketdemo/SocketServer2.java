package com.socketdemo;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * socket client 服务端
 */
public class SocketServer2 {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try{
            // 启动一个服务
            serverSocket = new ServerSocket(8888);
            while (true) {
                // 等待一个服务
                final Socket socket = serverSocket.accept();
                new Thread(()-> {
                    try {
                        // 读取数据
                        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        // 发送数据
                        PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                        while (true) {
                            String clientData = reader.readLine();
                            if (clientData == null) {
                                break;
                            }
                            System.out.println("服务端接收到的数据:" + clientData);
                            writer.println("Hello Mic;^_^");
                            writer.flush();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (serverSocket != null) {
                serverSocket.close();
            }
        }
    }
}
