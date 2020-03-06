package com.socketdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * socket client 服务端
 */
public class SocketServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try{
            // 启动一个服务
            serverSocket = new ServerSocket(8888);
            // 等待一个服务
            Socket socket = serverSocket.accept();
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println(reader.readLine());
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (serverSocket != null) {
                serverSocket.close();
            }
        }
    }
}
