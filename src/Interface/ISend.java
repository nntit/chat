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

    public void Remove(ThreadClients tc);

    public String ListUserInChanel();
}
