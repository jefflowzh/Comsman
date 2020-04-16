package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class LineItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lineItemId;
    @ManyToOne
    @JoinColumn
    private Product product;
    @Column(nullable = false)
    @NotNull
    private Integer quantity;
    @OneToOne (mappedBy = "lineItem")
    private ComputerSet computerSet;
    @ManyToOne
    @JoinColumn
    private CustomerOrder customerOrder;

    public LineItem() {
    }

    public LineItem(Product product, Integer quantity) {
        this();
        
        this.product = product;
        this.quantity = quantity;
    }
    
    // In the case of computer set, because you can't create a computer set before creating a line item first
    public LineItem(Integer quantity) {
        this();
        
        this.quantity = quantity;
    }
    
    public Long getLineItemId() {
        return lineItemId;
    }

    public void setLineItemId(Long lineItemId) {
        this.lineItemId = lineItemId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ComputerSet getComputerSet() {
        return computerSet;
    }

    public void setComputerSet(ComputerSet computerSet) {
        this.computerSet = computerSet;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lineItemId != null ? lineItemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LineItem)) {
            return false;
        }
        LineItem other = (LineItem) object;
        if ((this.lineItemId == null && other.lineItemId != null) || (this.lineItemId != null && !this.lineItemId.equals(other.lineItemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.LineItem[ id=" + lineItemId + " ]";
    }

    public CustomerOrder getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }
}
