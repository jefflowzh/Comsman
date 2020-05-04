package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class HDD extends ComputerPart implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    @NotNull
    private String manufacturer;
    @Column(nullable = false)
    @NotNull
    private String type; //(RPM)
    @Column(nullable = false)
    @NotNull
    private Integer capacity; //in GB
    @Column(nullable = false)
    @NotNull
    private String formFactor; //(3.5'')
    @Column(nullable = false)
    @NotNull
    private String interfaceForm; //(SATA 6gb/s  SATA 3gb/s )  

    public HDD() {
    }

    public HDD(String name, Double price, Integer inventoryQuantity, String image) {
        super(name, price, inventoryQuantity, image);
    }

    public HDD(String manufacturer, String type, Integer Capacity, String formFactor, String interfaceForm, String name, Double price, Integer inventoryQuantity, String image) {
        super(name, price, inventoryQuantity, image);
        this.manufacturer = manufacturer;
        this.type = type;
        this.capacity = Capacity;
        this.formFactor = formFactor;
        this.interfaceForm = interfaceForm;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer Capacity) {
        this.capacity = Capacity;
    }

    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }

    public String getInterfaceForm() {
        return interfaceForm;
    }

    public void setInterfaceForm(String interfaceForm) {
        this.interfaceForm = interfaceForm;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
      
}
