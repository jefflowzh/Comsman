package ejb.session.singleton;

import ejb.session.stateless.ComputerPartSessionBeanLocal;
import ejb.session.stateless.ComputerSetSessionBeanLocal;
import ejb.session.stateless.CouponSessionBeanLocal;
import ejb.session.stateless.CustomerSessionBeanLocal;
import ejb.session.stateless.LineItemSessionBeanLocal;
import ejb.session.stateless.CustomerOrderSessionBeanLocal;
import ejb.session.stateless.PeripheralSessionBeanLocal;
import ejb.session.stateless.PreBuiltComputerSetModelSessionBeanLocal;
import ejb.session.stateless.StaffSessionBeanLocal;
import entity.CPU;
import entity.CPUAirCooler;
import entity.CPUWaterCooler;
import entity.ComputerCase;
import entity.ComputerSet;
import entity.Customer;
import entity.LineItem;
import entity.CustomerOrder;
import entity.GPU;
import entity.HDD;
import entity.MotherBoard;
import entity.Peripheral;
import entity.PowerSupply;
import entity.PreBuiltComputerSetModel;
import entity.RAM;
import entity.SSD;
import entity.Staff;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import util.enumeration.PreBuiltComputerSetTierEnum;
import util.enumeration.StaffAccessRightEnum;
import util.exception.ComputerSetNotFoundException;
import util.exception.ComputerSetTierAlreadyExistsException;
import util.exception.CustomerNotFoundException;
import util.exception.StaffAlreadyExistsException;
import util.exception.StaffNotFoundException;

@Singleton
@LocalBean
@Startup
public class DataInitSessionBean {

    @EJB
    private PreBuiltComputerSetModelSessionBeanLocal preBuiltComputerSetModelSessionBeanLocal;
    @EJB
    private PeripheralSessionBeanLocal peripheralSessionBeanLocal;
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

//        try {
//            preBuiltComputerSetModelSessionBeanLocal.createNewPreBuiltComputerSetModel(new PreBuiltComputerSetModel(PreBuiltComputerSetTierEnum.PREMIUM));
//            preBuiltComputerSetModelSessionBeanLocal.createNewPreBuiltComputerSetModel(new PreBuiltComputerSetModel(PreBuiltComputerSetTierEnum.REGULAR));
//            preBuiltComputerSetModelSessionBeanLocal.createNewPreBuiltComputerSetModel(new PreBuiltComputerSetModel(PreBuiltComputerSetTierEnum.BUDGET));
//        } catch (ComputerSetTierAlreadyExistsException ex) {
//            System.out.println(ex.getMessage());
//        }
        try {
            Staff testStaff = new Staff(StaffAccessRightEnum.MANAGER, "StaffFN", "StaffLN", "Staff Address", "wd@email.com", "password", "12345");
            staffSessionBean.createNewStaff(testStaff);
            Staff testStaffTech = new Staff(StaffAccessRightEnum.TECHNICIAN, "StaffFN", "StaffLN", "Staff Address", "tech@email.com", "password", "12345");
            staffSessionBean.createNewStaff(testStaffTech);
        } catch (StaffAlreadyExistsException ex) {
            System.out.println("Staff already exists");
        }

//        ComputerPart testComputerPart = new ComputerPart("Computer Part 1", 100.00, 10, "image");
//        computerPartSessionBean.createNewComputerPart(testComputerPart);
//        ComputerPart testComputerPart2 = new ComputerPart("Computer Part 2", 100.00, 10, "image");
//        computerPartSessionBean.createNewComputerPart(testComputerPart2);
        // test computerset
        //create cpu
        CPU testcpu = new CPU("Manufacturer", 3, 3, "socket", true, true, "cpu", 100.00, 5, "test.png");
        computerPartSessionBean.createNewCPU(testcpu);
        //create mb
        // String[] suportedMemorySpeed = {"222", "111"};
        List<String> suportedMemorySpeed = new ArrayList<>();
        suportedMemorySpeed.add("222");
        suportedMemorySpeed.add("111");
        MotherBoard testMB = new MotherBoard("Manufacturer", "formFactor", "socket", "chipset", 4, "red", true, 5, 5, true, suportedMemorySpeed, "motherboard", 100.00, 5, "test.png");
        computerPartSessionBean.createNewMotherBoard(testMB);
        // create ram
        List<RAM> rams = new ArrayList<>();
        RAM testram = new RAM("Manufacturer", "speed", "type", 4, 3, 5, "ram", 100.00, 5, "test.png");
        RAM testram2 = new RAM("Manufacturer2", "speed2", "type2", 4, 3, 5, "ram2", 100.00, 5, "test.png");
        computerPartSessionBean.createNewRAM(testram);
        computerPartSessionBean.createNewRAM(testram2);
        rams.add(testram);
        rams.add(testram2);
        // create psu
        PowerSupply powersupply = new PowerSupply("Manufacturer", "formFactor", "efficiency", 5, "modularity", 5, 5, "psu", 100.00, 5, "test.png");
        computerPartSessionBean.createNewPowerSupply(powersupply);
        // create computer case
        // String[] colours = {"red", "blue"};

        List<String> motherBoardFormFactor = new ArrayList<>();
        motherBoardFormFactor.add("mbff1");
        motherBoardFormFactor.add("mbff2");
        // String[] MotherBoardFormFactor = {"MotherBoardFormFactor"};
        ComputerCase cs = new ComputerCase("Manufacturer", "type", "blue", "sidePanelView", motherBoardFormFactor, 5, 100.99, 10.00, 11.00, 102.00, "case", 100.00, 5, "test.png");
        computerPartSessionBean.createNewComCase(cs);

        // create gpu
        GPU gpu = new GPU("Manufacturer", "chipset", "Interface", 5.5, 5, 6, "externalPower", 8, "MemoryType", "gpu", 100.00, 10, "test.png");
        computerPartSessionBean.createNewGPU(gpu);

        // create hdd
        HDD hdd = new HDD("Manufacturer", "type", 5, "formFactor", "Interface", "hdd", 100.00, 10, "test.png");
        computerPartSessionBean.createNewHDD(hdd);

        // create ssd
        SSD ssd = new SSD("Manufacturer", "type", 5, "formFactor", "Interface", true, "ssd", 100.00, 10, "test.png");
        computerPartSessionBean.createNewSSD(ssd);

        // create watercooler
        // String[] CPUChipCompatibility2 = {"CPUChipCompatibility", "CPUChipCompatibility2"};
        List<String> CPUChipCompatibility = new ArrayList<>();
        CPUChipCompatibility.add("String One");
        CPUChipCompatibility.add("String Two");
        CPUWaterCooler watercooler = new CPUWaterCooler("Manufacturer", "color", 5, CPUChipCompatibility, 5.5, "CPUWaterCooler", 100.00, 10, "test.png");
        computerPartSessionBean.createNewCPUWaterCooler(watercooler);

        // create aircooler
        CPUAirCooler aircooler = new CPUAirCooler("Manufacturer", "color", 5, 5.5, CPUChipCompatibility, "CPUAirCooler", 100.00, 10, "test.png");
        computerPartSessionBean.createNewCPUAirCooler(aircooler);

        // test peripheral
        Peripheral peripheral = new Peripheral("manu", "description", "peripheral", 123.0, 10, "test.png");
        peripheralSessionBeanLocal.createNewPeripheral(peripheral);

        PreBuiltComputerSetModel p1 = new PreBuiltComputerSetModel(PreBuiltComputerSetTierEnum.PREMIUM);
        p1.setCpu(testcpu);
        p1.setMotherboard(testMB);
        p1.getRams().add(testram);
        p1.getRams().add(testram2);
        p1.setPrice(2.1);
        p1.setIsEnabled(true);
        try {
            preBuiltComputerSetModelSessionBeanLocal.createNewPreBuiltComputerSetModel(p1);
            preBuiltComputerSetModelSessionBeanLocal.createNewPreBuiltComputerSetModel(new PreBuiltComputerSetModel(PreBuiltComputerSetTierEnum.REGULAR));
            preBuiltComputerSetModelSessionBeanLocal.createNewPreBuiltComputerSetModel(new PreBuiltComputerSetModel(PreBuiltComputerSetTierEnum.BUDGET));
        } catch (ComputerSetTierAlreadyExistsException ex) {
            System.out.println(ex.getMessage());
        }

        // creat com set
        ComputerSet comset = new ComputerSet(5, false);
        comset.setCpu(testcpu);
        comset.setMotherBoard(testMB);
        for (RAM r : rams) {
            comset.addRam(r);
        }
        comset.setPsu(powersupply);
        comset.setCompCase(cs);

        LineItem testcomsetLineItem = new LineItem(2);
        testcomsetLineItem.setComputerSet(comset);

        List<Long> computerSetIds = computerSetSessionBean.createNewComputerSet(testcomsetLineItem);

        Customer testCustomer = new Customer("cardNum", "ccv", "Customer1", "Customer1", "Customer Address", "customer@email.com", "password", "12345678");
        customerSessionBean.createNewCustomer(testCustomer);

        try {
            List<LineItem> testLineItems3 = new ArrayList<>();
            LineItem line1 = lineItemSessionBean.retrieveLineItemById((long) 1);
            LineItem line2 = lineItemSessionBean.retrieveLineItemById((long) 2);
            testLineItems3.add(line1);
            testLineItems3.add(line2);
            LineItem testpartLineItemCPU = new LineItem(testcpu, 3);
            lineItemSessionBean.createNewLineItem(testpartLineItemCPU);
            testLineItems3.add(testpartLineItemCPU);
            Date date2 = new Date();

            CustomerOrder testCustomerOrder3 = new CustomerOrder(new Timestamp(date2.getTime()), true, "Billing address", testLineItems3);
            Long orderId = customerOrderSessionBean.createNewCustomerOrder(testCustomerOrder3, (long) 3);
            CustomerOrder order = customerOrderSessionBean.retrieveCustomerOrderById(orderId, Boolean.TRUE);
            line1.setCustomerOrder(order);
            line2.setCustomerOrder(order);
            System.out.println("AAAAAAAAAAAAAAA");
            System.out.println(computerSetIds);
            ComputerSet computerSet = new ComputerSet();
            LineItem lineItemToAddToOrder;
            try {
                computerSet = computerSetSessionBean.retrieveComputerSetById(computerSetIds.get(0));
            } catch (ComputerSetNotFoundException ex) {

            }
            System.out.println(computerSet);
            lineItemToAddToOrder = computerSet.getLineItem();
            //testCustomerOrder3.getLineItems().add(lineItemToAddToOrder);
            //lineItemToAddToOrder.setCustomerOrder(testCustomerOrder3);

            try { // for next line item
                computerSet = computerSetSessionBean.retrieveComputerSetById(computerSetIds.get(1));
            } catch (ComputerSetNotFoundException ex) {

            }
            System.out.println(computerSet);
            lineItemToAddToOrder = computerSet.getLineItem();
            // testCustomerOrder3.getLineItems().add(lineItemToAddToOrder);
            // lineItemToAddToOrder.setCustomerOrder(testCustomerOrder3);

        } catch (CustomerNotFoundException ex) {
            System.out.println("fail to create singleton orders for initial data !! >> " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex);
        }

        LineItem testpartLineItem = new LineItem(testcpu, 3);
        LineItem testpartLineItemPeri = new LineItem(peripheral, 3);

        try {
            lineItemSessionBean.createNewLineItem(testpartLineItem);
            lineItemSessionBean.createNewLineItem(testpartLineItemPeri);
            List<LineItem> testLineItems4 = new ArrayList<>();
            testLineItems4.add(testpartLineItem);
            testLineItems4.add(testpartLineItemPeri);
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
