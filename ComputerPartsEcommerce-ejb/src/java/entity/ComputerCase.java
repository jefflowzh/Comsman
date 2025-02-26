package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class ComputerCase extends ComputerPart implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    @NotNull
    private String manufacturer;
    @Column(nullable = false)
    @NotNull
    private String type; //(ATX Mid Tower)
    @Column(nullable = false)
    @NotNull
    private String sidePanelView; //tintered tempered glass
    @ElementCollection
    @NotNull
    private List<String> motherBoardFormFactor;
    @Column(nullable = false)
    @NotNull
    private Integer fullHeightExpansionSlot; //7
    @Column(nullable = false)
    @NotNull
    private Double maxVideoCardLength; //in mm
    @Column(nullable = false)
    @NotNull
    private Double topFanSupport;
    @Column(nullable = false)
    @NotNull
    private Double frontFanSupport;
    @Column(nullable = false)
    @NotNull
    private Double rearFanSupport;
    @Column
    private String colour;

    public ComputerCase() {
        motherBoardFormFactor = new ArrayList<>();
    }

    public ComputerCase(String name, Double price, Integer inventoryQuantity, String image) {
        super(name, price, inventoryQuantity, image);
    }

    public ComputerCase(String manufacturer, String type, String colour, String sidePanelView, List<String> motherBoardFormFactor, Integer fullHeightExpansionSlot, Double maxVideoCardLength, Double topFanSupport, Double frontFanSupport, Double rearFanSupport, String name, Double price, Integer inventoryQuantity, String image) {
        super(name, price, inventoryQuantity, image);
        this.manufacturer = manufacturer;
        this.type = type;
        this.colour = colour;
        this.sidePanelView = sidePanelView;
        this.motherBoardFormFactor = motherBoardFormFactor;
        this.fullHeightExpansionSlot = fullHeightExpansionSlot;
        this.maxVideoCardLength = maxVideoCardLength;
        this.topFanSupport = topFanSupport;
        this.frontFanSupport = frontFanSupport;
        this.rearFanSupport = rearFanSupport;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSidePanelView() {
        return sidePanelView;
    }

    public void setSidePanelView(String sidePanelView) {
        this.sidePanelView = sidePanelView;
    }

    public Integer getFullHeightExpansionSlot() {
        return fullHeightExpansionSlot;
    }

    public void setFullHeightExpansionSlot(Integer fullHeightExpansionSlot) {
        this.fullHeightExpansionSlot = fullHeightExpansionSlot;
    }

    public Double getMaxVideoCardLength() {
        return maxVideoCardLength;
    }

    public void setMaxVideoCardLength(Double maxVideoCardLength) {
        this.maxVideoCardLength = maxVideoCardLength;
    }

    public Double getTopFanSupport() {
        return topFanSupport;
    }

    public void setTopFanSupport(Double topFanSupport) {
        this.topFanSupport = topFanSupport;
    }

    public Double getFrontFanSupport() {
        return frontFanSupport;
    }

    public void setFrontFanSupport(Double frontFanSupport) {
        this.frontFanSupport = frontFanSupport;
    }

    public Double getRearFanSupport() {
        return rearFanSupport;
    }

    public void setRearFanSupport(Double rearFanSupport) {
        this.rearFanSupport = rearFanSupport;
    }

    public List<String> getMotherBoardFormFactor() {
        return motherBoardFormFactor;
    }

    public void setMotherBoardFormFactor(List<String> motherBoardFormFactor) {
        this.motherBoardFormFactor = motherBoardFormFactor;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

}
