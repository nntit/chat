/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Interface.ILog;
import Interface.ISend;
import Interface.ISys;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import obj.friend;

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
            public String ListUserInserver() {
                return ListUsersInServer();
            }

            @Override
            public String ListUserInChanel(String to) {
                return ListUsersInChanel(to);
            }
            
            @Override
            public String List_Friend(String to) {
                return List_friend(to);
            }
            
        };
        this.start();
    }

    private void RemoveUser(ThreadClients tc) {
        clients.remove(tc);
    }

    private String ListUsersInServer() {
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
        //send cho tất cả
        for (ThreadClients c : clients) {
            c.send(msg);
        }
    }

    private void Sendtochanel(String msg, String to) {
        //send to chanel
        for (ThreadClients c : clients) {
            if (c.ischanel.equals(to)) {
                c.send(msg);
            }
        }
    }

    private void SendTouser(String msg, String to) {
        //send to user
        for (ThreadClients c : clients) {
            if (c.islogin.equals(to)) {
                c.send(msg);
            }
        }
    }

    private String session_to_chanel(String ss) {
        //send to user
        for (ThreadClients c : clients) {
            if (c.isss.equals(ss)) {
                return c.ischanel;
            }
        }
        return null;
    }

    private String List_friend(String id) {
        sql db = new sql();
        String temp = "";
        ArrayList<friend> cls = new ArrayList<>();
        cls = db.listfriend(id);
        int b = 0;
        for (friend cl : cls) {
            String on = "off";
            for (ThreadClients c : clients) {
                if (c.islogin.equals(cl.getR2())) {
                    on = "on";
                    break;
                }
            }
            if (b == 0) {
                temp += db.username(cl.getR2()) + "@@" + cl.getId() + "@@" + on;
                b++;
            } else {
                temp += "@@@" + db.username(cl.getR2()) + "@@" + cl.getId() + "@@" + on;
            }
        }
        return temp;
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
            public String ListUsersInserver() {
                return ListUsersInServer();
            }

            @Override
            public String Session_to_chanel(String ss) {
                return session_to_chanel(ss);
            }

            @Override
            public String ListUserInChanel(String to) {
                return ListUsersInChanel(to);
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
