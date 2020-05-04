package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class SSD extends ComputerPart implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Column(nullable = false)
    @NotNull
    private String manufacturer;
    @Column(nullable = false)
    @NotNull
    private String type; //(RPM)
    @Column(nullable = false)
    @NotNull
    private Integer Capacity; //in GB
    @Column(nullable = false)
    @NotNull
    private String formFactor;// 2.5''
    @Column(nullable = false)
    @NotNull
    private String Interface; //(SATA 6gb/s  SATA 3gb/s ) 
    @Column(nullable = false)
    @NotNull
    private Boolean NVME;

    public SSD() {
    }

    public SSD(String name, Double price, Integer inventoryQuantity, String image) {
        super(name, price, inventoryQuantity, image);
    }

    public SSD(String manufacturer, String type, Integer Capacity, String formFactor, String Interface, Boolean NVME, String name, Double price, Integer inventoryQuantity, String image) {
        super(name, price, inventoryQuantity, image);
        this.manufacturer = manufacturer;
        this.type = type;
        this.Capacity = Capacity;
        this.formFactor = formFactor;
        this.Interface = Interface;
        this.NVME = NVME;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCapacity() {
        return Capacity;
    }

    public void setCapacity(Integer Capacity) {
        this.Capacity = Capacity;
    }

    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }

    public String getInterface() {
        return Interface;
    }

    public void setInterface(String Interface) {
        this.Interface = Interface;
    }

    public Boolean getNVME() {
        return NVME;
    }

    public void setNVME(Boolean NVME) {
        this.NVME = NVME;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    
    
}
