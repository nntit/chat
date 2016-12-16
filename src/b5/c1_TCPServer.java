/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package b5;

import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author nnt12
 */
public class c1_TCPServer {

    static final int port = 1234;
    private ServerSocket server = null;

    public c1_TCPServer() {
        try {
            server = new ServerSocket(port);
        } catch (Exception e) {
        }
    }

    public void action() {
        Socket socket = null;
        int i = 0;
        System.out.println("server ready");
        try {
            while ((socket = server.accept()) != null) {
                new c1_ServerThread(socket, "Client#" + i);
                System.out.printf("thread for Client %d %n", i++);
            }
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        new c1_TCPServer().action();
    }
}
