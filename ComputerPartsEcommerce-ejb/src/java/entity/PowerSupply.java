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
public class PowerSupply extends ComputerPart implements Serializable {

    private static final long serialVersionUID = 1L;
    
   // @OneToMany(mappedBy = "psu")
  // private List<ComputerSet> computerSets;

    private String Manufacturer;
    private String formFactor;// (micro atx)
    private String efficiency;
    private Integer wattage;
    private String modularity; //(wires)
    private Integer SATAConnectors;
    private Integer PCIe6plus2;

    public PowerSupply() {
    }

    public PowerSupply(String name, Double price, Integer inventoryQuantity, String image, String manufacturer) {
        super(name, price, inventoryQuantity, image, manufacturer);
    }

    public PowerSupply(String manufacturer, String formFactor, String efficiency, Integer wattage, String modularity, Integer SATAConnectors, Integer PCIe6plus2, String name, Double price, Integer inventoryQuantity, String image) {
        super(name, price, inventoryQuantity, image, manufacturer);
        this.formFactor = formFactor;
        this.efficiency = efficiency;
        this.wattage = wattage;
        this.modularity = modularity;
        this.SATAConnectors = SATAConnectors;
        this.PCIe6plus2 = PCIe6plus2;
    }


//    public String getManufacturer() {
//        return Manufacturer;
//    }
//
//    public void setManufacturer(String Manufacturer) {
//        this.Manufacturer = Manufacturer;
//    }

    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }

    public String getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(String efficiency) {
        this.efficiency = efficiency;
    }

    public Integer getWattage() {
        return wattage;
    }

    public void setWattage(Integer wattage) {
        this.wattage = wattage;
    }

    public String getModularity() {
        return modularity;
    }

    public void setModularity(String modularity) {
        this.modularity = modularity;
    }

    public Integer getSATAConnectors() {
        return SATAConnectors;
    }

    public void setSATAConnectors(Integer SATAConnectors) {
        this.SATAConnectors = SATAConnectors;
    }

    public Integer getPCIe6plus2() {
        return PCIe6plus2;
    }

    public void setPCIe6plus2(Integer PCIe6plus2) {
        this.PCIe6plus2 = PCIe6plus2;
    }
    
    
    
}
