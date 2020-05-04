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
    
    Boolean compatible;

    public CompatibilityCheckRsp() {
    }

    public CompatibilityCheckRsp(boolean compatible) {
        this.compatible = compatible;
    }

    public Boolean isCompatible() {
        return compatible;
    }

    public void setCompatible(boolean compatible) {
        this.compatible = compatible;
    }
    
    
    
}
