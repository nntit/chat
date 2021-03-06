/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.awt.event.KeyEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author nnt12
 */
public class Client extends javax.swing.JFrame {

    public DataOutputStream out = null;
    public DataInputStream in = null;
    public Socket sk = null;
    public int port = -1;
    public int portsys = -1;
    public String host = null;

    /**
     * Creates new form Client
     */
    public Client() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_msg = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        li_chanel = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        li_user = new javax.swing.JList<>();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        txt_list_cl_fr = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        bt_file = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Gửi");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });

        txt_msg.setEditable(false);
        txt_msg.setColumns(20);
        txt_msg.setLineWrap(true);
        txt_msg.setRows(5);
        jScrollPane1.setViewportView(txt_msg);

        li_chanel.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                li_chanelValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(li_chanel);

        jLabel2.setText("List user in chanel");

        jScrollPane3.setViewportView(li_user);

        jButton2.setText("+");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("-");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        txt_list_cl_fr.setText("List chanel");
        txt_list_cl_fr.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txt_list_cl_fr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_list_cl_frActionPerformed(evt);
            }
        });

        jButton5.setText("Clear msg");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        bt_file.setText("File in chanel");
        bt_file.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_fileActionPerformed(evt);
            }
        });

        jMenu1.setText("Kết nối");

        jMenuItem1.setText("Đăng nhập");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Chức năng");

        jMenuItem2.setText("Đổi display name");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuItem3.setText("Đổi password");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(txt_list_cl_fr, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton5)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                    .addComponent(bt_file, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_list_cl_fr)
                    .addComponent(jButton5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(bt_file))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public void send(String str) {
        try {
            out.writeUTF(str);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        sendmsg();
    }//GEN-LAST:event_jButton1ActionPerformed

    boolean login;
    String maillogin = "";
    String session;
    int a = 0;
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        Connect fm = new Connect(this, true);
        fm.setVisible(true);
        if (login == true && a == 0) {
            ThreadClient();
            a++;
        } else {
            send("sys-012@" + session);//send ss dang nhap lai
        }
        this.setTitle(maillogin);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        ChangeUsername ch = new ChangeUsername(this, true);
        ch.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        ChangePassword ch = new ChangePassword(this, true);
        ch.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            sendmsg();
        }
    }//GEN-LAST:event_jTextField2KeyReleased

    private void sendmsg() {
        if (login == true) {
            String message = jTextField2.getText().trim();
            if (!"".equals(message)) {
                jTextField2.setText("");
                send("msg-000@" + message);
            }
        } else {
            txt_msg.append("Bạn chưa đăng nhập" + "\n");
            txt_msg.setCaretPosition(txt_msg.getDocument().getLength());
        }
    }

    public String ischanel = "chanel";

    private void txt_list_cl_frActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_list_cl_frActionPerformed
        if ("chanel".equals(ischanel)) {
            txt_list_cl_fr.setText("List friend");
            ischanel = "friend";
        } else {
            txt_list_cl_fr.setText("List chanel");
            ischanel = "chanel";
        }
        if (ischanel == "chanel") {
            send("sys-010@");
        } else {
            send("sys-013@");
        }
    }//GEN-LAST:event_txt_list_cl_frActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        AddChanel fm = new AddChanel(this, true);
        fm.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private String sys016 = "";
    private void li_chanelValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_li_chanelValueChanged
        int a = li_chanel.getSelectedIndex();
        if (a < 0) {
            if (!"sys-016@".equals(sys016)) {
                sys016 = "sys-016@";
                send("sys-016@");
            }
        } else {
            String str = listchanel.get(a);
            if (!sys016.equals("sys-016@" + str)) {
                sys016 = "sys-016@" + str;
                txt_msg.setText("");
                send("sys-016@" + str);
            }
        }
    }//GEN-LAST:event_li_chanelValueChanged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if ("chanel".equals(ischanel)) {
            int a = li_chanel.getSelectedIndex();
            if (a < 0) {
            } else {
                int dialogResult = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa kênh \"" + li_chanel.getSelectedValue() + "\" không", "Warning", JOptionPane.YES_NO_OPTION);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    String str = listchanel.get(a);
                    send("sys-021@" + str);
                }
            }
        } else {
            int a = li_chanel.getSelectedIndex();
            if (a < 0) {
            } else {
                int dialogResult = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa \"" + li_chanel.getSelectedValue() + "\" không", "Warning", JOptionPane.YES_NO_OPTION);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    String str = listchanel.get(a);
                    send("sys-020@" + str);
                }
            }
        }
        if (ischanel == "chanel") {
            send("sys-010@");
        } else {
            send("sys-013@");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        txt_msg.setText("");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void bt_fileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_fileActionPerformed
        file ch = new file();
        ch.setVisible(true);
    }//GEN-LAST:event_bt_fileActionPerformed

    public void connect(String host, int port) {
        try {
            if (sk == null) {
                sk = new Socket(host, port);
                in = new DataInputStream(sk.getInputStream());
                out = new DataOutputStream(sk.getOutputStream());
            }
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private ArrayList<String> listchanel = new ArrayList<>();

    private void ThreadClient() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                send("sys-012@" + session); //send dang nhap

                send("sys-017@"); //xin thu moi

                if (ischanel == "chanel") {
                    send("sys-010@"); //xin list chanel
                } else {
                    send("sys-013@"); //xin list friend
                }

                while (true) {
                    // Nhận
                    String msg;
                    try {
                        msg = in.readUTF();
                        String code = msg.substring(4, msg.indexOf("@")); //
                        String body = msg.substring(msg.indexOf("@") + 1);// su li du lieu nhan
                        String title = msg.substring(0, msg.indexOf("@"));//
                        if ("msg".equals(msg.substring(0, 3))) {
                            txt_msg.append(body + "\n");//neu la tin nhan
                            txt_msg.setCaretPosition(txt_msg.getDocument().getLength());
                        } else if ("sys".equals(msg.substring(0, 3))) {//neu la tin he thong
                            if ("500".equals(code)) {//lỗi chưa dang nhap
                                txt_msg.append("System: Bạn chưa đăng nhập" + "\n");
                                txt_msg.setCaretPosition(txt_msg.getDocument().getLength());
                            } else if ("501".equals(code)) {//lỗi chưa chọn chanel
                                txt_msg.append("System: Bạn chưa chọn chanel" + "\n");
                                txt_msg.setCaretPosition(txt_msg.getDocument().getLength());
                            } else if ("006".equals(code)) { //nhan list user in chanel
                                DefaultListModel listModel = new DefaultListModel();
                                String[] auser = body.split("@@@");

                                for (String user : auser) {
                                    listModel.addElement(user);
                                }
                                li_user.setModel(listModel);
                            } else if ("011".equals(code)) {//nhan list chanel
                                if ("chanel".equals(ischanel)) {
                                    DefaultListModel listModel = new DefaultListModel();
                                    li_user.setModel(new DefaultListModel());
                                    if (!"".equals(body)) {
                                        String[] auser = body.split("@@@");
                                        listchanel.clear();
                                        for (String user : auser) {
                                            String[] ausers = user.split("@@");
                                            listModel.addElement(ausers[0]);
                                            listchanel.add(ausers[1]);
                                        }
                                    }
                                    li_chanel.setModel(listModel);
                                }
                            } else if ("014".equals(code)) {//nhan list friend
                                if (!"chanel".equals(ischanel)) {
                                    DefaultListModel listModel = new DefaultListModel();
                                    li_user.setModel(new DefaultListModel());
                                    if (!"".equals(body)) {
                                        String[] auser = body.split("@@@");
                                        listchanel.clear();
                                        for (String user : auser) {
                                            String[] ausers = user.split("@@");
                                            String temp = "";
                                            if (Integer.parseInt(ausers[3])>0) {
                                                temp = "("+ausers[3]+")";
                                            }
                                            listModel.addElement(ausers[0] + "("+ausers[2]+")"+temp);
                                            listchanel.add(ausers[1]);
                                        }
                                    }
                                    li_chanel.setModel(listModel);
                                }
                            } else if ("018".equals(code)) {//nhan thu moi kêt ban
                                String[] a = body.split("@@@");
                                int dialogResult = JOptionPane.showConfirmDialog(null, "Bạn " + a[1] + " muốn kết bạn với bạn.\nBạn có đồng ý không?", "Warning", JOptionPane.YES_NO_OPTION);
                                if (dialogResult == JOptionPane.YES_OPTION) {
                                    send("sys-019@" + a[0] + "@@@1");//có
                                } else {
                                    send("sys-019@" + a[0] + "@@@0");//không
                                }
                            }
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                        txt_msg.append("System: Mất kết nối server");
                        txt_msg.setCaretPosition(txt_msg.getDocument().getLength());
                        break;
                    }
                }
            }
        }).start();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Client().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_file;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JList<String> li_chanel;
    private javax.swing.JList<String> li_user;
    private javax.swing.JButton txt_list_cl_fr;
    private javax.swing.JTextArea txt_msg;
    // End of variables declaration//GEN-END:variables
}
