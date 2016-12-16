/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package b5;

import com.sun.scenario.effect.impl.prism.PrImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author nnt12
 */
public class c1_ServerThread implements Runnable{
    private Scanner in = null;
    private PrintWriter out = null;
    private Socket socket;
    private String name;
        
    public c1_ServerThread(Socket socket, String name)throws IOException{
        this.socket = socket;
        this.name = name;
        this.in = new Scanner(this.socket.getInputStream());
        this.out = new PrintWriter(this.socket.getOutputStream(),true);
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            while (true) {                
                String chuoi = in.nextLine().trim();
                chuoi= chuoi.toUpperCase();
                out.println(chuoi);
            }
        } catch (Exception e) {
        }
    }
    
}
