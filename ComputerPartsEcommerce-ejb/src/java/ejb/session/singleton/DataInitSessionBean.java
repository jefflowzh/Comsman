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
import entity.ComputerPart;
import entity.Coupon;
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
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import util.enumeration.CouponTypeEnum;
import util.enumeration.PreBuiltComputerSetTierEnum;
import util.enumeration.StaffAccessRightEnum;
import util.exception.CustomerNotFoundException;
import util.exception.StaffAlreadyExistsException;
import util.exception.StaffNotFoundException;

@Singleton
@LocalBean
@Startup
public class DataInitSessionBean {

    @EJB
    private PreBuiltComputerSetModelSessionBeanLocal preBuiltComputerSetModelSessionBean;
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
        Customer testCustomer = new Customer("Customer1", "Customer1", "Customer Address", "customer@email.com", "password", "12345678");
        try {
            customerSessionBean.createNewCustomer(testCustomer);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

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

        Peripheral testPeripheral1 = new Peripheral("Peripheral 1", 1.7, 1, "Image 1");
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

        try {
            Customer testCustomer1 = new Customer("Jeff", "Low", "NUS", "c@gmail.com", "password", "12345678");
            customerSessionBean.createNewCustomer(testCustomer1);

            Customer testCustomer2 = new Customer("Potato", "27", "NUH", "customer2@gmail.com", "password", "12345678");
            customerSessionBean.createNewCustomer(testCustomer2);

            LineItem newLineItem = new LineItem(testPeripheral1, 5);
            lineItemSessionBean.createNewLineItem(newLineItem);

            LineItem newLineItem2 = new LineItem(testPeripheral2, 20);
            lineItemSessionBean.createNewLineItem(newLineItem2);

            testCustomer1.getCart().add(newLineItem);
            testCustomer1.getCart().add(newLineItem2);
            
            Calendar date = new GregorianCalendar(2020, Calendar.APRIL, 24);
            Date startDate = date.getTime();
            date = new GregorianCalendar(2020, Calendar.MAY, 25);
            Date endDate = date.getTime();
            
            // do loyalty points if got time
            Coupon testCoupon1 = new Coupon("20%", startDate, endDate, 0, CouponTypeEnum.PERCENTAGE);
            testCoupon1.setPercentageRate(0.2);
            couponSessionBean.createNewCoupon(testCoupon1);
            Coupon testCoupon2 = new Coupon("20", startDate, endDate, 0, CouponTypeEnum.FLATAMOUNT);
            testCoupon2.setFlatAmount(20.0);
            couponSessionBean.createNewCoupon(testCoupon2);
            Coupon testCoupon3 = new Coupon("FreeDelivery", startDate, endDate, 0, CouponTypeEnum.FREEDELIVERY);
            couponSessionBean.createNewCoupon(testCoupon3);

            date = new GregorianCalendar(2020, Calendar.MAY, 30);
            Date testDate = date.getTime();
            
            Coupon testCoupon4 = new Coupon("testBefore", startDate, startDate, 0, CouponTypeEnum.FREEDELIVERY);
            couponSessionBean.createNewCoupon(testCoupon4);
            Coupon testCoupon5 = new Coupon("testAfter", endDate, endDate, 0, CouponTypeEnum.FREEDELIVERY);
            couponSessionBean.createNewCoupon(testCoupon5);
            
            String[] colours = new String[]{"white", "black"};
            String[] motherBoardFormFactor = new String[]{"motherBoardFormFactor1", "motherBoardFormFactor2"};
            
            ComputerCase testComputerCase1 = new ComputerCase("Manufacturer1", "type1", colours, "sidePanelView", motherBoardFormFactor, 1, 1.0, 1.0, 1.0, 1.0, "Computer Case 1", 1.0, 1, "image1");
            computerPartSessionBean.createNewComCase(testComputerCase1);
            ComputerCase testComputerCase2 = new ComputerCase("Manufacturer2", "type2", colours, "sidePanelView", motherBoardFormFactor, 2, 2.0, 2.0, 2.0, 2.0, "Computer Case 2", 2.0, 2, "image2");
            computerPartSessionBean.createNewComCase(testComputerCase2);
            ComputerCase testComputerCase3 = new ComputerCase("Manufacturer3", "type3", colours, "sidePanelView", motherBoardFormFactor, 3, 3.0, 3.0, 3.0, 3.0, "Computer Case 3", 3.0, 3, "image3");
            computerPartSessionBean.createNewComCase(testComputerCase3);
            ComputerCase testComputerCase4 = new ComputerCase("Manufacturer4", "type4", colours, "sidePanelView", motherBoardFormFactor, 4, 4.0, 4.0, 4.0, 4.0, "Computer Case 4", 4.0, 4, "image4");
            computerPartSessionBean.createNewComCase(testComputerCase4);
            
            CPU testCpu = new CPU("Intel", 6, 95, "LGA1151", true, false, "Intel Core i5-9600K 3.7 GHz 6-Core Processor", 300.0, 50,"image1");
            CPU testCpu1 = new CPU("Intel", 8, 95, "LGA1151", true, false, "Intel Core i7-9700K 3.6 GHz 8-Core Processor", 500.0, 50,"image2");
            CPU testCpu2 = new CPU("Intel", 8, 95, "LGA1151", true, false, "Intel Core i9-9900K 3.6 GHz 8-Core Processor", 700.0, 50,"image3");
            CPU testCpu3 = new CPU("Intel", 4, 65, "LGA1151", false, false, "Intel Core i3-9100F 3.6 GHz Quad-Core Processor", 100.0, 50,"image4");
            computerPartSessionBean.createNewCPU(testCpu);
            computerPartSessionBean.createNewCPU(testCpu1);
            computerPartSessionBean.createNewCPU(testCpu2);
            computerPartSessionBean.createNewCPU(testCpu3);
            
            CPU testCpu4 = new CPU("AMD", 4, 65, "AM4", true, true, "AMD Ryzen 3 3200G 3.6 GHz Quad-Core Processor", 150.0, 50,"image5");
            CPU testCpu5 = new CPU("AMD", 6, 65, "AM4", false, true, "AMD Ryzen 5 3600 3.6 GHz 6-Core Processor", 250.0, 50,"image6");
            CPU testCpu6 = new CPU("AMD", 8, 105, "AM4", false, true, "AMD Ryzen 7 3800X 3.9 GHz 8-Core Processor", 550.0, 50,"image7");
            CPU testCpu7 = new CPU("AMD", 4, 65, "AM4", false, true, "AMD Ryzen 5 1400 3.2 GHz Quad-Core Processor", 250.0, 50,"image8");
            computerPartSessionBean.createNewCPU(testCpu4);
            computerPartSessionBean.createNewCPU(testCpu5);
            computerPartSessionBean.createNewCPU(testCpu6);
            computerPartSessionBean.createNewCPU(testCpu7);
            
            GPU testGpu = new GPU("MSI", "GeForce GTX 1660 Ti", "PCIe x16", 204.0, 120, 2, "PCIe 8-pin",6, "GDDR6","MSI GeForce GTX 1660 Ti 6 GB VENTUS XS OC Video Card",450.0, 50,"image5");
            GPU testGpu2 = new GPU("GigaByte", "GeForce RTX 2070 SUPER", "PCIe x16", 280.35, 215, 2, "PCIe 8-pin + 1 PCIe 6-pin",8, "GDDR6","Gigabyte GeForce RTX 2070 SUPER 8 GB WINDFORCE OC 3X Video Card",750.0, 50,"image5");
            GPU testGpu3 = new GPU("Asus", "GeForce RTX 2080 Ti", "PCIe x16", 304.7, 250, 2, "2 PCIe 8-pin",11, "GDDR6","Asus GeForce RTX 2080 Ti 11 GB ROG Strix Gaming OC Video Card",1800.0, 50,"image5");
            GPU testGpu4 = new GPU("EVGA", "GeForce GTX 1650 SUPER", "PCIe x16", 202.1, 100, 2, "PCIe 6-pin",4,"GDDR6", "EVGA GeForce GTX 1650 SUPER 4 GB SC ULTRA GAMING Video Card",300.0,50,"image5");
            computerPartSessionBean.createNewGPU(testGpu);
            computerPartSessionBean.createNewGPU(testGpu2);
            computerPartSessionBean.createNewGPU(testGpu3);
            computerPartSessionBean.createNewGPU(testGpu4);
            
            String[] speeds = new String[]{"DDR4-2400","DDR4-2666","DDR4-2800","DDR4-3000","DDR4-3200","DDR4-3600"};
            MotherBoard testmb = new MotherBoard("MSI", "ATX", "AM4", "AMD B450", 4, "Black", true,2, 1,true,speeds,"MSI B450 TOMAHAWK MAX ATX AM4 Motherboard",450.0, 50,"image5");
            MotherBoard testmb1 = new MotherBoard("Asus", "ATX", "AM4", "AMD X570", 4, "Black", true,2, 2,true,speeds,"Asus TUF GAMING X570-PLUS (WI-FI) ATX AM4 Motherboard",450.0, 50,"image5");
            MotherBoard testmb2 = new MotherBoard("MSI", "ATX", "LGA1151", "Intel Z390", 4, "Black", true,2, 2,true,speeds,"MSI Z390-A PRO ATX LGA1151 Motherboard",200.0, 50,"image5");
            MotherBoard testmb3 = new MotherBoard("ASRock", "Micro ATX", "LGA1151", "Intel B365", 4, "Black", true,2, 3,true,speeds,"ASRock B365M Pro4 Micro ATX LGA1151 Motherboard",150.0, 50,"image5");
            
            computerPartSessionBean.createNewMotherBoard(testmb);
            computerPartSessionBean.createNewMotherBoard(testmb1);
            computerPartSessionBean.createNewMotherBoard(testmb2);
            computerPartSessionBean.createNewMotherBoard(testmb3);
            
            PowerSupply testPsu = new PowerSupply("Thermaltake", "ATX", "80+", 600,"No",6, 2,"Thermaltake Smart 600 W 80+ Certified ATX Power Supply",100.0, 50,"image5");
            PowerSupply testPsu1 = new PowerSupply("Corsair", "ATX", "80+ Gold", 750,"Full",10, 2,"Corsair RM (2019) 750 W 80+ Gold Certified Fully Modular ATX Power Supply",150.0, 50,"image5");
            PowerSupply testPsu2= new PowerSupply("EVGA", "ATX", "80+ Bronze", 450,"No",6, 2,"EVGA BR 450 W 80+ Bronze Certified ATX Power Supply", 75.0, 50,"image5");
            PowerSupply testPsu3 = new PowerSupply("Cooler Master", "ATX", "80+", 650,"Full",8, 4,"Cooler Master MWE Gold 650 W 80+ Gold Certified Fully Modular ATX Power Supply",150.0, 50,"image5");
            
            computerPartSessionBean.createNewPowerSupply(testPsu);
            computerPartSessionBean.createNewPowerSupply(testPsu1);
            computerPartSessionBean.createNewPowerSupply(testPsu2);
            computerPartSessionBean.createNewPowerSupply(testPsu3);
            
            RAM testRam = new RAM("Corsair", "DDR4-3200", "288-pin DIMM", 2,8,16,"Corsair Vengeance LPX 16 GB (2 x 8 GB) DDR4-3200 Memory",100.0, 50,"image5");
            RAM testRam1 = new RAM("G.Skill", "DDR4-3600", "288-pin DIMM", 2,8,16,"G.Skill Ripjaws V 16 GB (2 x 8 GB) DDR4-3600 Memory",140.0, 50,"image5");
            RAM testRam2 = new RAM("Corsair", "DDR4-3000", "288-pin DIMM", 2,8,15,"Corsair Vengeance LPX 16 GB (2 x 8 GB) DDR4-3000 Memory",120.0, 50,"image5");
            RAM testRam3 = new RAM("G.Skill", "DDR4-3600", "288-pin DIMM", 2,8,16,"G.Skill Trident Z Neo 32 GB (2 x 16 GB) DDR4-3600 Memory",240.0, 50,"image5");
            
            computerPartSessionBean.createNewRAM(testRam);
            computerPartSessionBean.createNewRAM(testRam1);
            computerPartSessionBean.createNewRAM(testRam2);
            computerPartSessionBean.createNewRAM(testRam3);
            
            String[] compatibleCPUChips1 = new String[]{"compatibleCPUChip1", "compatibleCPUChip2"};
            CPUAirCooler testCPUAirCooler1 = new CPUAirCooler("Manufacturer1", "Red", 1, 1.0, compatibleCPUChips1, "CPUAirCooler1", 1.0, 1, "image1");
            String[] compatibleCPUChips2 = new String[]{"compatibleCPUChip1", "compatibleCPUChip3"};
            CPUAirCooler testCPUAirCooler2 = new CPUAirCooler("Manufacturer2", "Yellow", 2, 2.0, compatibleCPUChips2, "CPUAirCooler2", 2.0, 2, "image2");
            String[] compatibleCPUChips3 = new String[]{"compatibleCPUChip2", "compatibleCPUChip3"};
            CPUAirCooler testCPUAirCooler3 = new CPUAirCooler("Manufacturer3", "Green", 3, 3.0, compatibleCPUChips3, "CPUAirCooler3", 3.0, 3, "image3");
            
            computerPartSessionBean.createNewCPUAirCooler(testCPUAirCooler1);
            computerPartSessionBean.createNewCPUAirCooler(testCPUAirCooler2);
            computerPartSessionBean.createNewCPUAirCooler(testCPUAirCooler3);
            
            CPUWaterCooler testCPUWaterCooler1 = new CPUWaterCooler("Manufacturer1", "Red", 1, compatibleCPUChips1, 1.0, "CPUWaterCooler1", 1.0, 1, "image1");
            CPUWaterCooler testCPUWaterCooler2 = new CPUWaterCooler("Manufacturer1", "Yellow", 2, compatibleCPUChips2, 2.0, "CPUWaterCooler2", 2.0, 2, "image2");
            CPUWaterCooler testCPUWaterCooler3 = new CPUWaterCooler("Manufacturer1", "Green", 3, compatibleCPUChips3, 3.0, "CPUWaterCooler3", 3.0, 3, "image3");
            
            computerPartSessionBean.createNewCPUWaterCooler(testCPUWaterCooler1);
            computerPartSessionBean.createNewCPUWaterCooler(testCPUWaterCooler2);
            computerPartSessionBean.createNewCPUWaterCooler(testCPUWaterCooler3);
            
            Date today = new Date();
            LineItem newLineItem3 = new LineItem(testRam, 1);
            lineItemSessionBean.createNewLineItem(newLineItem3);

            LineItem newLineItem4 = new LineItem(testGpu, 1);
            lineItemSessionBean.createNewLineItem(newLineItem4);
            
            List<LineItem> lineItems = new ArrayList();
            lineItems.add(newLineItem3);
            lineItems.add(newLineItem4);
            
            CustomerOrder order = new CustomerOrder(today, true, "home", lineItems);
            customerOrderSessionBean.createNewCustomerOrder(order, testCustomer2.getUserId());
            
            
            SSD testSSD1 = new SSD("Manufacturer1", "type1", 1, "formFactor1", "Interface1", true, "ssd1", 1.0, 1, "image1");
            SSD testSSD2 = new SSD("Manufacturer2", "type2", 2, "formFactor2", "Interface2", false, "ssd2", 2.0, 2, "image2");
            SSD testSSD3 = new SSD("Manufacturer3", "type3", 3, "formFactor3", "Interface3", true, "ssd3", 3.0, 3, "image3");
            
            computerPartSessionBean.createNewSSD(testSSD1);
            computerPartSessionBean.createNewSSD(testSSD2);
            computerPartSessionBean.createNewSSD(testSSD3);
            
            HDD testHDD1 = new HDD("Manufacturer1", "type1", 1, "formFactor1", "Interface1", "hdd1", 1.0, 1, "image1");
            HDD testHDD2 = new HDD("Manufacturer2", "type2", 2, "formFactor2", "Interface2", "hdd2", 2.0, 2, "image2");
            HDD testHDD3 = new HDD("Manufacturer3", "type3", 3, "formFactor3", "Interface3", "hdd3", 3.0, 3, "image3");
            
            computerPartSessionBean.createNewHDD(testHDD1);
            computerPartSessionBean.createNewHDD(testHDD2);
            computerPartSessionBean.createNewHDD(testHDD3);
            
            PreBuiltComputerSetModel p1 = new PreBuiltComputerSetModel(PreBuiltComputerSetTierEnum.BUDGET);
            p1.setCpu(testCpu);
            p1.setWaterCooler(testCPUWaterCooler1);
            p1.setCompCase(testComputerCase1);
            p1.getGpus().add(testGpu);
            p1.getHdds().add(testHDD1);
            p1.getSsds().add(testSSD1);
            p1.setMotherboard(testmb1);
            p1.setPsu(testPsu);
            p1.getRams().add(testRam);
            p1.getRams().add(testRam1);
            p1.setPrice(800.0);
            p1.setIsEnabled(true);
            preBuiltComputerSetModelSessionBean.createNewPreBuiltComputerSetModel(p1);
            
            PreBuiltComputerSetModel p2 = new PreBuiltComputerSetModel(PreBuiltComputerSetTierEnum.REGULAR);
            p2.setCpu(testCpu);
            p2.setWaterCooler(testCPUWaterCooler1);
            p2.setCompCase(testComputerCase1);
            p2.getGpus().add(testGpu);
            p2.getHdds().add(testHDD1);
            p2.getSsds().add(testSSD1);
            p2.setMotherboard(testmb1);
            p2.setPsu(testPsu);
            p2.getRams().add(testRam);
            p2.getRams().add(testRam1);
            p2.setPrice(1500.0);
            p2.setIsEnabled(true);
            preBuiltComputerSetModelSessionBean.createNewPreBuiltComputerSetModel(p2);
            
            PreBuiltComputerSetModel p3 = new PreBuiltComputerSetModel(PreBuiltComputerSetTierEnum.PREMIUM);
            p3.setCpu(testCpu);
            p3.setWaterCooler(testCPUWaterCooler1);
            p3.setCompCase(testComputerCase1);
            p3.getGpus().add(testGpu);
            p3.getHdds().add(testHDD1);
            p3.getSsds().add(testSSD1);
            p3.setMotherboard(testmb1);
            p3.setPsu(testPsu);
            p3.getRams().add(testRam);
            p3.getRams().add(testRam1);
            p3.setPrice(3722.0);
            p3.setIsEnabled(true);
            preBuiltComputerSetModelSessionBean.createNewPreBuiltComputerSetModel(p3);
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

}
