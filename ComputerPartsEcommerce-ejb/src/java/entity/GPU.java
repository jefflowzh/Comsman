package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class GPU extends ComputerPart implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(nullable = false)
    @NotNull
    private String manufacturer;
    @Column(nullable = false)
    @NotNull
    private String chipset; //(GTX 1660)
    @Column(nullable = false)
    @NotNull
    private String Interface;  //(PCI-Ex16) matches with motherboard
    @Column(nullable = false)
    @NotNull
    private Double length; //in mm
    @Column(nullable = false)
    @NotNull
    private Integer TDP;
    @Column(nullable = false)
    @NotNull
    private Integer expansionSlotWidth;
    @Column(nullable = false)
    @NotNull
    private String externalPower; //cable type
    @Column(nullable = false)
    @NotNull
    private Integer memory; //GB
    @Column(nullable = false)
    @NotNull
    private String memoryType;

    public GPU() {
    }

    public GPU(String name, Double price, Integer inventoryQuantity, String image) {
        super(name, price, inventoryQuantity, image);
    }

    public GPU(String manufacturer, String chipset, String Interface, Double length, Integer TDP, Integer expansionSlotWidth, String externalPower, Integer memory, String memoryType, String name, Double price, Integer inventoryQuantity, String image) {
        super(name, price, inventoryQuantity, image);
        this.manufacturer = manufacturer;
        this.chipset = chipset;
        this.Interface = Interface;
        this.length = length;
        this.TDP = TDP;
        this.expansionSlotWidth = expansionSlotWidth;
        this.externalPower = externalPower;
        this.memory = memory;
        this.memoryType = memoryType;
    }

    public String getChipset() {
        return chipset;
    }

    public void setChipset(String chipset) {
        this.chipset = chipset;
    }

    public String getInterface() {
        return Interface;
    }

    public void setInterface(String Interface) {
        this.Interface = Interface;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Integer getTDP() {
        return TDP;
    }

    public void setTDP(Integer TDP) {
        this.TDP = TDP;
    }

    public Integer getExpansionSlotWidth() {
        return expansionSlotWidth;
    }

    public void setExpansionSlotWidth(Integer expansionSlotWidth) {
        this.expansionSlotWidth = expansionSlotWidth;
    }

    public String getExternalPower() {
        return externalPower;
    }

    public void setExternalPower(String externalPower) {
        this.externalPower = externalPower;
    }

    public Integer getMemory() {
        return memory;
    }

    public void setMemory(Integer Memory) {
        this.memory = Memory;
    }

    public String getMemoryType() {
        return memoryType;
    }

    public void setMemoryType(String MemoryType) {
        this.memoryType = MemoryType;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
  
}
