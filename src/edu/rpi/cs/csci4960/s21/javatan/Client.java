package edu.rpi.cs.csci4960.s21.javatan;
import java.net.*;
import java.io.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
public class Client {
    public String serverName = "127.0.0.1";
    public int port = 1000;
    Socket client = null;
    Scanner getKey = null;
    Scanner getServer = null;
    PrintWriter out = null;

    public void connect() throws IOException {
        try {
            client = new Socket(serverName, port);
            getServer = new Scanner(client.getInputStream());
            System.out.println(getServer.nextLine());
            out = new PrintWriter(client.getOutputStream());


            getKey = new Scanner(System.in);
            while (getKey.hasNextLine()) {
                //write to server
                out.println(getKey.nextLine());
                out.flush();
                try {
                    System.out.println("server：" + getServer.nextLine());
                } catch (Exception e) {

                    break;
                }
                //阻塞等待接收服务端的消息
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            getKey.close();
            getServer.close();
            out.close();
            client.close();
        }
    }
}
