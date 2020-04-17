/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;

/**
 *
 * @author zeplh
 */
@Entity
public class CPUAirCooler extends ComputerPart implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String Manufacturer;
    private String colour;
    private Integer noiseLevel; // (in dB)
    private Double Height; // (mm) //this one not really useful if its water cooled
    @ElementCollection
    private List<String> CPUChipCompatibility;
// private String[] CPUChipCompatibility;

    public CPUAirCooler() {
        CPUChipCompatibility = new ArrayList<>();
    }

    public CPUAirCooler(String name, Double price, Integer inventoryQuantity, String image) {
        super(name, price, inventoryQuantity, image);
    }

    public CPUAirCooler(String Manufacturer, String colour, Integer noiseLevel, Double Height, List<String> CPUChipCompatibility, String name, Double price, Integer inventoryQuantity, String image) {
        super(name, price, inventoryQuantity, image);
        this.Manufacturer = Manufacturer;
        this.colour = colour;
        this.noiseLevel = noiseLevel;
        this.Height = Height;
        this.CPUChipCompatibility = CPUChipCompatibility;
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

    public Double getHeight() {
        return Height;
    }

    public void setHeight(Double Height) {
        this.Height = Height;
    }

    public List<String> getCPUChipCompatibility() {
        return CPUChipCompatibility;
    }

    public void setCPUChipCompatibility(List<String> CPUChipCompatibility) {
        this.CPUChipCompatibility = CPUChipCompatibility;
    }

    
}
