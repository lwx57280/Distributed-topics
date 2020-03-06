package com.socketdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * socket client 客户端
 * 一对一单播操作
 */
public class SocketClient2 {

    public static void main(String[] args) throws IOException {
        Socket socket = null;
        try {
            socket = new Socket("localhost", 8888);
            //  读取服务端信息
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // 往服务端写数据
            PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);
            writer.println("Hello 菲菲");
            while (true) {
                String serverData = reader.readLine();
                if (serverData == null) {
                    break;
                }
                System.out.println("客户端收到的数据:"+serverData);
            }

            writer.close();
            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
