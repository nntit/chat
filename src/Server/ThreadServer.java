/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Interface.ILog;
import Interface.ISend;
import Interface.ISys;
import java.io.DataInputStream;
import java.io.DataOutputStream;
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
public class ThreadServer extends Thread {

    private ISend server = null;
    private ILog log = null;
    private ArrayList<ThreadClients> clients = new ArrayList<>();

    public ThreadServer(ILog log) {
        this.log = log;
        server = new ISend() {
            @Override
            public void SendAll(String msg) {
                SendALl(msg);
            }

            @Override
            public void SendToChanel(String msg, String to) {
                Sendtochanel(msg, to);
            }

            @Override
            public void SendToUser(String msg, String to) {
                SendTouser(msg, to);
            }

            @Override
            public void Remove(ThreadClients tc) {
                RemoveUser(tc);
            }

            @Override
            public String ListUserInChanel() {
                return ListUsersInChanel();
            }

            @Override
            public String ListUserInChanel(String to) {
                return ListUsersInChanel(to);
            }
        };
        this.start();
    }

    private void RemoveUser(ThreadClients tc) {
        clients.remove(tc);
    }

    private String ListUsersInChanel() {
        String a = "";
        int b = 0;
        for (ThreadClients c : clients) {
            sql db = new sql();
            String name = db.username(c.islogin);
            if (b == 0) {
                a += name;
                b++;
            } else {
                a += "@@@" + name;
            }
        }
        return a;
    }

    private String ListUsersInChanel(String to) {
        String a = "";
        int b = 0;
        for (ThreadClients c : clients) {
            sql db = new sql();
            if (c.ischanel.equals(to) && !c.ischanel.equals("")) {
                String name = db.username(c.islogin);
                if (b == 0) {
                    a += name;
                    b++;
                } else {
                    a += "@@@" + name;
                }
            }
        }
        return a;
    }

    private void SendALl(String msg) {
        for (ThreadClients c : clients) {
            c.send(msg);
        }
    }

    private void Sendtochanel(String msg, String to) {
        for (ThreadClients c : clients) {
            if (c.ischanel.equals(to)) {
                c.send(msg);
            }
        }
    }

    private void SendTouser(String msg, String to) {
        for (ThreadClients c : clients) {
            if (c.islogin.equals(to)) {
                c.send(msg);
            }
        }
    }

    @Override
    public void run() {
        new Threadsys(new ISys() {
            @Override
            public void Log(String msg) {
                log.Log(msg);
            }

            @Override
            public void SendAll(String msg) {
                SendALl(msg);
            }

            @Override
            public void SendToChanel(String msg, String to) {
                Sendtochanel(msg, to);
            }

            @Override
            public void SendToUser(String msg, String to) {
                SendTouser(msg, to);
            }

            @Override
            public String port() {
                return log.port();
            }

            @Override
            public String portsys() {
                return log.portsys();
            }

            @Override
            public String ListUserInChanel() {
                return ListUsersInChanel();
            }
        });
        try {
            ServerSocket ssocket = new ServerSocket(Integer.parseInt(log.port()));
            log.Log("server start ...");
            log.ServerStart(true);
            while (true) {
                Socket client = ssocket.accept();
                log.Log("new connect ...");
                clients.add(new ThreadClients(server, client, log));
                log.Log(clients.size() + "");
            }
        } catch (IOException ex) {
            log.ServerStart(false);
            log.Log("Error: can not start server");
            Logger.getLogger(ThreadServer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
