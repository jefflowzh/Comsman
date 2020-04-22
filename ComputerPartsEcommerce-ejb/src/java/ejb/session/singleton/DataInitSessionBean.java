package ejb.session.singleton;

import ejb.session.stateless.ComputerPartSessionBeanLocal;
import ejb.session.stateless.ComputerSetSessionBeanLocal;
import ejb.session.stateless.CouponSessionBeanLocal;
import ejb.session.stateless.CustomerSessionBeanLocal;
import ejb.session.stateless.LineItemSessionBeanLocal;
import ejb.session.stateless.CustomerOrderSessionBeanLocal;
import ejb.session.stateless.PeripheralSessionBeanLocal;
import ejb.session.stateless.StaffSessionBeanLocal;
import entity.ComputerPart;
import entity.ComputerSet;
import entity.Coupon;
import entity.Customer;
import entity.LineItem;
import entity.CustomerOrder;
import entity.Peripheral;
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
    private PeripheralSessionBeanLocal peripheralSessionBean;
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
        } catch (StaffAlreadyExistsException ex) {
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
        Customer testCustomer = new Customer( "Customer1", "Customer1", "Customer Address", "customer@email.com", "password", "12345678");
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
       
       
       
       
        Peripheral testPeripheral1 = new Peripheral("Peripheral 1", 1.0, 1, "Image 1");
        peripheralSessionBean.createNewPeripheral(testPeripheral1);
        Peripheral testPeripheral2 = new Peripheral("Peripheral 2", 2.0, 2, "Image 2");
        peripheralSessionBean.createNewPeripheral(testPeripheral2);
        Peripheral testPeripheral3 = new Peripheral("Peripheral 3", 3.0, 3, "Image 3");
        peripheralSessionBean.createNewPeripheral(testPeripheral3);
        Peripheral testPeripheral4 = new Peripheral("Peripheral 4", 4.0, 4, "Image 4");
        peripheralSessionBean.createNewPeripheral(testPeripheral4);
        Peripheral testPeripheral5 = new Peripheral("Peripheral 5", 5.0, 5, "Image 5");
        peripheralSessionBean.createNewPeripheral(testPeripheral5);
        Peripheral testPeripheral6 = new Peripheral("Peripheral 6", 6.0, 6, "Image 6");
        peripheralSessionBean.createNewPeripheral(testPeripheral6);
        Peripheral testPeripheral7 = new Peripheral("Peripheral 7", 1.0, 1, "Image 1");
        peripheralSessionBean.createNewPeripheral(testPeripheral7);
        Peripheral testPeripheral8 = new Peripheral("Peripheral 8", 2.0, 2, "Image 2");
        peripheralSessionBean.createNewPeripheral(testPeripheral8);
        Peripheral testPeripheral9 = new Peripheral("Peripheral 9", 3.0, 3, "Image 3");
        peripheralSessionBean.createNewPeripheral(testPeripheral9);
        Peripheral testPeripheral10 = new Peripheral("Peripheral 10", 1.0, 1, "Image 1");
        peripheralSessionBean.createNewPeripheral(testPeripheral10);
        Peripheral testPeripheral11 = new Peripheral("Peripheral 11", 2.0, 2, "Image 2");
        peripheralSessionBean.createNewPeripheral(testPeripheral11);
        Peripheral testPeripheral12 = new Peripheral("Peripheral 12", 3.0, 3, "Image 3");
        peripheralSessionBean.createNewPeripheral(testPeripheral12);
        Peripheral testPeripheral13 = new Peripheral("Peripheral 13", 6.0, 6, "Image 6");
        peripheralSessionBean.createNewPeripheral(testPeripheral13);
        Peripheral testPeripheral14 = new Peripheral("Peripheral 14", 1.0, 1, "Image 1");
        peripheralSessionBean.createNewPeripheral(testPeripheral14);
        Peripheral testPeripheral15 = new Peripheral("Peripheral 15", 2.0, 2, "Image 2");
        peripheralSessionBean.createNewPeripheral(testPeripheral15);
        Peripheral testPeripheral16 = new Peripheral("Peripheral 16", 3.0, 3, "Image 3");
        peripheralSessionBean.createNewPeripheral(testPeripheral16);
        Peripheral testPeripheral17 = new Peripheral("Peripheral 17", 1.0, 1, "Image 1");
        peripheralSessionBean.createNewPeripheral(testPeripheral17);
        Peripheral testPeripheral18 = new Peripheral("Peripheral 18", 2.0, 2, "Image 2");
        peripheralSessionBean.createNewPeripheral(testPeripheral18);
        Peripheral testPeripheral19 = new Peripheral("Peripheral 19", 3.0, 3, "Image 3");
        peripheralSessionBean.createNewPeripheral(testPeripheral19);
        Peripheral testPeripheral20 = new Peripheral("Peripheral 20", 2.0, 2, "Image 2");
        peripheralSessionBean.createNewPeripheral(testPeripheral20);
        Peripheral testPeripheral21 = new Peripheral("Peripheral 21", 3.0, 3, "Image 3");
        peripheralSessionBean.createNewPeripheral(testPeripheral21);
        
        Customer testCustomer1 = new Customer("Jeff", "Low", "NUS", "customer@gmail.com", "password", "12345678");
        customerSessionBean.createNewCustomer(testCustomer1);
        
        Customer testCustomer2 = new Customer("Potato", "27", "NUH", "customer2@gmail.com", "password", "12345678");
        customerSessionBean.createNewCustomer(testCustomer2);
        
        LineItem newLineItem = new LineItem(testPeripheral1, 5);
        lineItemSessionBean.createNewLineItem(newLineItem);
        
        LineItem newLineItem2 = new LineItem(testPeripheral2, 20);
        lineItemSessionBean.createNewLineItem(newLineItem2);
        
        testCustomer.getCart().add(newLineItem);
        testCustomer.getCart().add(newLineItem2);
    }
    
}
