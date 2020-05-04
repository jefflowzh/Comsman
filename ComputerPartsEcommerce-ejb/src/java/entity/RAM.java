package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class RAM extends ComputerPart implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(nullable = false)
    @NotNull
    private String manufacturer;
    @Column(nullable = false)
    @NotNull
    private String speed;
    @Column(nullable = false)
    @NotNull
    private String type;
    @Column(nullable = false)
    @NotNull
    private Integer sticks; //(2 x 8GB)
    @Column(nullable = false)
    @NotNull
    private Integer perStickGB;
    @Column(nullable = false)
    @NotNull
    private Integer casLatency;

    public RAM() {
    }

    public RAM(String name, Double price, Integer inventoryQuantity, String image) {
        super(name, price, inventoryQuantity, image);
    }

    public RAM(String manufacturer, String speed, String type, Integer sticks, Integer perStickGB, Integer casLatency, String name, Double price, Integer inventoryQuantity, String image) {
        super(name, price, inventoryQuantity, image);
        this.manufacturer = manufacturer;
        this.speed = speed;
        this.type = type;
        this.sticks = sticks;
        this.perStickGB = perStickGB;
        this.casLatency = casLatency;
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

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
     
}
