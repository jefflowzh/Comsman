package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class CPUAirCooler extends ComputerPart implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Column(nullable = false)
    @NotNull
    private String manufacturer;
    @Column(nullable = false)
    @NotNull
    private String colour;
    @Column(nullable = false)
    @NotNull
    private Integer noiseLevel; // (in dB)
    @Column(nullable = false)
    @NotNull
    private Double height; // (mm) //this one not really useful if its water cooled
    @ElementCollection
    @NotNull
    private List<String> CPUChipCompatibility;
    
    public CPUAirCooler() {
        CPUChipCompatibility = new ArrayList<>();
    }

    public CPUAirCooler(String name, Double price, Integer inventoryQuantity, String image) {
        super(name, price, inventoryQuantity, image);
    }

    public CPUAirCooler(String manufacturer, String colour, Integer noiseLevel, Double Height, List<String> CPUChipCompatibility, String name, Double price, Integer inventoryQuantity, String image) {
        super(name, price, inventoryQuantity, image);
        this.manufacturer = manufacturer;
        this.colour = colour;
        this.noiseLevel = noiseLevel;
        this.height = Height;
        this.CPUChipCompatibility = CPUChipCompatibility;
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

    public List<String> getCPUChipCompatibility() {
        return CPUChipCompatibility;
    }

    public void setCPUChipCompatibility(List<String> CPUChipCompatibility) {
        this.CPUChipCompatibility = CPUChipCompatibility;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    
}
