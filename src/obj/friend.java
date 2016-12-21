/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obj;

/**
 *
 * @author nnt12
 */
public class friend {
    private String id;
    private String r1;
    private String r2;
    private int Status;

    public friend(String id, String r1, String r2, int Status) {
        this.id = id;
        this.r1 = r1;
        this.r2 = r2;
        this.Status = Status;
    }

    public String getId() {
        return id;
    }

    public String getR1() {
        return r1;
    }

    public String getR2() {
        return r2;
    }

    public int getStatus() {
        return Status;
    }
    
}
