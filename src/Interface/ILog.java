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
public interface ILog {

    public void Log(String msg);

    public String port();

    public String portsys();

    public void ServerStart(boolean start);
}
