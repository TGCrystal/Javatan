package edu.rpi.cs.csci4960.s21.javatan;

import org.junit.BeforeClass;
import org.junit.Test;

import java.net.InetAddress;

import static org.junit.Assert.assertEquals;

public class ClientTest {

    private static Server server;
    private static String ip;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        InetAddress addr = InetAddress.getLocalHost();
        ip = addr.getHostAddress();

        server = new Server(new GUI(), new Game());

        server.startServer(ip, 9999);
    }

    @Test
    public void basicTest() throws InterruptedException {
        // init four clients
        //Player 1
        Client client = new Client();
        client.connect(ip, 9999);
        //Player 2
        client = new Client();
        client.connect(ip, 9999);
        //Player 3
        client = new Client();
        client.connect(ip, 9999);
        //Player 4
        client = new Client();
        client.connect(ip, 9999);
        client.handleThisPlayerClickingRoad(2,5);
        Thread.sleep(2000);
//        while(true){}


    }



}
