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
import javax.validation.constraints.NotNull;
import util.enumeration.PreBuiltComputerSetTierEnum;

@Entity
public class PreBuiltComputerSetModel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long preBuiltComputerSetModelId;
    @Column
    private Integer warrentyLengthInYears;
    @Column(nullable = false)
    @NotNull
    private Double price;
    @Column(nullable = false, unique = true)
    @NotNull
    private PreBuiltComputerSetTierEnum preBuiltComputerSetTier;
    @Column(nullable = false)
    @NotNull
    private Boolean isEnabled;

    @ManyToOne
    @JoinColumn
    private CPU cpu;
    
    @ManyToOne
    @JoinColumn
    private MotherBoard motherboard;
    
    @ManyToMany
    @JoinColumn
    private List<RAM> rams;
    
    @ManyToOne
    @JoinColumn
    private PowerSupply psu;
    
    @ManyToOne
    @JoinColumn
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

    public PreBuiltComputerSetModel() {
        this.rams = new ArrayList<>();
        this.gpus = new ArrayList<>();
        this.hdds = new ArrayList<>();
        this.ssds = new ArrayList<>();
        price = 0.0;
        isEnabled = false;
    }

    public PreBuiltComputerSetModel(PreBuiltComputerSetTierEnum preBuiltComputerSetTier) {
        this();
        this.preBuiltComputerSetTier = preBuiltComputerSetTier;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (preBuiltComputerSetModelId != null ? preBuiltComputerSetModelId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the preBuiltComputerSetModelId fields are not set
        if (!(object instanceof PreBuiltComputerSetModel)) {
            return false;
        }
        PreBuiltComputerSetModel other = (PreBuiltComputerSetModel) object;
        if ((this.preBuiltComputerSetModelId == null && other.preBuiltComputerSetModelId != null) || (this.preBuiltComputerSetModelId != null && !this.preBuiltComputerSetModelId.equals(other.preBuiltComputerSetModelId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.PreBuiltComputerSetModel[ id=" + preBuiltComputerSetModelId + " ]";
    }

    public Long getPreBuiltComputerSetModelId() {
        return preBuiltComputerSetModelId;
    }

    public void setPreBuiltComputerSetModelId(Long preBuiltComputerSetModelId) {
        this.preBuiltComputerSetModelId = preBuiltComputerSetModelId;
    }

    public Integer getWarrentyLengthInYears() {
        return warrentyLengthInYears;
    }

    public void setWarrentyLengthInYears(Integer warrentyLengthInYears) {
        this.warrentyLengthInYears = warrentyLengthInYears;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public PreBuiltComputerSetTierEnum getPreBuiltComputerSetTier() {
        return preBuiltComputerSetTier;
    }

    public void setPreBuiltComputerSetTier(PreBuiltComputerSetTierEnum preBuiltComputerSetTier) {
        this.preBuiltComputerSetTier = preBuiltComputerSetTier;
    }

    public Boolean getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }
    
    public CPU getCpu() {
        return cpu;
    }

    public void setCpu(CPU cpu) {
        this.cpu = cpu;
    }

    public MotherBoard getMotherboard() {
        return motherboard;
    }

    public void setMotherboard(MotherBoard motherboard) {
        this.motherboard = motherboard;
    }

    public List<RAM> getRams() {
        return rams;
    }

    public void setRams(List<RAM> rams) {
        this.rams = rams;
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

    public List<GPU> getGpus() {
        return gpus;
    }

    public void setGpus(List<GPU> gpus) {
        this.gpus = gpus;
    }

    public List<HDD> getHdds() {
        return hdds;
    }

    public void setHdds(List<HDD> hdds) {
        this.hdds = hdds;
    }

    public List<SSD> getSsds() {
        return ssds;
    }

    public void setSsds(List<SSD> ssds) {
        this.ssds = ssds;
    }

    public CPUWaterCooler getWaterCooler() {
        return waterCooler;
    }

    public void setWaterCooler(CPUWaterCooler waterCooler) {
        this.waterCooler = waterCooler;
    }

    public CPUAirCooler getAirCooler() {
        return airCooler;
    }

    public void setAirCooler(CPUAirCooler airCooler) {
        this.airCooler = airCooler;
    }
}
