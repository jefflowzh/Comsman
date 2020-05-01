package jsf.managedbean;

import ejb.session.stateless.CouponSessionBeanLocal;
import entity.Coupon;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import util.enumeration.CouponTypeEnum;
import util.exception.CouponNotFoundException;

@Named(value = "couponManagementManagedBean")
@ViewScoped
public class CouponManagementManagedBean implements Serializable {

    @EJB(name = "CouponSessionBeanLocal")
    private CouponSessionBeanLocal couponSessionBeanLocal;
    
    private List<Coupon> coupons;
    private List<Coupon> filteredCoupons;
    
    private Coupon newCoupon;
    private Coupon selectedCouponToView;
    private Coupon selectedCouponToUpdate;
    private Coupon selectedCouponToDelete;
    
    private CouponTypeEnum tempEnum;
    
    private CouponTypeEnum[] couponTypes;
    
    public CouponManagementManagedBean() {
    }
    
    @PostConstruct
    public void postConstruct() {
        newCoupon = new Coupon();
        selectedCouponToUpdate = new Coupon();
        setCouponTypes(CouponTypeEnum.values());
        setCoupons(couponSessionBeanLocal.retrieveAllCoupons());
    }
    
    public void createNewCoupon(ActionEvent event) {
        try {
            System.out.println("******** code: " + newCoupon.getCode());
            System.out.println("******** start: " + newCoupon.getStartDate());
            System.out.println("******** end: " + newCoupon.getEndDate());
            System.out.println("******** points: " + newCoupon.getLoyaltyPointRequired());
            System.out.println("******** type: " + newCoupon.getCouponType());
            System.out.println("******** rate: " + newCoupon.getRate());
            System.out.println("******** amount: " + newCoupon.getFlatAmount());
            System.out.println("******** disabled: " + newCoupon.getIsDisabled());
            Long newCouponId = couponSessionBeanLocal.createNewCoupon(getNewCoupon());
            setNewCoupon(couponSessionBeanLocal.retrieveCouponById(newCouponId));
            getCoupons().add(getNewCoupon());

            setTempEnum(null);
            setNewCoupon(new Coupon());

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Coupon " + newCouponId + " created successfully!", null));
        } catch (CouponNotFoundException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Coupon does not exist!", null));
        }
    }
    
    public void test(UIComponent component) {
        System.out.println("****TEST" + component.getChildCount());
        FacesContext.getCurrentInstance().addMessage("formAllCoupons:test123", new FacesMessage(FacesMessage.SEVERITY_INFO, "testing123", "testing12"));
    }
    
    public void doUpdateCoupon(ActionEvent event) {
        setSelectedCouponToUpdate((Coupon) event.getComponent().getAttributes().get("selectedCouponToUpdate"));
    }
    
    public void updateCoupon(ActionEvent event) {
        if (selectedCouponToUpdate.getCouponType() != CouponTypeEnum.FLAT_AMOUNT) {
            selectedCouponToUpdate.setFlatAmount(null);
        }
        if (selectedCouponToUpdate.getCouponType() != CouponTypeEnum.PERCENTAGE) {
            selectedCouponToUpdate.setRate(null);
        }
        couponSessionBeanLocal.updateCoupon(getSelectedCouponToUpdate());
        setSelectedCouponToUpdate(new Coupon());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Coupon updated successfully", null));
    }
    
    public void doDeleteCoupon(ActionEvent event) {
        setSelectedCouponToDelete((Coupon) event.getComponent().getAttributes().get("selectedCouponToDelete"));
    }
    
    public void deleteCoupon(ActionEvent event) {
        selectedCouponToDelete = (Coupon) event.getComponent().getAttributes().get("selectedCouponToDelete");
        try {
            couponSessionBeanLocal.deleteCoupon(getSelectedCouponToDelete().getCouponId());
            getCoupons().remove(getSelectedCouponToDelete());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Coupon deleted successfully", null));
        } catch (CouponNotFoundException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error occured while deleting coupon: " + ex.getMessage(), null));
        }
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }

    public List<Coupon> getFilteredCoupons() {
        return filteredCoupons;
    }

    public void setFilteredCoupons(List<Coupon> filteredCoupons) {
        this.filteredCoupons = filteredCoupons;
    }

    public Coupon getNewCoupon() {
        return newCoupon;
    }

    public void setNewCoupon(Coupon newCoupon) {
        this.newCoupon = newCoupon;
    }
    
    public Coupon getSelectedCouponToView() {
        return selectedCouponToView;
    }

    public void setSelectedCouponToView(Coupon selectedCouponToView) {
        this.selectedCouponToView = selectedCouponToView;
    }

    public Coupon getSelectedCouponToUpdate() {
        return selectedCouponToUpdate;
    }

    public void setSelectedCouponToUpdate(Coupon selectedCouponToUpdate) {
        this.selectedCouponToUpdate = selectedCouponToUpdate;
    }

    public Coupon getSelectedCouponToDelete() {
        return selectedCouponToDelete;
    }

    public void setSelectedCouponToDelete(Coupon selectedCouponToDelete) {
        this.selectedCouponToDelete = selectedCouponToDelete;
    }

    public CouponTypeEnum getTempEnum() {
        return tempEnum;
    }

    public void setTempEnum(CouponTypeEnum tempEnum) {
        this.tempEnum = tempEnum;
    }

    public CouponTypeEnum[] getCouponTypes() {
        return couponTypes;
    }

    public void setCouponTypes(CouponTypeEnum[] couponTypes) {
        this.couponTypes = couponTypes;
    }

    public UIComponent getComponent() {
        return component;
    }

    public void setComponent(UIComponent component) {
        this.component = component;
    }
}
