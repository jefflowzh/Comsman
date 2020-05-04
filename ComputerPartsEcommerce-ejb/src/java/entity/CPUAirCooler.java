/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author zeplh
 */
@Entity
public class CPUAirCooler extends ComputerPart implements Serializable {

    private static final long serialVersionUID = 1L;
    
    
    private String manufacturer;
    private String colour;
    private Integer noiseLevel; // (in dB)
    private Double height; // (mm) //this one not really useful if its water cooled
    private String[] CPUChipCompatibility;

    public CPUAirCooler() {
    }

    public CPUAirCooler(String name, Double price, Integer inventoryQuantity, String image) {
        super(name, price, inventoryQuantity, image);
    }

    public CPUAirCooler(String Manufacturer, String colour, Integer noiseLevel, Double Height, String[] CPUChipCompatibility, String name, Double price, Integer inventoryQuantity, String image) {
        super(name, price, inventoryQuantity, image);
        this.manufacturer = Manufacturer;
        this.colour = colour;
        this.noiseLevel = noiseLevel;
        this.height = Height;
        this.CPUChipCompatibility = CPUChipCompatibility;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String Manufacturer) {
        this.manufacturer = Manufacturer;
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

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double Height) {
        this.height = Height;
    }

    public String[] getCPUChipCompatibility() {
        return CPUChipCompatibility;
    }

    public void setCPUChipCompatibility(String[] CPUChipCompatibility) {
        this.CPUChipCompatibility = CPUChipCompatibility;
    }

    
}
