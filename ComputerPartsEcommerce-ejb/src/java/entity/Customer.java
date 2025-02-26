package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Customer extends User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(length = 16)
    private String cardNumber;
    @Column(length = 3)
    private String ccv;
    @OneToMany
    @NotNull
    private List<LineItem> cart;
    @OneToMany
    @NotNull
    private List<ComputerPart> currComputerBuild;
    @Column(nullable = false)
    @NotNull
    private Integer loyaltyPoints;
    @OneToMany(mappedBy = "customer")
    @NotNull
    private List<CustomerOrder> orders;

    public Customer() {
        cart = new ArrayList<>();
        loyaltyPoints = 0;
        orders = new ArrayList<>();
        currComputerBuild = new ArrayList<>();
    }
    
    public Customer(String firstName, String lastName, String address, String email, String password, String contactNumber) {
        super(firstName, lastName, address, email, password, contactNumber);
        
        cart = new ArrayList<>();
        loyaltyPoints = 0;
        orders = new ArrayList<>();
        currComputerBuild = new ArrayList<>();
    }
    
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCcv() {
        return ccv;
    }

    public void setCcv(String ccv) {
        this.ccv = ccv;
    }

    public List<LineItem> getCart() {
        return cart;
    }

    public void setCart(List<LineItem> cart) {
        this.cart = cart;
    }
    
    public List<ComputerPart> getCurrComputerBuild() {
        return currComputerBuild;
    }

    public void setCurrComputerBuild(List<ComputerPart> currComputerBuild) {
        this.currComputerBuild = currComputerBuild;
    }

    public Integer getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(Integer loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    public List<CustomerOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<CustomerOrder> orders) {
        this.orders = orders;
    }
}
