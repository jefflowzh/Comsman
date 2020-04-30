/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

/**
 *
 * @author zeplh
 */
@Entity
public class GPU extends ComputerPart implements Serializable {

    private static final long serialVersionUID = 1L;
   
   //@ManyToMany(mappedBy = "gpus")
   //private List<ComputerSet> computerSets;
    
    private String manufacturer;
    private String chipset; //(GTX 1660)
    private String Interface;  //(PCI-Ex16) matches with motherboard
    private Double length; //in mm
    private Integer TDP;
    private Integer expansionSlotWidth;
    private String externalPower;
    private Integer memory;
    private String memoryType;

    public GPU() {
    }

    public GPU(String name, Double price, Integer inventoryQuantity, String image) {
        super(name, price, inventoryQuantity, image);
    }

    public GPU(String Manufacturer, String chipset, String Interface, Double length, Integer TDP, Integer ExpansionSlotWidth, String externalPower, Integer Memory, String memoryType, String name, Double price, Integer inventoryQuantity, String image) {
        super(name, price, inventoryQuantity, image);
        this.manufacturer = Manufacturer;
        this.chipset = chipset;
        this.Interface = Interface;
        this.length = length;
        this.TDP = TDP;
        this.expansionSlotWidth = ExpansionSlotWidth;
        this.externalPower = externalPower;
        this.memory = Memory;
        this.memoryType = memoryType;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String Manufacturer) {
        this.manufacturer = Manufacturer;
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

    
}
