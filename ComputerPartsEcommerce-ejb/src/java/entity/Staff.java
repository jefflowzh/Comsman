package entity;

import util.enumeration.StaffAccessRightEnum;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Staff extends User implements Serializable {

    private static final long serialVersionUID = 1L;  
    @Column(nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    private StaffAccessRightEnum role;
    @OneToMany(mappedBy = "assemblyAssignedTo")
    private List<ComputerSet> assignedComputerSets;
    @OneToMany(mappedBy = "deliveredBy")
    private List<CustomerOrder> deliveries;

    public Staff() {
    }
  
    public Staff(StaffAccessRightEnum role, String firstName, String lastName, String address, String email, String password, String contactNumber) {
        super(firstName, lastName, address, email, password, contactNumber);
        this.role = role;
        assignedComputerSets = new ArrayList<>();
        deliveries = new ArrayList<>();
    }
  
    public Enum getRole() {
        return role;
    }

    public void setRole(StaffAccessRightEnum role) {
        this.role = role;
    }

    public List<ComputerSet> getAssignedComputerSets() {
        return assignedComputerSets;
    }

    public void setAssignedComputerSets(List<ComputerSet> assignedComputerSets) {
        this.assignedComputerSets = assignedComputerSets;
    }

    public List<CustomerOrder> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(List<CustomerOrder> deliveries) {
        this.deliveries = deliveries;
    }  
}
