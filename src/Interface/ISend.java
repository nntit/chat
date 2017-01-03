/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Server.ThreadClients;

/**
 *
 * @author nnt12
 */
public interface ISend {

    public void SendAll(String msg);

    public void SendToChanel(String msg, String to);

    public void SendToUser(String msg, String to);
    
    public void Remove(ThreadClients tc);

    public String ListUserInserver();

    public String ListUserInChanel(String to);
    
    public String List_Friend(String to);
}
