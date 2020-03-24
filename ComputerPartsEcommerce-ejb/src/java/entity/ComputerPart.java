package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ComputerPart extends Product implements Serializable {

    private static final long serialVersionUID = 1L;

    public ComputerPart() {
    }

    public ComputerPart(String name, Double price, Integer inventoryQuantity, String image) {
        super(name, price, inventoryQuantity, image);
    }
 
}
