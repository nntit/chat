/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Interface.ISys;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nnt12
 */
public class Threadsys extends Thread {

    private ISys sys = null;

    Threadsys(ISys iSys) {
        this.sys = iSys;
        this.start();
    }

    @Override
    public void run() {
        try {
            ServerSocket ssocket = new ServerSocket(Integer.parseInt(sys.port()));
            sys.Log("sys start ...");
            while (true) {
                Socket client = ssocket.accept();
                sys.Log("sys connect");
                new ThreadsysClients(client, sys);
            }
        } catch (IOException ex) {
            sys.Log("Error: can not start sys server");
            Logger.getLogger(ThreadServer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
