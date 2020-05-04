package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class MotherBoard extends ComputerPart implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(nullable = false)
    @NotNull
    private String manufacturer;
    @Column(nullable = false)
    @NotNull
    private String formFactor;
    @Column(nullable = false)
    @NotNull
    private String socket;
    @Column(nullable = false)
    @NotNull
    private String chipset;
    @Column(nullable = false)
    @NotNull
    private Integer memorySlot;
    @Column(nullable = false)
    @NotNull
    private String colour;
    @Column(nullable = false)
    @NotNull
    private Boolean SLICrossFire;
    @Column(nullable = false)
    @NotNull
    private Integer PCIEx16;
    @Column(nullable = false)
    @NotNull
    private Integer m2Slot;
    @Column(nullable = false)
    @NotNull
    private Boolean wiFi;
    @ElementCollection
    private List<String> suportedMemorySpeed;

    public MotherBoard() {
        suportedMemorySpeed = new ArrayList<>();
    }

    public MotherBoard(String name, Double price, Integer inventoryQuantity, String image) {
        super(name, price, inventoryQuantity, image);
    }

    public MotherBoard(String manufacturer, String formFactor, String socket, String chipset, Integer memorySlot, String colour, Boolean SLICrossFire, Integer PCIEx16, Integer m2Slot ,Boolean wiFi, List<String> suportedMemorySpeed, String name, Double price, Integer inventoryQuantity, String image) {
        super(name, price, inventoryQuantity, image);
        this.manufacturer = manufacturer;
        this.formFactor = formFactor;
        this.socket = socket;
        this.chipset = chipset;
        this.memorySlot = memorySlot;
        this.colour = colour;
        this.SLICrossFire = SLICrossFire;
        this.PCIEx16 = PCIEx16;
        this.m2Slot = m2Slot;
        this.wiFi = wiFi;
        this.suportedMemorySpeed = suportedMemorySpeed;
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
        return colour;
    }

    public void setColour(String Colour) {
        this.colour = Colour;
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

    public List<String> getSuportedMemorySpeed() {
        return suportedMemorySpeed;
    }

    public void setSuportedMemorySpeed(List<String> suportedMemorySpeed) {
        this.suportedMemorySpeed = suportedMemorySpeed;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}
