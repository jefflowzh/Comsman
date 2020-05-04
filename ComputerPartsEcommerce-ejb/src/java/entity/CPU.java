package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class CPU extends ComputerPart implements Serializable {

   private static final long serialVersionUID = 1L;
   
   @Column(nullable = false)
   @NotNull
   private String manufacturer;
   @Column(nullable = false)
   @NotNull
   private Integer coreCount;
   @Column(nullable = false)
   @NotNull
   private Integer TDP;
   @Column(nullable = false)
   @NotNull
   private String socket;
   @Column(nullable = false)
   @NotNull
   private Boolean hasIntergratedGraphics;
   @Column(nullable = false)
   @NotNull
   private Boolean includesCpuCooler;

    public CPU() {
    }

    public CPU(String name, Double price, Integer inventoryQuantity, String image) {
        super(name, price, inventoryQuantity, image);
    }

    public CPU(String manufacturer, Integer coreCount, Integer TDP, String socket, Boolean hasIntergratedGraphics, Boolean includesCpuCooler, String name, Double price, Integer inventoryQuantity, String image) {
        super(name, price, inventoryQuantity, image);

        this.manufacturer = manufacturer;
        this.coreCount = coreCount;
        this.TDP = TDP;
        this.socket = socket;
        this.hasIntergratedGraphics = hasIntergratedGraphics;
        this.includesCpuCooler = includesCpuCooler;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
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
