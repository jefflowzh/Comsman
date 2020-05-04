/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.restful.model;

/**
 *
 * @author zeplh
 */
public class CompatibilityCheckRsp {
    
    boolean compatible;
    String message;

    public CompatibilityCheckRsp() {
    }

    public CompatibilityCheckRsp(boolean compatible, String message) {
        this.compatible = compatible;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    
    public boolean isCompatible() {
        return compatible;
    }

    public void setCompatible(boolean compatible) {
        this.compatible = compatible;
    }
    
    
    
}
