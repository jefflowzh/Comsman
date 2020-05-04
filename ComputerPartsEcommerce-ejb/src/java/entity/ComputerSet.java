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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class ComputerSet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long computerSetId;
    
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
    @ManyToOne
    @JoinColumn(nullable = false)
    private PowerSupply psu;
    
    @NotNull
    @ManyToOne
    @JoinColumn(nullable = false)
    private ComputerCase compCase;
    
    @ManyToMany
    private List<GPU> gpus;
    
    @ManyToMany
    private List<HDD> hdds;
            
    @ManyToMany
    private List<SSD> ssds;
    
    @ManyToOne
    private CPUWaterCooler waterCooler;
            
    @ManyToOne
    private CPUAirCooler airCooler;
    
    @Column(nullable = false)
    @NotNull
    private Integer warrentyLengthInYears;
    @ManyToOne
    @JoinColumn
    private Staff assemblyAssignedTo;
    @Column(nullable = false)
    @NotNull
    private Boolean assemblyComplete;
    @OneToOne(optional = false)
    @JoinColumn (nullable = false)
    private LineItem lineItem;
    @Column(nullable = false)
    @NotNull
    private Double price;
    
    public ComputerSet(){
        this.rams = new ArrayList<>();
        this.gpus = new ArrayList<>();
        this.hdds = new ArrayList<>();
        this.ssds = new ArrayList<>();
        this.price = 0.0;
        this.assemblyComplete = false;
    }

    public ComputerSet(Integer warrentyLengthInYears, Boolean isAmatuer) {
        this();
        this.warrentyLengthInYears = warrentyLengthInYears;
        // assuming warrenty is $100/year
        this.price += (warrentyLengthInYears - 3) * 100; 
    }
    
    public CPU getCpu() {
        return cpu;
    }

    public void setCpu(CPU cpu) {
        this.cpu = cpu;
        this.price += cpu.getPrice();
    }

    public MotherBoard getMotherBoard() {
        return motherBoard;
    }

    public void setMotherBoard(MotherBoard motherBoard) {
        this.motherBoard = motherBoard;
        this.price += motherBoard.getPrice();
    }

    public PowerSupply getPsu() {
        return psu;
    }

    public void setPsu(PowerSupply psu) {
        this.psu = psu;
        this.price += psu.getPrice();
    }

    public ComputerCase getCompCase() {
        return compCase;
    }

    public void setCompCase(ComputerCase compCase) {
        this.compCase = compCase;
        this.price += compCase.getPrice();
    }

    public List<RAM> getRams() {
        return rams;
    }

    public void setRams(List<RAM> rams) {
        this.rams = rams;
    }
    
    public void addRam(RAM ram){
        this.rams.add(ram);
        this.price += ram.getPrice();
    }

    public List<GPU> getGpus() {
        return gpus;
    }

    public void setGpus(List<GPU> gpus) {
        this.gpus = gpus;
    }
    
    public void addGpu(GPU gpu){
        this.gpus.add(gpu);
        this.price += gpu.getPrice();
    }

    public List<HDD> getHdds() {
        return hdds;
    }

    public void setHdds(List<HDD> hdds) {
        this.hdds = hdds;
    }
    
    public void addHdd(HDD hdd){
        this.hdds.add(hdd);
        this.price += hdd.getPrice();
    }

    public List<SSD> getSsds() {
        return ssds;
    }

    public void setSsds(List<SSD> ssds) {
        this.ssds = ssds;
    }

    public void addSsd(SSD ssd){
        this.ssds.add(ssd);
        this.price += ssd.getPrice();
    }
    
    public Integer getWarrentyLengthInYears() {
        return warrentyLengthInYears;
    }

    public void setWarrentyLengthInYears(Integer warrentyLengthInYears) {
        this.warrentyLengthInYears = warrentyLengthInYears;
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

    public Long getComputerSetId() {
        return computerSetId;
    }

    public void setComputerSetId(Long computerSetId) {
        this.computerSetId = computerSetId;
    }

    public CPUWaterCooler getWaterCooler() {
        return waterCooler;
    }

    public void setWaterCooler(CPUWaterCooler waterCooler) {
        this.waterCooler = waterCooler;
        this.price += waterCooler.getPrice();
    }

    public CPUAirCooler getAirCooler() {
        return airCooler;
    }

    public void setAirCooler(CPUAirCooler airCooler) {
        this.airCooler = airCooler;
        this.price += airCooler.getPrice();
    }
    
    public LineItem getLineItem() {
        return lineItem;
    }

    public void setLineItem(LineItem lineItem) {
        this.lineItem = lineItem;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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
