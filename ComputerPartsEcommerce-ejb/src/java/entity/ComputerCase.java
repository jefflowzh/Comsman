/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
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
 
    private String Manufacturer;
    private String type; //(ATX Mid Tower)
    private String[] colours;
    private String sidePanelView; //tintered tempered glass
    private String[] MotherBoardFormFactor; //atx micro atx mini itx
    private Integer fullHeightExpansionSlot; //7
    private Double MaxVideoCardLength; //in mm
    private Double topFanSupport;
    private Double frontFanSupport;
    private Double rearFanSupport;
    private String selectedColour;

    public ComputerCase() {
    }

    public ComputerCase(String name, Double price, Integer inventoryQuantity, String image) {
        super(name, price, inventoryQuantity, image);
    }

    public ComputerCase(String Manufacturer, String type, String[] colours, String sidePanelView, String[] MotherBoardFormFactor, Integer fullHeightExpansionSlot, Double MaxVideoCardLength, Double topFanSupport, Double frontFanSupport, Double rearFanSupport, String name, Double price, Integer inventoryQuantity, String image) {
        super(name, price, inventoryQuantity, image);
        this.Manufacturer = Manufacturer;
        this.type = type;
        this.colours = colours;
        this.sidePanelView = sidePanelView;
        this.MotherBoardFormFactor = MotherBoardFormFactor;
        this.fullHeightExpansionSlot = fullHeightExpansionSlot;
        this.MaxVideoCardLength = MaxVideoCardLength;
        this.topFanSupport = topFanSupport;
        this.frontFanSupport = frontFanSupport;
        this.rearFanSupport = rearFanSupport;
    }

    

    public String getManufacturer() {
        return Manufacturer;
    }

    public void setManufacturer(String Manufacturer) {
        this.Manufacturer = Manufacturer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String[] getColours() {
        return colours;
    }

    public void setColours(String[] colours) {
        this.colours = colours;
    }

    public String getSidePanelView() {
        return sidePanelView;
    }

    public void setSidePanelView(String sidePanelView) {
        this.sidePanelView = sidePanelView;
    }

    public String[] getMotherBoardFormFactor() {
        return MotherBoardFormFactor;
    }

    public void setMotherBoardFormFactor(String[] MotherBoardFormFactor) {
        this.MotherBoardFormFactor = MotherBoardFormFactor;
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
    
}
