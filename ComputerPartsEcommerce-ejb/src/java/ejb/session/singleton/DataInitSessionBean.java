package ejb.session.singleton;

import ejb.session.stateless.ComputerPartSessionBeanLocal;
import ejb.session.stateless.ComputerSetSessionBeanLocal;
import ejb.session.stateless.CouponSessionBeanLocal;
import ejb.session.stateless.CustomerSessionBeanLocal;
import ejb.session.stateless.LineItemSessionBeanLocal;
import ejb.session.stateless.CustomerOrderSessionBeanLocal;
import ejb.session.stateless.StaffSessionBeanLocal;
import entity.CPU;
import entity.ComputerCase;
import entity.ComputerPart;
import entity.ComputerSet;
import entity.Coupon;
import entity.Customer;
import entity.LineItem;
import entity.CustomerOrder;
import entity.MotherBoard;
import entity.PowerSupply;
import entity.RAM;
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
import util.exception.LineItemNotFoundException;
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

//        ComputerPart testComputerPart = new ComputerPart("Computer Part 1", 100.00, 10, "image");
//        computerPartSessionBean.createNewComputerPart(testComputerPart);
//        ComputerPart testComputerPart2 = new ComputerPart("Computer Part 2", 100.00, 10, "image");
//        computerPartSessionBean.createNewComputerPart(testComputerPart2);

        // test computerset
        //create cpu
        CPU testcpu = new CPU("Manufacturer", 3, 3, "socket", true, true, "cpu", 100.00, 5, "image");
        computerPartSessionBean.createNewCPU(testcpu);
        //create mb
        String[] suportedMemorySpeed = {"222", "111"};
        MotherBoard testMB = new MotherBoard("Manufacturer", "formFactor", "socket", "chipset", 4, "red", true, 5, 5, true, suportedMemorySpeed, "motherboard", 100.00, 5, "image");
        computerPartSessionBean.createNewMotherBoard(testMB);
        // create ram
        List<RAM> rams = new ArrayList<>();
        RAM testram = new RAM("Manufacturer", "speed", "type", 4, 3, 5, "ram", 100.00, 5, "image");
        computerPartSessionBean.createNewRAM(testram);
        rams.add(testram);
        // create psu
        PowerSupply powersupply = new PowerSupply("Manufacturer", "formFactor", "efficiency", 5, "modularity", 5, 5, "psu", 100.00, 5, "image");
        computerPartSessionBean.createNewPowerSupply(powersupply);
        // create computer case
        String[] colours = {"red", "blue"};
        String[] MotherBoardFormFactor = {"MotherBoardFormFactor"};
        ComputerCase cs = new ComputerCase("Manufacturer", "type", colours, "sidePanelView", MotherBoardFormFactor, 5, 100.99, 10.00, 11.00, 102.00, "case", 100.00, 5, "image");
        computerPartSessionBean.createNewComCase(cs);
        
        // creat com set
        ComputerSet comset = new ComputerSet(5, false);
        comset.setCpu(testcpu);
        comset.setMotherBoard(testMB);
        for (RAM r: rams) {
            comset.addRam(r);
        }
        comset.setPsu(powersupply);
        cs.setSelectedColour("red");
        comset.setCompCase(cs);

        LineItem testcomsetLineItem = new LineItem(1);
        
        computerSetSessionBean.createNewComputerSet(comset, testcomsetLineItem);
        
        Customer testCustomer = new Customer("cardNum", "ccv", "Customer1", "Customer1", "Customer Address", "customer@email.com", "password", "12345678");
        customerSessionBean.createNewCustomer(testCustomer);
        
        try {
            List<LineItem> testLineItems3 = new ArrayList<>();
            testLineItems3.add(lineItemSessionBean.retrieveLineItemById((long) 1));
            Date date2 = new Date();
            
            CustomerOrder testCustomerOrder3 = new CustomerOrder(new Timestamp(date2.getTime()), true, "Billing address", testLineItems3);
            customerOrderSessionBean.createNewCustomerOrder(testCustomerOrder3, (long) 3);

        } catch (CustomerNotFoundException ex) {
            System.out.println("fail to create singleton orders for initial data !! >> " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex);
        }
        
        LineItem testpartLineItem = new LineItem(testcpu, 3);

        try {
            lineItemSessionBean.createNewLineItem(testpartLineItem);
            List<LineItem> testLineItems4 = new ArrayList<>();
            testLineItems4.add(testpartLineItem);
            Date date2 = new Date();
            
            CustomerOrder testCustomerOrder4 = new CustomerOrder(new Timestamp(date2.getTime()), true, "Billing address", testLineItems4);
            customerOrderSessionBean.createNewCustomerOrder(testCustomerOrder4, (long) 3);

        } catch (CustomerNotFoundException ex) {
            System.out.println("fail to create singleton orders for initial data !! >> " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex);
        }

//        List<ComputerPart> testComputerParts = new ArrayList<>();
//        testComputerParts.add(testComputerPart);
//        ComputerSet testComputerSet = new ComputerSet(testComputerParts, 1, true, testStaff, "Computer Set 1", 1.0, 1, "image");
//        computerSetSessionBean.createNewComputerSet(testComputerSet);
//        
//        Coupon testCoupon = new Coupon("YAY2020", new Date(), new Date(), 1, CouponTypeEnum.PERCENTAGE);
//        couponSessionBean.createNewCoupon(testCoupon);
//        
        
//        
//        LineItem testLineItem = new LineItem(testComputerPart, 1);
//        lineItemSessionBean.createNewLineItem(testLineItem);
//        LineItem testLineItem2 = new LineItem(testComputerPart2, 5);
//        lineItemSessionBean.createNewLineItem(testLineItem2);
//        LineItem testLineItem3 = new LineItem(testComputerPart, 100);
//        lineItemSessionBean.createNewLineItem(testLineItem3);
////      
//        try {
//            List<LineItem> testLineItems = new ArrayList<>();
//            testLineItems.add(testLineItem);
//            testLineItems.add(testLineItem2);
//            Date date = new Date();
//            CustomerOrder testCustomerOrder = new CustomerOrder(new Timestamp(date.getTime()), true, "Billing address", testLineItems);
//            customerOrderSessionBean.createNewCustomerOrder(testCustomerOrder, (long) 3);
//
//        } catch (CustomerNotFoundException ex) {
//            System.out.println("fail to create singleton orders for initial data !! >> " + ex.getMessage());
//        }
//
//        try {
//            List<LineItem> testLineItems2 = new ArrayList<>();
//            testLineItems2.add(testLineItem3);
//            Date date2 = new Date();
//            CustomerOrder testCustomerOrder2 = new CustomerOrder(new Timestamp(date2.getTime()), true, "Billing address", testLineItems2);
//            customerOrderSessionBean.createNewCustomerOrder(testCustomerOrder2, (long) 3);
//
//        } catch (CustomerNotFoundException ex) {
//            System.out.println("fail to create singleton orders for initial data !! >> " + ex.getMessage());
//        }
//    }
    }
}
