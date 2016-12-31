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
import java.util.Date;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import obj.chanel;
import obj.friend;
import sun.security.pkcs11.Secmod;

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
    public String ischanel = "";

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
                        sql db = new sql();
                        String id = db.user_session_to_id(body);
                        if (id != null) {
                            islogin = id;
                        }
                    }
                    if (!islogin.equals("")) {
                        //005 - yeu cau list user
                        //006 - gui list user
                        if ("005".equals(code)) {
                            String listuser = server.ListUserInChanel(ischanel);
                            if (!"".equals(listuser)) {
                                server.SendToChanel("sys-006@" + listuser, ischanel);
                            }
                        } // 010 - xin list chanel
                        // 011 - send list chanel
                        else if ("010".equals(code)) {
                            String temp = "";
                            ArrayList<chanel> cls = new ArrayList<>();
                            sql db = new sql();
                            cls = db.listchanel();
                            int b = 0;
                            for (chanel cl : cls) {
                                if (b == 0) {
                                    temp += cl.getName() + "@@" + cl.getId();
                                    b++;
                                } else {
                                    temp += "@@@" + cl.getName() + "@@" + cl.getId();
                                }
                            }
                            send("sys-011@" + temp);
                        } // 013 - xin list friend
                        // 014 - send list friend
                        else if ("013".equals(code)) {
                            String temp = "";
                            ArrayList<friend> cls = new ArrayList<>();
                            sql db = new sql();
                            cls = db.listfriend(islogin);
                            int b = 0;
                            for (friend cl : cls) {
                                if (b == 0) {
                                    temp += db.username(cl.getR2()) + "@@" + cl.getId();
                                    b++;
                                } else {
                                    temp += "@@@" + db.username(cl.getR2()) + "@@" + cl.getId();
                                }
                            }
                            send("sys-014@" + temp);
                        } //016 - ischanel;
                        else if ("016".equals(code)) {
                            String old = ischanel;
                            ischanel = body;
                            String listuser = server.ListUserInChanel(old);
                            server.SendToChanel("sys-006@" + listuser, old);
                            String listusernew = server.ListUserInChanel(body);
                            server.SendToChanel("sys-006@" + listusernew, body);
                            sql db = new sql();
                            ArrayList<String> as = db.listmsgchuaxem(islogin, ischanel);
                            for (String a : as) {
                                send("msg-000@" + a);
                            }
                            DaXem(islogin, ischanel);
                        }// 017 - xin thu moi friend
                        // 018 - send thu moi friend
                        else if ("017".equals(code)) {
                            ArrayList<friend> cls = new ArrayList<>();
                            sql db = new sql();
                            cls = db.listthumoifriend(islogin);
                            for (friend cl : cls) {
                                String str = cl.getR1() + "@@@" + db.username(cl.getR1());
                                send("sys-018@" + str);
                            }
                        } else if ("019".equals(code)) {
                            //quyen dinh ket friend
                            sql db = new sql();
                            String[] a = body.split("@@@");
                            if ("1".equals(a[1])) {
                                db.Update("UPDATE friend SET Status =1 WHERE f1='" + a[0] + "' and f2 ='" + islogin + "'");
                                if (db.kiemtrafriendToStatus(islogin, a[0]) >= 0) {
                                    db.Update("UPDATE friend SET Status =1 WHERE f1='" + islogin + "' and f2 ='" + a[0] + "'");
                                } else {
                                    db.Update("INSERT INTO friend VALUES ('" + db.kiemtrafriendToid(a[0], islogin) + "','" + islogin + "','" + a[0] + "',1)");
                                }
                            } else {
                                db.Update("DELETE FROM friend WHERE f1='" + a[0] + "' and f2 ='" + islogin + "'");
                            }
                        } else if ("020".equals(code)) {
                            //xoa ban
                            sql db = new sql();
                            db.Update("DELETE FROM friend WHERE f1='" + islogin + "' and f2 ='" + body + "'");
                            db.Update("DELETE FROM friend WHERE f1='" + body + "' and f2 ='" + islogin + "'");
                            String temp = "";
                            ArrayList<friend> cls = new ArrayList<>();
                            cls = db.listfriend(islogin);
                            int b = 0;
                            for (friend cl : cls) {
                                if (b == 0) {
                                    temp += db.username(cl.getR2()) + "@@" + cl.getId();
                                    b++;
                                } else {
                                    temp += "@@@" + db.username(cl.getR2()) + "@@" + cl.getId();
                                }
                            }
                            server.SendToUser("sys-014@" + temp, islogin);
                            
                            cls = db.listfriend(body);
                            b = 0;
                            for (friend cl : cls) {
                                if (b == 0) {
                                    temp += db.username(cl.getR2()) + "@@" + cl.getId();
                                    b++;
                                } else {
                                    temp += "@@@" + db.username(cl.getR2()) + "@@" + cl.getId();
                                }
                            }
                            server.SendToUser("sys-014@" + temp, body);
                        } else if ("021".equals(code)) {
                            //xoa chanel
                            sql db = new sql();
                            db.xoachanel(islogin, body);
                            ArrayList<chanel> cls = new ArrayList<>();
                            cls = db.listchanel();
                            int b = 0;
                            String temp = "";
                            for (chanel cl : cls) {
                                if (b == 0) {
                                    temp += cl.getName() + "@@" + cl.getId();
                                    b++;
                                } else {
                                    temp += "@@@" + cl.getName() + "@@" + cl.getId();
                                }
                            }
                            server.SendAll("sys-011@" + temp);
                        } else if ("020".equals(code)) {

                        }
                    }
                } else if ("msg".equals(msg.substring(0, 3)) && !islogin.equals("")) {
                    if (!ischanel.equals("")) {
                        Date d = new Date();
                        long timestamp = d.getTime();
                        sql db = new sql();
                        String name = db.username(islogin);
                        db.Update("INSERT INTO msg VALUES ('" + db.ruuid() + "','" + name + ": " + body + "','" + islogin + "','" + ischanel + "'," + timestamp + ")");
                        server.SendToChanel("msg-" + "000" + "@" + name + ": " + body, ischanel);
                        DaXem(islogin, ischanel);
                    } else {
                        send("sys-501@");
                    }
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

    public void DaXem(String user, String chanel) {
        Date d = new Date();
        long timestamp = d.getTime();
        sql db = new sql();
        int a = db.kiemtramsgold(user, chanel);
        if (a == 1) {
            db.Update("UPDATE msgold SET time =" + timestamp + " WHERE user='" + user + "' and chanel ='" + chanel + "'");
        } else {
            db.Update("INSERT INTO msgold VALUES ('" + db.ruuid() + "','" + user + "','" + chanel + "'," + timestamp + ")");
        }
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
