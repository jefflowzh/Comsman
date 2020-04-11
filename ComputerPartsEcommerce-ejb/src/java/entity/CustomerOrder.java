package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import util.enumeration.OrderStatusEnum;

@Entity
public class CustomerOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerOrderId;
    @Column(nullable = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;
    @Column(nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fulfilledDate;
    @Column(nullable = false)
    @NotNull
    private Double totalPrice;
    @Column(nullable = false)
    @NotNull
    private Boolean requiresDelivery;
    private String deliveryAddress;
    @Column(nullable = false)
    @NotNull
    private String billingAddress;
    @OneToMany
    private List<LineItem> lineItems;
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Customer customer;
    @ManyToOne
    @JoinColumn
    private Staff deliveryAssignedTo;
    @ManyToOne
    @JoinColumn
    private Coupon coupon;
    @Column(nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    private OrderStatusEnum orderStatus;
   // @OneToOne(mappedBy = "customerOrder")
   // private ComputerSet computerSet;
    

    public CustomerOrder() {
        this.totalPrice = 0.0;
        this.orderStatus = OrderStatusEnum.UNASSIGNED;
    }

    public CustomerOrder(Date orderDate, Boolean requiresDelivery, String billingAddress, List<LineItem> lineItems) {
        this();
        
        for(LineItem l : lineItems) {
            if (l.getComputerSet() != null) {
                this.totalPrice += l.getComputerSet().getPrice() * l.getQuantity();
            } else {
                this.totalPrice += l.getProduct().getPrice() * l.getQuantity();
            }
        }
        
        System.out.println("********************" + this.totalPrice);
        this.orderDate = orderDate;
        this.requiresDelivery = requiresDelivery;
        this.billingAddress = billingAddress;
        this.lineItems = lineItems;
    }

    public Long getCustomerOrderId() {
        return customerOrderId;
    }

    public void setCustomerOrderId(Long customerOrderId) {
        this.customerOrderId = customerOrderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getFulfilledDate() {
        return fulfilledDate;
    }

    public void setFulfilledDate(Date fulfilledDate) {
        this.fulfilledDate = fulfilledDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Boolean getRequiresDelivery() {
        return requiresDelivery;
    }

    public void setRequiresDelivery(Boolean requiresDelivery) {
        this.requiresDelivery = requiresDelivery;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Staff getDeliveryAssignedTo() {
        return deliveryAssignedTo;
    }

    public void setDeliveryAssignedTo(Staff deliveryAssignedTo) {
        this.deliveryAssignedTo = deliveryAssignedTo;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    /*
    public ComputerSet getComputerSet() {
        return computerSet;
    }

    public void setComputerSet(ComputerSet computerSet) {
        this.computerSet = computerSet;
    }
*/
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (customerOrderId != null ? customerOrderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CustomerOrder)) {
            return false;
        }
        CustomerOrder other = (CustomerOrder) object;
        if ((this.customerOrderId == null && other.customerOrderId != null) || (this.customerOrderId != null && !this.customerOrderId.equals(other.customerOrderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Order[ id=" + customerOrderId + " ]";
    }

    public OrderStatusEnum getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatusEnum orderStatus) {
        this.orderStatus = orderStatus;
    }
    
}
