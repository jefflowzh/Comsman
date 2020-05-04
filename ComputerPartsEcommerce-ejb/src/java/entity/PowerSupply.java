package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class PowerSupply extends ComputerPart implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    @NotNull
    private String manufacturer;
    @Column(nullable = false)
    @NotNull
    private String formFactor;// (micro atx)
    @Column(nullable = false)
    @NotNull
    private String efficiency;
    @Column(nullable = false)
    @NotNull
    private Integer wattage;
    @Column(nullable = false)
    @NotNull
    private String modularity; //(wires)
    @Column(nullable = false)
    @NotNull
    private Integer SATAConnectors;
    @Column(nullable = false)
    @NotNull
    private Integer PCIe6plus2;

    public PowerSupply() {
    }

    public PowerSupply(String name, Double price, Integer inventoryQuantity, String image) {
        super(name, price, inventoryQuantity, image);
    }

    public PowerSupply(String manufacturer, String formFactor, String efficiency, Integer wattage, String modularity, Integer SATAConnectors, Integer PCIe6plus2, String name, Double price, Integer inventoryQuantity, String image) {
        super(name, price, inventoryQuantity, image);
        this.manufacturer = manufacturer;
        this.formFactor = formFactor;
        this.efficiency = efficiency;
        this.wattage = wattage;
        this.modularity = modularity;
        this.SATAConnectors = SATAConnectors;
        this.PCIe6plus2 = PCIe6plus2;
    }

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

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}
