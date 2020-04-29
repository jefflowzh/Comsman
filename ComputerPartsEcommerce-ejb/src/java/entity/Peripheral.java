package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
public class Peripheral extends Product implements Serializable {

    private static final long serialVersionUID = 1L;

    private String manufacturer;
    private String description;
    
    public Peripheral() {
    }

    public Peripheral(String name, Double price, Integer inventoryQuantity, String image, String manufacturer) {
        super(name, price, inventoryQuantity, image, manufacturer);
    }
    
    public Peripheral(String manufacturer, String description, String name, Double price, Integer inventoryQuantity, String image) {
        super(name, price, inventoryQuantity, image, manufacturer);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
 
}
