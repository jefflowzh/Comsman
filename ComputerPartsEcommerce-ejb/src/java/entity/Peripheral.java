package entity;

import java.io.Serializable;
import javax.persistence.Entity;

@Entity
public class Peripheral extends Product implements Serializable {

    private static final long serialVersionUID = 1L;

    private String manufacturer;
    
    public Peripheral() {
    }

    public Peripheral(String name, Double price, Integer inventoryQuantity, String image) {
        super(name, price, inventoryQuantity, image);
    }
    
    public Peripheral(String manufacturer, String description, String name, Double price, Integer inventoryQuantity, String image) {
        super(name, price, inventoryQuantity, image);
        this.manufacturer = manufacturer;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
 
}
