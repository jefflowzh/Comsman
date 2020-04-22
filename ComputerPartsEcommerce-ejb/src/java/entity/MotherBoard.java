/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author zeplh
 */
@Entity
public class MotherBoard extends ComputerPart implements Serializable {

    private static final long serialVersionUID = 1L;
    
    //@OneToMany(mappedBy = "motherBoard")
   //private List<ComputerSet> computerSets;
    
    private String Manufacturer;
    private String formFactor;
    private String socket;
    private String chipset;
    private Integer memorySlot;
    private String Colour;
    //private String[] Interfaces;
    private Boolean SLICrossFire;
    private Integer PCIEx16;
    private Integer m2Slot;
    //private Integer SATA6GB;
    private Boolean wiFi;
    private String[] suportedMemorySpeed;
    


    public MotherBoard() {
    }

    public MotherBoard(String name, Double price, Integer inventoryQuantity, String image) {
        super(name, price, inventoryQuantity, image);
    }

    public MotherBoard(String Manufacturer, String formFactor, String socket, String chipset, Integer memorySlot, String Colour, Boolean SLICrossFire, Integer PCIEx16, Integer m2Slot, Boolean wiFi, String[] suportedMemorySpeed, String name, Double price, Integer inventoryQuantity, String image) {
        super(name, price, inventoryQuantity, image);
        this.Manufacturer = Manufacturer;
        this.formFactor = formFactor;
        this.socket = socket;
        this.chipset = chipset;
        this.memorySlot = memorySlot;
        this.Colour = Colour;
        this.SLICrossFire = SLICrossFire;
        this.PCIEx16 = PCIEx16;
        this.m2Slot = m2Slot;
        this.wiFi = wiFi;
        this.suportedMemorySpeed = suportedMemorySpeed;
    }

    
    
    public String getManufacturer() {
        return Manufacturer;
    }

    public void setManufacturer(String Manufacturer) {
        this.Manufacturer = Manufacturer;
    }

    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }

    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }

    public String getChipset() {
        return chipset;
    }

    public void setChipset(String chipset) {
        this.chipset = chipset;
    }

    public Integer getMemorySlot() {
        return memorySlot;
    }

    public void setMemorySlot(Integer memorySlot) {
        this.memorySlot = memorySlot;
    }

    public String getColour() {
        return Colour;
    }

    public void setColour(String Colour) {
        this.Colour = Colour;
    }

    public Boolean getSLICrossFire() {
        return SLICrossFire;
    }

    public void setSLICrossFire(Boolean SLICrossFire) {
        this.SLICrossFire = SLICrossFire;
    }

    public Integer getPCIEx16() {
        return PCIEx16;
    }

    public void setPCIEx16(Integer PCIEx16) {
        this.PCIEx16 = PCIEx16;
    }

    public Integer getM2Slot() {
        return m2Slot;
    }

    public void setM2Slot(Integer m2Slot) {
        this.m2Slot = m2Slot;
    }

    public Boolean getWiFi() {
        return wiFi;
    }

    public void setWiFi(Boolean wiFi) {
        this.wiFi = wiFi;
    }

    public String[] getSuportedMemorySpeed() {
        return suportedMemorySpeed;
    }

    public void setSuportedMemorySpeed(String[] suportedMemorySpeed) {
        this.suportedMemorySpeed = suportedMemorySpeed;
    }
   
    
    
    
}
