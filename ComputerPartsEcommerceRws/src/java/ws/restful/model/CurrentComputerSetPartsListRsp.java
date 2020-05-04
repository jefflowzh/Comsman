/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.restful.model;

import entity.ComputerPart;
import java.util.List;

/**
 *
 * @author zeplh
 */
public class CurrentComputerSetPartsListRsp {
    
    List<ComputerPart> currentComputerSetPartsList;
    
    
    
//    ComputerSet currentComputerSet;
//
//    public CurrentComputerSetPartsListRsp() {
//    }
//
//    public CurrentComputerSetPartsListRsp(ComputerSet CurrentComputerSet) {
//        this.currentComputerSet = CurrentComputerSet;
//    }
//
//    public ComputerSet getCurrentComputerSet() {
//        return currentComputerSet;
//    }
//
//    public void setCurrentComputerSet(ComputerSet CurrentComputerSet) {
//        this.currentComputerSet = CurrentComputerSet;
//    }
//
//    
//    

    public CurrentComputerSetPartsListRsp(List<ComputerPart> currentComputerSetPartsList) {
        this.currentComputerSetPartsList = currentComputerSetPartsList;
    }

    public List<ComputerPart> getCurrentComputerSetPartsList() {
        return currentComputerSetPartsList;
    }

    public void setCurrentComputerSetPartsList(List<ComputerPart> currentComputerSetPartsList) {
        this.currentComputerSetPartsList = currentComputerSetPartsList;
    }

    public CurrentComputerSetPartsListRsp() {
    }
    
}
