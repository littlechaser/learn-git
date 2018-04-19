package com.test;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.Socket;

/**
 * Author   yang_tao@<yangtao.letzgo.com.cn>
 * Date     2017-10-31 9:44
 * Version  1.0
 */
public class TestSocket {
    private static final String host = "192.168.3.114";
    //    private static final String host = "localhost";
    private static final int port = 13002;

    public static void main(String[] args) {
//        try {
//            Socket socket = new Socket(host, port);
//            DataInputStream dis = new DataInputStream(socket.getInputStream());
//            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
//            dos.write("this is test message".getBytes());
//            dos.flush();
//            socket.shutdownOutput();
//            StringBuffer msg = new StringBuffer();
//            byte[] temp=new byte[1024];
//            while (dis.read(temp) != -1) {
//                msg.append(temp);
//            }
//            socket.shutdownInput();
//            System.out.println("接收到服务端的数据：" + msg.toString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        Socket socket;
//        try {
//            socket = new Socket(host, port);
//            socket.setKeepAlive(true);
//            BufferedWriter dos = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
////            dos.write("##20020009沪FN0452");
//            dos.write("##20020009沪GV8682");
//            dos.flush();
//            socket.shutdownOutput();
//
//            BufferedReader reader;
//            while (true) {
//                reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
//                StringBuffer msg = new StringBuffer();
//                String temp;
//                while ((temp = reader.readLine()) != null) {
//                    msg.append(temp);
//                }
//                System.out.println("接收到服务端的数据：" + msg.toString());
//                Thread.sleep(1000);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }finally {
//
//        }

        Socket socket = null;
        try {
            socket = new Socket(host, port);
            socket.setKeepAlive(true);
//            socket.setSoTimeout(10000);
            BufferedWriter dos = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
            dos.write("##20010009沪GV8682");
//            dos.write("##2005");
            dos.flush();
//            dos.close();
            System.out.println(socket.isClosed());
            System.out.println(socket.isConnected());
            System.out.println(socket.isOutputShutdown());
//            socket.shutdownOutput();
            BufferedReader reader;
            while (true) {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
                char[] response = new char[36];
                reader.read(response);
                String msg = new String(response).trim();
                if (StringUtils.isBlank(msg)) {
                    break;
                }
                System.out.println("接收到服务端的数据：" + msg + socket.isClosed());
                Thread.sleep(1000);
            }
            reader.close();
            System.out.println(socket.isClosed());
            System.out.println(socket.isConnected());
            System.out.println(socket.isOutputShutdown());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}