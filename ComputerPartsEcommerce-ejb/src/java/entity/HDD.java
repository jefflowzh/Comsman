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
import javax.persistence.ManyToMany;

/**
 *
 * @author zeplh
 */
@Entity
public class HDD extends ComputerPart implements Serializable {

    private static final long serialVersionUID = 1L;
   
    //@ManyToMany(mappedBy = "hdds")
   //private List<ComputerSet> computerSets;

    private String manufacturer;
    private String type; //(RPM)
    private Integer capacity; //in GB
    private String formFactor; //(3.5'')
    private String interfaceForm; //(SATA 6gb/s  SATA 3gb/s )  

    public HDD() {
    }

    public HDD(String name, Double price, Integer inventoryQuantity, String image) {
        super(name, price, inventoryQuantity, image);
    }

    public HDD(String Manufacturer, String type, Integer Capacity, String formFactor, String Interface, String name, Double price, Integer inventoryQuantity, String image) {
        super(name, price, inventoryQuantity, image);
        this.manufacturer = Manufacturer;
        this.type = type;
        this.capacity = Capacity;
        this.formFactor = formFactor;
        this.interfaceForm = Interface;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String Manufacturer) {
        this.manufacturer = Manufacturer;
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
    
    
    
}
