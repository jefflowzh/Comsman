package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class CPUWaterCooler extends ComputerPart implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Column(nullable = false)
    @NotNull
    private String manufacturer;
    @Column(nullable = false)
    @NotNull
    private String colour;
    @Column(nullable = false)
    @NotNull
    private Integer noiseLevel;
    @ElementCollection
    @NotNull
    private List<String> CPUChipCompatibility;
    @Column(nullable = false)
    @NotNull
    private Double radiatorSize; 

    public CPUWaterCooler() {
        CPUChipCompatibility = new ArrayList<>();
    }

    public CPUWaterCooler(String name, Double price, Integer inventoryQuantity, String image) {
        super(name, price, inventoryQuantity, image);
    }

    public CPUWaterCooler(String manufacturer, String colour, Integer noiseLevel, List<String> CPUChipCompatibility, Double radiatorSize, String name, Double price, Integer inventoryQuantity, String image) {
        super(name, price, inventoryQuantity, image);
        this.manufacturer = manufacturer;
        this.colour = colour;
        this.noiseLevel = noiseLevel;
        this.CPUChipCompatibility = CPUChipCompatibility;
        this.radiatorSize = radiatorSize;
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

    public Double getRadiatorSize() {
        return radiatorSize;
    }

    public void setRadiatorSize(Double RadiatorSize) {
        this.radiatorSize = RadiatorSize;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
  
}
