/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.restful.model;

import java.util.List;

/**
 *
 * @author zeplh
 */
public class CompatibilityCheckReq {
    
    List<Long> currentComputerBuildPartIds;
    Long addedPartId;

    public CompatibilityCheckReq() {
    }

    public CompatibilityCheckReq(List<Long> currentComputerBuildPartIds, Long addedPartId) {
        this.currentComputerBuildPartIds = currentComputerBuildPartIds;
        this.addedPartId = addedPartId;
    }

    public List<Long> getCurrentComputerBuildPartIds() {
        return currentComputerBuildPartIds;
    }

    public void setCurrentComputerBuildPartIds(List<Long> currentComputerBuildPartIds) {
        this.currentComputerBuildPartIds = currentComputerBuildPartIds;
    }

    public Long getAddedPartId() {
        return addedPartId;
    }

    public void setAddedPartId(Long addedPartId) {
        this.addedPartId = addedPartId;
    }
    
    
    
}
