package com.multicastdemo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * 组播示例 （一对多发送消息）
 */
public class MulticastClient {
    public static void main(String[] args) {
        try {
            InetAddress group = InetAddress.getByName("224.5.6.7");
            MulticastSocket socket = new MulticastSocket(8888);
            // 加到指定的组里面
            socket.joinGroup(group);
            byte[] buf = new byte[256];
            while (true) {
                DatagramPacket msgPacket = new DatagramPacket(buf, buf.length);
                //  socket.receive(msgPacket ); 读不到数据则阻塞
                socket.receive(msgPacket );
                String msg = new String(msgPacket.getData());
                System.out.println("接收到的数据："+msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
