/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Interface.ILog;
import Interface.ISend;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import obj.chanel;

/**
 *
 * @author nnt12
 */
public class ThreadClients extends Thread {

    private DataOutputStream out = null;
    private DataInputStream in = null;
    private ISend server = null;
    private ILog log = null;
    public String islogin = "";

    public ThreadClients(ISend server, Socket sk, ILog log) {
        try {
            this.server = server;
            this.log = log;
            in = new DataInputStream(sk.getInputStream());
            out = new DataOutputStream(sk.getOutputStream());
            this.start();
        } catch (IOException ex) {

        }
    }

    @Override
    public void run() {

        String sysold = "";
        String ssql = "";

        while (true) {
            try {
                String msg = in.readUTF();
                log.Log("(<)" + msg);

                String code = msg.substring(4, msg.indexOf("@"));
                String body = msg.substring(msg.indexOf("@") + 1);
                String title = msg.substring(0, msg.indexOf("@"));
                if ("sys".equals(msg.substring(0, 3))) {
                    if ("012".equals(code)) {
                        //islogin = body;
                    } else if (!islogin.equals("")) {
                        //005 - yeu cau list user
                        //006 - gui list user
                        if ("005".equals(code)) {
                            String listuser = server.ListUserInChanel();
                            if (!"".equals(listuser)) {
                                server.SendAll("sys-006@" + listuser);
                            }
                        }
                        // 010 - xin list chanel
                        // 011 - send list chanel
                        if ("010".equals(code)) {
                            String temp = "";
                            ArrayList<chanel> cls = new ArrayList<>();
                            sql db = new sql();
                            cls = db.listchanel();
                            int b = 0;
                            for (chanel cl : cls) {
                                if (b == 0) {
                                    temp += cl.getName();
                                    b++;
                                } else {
                                    temp += "@@@" + cl.getName();
                                }
                                temp += cl.getName();
                            }
                            send("sys-011@" + temp);
                        }
                    }
                } else if ("msg".equals(msg.substring(0, 3)) && !islogin.equals("")) {
                    sql db = new sql();
                    String name = db.username(islogin);
                    server.SendAll("msg-" + "000" + "@" + name + ": " + body);
                } else if ("msg".equals(msg.substring(0, 3)) && islogin.equals("")) {
                    send("sys-500@");
                }
            } catch (IOException ex) {
                Logger.getLogger(ThreadClients.class.getName()).log(Level.SEVERE, null, ex);
                try {
                    server.Remove(this);
                    String listuser = server.ListUserInChanel();
                    if (!"".equals(listuser)) {
                        server.SendAll("sys-006@" + listuser);
                    }
                    this.join();
                } catch (InterruptedException ex1) {
                    Logger.getLogger(ThreadClients.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }

        }
    }

    public String ruuid() {
        UUID uid = UUID.randomUUID();
        return uid.toString();
    }

    public void send(String msg) {
        try {
            out.writeUTF(msg);
            log.Log("(>)" + msg);

        } catch (IOException ex) {
            Logger.getLogger(ThreadClients.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
}
