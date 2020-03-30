package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class ComputerSet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long computerSetId;
    //@OneToMany
    //private List<ComputerPart> computerParts;
    
    /*For a computer set there are mandatory computer parts
    cpu 
    motherboard
    ram
    case
    powersupply
    storage (HDD or SDD)
    */
    
    
    
    @NotNull
    @ManyToOne
    @JoinColumn(nullable = false)
    private CPU cpu;
    
    @NotNull
    @ManyToOne
    @JoinColumn(nullable = false)
    private MotherBoard motherBoard;
    
    @NotNull
    @ManyToMany
    @JoinColumn(nullable = false)
    private List<RAM> rams;
    
    @NotNull
    @ManyToMany
    @JoinColumn(nullable = false)
    private PowerSupply psu;
    
    @NotNull
    @ManyToOne
    @JoinColumn(nullable = false)
    private ComputerCase compCase;
    
    @ManyToMany
    //@JoinColumn(name = )
    private List<GPU> gpus;
    
    @ManyToMany
    //@JoinColumn(name = )
    private List<HDD> hdds;
            
    @ManyToMany
    //@JoinColumn(name = )
    private List<SSD> ssds;
    
    @ManyToOne
    //@JoinColumn(name = )
    private CPUWaterCooler waterCooler;
            
    @ManyToOne
    //@JoinColumn(name = )
    private CPUAirCooler airCooler;
    
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

    
    /* original constructor
    public ComputerSet(List<ComputerPart> computerParts, Integer warrentyLengthInYears, Boolean isAmatuer, Staff assemblyAssignedTo, String name, Double price, Integer inventoryQuantity, String image) {
=======

    public ComputerSet(List<ComputerPart> computerParts, Integer warrentyLengthInYears, Boolean isAmatuer, String name, Double price, Integer inventoryQuantity, String image) {
>>>>>>> 79ae37ff48bf11c063a4b521bd8a742ae28be54b
        super(name, price, inventoryQuantity, image);
        this.computerParts = computerParts;
        this.warrentyLengthInYears = warrentyLengthInYears;
        this.isAmatuer = isAmatuer;      
<<<<<<< HEAD
        assemblyComplete = false;
    }   */
    
    // new constructor 
    
   
    
    public CPU getCpu() {
        return cpu;
    }

    public void setCpu(CPU cpu) {
        this.cpu = cpu;
    }

    public MotherBoard getMotherBoard() {
        return motherBoard;
    }

    public void setMotherBoard(MotherBoard motherBoard) {
        this.motherBoard = motherBoard;
    }

    public PowerSupply getPsu() {
        return psu;
    }

    public void setPsu(PowerSupply psu) {
        this.psu = psu;
    }

    public ComputerCase getCompCase() {
        return compCase;
    }

    public void setCompCase(ComputerCase compCase) {
        this.compCase = compCase;
    }

    public List<RAM> getRams() {
        return rams;
    }

    public void setRams(List<RAM> rams) {
        this.rams = rams;
    }
    
    public void addRam(RAM ram){
        this.rams.add(ram);
    }

    public List<GPU> getGpus() {
        return gpus;
    }

    public void setGpus(List<GPU> gpus) {
        this.gpus = gpus;
    }
    
    public void addGpu(GPU gpu){
        this.gpus.add(gpu);
    }

    public List<HDD> getHdds() {
        return hdds;
    }

    public void setHdds(List<HDD> hdds) {
        this.hdds = hdds;
    }
    
    public void addHdd(HDD hdd){
        this.hdds.add(hdd);
    }

    public List<SSD> getSsds() {
        return ssds;
    }

    public void setSsds(List<SSD> ssds) {
        this.ssds = ssds;
    }

    public void addSsd(SSD ssd){
        this.ssds.add(ssd);
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
    
      @Override
    public int hashCode() {
        int hash = 0;
        hash += (computerSetId != null ? computerSetId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ComputerSet)) {
            return false;
        }
        ComputerSet other = (ComputerSet) object;
        if ((this.computerSetId == null && other.computerSetId != null) || (this.computerSetId != null && !this.computerSetId.equals(other.computerSetId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ComputerSet[ id=" + computerSetId + " ]";
    }
    
    
}
