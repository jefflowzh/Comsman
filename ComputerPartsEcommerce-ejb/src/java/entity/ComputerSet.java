package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class ComputerSet extends Product implements Serializable {

    private static final long serialVersionUID = 1L;
    @OneToMany
    private List<ComputerPart> computerParts;
    @Column(nullable = false)
    @NotNull
    private Integer warrentyLengthInYears;
    @Column(nullable = false)
    @NotNull
    private Boolean isAmatuer;
    @ManyToOne
    @JoinColumn
    private Staff assemblyAssignedTo;
    @Column(nullable = false)
    @NotNull
    private Boolean assemblyComplete;
    
    public ComputerSet(){
    }

    public ComputerSet(List<ComputerPart> computerParts, Integer warrentyLengthInYears, Boolean isAmatuer, String name, Double price, Integer inventoryQuantity, String image) {
        super(name, price, inventoryQuantity, image);
        this.computerParts = computerParts;
        this.warrentyLengthInYears = warrentyLengthInYears;
        this.isAmatuer = isAmatuer;
        
        this.assemblyComplete = false;
    }   

    public List<ComputerPart> getComputerParts() {
        return computerParts;
    }

    public void setComputerParts(List<ComputerPart> computerParts) {
        this.computerParts = computerParts;
    }

    public Integer getWarrentyLengthInYears() {
        return warrentyLengthInYears;
    }

    public void setWarrentyLengthInYears(Integer warrentyLengthInYears) {
        this.warrentyLengthInYears = warrentyLengthInYears;
    }

    public Boolean getIsAmatuer() {
        return isAmatuer;
    }

    public void setIsAmatuer(Boolean isAmatuer) {
        this.isAmatuer = isAmatuer;
    }

    public Staff getAssemblyAssignedTo() {
        return assemblyAssignedTo;
    }

    public void setAssemblyAssignedTo(Staff assemblyAssignedTo) {
        this.assemblyAssignedTo = assemblyAssignedTo;
    }

    public Boolean getAssemblyComplete() {
        return assemblyComplete;
    }

    public void setAssemblyComplete(Boolean assemblyComplete) {
        this.assemblyComplete = assemblyComplete;
    }
    
    
}
