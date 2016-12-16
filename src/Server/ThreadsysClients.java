/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Interface.ISys;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nnt12
 */
public class ThreadsysClients extends Thread {

    private DataOutputStream out = null;
    private DataInputStream in = null;
    private ISys sys = null;

    ThreadsysClients(Socket sk, ISys sys) {
        this.sys = sys;
        try {
            in = new DataInputStream(sk.getInputStream());
            out = new DataOutputStream(sk.getOutputStream());
            this.start();
        } catch (IOException ex) {
            Logger.getLogger(ThreadsysClients.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {

        String sysold = "";
        String ssql = "";

        while (true) {
            String msg;
            try {
                
                msg = in.readUTF();

                sys.Log("(<)" + msg);
                String code = msg.substring(0, 3);
                String session = msg.substring(4, msg.indexOf("@"));
                String body = msg.substring(msg.indexOf("@") + 1);
                String title = msg.substring(0, msg.indexOf("@"));
                if ("003".equals(code)) {
                    //003 - login = gui mail kiem tra
                    if ("003".equals(code)) {
                        sql db = new sql();
                        if (!db.isMailNull(body)) {
                            ssql = body;
                            sysold = code;
                            send("000-000@");
                        } else {
                            send("401-000@");
                        }
                    }
                    //004 - login = gui pass kiem tra
                    if ("004".equals(code)) {
                        sql db = new sql();
                        String temp = db.login(ssql, body);
                        if (temp != null) {
                            String ss = db.ruuid();
                            ssql = "UPDATE user SET session='" + ss + "'WHERE id='" + temp + "'";
                            db.Update(ssql);
                            send("000-000@" + ss);
                        } else {
                            send("401-000@");
                        }
                    }
                } else if ("007".equals(code)) {
                    //007 - doi ten
                    int c = -0;
                    if (body.length() >= 4 && body.length() <= 20 && (body.indexOf("@") < 0)) {
                        sql db = new sql();
                        ssql = "UPDATE user SET name='" + body + "'WHERE session='" + session + "'";
                        if (db.Update(ssql) != -1) {
                            c = 1;
                        }
                        String listuser = sys.ListUserInChanel();
                        if (!"".equals(listuser)) {
                            sys.SendAll("sys-006@" + listuser);
                        }
                    }
                    if (c == 1) {
                        send("000-000@");
                    } else {
                        send("401-000@");
                    }
                } else if ("008".equals(code)) {
                    //008 - doi pass
                    int c = -0;
                    sql db = new sql();
                    String s[] = body.split("@@@");
                    if (s.length == 2) {
                        String a = db.username(session, s[0]);
                        if (a != null) {
                            if (s[1].length() >= 3) {
                                ssql = "UPDATE user SET pass='" + s[1] + "'WHERE session='" + session + "'";
                                if (db.Update(ssql) != -1) {
                                    c = 1;
                                }
                            }
                        }
                    }
                    if (c == 1) {
                        send("000-000@");
                    } else {
                        send("401-000@");
                    }
                } else if ("001".equals(code)) {
                    //001 - tao id = gui mail kiem tra
                    sql db = new sql();
                    if (db.isMailNull(body)) {
                        sysold = code;
                        ssql = "INSERT INTO user VALUES ('" + ruuid() + "','" + body + "','" + body + "','";
                        send("000-000@");
                    } else {
                        send("401-000@");
                    }
                }else if ("002".equals(code)&& sysold.equals("001")){
                    //002 - tao id = gui pass;
                        sysold = "";
                        ssql += body + "','')";
                        sql db = new sql();
                        System.err.println(ssql);
                        if (db.Update(ssql) >= 1) {
                            send("000-000@");
                        } else {
                            send("401-000@");
                        }
                }

            } catch (IOException ex) {
                Logger.getLogger(ThreadsysClients.class.getName()).log(Level.SEVERE, null, ex);
                try {
                    this.join();
                } catch (InterruptedException ex1) {
                    Logger.getLogger(ThreadsysClients.class.getName()).log(Level.SEVERE, null, ex1);
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
            sys.Log("(S>)" + msg);

        } catch (IOException ex) {
            Logger.getLogger(ThreadClients.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

}
