/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author nnt12
 */
public class AddChanel extends javax.swing.JDialog {

    /**
     * Creates new form AddChanel
     */
    public DataOutputStream out = null;
    public DataInputStream in = null;
    public Socket sk = null;

    public AddChanel(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.client = (Client) parent;
        connect(client.host, client.portsys);
        if ("chanel".equals(client.ischanel)) {
            jLabel1.setText("Tên chanel muốn tạo");
            jButton1.setText("tạo");
        } else {
            jLabel1.setText("Username cần thêm");
            jButton1.setText("thêm");
        }
    }

    Client client;

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

    public void send(String str) {
        try {
            out.writeUTF(str);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txt_namechanel = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setText("Tên chanel muốn tạo");

        jButton1.setText("Tạo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("thoát");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(txt_namechanel, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(43, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(60, 60, 60))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txt_namechanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (client.login == true) {
            String str = txt_namechanel.getText();
            if ("chanel".equals(client.ischanel)) {
                if (str.length() >= 4 && str.length() <= 20 && (str.indexOf("@") < 0) && (str.indexOf("'") < 0) && (str.indexOf("'") < 0)) {
                    send("015-" + client.session + "@" + str);
                    String msg;
                    try {
                        msg = in.readUTF();
                        if ("000".equals(msg.substring(0, 3))) {
                            JOptionPane.showMessageDialog(this, "Tạo thành công");
                            this.dispose();
                        } else if ("401".equals(msg.substring(0, 3))) {
                            JOptionPane.showMessageDialog(this, "Tạo ko thành công");
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(ChangePassword.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Tên chanel lớn hơn 3,\n"
                            + "nhỏ hơn hoặc bằng 20,\n"
                            + "không có chứ @,\n"
                            + "và không có dấu '");
                }
            } else if (str.length() >= 4 && str.length() <= 20 && (str.indexOf("@") < 0) && (str.indexOf("'") < 0) && (str.indexOf("'") < 0)) {
                send("016-" + client.session + "@" + str);
                String msg;
                try {
                    msg = in.readUTF();
                    if ("000".equals(msg.substring(0, 3))) {
                        JOptionPane.showMessageDialog(this, "Gửi thư mời thành công");
                        this.dispose();
                    } else if ("401".equals(msg.substring(0, 3))) {
                        JOptionPane.showMessageDialog(this, "User không tồn tại");
                    } else if ("402".equals(msg.substring(0, 3))) {
                        JOptionPane.showMessageDialog(this, "đã kết bạn rồi");
                    }else if ("403".equals(msg.substring(0, 3))) {
                        JOptionPane.showMessageDialog(this, "Không được phép tự kỷ");
                    }
                } catch (IOException ex) {
                    Logger.getLogger(ChangePassword.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Username không đúng");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Bạn chưa login");
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField txt_namechanel;
    // End of variables declaration//GEN-END:variables
}
