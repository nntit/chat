/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package b5;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author nnt12
 */
class c3_ThreadChat implements Runnable {

    private Scanner in = null;
    private Socket socket = null;
    public c3_Client chat = null;
    ServerSocket server = null;

    public c3_ThreadChat() {
        try {
            server = new ServerSocket(1234);
        } catch (Exception e) {
        }
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            while (true) {
                while ((socket = server.accept()) != null) {
                    this.in = new Scanner(this.socket.getInputStream());
                    String chuoi = in.nextLine().trim();
                    chat.hienthi(chuoi + "\n");
                }
            }
        } catch (Exception e) {
        } finally {
            try {
                socket.close();
            } catch (Exception e) {
            }
        }
    }
}
