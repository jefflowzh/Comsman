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
import java.sql.Timestamp;
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
import util.exception.CustomerNotFoundException;
import util.exception.StaffAlreadyExistsException;
import util.exception.StaffNotFoundException;

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
    public void postConstruct() {
        try {
            // check if there is anything in database
            // customerSessionBean.retrieveCustomerByEmail("email@email.com");
            staffSessionBean.retrieveStaffByEmail("wd@email.com", true, true);
        } catch (StaffNotFoundException ex) {
            initializeData();

        }
    }

    private void initializeData() {

        try {
            Staff testStaff = new Staff(StaffAccessRightEnum.MANAGER, "StaffFN", "StaffLN", "Staff Address", "wd@email.com", "password", "12345");
            staffSessionBean.createNewStaff(testStaff);
            Staff testStaffTech = new Staff(StaffAccessRightEnum.TECHNICIAN, "StaffFN", "StaffLN", "Staff Address", "tech@email.com", "password", "12345");
            staffSessionBean.createNewStaff(testStaffTech);
        } catch(StaffAlreadyExistsException ex) {
            System.out.println("Staff already exists");
        }

        ComputerPart testComputerPart = new ComputerPart("Computer Part 1", 100.00, 10, "image");
        computerPartSessionBean.createNewComputerPart(testComputerPart);
        ComputerPart testComputerPart2 = new ComputerPart("Computer Part 2", 100.00, 10, "image");
        computerPartSessionBean.createNewComputerPart(testComputerPart2);

//        List<ComputerPart> testComputerParts = new ArrayList<>();
//        testComputerParts.add(testComputerPart);
//        ComputerSet testComputerSet = new ComputerSet(testComputerParts, 1, true, testStaff, "Computer Set 1", 1.0, 1, "image");
//        computerSetSessionBean.createNewComputerSet(testComputerSet);
//        
//        Coupon testCoupon = new Coupon("YAY2020", new Date(), new Date(), 1, CouponTypeEnum.PERCENTAGE);
//        couponSessionBean.createNewCoupon(testCoupon);
//        
        Customer testCustomer = new Customer("cardNum", "ccv", "Customer1", "Customer1", "Customer Address", "customer@email.com", "password", "12345678");
        customerSessionBean.createNewCustomer(testCustomer);
//        
        LineItem testLineItem = new LineItem(testComputerPart, 1);
        lineItemSessionBean.createNewLineItem(testLineItem);
        LineItem testLineItem2 = new LineItem(testComputerPart2, 5);
        lineItemSessionBean.createNewLineItem(testLineItem2);
        LineItem testLineItem3 = new LineItem(testComputerPart, 100);
        lineItemSessionBean.createNewLineItem(testLineItem3);
//      
        try {
            List<LineItem> testLineItems = new ArrayList<>();
            testLineItems.add(testLineItem);
            testLineItems.add(testLineItem2);
            Date date = new Date();
            CustomerOrder testCustomerOrder = new CustomerOrder(new Timestamp(date.getTime()), true, "Billing address", testLineItems);
            customerOrderSessionBean.createNewCustomerOrder(testCustomerOrder, (long) 3);
            
            } catch (CustomerNotFoundException ex) {
            System.out.println("fail to create singleton orders for initial data !! >> " + ex.getMessage());
        }
        
       try {
            List<LineItem> testLineItems2 = new ArrayList<>();
            testLineItems2.add(testLineItem3);
            Date date2 = new Date();
            CustomerOrder testCustomerOrder2 = new CustomerOrder(new Timestamp(date2.getTime()), true, "Billing address", testLineItems2);
            customerOrderSessionBean.createNewCustomerOrder(testCustomerOrder2, (long) 3);
            
            } catch (CustomerNotFoundException ex) {
            System.out.println("fail to create singleton orders for initial data !! >> " + ex.getMessage());
        }
    }

}
