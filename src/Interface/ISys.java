/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

/**
 *
 * @author nnt12
 */
public interface ISys {

    public void Log(String msg);

    public void SendAll(String msg);

    public void SendToChanel(String msg, String to);

    public void SendToUser(String msg, String to);

    public String port();

    public String portsys();

    public String ListUserInChanel();
}
