/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import datamodel.StringValue;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.ElementCollection;
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
    
    private String colour;
    private Integer noiseLevel;
    // private String[] CPUChipCompatibility;
    @ElementCollection
    private List<String> CPUChipCompatibility;
    private Double RadiatorSize; 

    public CPUWaterCooler() {
        CPUChipCompatibility = new ArrayList<>();
    }

    public CPUWaterCooler(String name, Double price, Integer inventoryQuantity, String image, String manufacturer) {
        super(name, price, inventoryQuantity, image, manufacturer);
    }

    public CPUWaterCooler(String manufacturer, String colour, Integer noiseLevel, List<String> CPUChipCompatibility, Double RadiatorSize, String name, Double price, Integer inventoryQuantity, String image) {
        super(name, price, inventoryQuantity, image, manufacturer);
        this.colour = colour;
        this.noiseLevel = noiseLevel;
        this.CPUChipCompatibility = CPUChipCompatibility;
        this.RadiatorSize = RadiatorSize;
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

    public List<String> getCPUChipCompatibility() {
        return CPUChipCompatibility;
    }

    public void setCPUChipCompatibility(List<String> CPUChipCompatibility) {
        this.CPUChipCompatibility = CPUChipCompatibility;
    }

//    public String[] getCPUChipCompatibility() {
//        return CPUChipCompatibility;
//    }
//
//    public void setCPUChipCompatibility(String[] CPUChipCompatibility) {
//        this.CPUChipCompatibility = CPUChipCompatibility;
//    }

    public Double getRadiatorSize() {
        return RadiatorSize;
    }

    public void setRadiatorSize(Double RadiatorSize) {
        this.RadiatorSize = RadiatorSize;
    }

    
    
}
