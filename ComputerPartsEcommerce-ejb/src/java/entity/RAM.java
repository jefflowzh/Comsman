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
import javax.persistence.ManyToMany;

/**
 *
 * @author zeplh
 */
@Entity
public class RAM extends ComputerPart implements Serializable {

    private static final long serialVersionUID = 1L;
   
   //@ManyToMany(mappedBy = "rams")
   //private List<ComputerSet> computerSets;
    
    private String manufacturer;
    private String speed;
    private String type;
    private Integer sticks; //(2 x 8GB)
    private Integer perStickGB;
    private Integer casLatency;

    public RAM() {
    }

    public RAM(String name, Double price, Integer inventoryQuantity, String image) {
        super(name, price, inventoryQuantity, image);
    }

    public RAM(String Manufacturer, String speed, String type, Integer sticks, Integer perStickGB, Integer CasLatency, String name, Double price, Integer inventoryQuantity, String image) {
        super(name, price, inventoryQuantity, image);
        this.manufacturer = Manufacturer;
        this.speed = speed;
        this.type = type;
        this.sticks = sticks;
        this.perStickGB = perStickGB;
        this.casLatency = CasLatency;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String Manufacturer) {
        this.manufacturer = Manufacturer;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSticks() {
        return sticks;
    }

    public void setSticks(Integer sticks) {
        this.sticks = sticks;
    }

    public Integer getPerStickGB() {
        return perStickGB;
    }

    public void setPerStickGB(Integer perStickGB) {
        this.perStickGB = perStickGB;
    }

    public Integer getCasLatency() {
        return casLatency;
    }

    public void setCasLatency(Integer CasLatency) {
        this.casLatency = CasLatency;
    }

    
    
}
