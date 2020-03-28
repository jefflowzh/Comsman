package ejb.session.singleton;

import ejb.session.stateless.ComputerPartSessionBeanLocal;
import ejb.session.stateless.ComputerSetSessionBeanLocal;
import ejb.session.stateless.CouponSessionBeanLocal;
import ejb.session.stateless.CustomerSessionBeanLocal;
import ejb.session.stateless.LineItemSessionBeanLocal;
import ejb.session.stateless.CustomerOrderSessionBeanLocal;
import ejb.session.stateless.StaffSessionBeanLocal;
import entity.ComputerPart;
import entity.ComputerSet;
import entity.Coupon;
import entity.Customer;
import entity.LineItem;
import entity.CustomerOrder;
import entity.Staff;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import util.enumeration.CouponTypeEnum;
import util.enumeration.StaffAccessRightEnum;

@Singleton
@LocalBean
@Startup
public class DataInitSessionBean {

    @EJB
    private ComputerPartSessionBeanLocal computerPartSessionBean;
    @EJB
    private ComputerSetSessionBeanLocal computerSetSessionBean;
    @EJB
    private CouponSessionBeanLocal couponSessionBean;
    @EJB
    private CustomerSessionBeanLocal customerSessionBean;
    @EJB
    private LineItemSessionBeanLocal lineItemSessionBean;
    @EJB
    private CustomerOrderSessionBeanLocal customerOrderSessionBean;
    @EJB
    private StaffSessionBeanLocal staffSessionBean;

    public DataInitSessionBean() {
    }
    
    @PostConstruct
    public void postConstruct()
    {
        try
        {
            // check if there is anything in database
            // customerSessionBean.retrieveCustomerByEmail("email@email.com");
            staffSessionBean.retrieveStaffByEmail("wd@email.com", true, true);
        }
        catch(Exception ex)
        {
            initializeData();
        }
    }
    
    private void initializeData(){
//        ComputerPart testComputerPart = new ComputerPart("Computer Part 1", 1.0, 1, "image");
//        computerPartSessionBean.createNewComputerPart(testComputerPart);
//        
        Staff testStaff = new Staff(StaffAccessRightEnum.MANAGER, "StaffFN", "StaffLN", "Staff Address", "wd@email.com", "password", "12345");
        staffSessionBean.createNewStaff(testStaff);
        Staff testStaffTech = new Staff(StaffAccessRightEnum.TECHNICIAN, "StaffFN", "StaffLN", "Staff Address", "tech@email.com", "password", "12345");
        staffSessionBean.createNewStaff(testStaffTech);
//        
//        List<ComputerPart> testComputerParts = new ArrayList<>();
//        testComputerParts.add(testComputerPart);
//        ComputerSet testComputerSet = new ComputerSet(testComputerParts, 1, true, testStaff, "Computer Set 1", 1.0, 1, "image");
//        computerSetSessionBean.createNewComputerSet(testComputerSet);
//        
//        Coupon testCoupon = new Coupon("YAY2020", new Date(), new Date(), 1, CouponTypeEnum.PERCENTAGE);
//        couponSessionBean.createNewCoupon(testCoupon);
//        
//        Customer testCustomer = new Customer("123", "123", "Customer1", "Customer1", "Customer Address", "Customer Email", "Customer Password", "12345");
//        customerSessionBean.createNewCustomer(testCustomer);
//        
//        LineItem testLineItem = new LineItem(testComputerPart, 1);
//        lineItemSessionBean.createNewLineItem(testLineItem);
//        
//        List<LineItem> testLineItems = new ArrayList<>();
//        testLineItems.add(testLineItem);
//        CustomerOrder testCustomerOrder = new CustomerOrder(new Date(), 1.0, true, "Billing address", testLineItems, testCustomer);
//        customerOrderSessionBean.createNewCustomerOrder(testCustomerOrder);   
    }
    
}
