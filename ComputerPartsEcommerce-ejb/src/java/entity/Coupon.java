package entity;

import util.enumeration.CouponTypeEnum;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
public class Coupon implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long couponId;
    @Column(nullable = false)
    @NotNull
    private String code;
    @Column(nullable = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Column(nullable = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @Column(nullable = false)
    @NotNull
    private Integer loyaltyPointRequired;
    @Column(nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    private CouponTypeEnum couponType;
    private Double percentageRate;
    private Double flatAmount;
    @Column(nullable = false)
    @NotNull
    private Boolean isDisabled;

    public Coupon() {
    }

    public Coupon(String code, Date startDate, Date endDate, Integer loyaltyPointRequired, CouponTypeEnum couponType) {
        this();
        
        this.code = code;
        this.startDate = startDate;
        this.endDate = endDate;
        this.loyaltyPointRequired = loyaltyPointRequired;
        this.couponType = couponType;
        this.isDisabled = false;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getLoyaltyPointRequired() {
        return loyaltyPointRequired;
    }

    public void setLoyaltyPointRequired(Integer loyaltyPointRequired) {
        this.loyaltyPointRequired = loyaltyPointRequired;
    }

    public CouponTypeEnum getCouponType() {
        return couponType;
    }

    public void setCouponType(CouponTypeEnum couponType) {
        this.couponType = couponType;
    }

    public Double getPercentageRate() {
        return percentageRate;
    }

    public void setPercentageRate(Double percentageRate) {
        this.percentageRate = percentageRate;
    }

    public Double getFlatAmount() {
        return flatAmount;
    }

    public void setFlatAmount(Double flatAmount) {
        this.flatAmount = flatAmount;
    }

    public Boolean getIsDisabled() {
        return isDisabled;
    }

    public void setIsDisabled(Boolean isDisabled) {
        this.isDisabled = isDisabled;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (couponId != null ? couponId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Coupon)) {
            return false;
        }
        Coupon other = (Coupon) object;
        if ((this.couponId == null && other.couponId != null) || (this.couponId != null && !this.couponId.equals(other.couponId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Coupon[ id=" + couponId + " ]";
    }
    
}
