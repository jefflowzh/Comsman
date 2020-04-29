/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author zeplh
 */
@Entity
public class CPU extends ComputerPart implements Serializable {

   private static final long serialVersionUID = 1L;
   
   
   // private String Manufacturer;
   private Integer coreCount;
   private Integer TDP;
   private String socket;
   private Boolean hasIntergratedGraphics;
   private Boolean includesCpuCooler;

    public CPU() {
    }

    public CPU(String name, Double price, Integer inventoryQuantity, String image, String manufacturer) {
        super(name, price, inventoryQuantity, image, manufacturer);
    }

    public CPU(String manufacturer, Integer coreCount, Integer TDP, String socket, Boolean hasIntergratedGraphics, Boolean includesCpuCooler, String name, Double price, Integer inventoryQuantity, String image) {
        super(name, price, inventoryQuantity, image, manufacturer);
        this.coreCount = coreCount;
        this.TDP = TDP;
        this.socket = socket;
        this.hasIntergratedGraphics = hasIntergratedGraphics;
        this.includesCpuCooler = includesCpuCooler;
    }

    public Integer getCoreCount() {
        return coreCount;
    }

    public void setCoreCount(Integer coreCount) {
        this.coreCount = coreCount;
    }

    public Integer getTDP() {
        return TDP;
    }

    public void setTDP(Integer TDP) {
        this.TDP = TDP;
    }

    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }

    public Boolean getHasIntergratedGraphics() {
        return hasIntergratedGraphics;
    }

    public void setHasIntergratedGraphics(Boolean hasIntergratedGraphics) {
        this.hasIntergratedGraphics = hasIntergratedGraphics;
    }

    public Boolean getIncludesCpuCooler() {
        return includesCpuCooler;
    }

    public void setIncludesCpuCooler(Boolean includesCpuCooler) {
        this.includesCpuCooler = includesCpuCooler;
    }
   

    
}
