/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author zeplh
 */
@Entity
public class CPUWaterCooler extends ComputerPart implements Serializable {

    private static final long serialVersionUID = 1L;
   
    private String Manufacturer;
    private String colour;
    private Integer noiseLevel; //(in dB)
    private String[] CPUChipCompatibility;
    private Double RadiatorSize; // (mm)

    public CPUWaterCooler() {
    }

    public CPUWaterCooler(String name, Double price, Integer inventoryQuantity, String image) {
        super(name, price, inventoryQuantity, image);
    }

    public CPUWaterCooler(String Manufacturer, String colour, Integer noiseLevel, String[] CPUChipCompatibility, Double RadiatorSize, String name, Double price, Integer inventoryQuantity, String image) {
        super(name, price, inventoryQuantity, image);
        this.Manufacturer = Manufacturer;
        this.colour = colour;
        this.noiseLevel = noiseLevel;
        this.CPUChipCompatibility = CPUChipCompatibility;
        this.RadiatorSize = RadiatorSize;
    }

    public String getManufacturer() {
        return Manufacturer;
    }

    public void setManufacturer(String Manufacturer) {
        this.Manufacturer = Manufacturer;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public Integer getNoiseLevel() {
        return noiseLevel;
    }

    public void setNoiseLevel(Integer noiseLevel) {
        this.noiseLevel = noiseLevel;
    }

    public String[] getCPUChipCompatibility() {
        return CPUChipCompatibility;
    }

    public void setCPUChipCompatibility(String[] CPUChipCompatibility) {
        this.CPUChipCompatibility = CPUChipCompatibility;
    }

    public Double getRadiatorSize() {
        return RadiatorSize;
    }

    public void setRadiatorSize(Double RadiatorSize) {
        this.RadiatorSize = RadiatorSize;
    }

    
    
}
