package main;

import com.pi4j.io.gpio.*;
import udp.UDPServer;

import java.util.concurrent.ThreadFactory;

public class Main {

    public static void main(String[] args) {
        UDPServer server = new UDPServer(4444);
        Thread t = new Thread(server);
        t.start();
        while(true) {
            server.listen();
        }
    }


}
