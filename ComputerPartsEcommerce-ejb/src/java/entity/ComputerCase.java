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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author zeplh
 */
@Entity
public class ComputerCase extends ComputerPart implements Serializable {

    private static final long serialVersionUID = 1L;
    
   //@OneToMany(mappedBy = "compCase")
   //private List<ComputerSet> computerSets;
 
    private String type; //(ATX Mid Tower)
    // private String[] colours;
    @ElementCollection
    private List<String> colours;
    private String sidePanelView; //tintered tempered glass
    // private String[] MotherBoardFormFactor; //atx micro atx mini itx
    @ElementCollection
    private List<String> motherBoardFormFactor;
    private Integer fullHeightExpansionSlot; //7
    private Double MaxVideoCardLength; //in mm
    private Double topFanSupport;
    private Double frontFanSupport;
    private Double rearFanSupport;
    private String selectedColour; // the color customer selected

    public ComputerCase() {
        colours = new ArrayList<>();
        motherBoardFormFactor = new ArrayList<>();
    }

    public ComputerCase(String name, Double price, Integer inventoryQuantity, String image, String manufacturer) {
        super(name, price, inventoryQuantity, image, manufacturer);
    }

    public ComputerCase(String manufacturer, String type, List<String> colours, String sidePanelView, List<String> motherBoardFormFactor, Integer fullHeightExpansionSlot, Double MaxVideoCardLength, Double topFanSupport, Double frontFanSupport, Double rearFanSupport, String name, Double price, Integer inventoryQuantity, String image) {
        super(name, price, inventoryQuantity, image, manufacturer);
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
    
}
