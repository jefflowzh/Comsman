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
    // private String[] colours;
    @ElementCollection
    @NotNull
    private List<String> colours;
    @Column(nullable = false)
    @NotNull
    private String sidePanelView; //tintered tempered glass
    // private String[] MotherBoardFormFactor; //atx micro atx mini itx
    @ElementCollection
    @NotNull
    private List<String> motherBoardFormFactor;
    @Column(nullable = false)
    @NotNull
    private Integer fullHeightExpansionSlot; //7
    @Column(nullable = false)
    @NotNull
    private Double MaxVideoCardLength; //in mm
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
    private String selectedColour; // the color customer selected

    public ComputerCase() {
        colours = new ArrayList<>();
        motherBoardFormFactor = new ArrayList<>();
    }

    public ComputerCase(String name, Double price, Integer inventoryQuantity, String image) {
        super(name, price, inventoryQuantity, image);
    }

    public ComputerCase(String manufacturer, String type, List<String> colours, String sidePanelView, List<String> motherBoardFormFactor, Integer fullHeightExpansionSlot, Double MaxVideoCardLength, Double topFanSupport, Double frontFanSupport, Double rearFanSupport, String name, Double price, Integer inventoryQuantity, String image) {
        super(name, price, inventoryQuantity, image);
        this.manufacturer = manufacturer;
        this.type = type;
        this.colours = colours;
        this.sidePanelView = sidePanelView;
        this.motherBoardFormFactor = motherBoardFormFactor;
        this.fullHeightExpansionSlot = fullHeightExpansionSlot;
        this.MaxVideoCardLength = MaxVideoCardLength;
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
        return MaxVideoCardLength;
    }

    public void setMaxVideoCardLength(Double MaxVideoCardLength) {
        this.MaxVideoCardLength = MaxVideoCardLength;
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

   public String getSelectedColour() {
        return selectedColour;
    }

    public void setSelectedColour(String selectedColour) {
        this.selectedColour = selectedColour;
    } 

    public List<String> getColours() {
        return colours;
    }

    public void setColours(List<String> colours) {
        this.colours = colours;
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
    
}
