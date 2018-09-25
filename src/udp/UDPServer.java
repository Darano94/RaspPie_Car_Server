package udp;

import pins.PinHandler;
import thread.ThreadHandler;

import java.io.IOException;
import java.net.*;

public class UDPServer implements Runnable {
    private DatagramSocket socket;
    private DatagramPacket packet;
    private PinHandler pinHandler;
    private int port;
    ThreadHandler threadHandler = new ThreadHandler();

    private String msg;

    public boolean listen() {

        boolean status = true;
        try {
            packet = new DatagramPacket(new byte[15], 15);
            socket.receive(packet);
            msg = new String(packet.getData());
            threadHandler.setMsg(msg);
        } catch (SocketException e) {
            status = false;
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return status;

    }

    public UDPServer(int port) {
        this.port = port;
        try {
            socket = new DatagramSocket(port);
            System.out.println("Addresse: " + socket.getInetAddress().getLocalHost().getHostAddress());
            System.out.println("Listen for packets");
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        pinHandler = new PinHandler();
        while (!Thread.currentThread().isInterrupted()) {
            String message = threadHandler.getMsg();
// sudo rpi-update 52241088c1da59a359110d39c1875cda56496764 downgrade kernel to 4.4 for wiringPi
            if (message != null) {
                if ("vorwaerts".equalsIgnoreCase(message.trim())) {
                    pinHandler.fahreVorwaerts();
                    System.out.println("vor");
                }else if("rueckwaerts".equalsIgnoreCase(message.trim())){
                    pinHandler.fahreRueckwaerts();
                    System.out.println("rück");
                }else if("links".equalsIgnoreCase(message.trim())){
                    pinHandler.fahreLinks();
                    System.out.println("links");
                }else if("rechts".equalsIgnoreCase(message.trim())){
                    pinHandler.fahreRechts();
                    System.out.println("rechts");
                }else if("links-drehen".equalsIgnoreCase(message.trim())){
                    pinHandler.linksDrehen();
                    System.out.println("links drh");
                }else if("rechts-drehen".equalsIgnoreCase(message.trim())){
                    pinHandler.rechtsDrehen();
                    System.out.println("rechtsdrejem");
                }else{
                    pinHandler.closePins();
                }
                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public String getMsg() {
        return msg;
    }
}
