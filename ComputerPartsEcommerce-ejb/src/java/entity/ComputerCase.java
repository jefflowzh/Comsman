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
 
    private String manufacturer;
    private String type; //(ATX Mid Tower)
    private String colour;
    private String sidePanelView; //tintered tempered glass
    private String[] motherBoardFormFactor; //atx micro atx mini itx
    private Integer fullHeightExpansionSlot; //7
    private Double maxVideoCardLength; //in mm
    private Double topFanSupport;
    private Double frontFanSupport;
    private Double rearFanSupport;

    public ComputerCase() {
    }

    public ComputerCase(String name, Double price, Integer inventoryQuantity, String image) {
        super(name, price, inventoryQuantity, image);
    }

    public ComputerCase(String manufacturer, String type, String colours, String sidePanelView, String[] motherBoardFormFactor, Integer fullHeightExpansionSlot, Double maxVideoCardLength, Double topFanSupport, Double frontFanSupport, Double rearFanSupport, String name, Double price, Integer inventoryQuantity, String image) {
        super(name, price, inventoryQuantity, image);
        this.manufacturer = manufacturer;
        this.type = type;
        this.colour = colours;
        this.sidePanelView = sidePanelView;
        this.motherBoardFormFactor = motherBoardFormFactor;
        this.fullHeightExpansionSlot = fullHeightExpansionSlot;
        this.maxVideoCardLength = maxVideoCardLength;
        this.topFanSupport = topFanSupport;
        this.frontFanSupport = frontFanSupport;
        this.rearFanSupport = rearFanSupport;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colours) {
        this.colour = colours;
    }

    public String getSidePanelView() {
        return sidePanelView;
    }

    public void setSidePanelView(String sidePanelView) {
        this.sidePanelView = sidePanelView;
    }

    public String[] getMotherBoardFormFactor() {
        return motherBoardFormFactor;
    }

    public void setMotherBoardFormFactor(String[] motherBoardFormFactor) {
        this.motherBoardFormFactor = motherBoardFormFactor;
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

}
