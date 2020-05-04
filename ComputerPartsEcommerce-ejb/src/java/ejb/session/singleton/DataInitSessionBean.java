package ejb.session.singleton;

import ejb.session.stateless.ComputerPartSessionBeanLocal;
import ejb.session.stateless.ComputerSetSessionBeanLocal;
import ejb.session.stateless.CouponSessionBeanLocal;
import ejb.session.stateless.CustomerSessionBeanLocal;
import ejb.session.stateless.LineItemSessionBeanLocal;
import ejb.session.stateless.CustomerOrderSessionBeanLocal;
import ejb.session.stateless.LineItemSessionBean;
import ejb.session.stateless.PeripheralSessionBeanLocal;
import ejb.session.stateless.PreBuiltComputerSetModelSessionBeanLocal;
import ejb.session.stateless.StaffSessionBeanLocal;
import entity.CPU;
import entity.CPUAirCooler;
import entity.CPUWaterCooler;
import entity.ComputerCase;
import entity.ComputerPart;
import entity.ComputerSet;
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
import util.exception.ComputerSetNotFoundException;
import util.exception.ComputerSetTierAlreadyExistsException;
import util.exception.CustomerNotFoundException;
import util.exception.StaffAlreadyExistsException;
import util.exception.CustomerEmailExistException;
import util.exception.StaffNotFoundException;

@Singleton
@LocalBean
@Startup
public class DataInitSessionBean {

    private PreBuiltComputerSetModelSessionBeanLocal preBuiltComputerSetModelSessionBeanLocal;
    @EJB
    private PeripheralSessionBeanLocal peripheralSessionBeanLocal;
    @EJB
    private ComputerPartSessionBeanLocal computerPartSessionBeanLocal;
    @EJB
    private ComputerSetSessionBeanLocal computerSetSessionBeanLocal;
    @EJB
    private CouponSessionBeanLocal couponSessionBeanLocal;
    @EJB
    private CustomerSessionBeanLocal customerSessionBeanLocal;
    @EJB
    private LineItemSessionBeanLocal lineItemSessionBeanLocal;
    @EJB
    private CustomerOrderSessionBeanLocal customerOrderSessionBeanLocal;
    @EJB
    private StaffSessionBeanLocal staffSessionBeanLocal;

    public DataInitSessionBean() {
    }

    @PostConstruct
    public void postConstruct() {
        try {
            // check if there is anything in database
            staffSessionBeanLocal.retrieveStaffByEmail("wd@email.com", true, true);
        } catch (StaffNotFoundException ex) {
            initializeData();

        }
    }

    private void initializeData() {

        // create staffs
        try {
            Staff manager = new Staff(StaffAccessRightEnum.MANAGER, "John", "Doe", "21 Lower Kent Ridge Rd", "john@gmail.com", "password", "97712345");
            staffSessionBeanLocal.createNewStaff(manager);
            Staff technician = new Staff(StaffAccessRightEnum.TECHNICIAN, "Amanda", "Tan", "21 Upper Kent Ridge Rd", "amanda@gmail.com", "password", "98812345");
            staffSessionBeanLocal.createNewStaff(technician);
            technician = new Staff(StaffAccessRightEnum.TECHNICIAN, "Bob", "Lim", "21 Upper Kent Ridge Rd", "bob@gmail.com", "password", "98812345");
            staffSessionBeanLocal.createNewStaff(technician);
            technician = new Staff(StaffAccessRightEnum.TECHNICIAN, "Ben", "Tan", "21 Upper Kent Ridge Rd", "ben@gmail.com", "password", "98812345");
            staffSessionBeanLocal.createNewStaff(technician);
            technician = new Staff(StaffAccessRightEnum.TECHNICIAN, "David", "Tan", "21 Upper Kent Ridge Rd", "david@gmail.com", "password", "98812345");
            staffSessionBeanLocal.createNewStaff(technician);
            technician = new Staff(StaffAccessRightEnum.TECHNICIAN, "Mary", "Tan", "21 Upper Kent Ridge Rd", "mary@gmail.com", "password", "98812345");
            staffSessionBeanLocal.createNewStaff(technician);
        } catch (StaffAlreadyExistsException ex) {
            System.out.println("Staff already exists");
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        // create customers
        try {
            Customer customer = new Customer("Johnny", "Doe", "21 Lower Kent Ridge Rd", "johnny@gmail.com", "password", "97712345");
            customerSessionBeanLocal.createNewCustomer(customer);
            customer = new Customer("Mandy", "Doe", "21 Lower Kent Ridge Rd", "mandy@gmail.com", "password", "97712345");
            customerSessionBeanLocal.createNewCustomer(customer);
            customer = new Customer("Peter", "Doe", "21 Lower Kent Ridge Rd", "peter@gmail.com", "password", "97712345");
            customerSessionBeanLocal.createNewCustomer(customer);
        } catch (CustomerEmailExistException ex) {
            System.out.println("Customer already exists");
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        // create coupons
        Calendar date = new GregorianCalendar(2020, Calendar.APRIL, 24);
        Date startDate = date.getTime();
        date = new GregorianCalendar(2020, Calendar.JUNE, 24);
        Date endDate = date.getTime();

        Coupon coupon = new Coupon("20%", startDate, endDate, 0, CouponTypeEnum.PERCENTAGE);
        coupon.setPercentageRate(0.2);
        couponSessionBeanLocal.createNewCoupon(coupon);
        coupon = new Coupon("20", startDate, endDate, 0, CouponTypeEnum.FLAT_AMOUNT);
        coupon.setFlatAmount(20.0);
        couponSessionBeanLocal.createNewCoupon(coupon);
        coupon = new Coupon("FREEDELIV", startDate, endDate, 0, CouponTypeEnum.FREE_DELIVERY);
        couponSessionBeanLocal.createNewCoupon(coupon);

        // create peripherals
        Peripheral peripheral = new Peripheral("Headphones", 11.7, 10, "Image 1");
        peripheralSessionBeanLocal.createNewPeripheral(peripheral);
        peripheral = new Peripheral("Logitech MK120 Mouse", 22.99, 9, "Image 2");
        peripheralSessionBeanLocal.createNewPeripheral(peripheral);
        peripheral = new Peripheral("Corsair K70 Mechanical Gaming Keyboard ", 31.0, 5, "Image 3");
        peripheralSessionBeanLocal.createNewPeripheral(peripheral);
        peripheral = new Peripheral("Rosewill Wireless Computer Mouse,", 14.0, 14, "Image 4");
        peripheralSessionBeanLocal.createNewPeripheral(peripheral);
        peripheral = new Peripheral("Rosewill 7.1 Surround Sound Gaming Headset", 55.0, 50, "Image 5");
        peripheralSessionBeanLocal.createNewPeripheral(peripheral);

        // create CPUs
        CPU testCpu = new CPU("Intel", 6, 95, "LGA1151", true, false, "Intel Core i5-9600K 3.7 GHz 6-Core Processor", 300.0, 50, "image1");
        CPU testCpu1 = new CPU("Intel", 8, 95, "LGA1151", true, false, "Intel Core i7-9700K 3.6 GHz 8-Core Processor", 500.0, 50, "image2");
        CPU testCpu2 = new CPU("Intel", 8, 95, "LGA1151", true, false, "Intel Core i9-9900K 3.6 GHz 8-Core Processor", 700.0, 50, "image3");
        CPU testCpu3 = new CPU("Intel", 4, 65, "LGA1151", false, false, "Intel Core i3-9100F 3.6 GHz Quad-Core Processor", 100.0, 50, "image4");
        CPU testCpu4 = new CPU("AMD", 4, 65, "AM4", true, true, "AMD Ryzen 3 3200G 3.6 GHz Quad-Core Processor", 150.0, 50, "image5");
        CPU testCpu5 = new CPU("AMD", 6, 65, "AM4", false, true, "AMD Ryzen 5 3600 3.6 GHz 6-Core Processor", 250.0, 50, "image6");
        CPU testCpu6 = new CPU("AMD", 8, 105, "AM4", false, true, "AMD Ryzen 7 3800X 3.9 GHz 8-Core Processor", 550.0, 50, "image7");
        CPU testCpu7 = new CPU("AMD", 4, 65, "AM4", false, true, "AMD Ryzen 5 1400 3.2 GHz Quad-Core Processor", 250.0, 50, "image8");
        computerPartSessionBeanLocal.createNewCPU(testCpu);
        computerPartSessionBeanLocal.createNewCPU(testCpu1);
        computerPartSessionBeanLocal.createNewCPU(testCpu2);
        computerPartSessionBeanLocal.createNewCPU(testCpu3);
        computerPartSessionBeanLocal.createNewCPU(testCpu4);
        computerPartSessionBeanLocal.createNewCPU(testCpu5);
        computerPartSessionBeanLocal.createNewCPU(testCpu6);
        computerPartSessionBeanLocal.createNewCPU(testCpu7);

        // create GPUs
        GPU testGpu = new GPU("MSI", "GeForce GTX 1660 Ti", "PCIe x16", 204.0, 120, 2, "PCIe 8-pin", 6, "GDDR6", "MSI GeForce GTX 1660 Ti 6 GB VENTUS XS OC Video Card", 450.0, 50, "image5");
        GPU testGpu2 = new GPU("GigaByte", "GeForce RTX 2070 SUPER", "PCIe x16", 280.35, 215, 2, "PCIe 8-pin + 1 PCIe 6-pin", 8, "GDDR6", "Gigabyte GeForce RTX 2070 SUPER 8 GB WINDFORCE OC 3X Video Card", 750.0, 50, "image5");
        GPU testGpu3 = new GPU("Asus", "GeForce RTX 2080 Ti", "PCIe x16", 304.7, 250, 2, "2 PCIe 8-pin", 11, "GDDR6", "Asus GeForce RTX 2080 Ti 11 GB ROG Strix Gaming OC Video Card", 1800.0, 50, "image5");
        GPU testGpu4 = new GPU("EVGA", "GeForce GTX 1650 SUPER", "PCIe x16", 202.1, 100, 2, "PCIe 6-pin", 4, "GDDR6", "EVGA GeForce GTX 1650 SUPER 4 GB SC ULTRA GAMING Video Card", 300.0, 50, "image5");
        computerPartSessionBeanLocal.createNewGPU(testGpu);
        computerPartSessionBeanLocal.createNewGPU(testGpu2);
        computerPartSessionBeanLocal.createNewGPU(testGpu3);
        computerPartSessionBeanLocal.createNewGPU(testGpu4);

        // create HDDs
        HDD testHDD1 = new HDD("Manufacturer1", "type1", 1, "formFactor1", "Interface1", "hdd1", 1.0, 1, "image1");
        HDD testHDD2 = new HDD("Manufacturer2", "type2", 2, "formFactor2", "Interface2", "hdd2", 2.0, 2, "image2");
        HDD testHDD3 = new HDD("Manufacturer3", "type3", 3, "formFactor3", "Interface3", "hdd3", 3.0, 3, "image3");
        computerPartSessionBeanLocal.createNewHDD(testHDD1);
        computerPartSessionBeanLocal.createNewHDD(testHDD2);
        computerPartSessionBeanLocal.createNewHDD(testHDD3);

        // create motherboards
        List<String> speeds = new ArrayList<>();
        speeds.add("DDR4-2400");
        speeds.add("DDR4-2666");
        speeds.add("DDR4-2800");
        speeds.add("DDR4-3000");
        speeds.add("DDR4-3200");
        speeds.add("DDR4-3600");
        MotherBoard testmb = new MotherBoard("MSI", "ATX", "AM4", "AMD B450", 4, "Black", true, 2, 1, true, speeds, "MSI B450 TOMAHAWK MAX ATX AM4 Motherboard", 450.0, 50, "image5");
        MotherBoard testmb1 = new MotherBoard("Asus", "ATX", "AM4", "AMD X570", 4, "Black", true, 2, 2, true, speeds, "Asus TUF GAMING X570-PLUS (WI-FI) ATX AM4 Motherboard", 450.0, 50, "image5");
        MotherBoard testmb2 = new MotherBoard("MSI", "ATX", "LGA1151", "Intel Z390", 4, "Black", true, 2, 2, true, speeds, "MSI Z390-A PRO ATX LGA1151 Motherboard", 200.0, 50, "image5");
        MotherBoard testmb3 = new MotherBoard("ASRock", "Micro ATX", "LGA1151", "Intel B365", 4, "Black", true, 2, 3, true, speeds, "ASRock B365M Pro4 Micro ATX LGA1151 Motherboard", 150.0, 50, "image5");
        computerPartSessionBeanLocal.createNewMotherBoard(testmb);
        computerPartSessionBeanLocal.createNewMotherBoard(testmb1);
        computerPartSessionBeanLocal.createNewMotherBoard(testmb2);
        computerPartSessionBeanLocal.createNewMotherBoard(testmb3);

        // create power supplies
        PowerSupply testPsu = new PowerSupply("Thermaltake", "ATX", "80+", 600, "No", 6, 2, "Thermaltake Smart 600 W 80+ Certified ATX Power Supply", 100.0, 50, "image5");
        PowerSupply testPsu1 = new PowerSupply("Corsair", "ATX", "80+ Gold", 750, "Full", 10, 2, "Corsair RM (2019) 750 W 80+ Gold Certified Fully Modular ATX Power Supply", 150.0, 50, "image5");
        PowerSupply testPsu2 = new PowerSupply("EVGA", "ATX", "80+ Bronze", 450, "No", 6, 2, "EVGA BR 450 W 80+ Bronze Certified ATX Power Supply", 75.0, 50, "image5");
        PowerSupply testPsu3 = new PowerSupply("Cooler Master", "ATX", "80+", 650, "Full", 8, 4, "Cooler Master MWE Gold 650 W 80+ Gold Certified Fully Modular ATX Power Supply", 150.0, 50, "image5");
        computerPartSessionBeanLocal.createNewPowerSupply(testPsu);
        computerPartSessionBeanLocal.createNewPowerSupply(testPsu1);
        computerPartSessionBeanLocal.createNewPowerSupply(testPsu2);
        computerPartSessionBeanLocal.createNewPowerSupply(testPsu3);

        // create rams
        RAM testRam = new RAM("Corsair", "DDR4-3200", "288-pin DIMM", 2, 8, 16, "Corsair Vengeance LPX 16 GB (2 x 8 GB) DDR4-3200 Memory", 100.0, 50, "image5");
        RAM testRam1 = new RAM("G.Skill", "DDR4-3600", "288-pin DIMM", 2, 8, 16, "G.Skill Ripjaws V 16 GB (2 x 8 GB) DDR4-3600 Memory", 140.0, 50, "image5");
        RAM testRam2 = new RAM("Corsair", "DDR4-3000", "288-pin DIMM", 2, 8, 15, "Corsair Vengeance LPX 16 GB (2 x 8 GB) DDR4-3000 Memory", 120.0, 50, "image5");
        RAM testRam3 = new RAM("G.Skill", "DDR4-3600", "288-pin DIMM", 2, 8, 16, "G.Skill Trident Z Neo 32 GB (2 x 16 GB) DDR4-3600 Memory", 240.0, 50, "image5");
        computerPartSessionBeanLocal.createNewRAM(testRam);
        computerPartSessionBeanLocal.createNewRAM(testRam1);
        computerPartSessionBeanLocal.createNewRAM(testRam2);
        computerPartSessionBeanLocal.createNewRAM(testRam3);

        // create SSDs
        SSD testSSD1 = new SSD("Manufacturer1", "type1", 1, "formFactor1", "Interface1", true, "ssd1", 1.0, 1, "image1");
        SSD testSSD2 = new SSD("Manufacturer2", "type2", 2, "formFactor2", "Interface2", false, "ssd2", 2.0, 2, "image2");
        SSD testSSD3 = new SSD("Manufacturer3", "type3", 3, "formFactor3", "Interface3", true, "ssd3", 3.0, 3, "image3");
        computerPartSessionBeanLocal.createNewSSD(testSSD1);
        computerPartSessionBeanLocal.createNewSSD(testSSD2);
        computerPartSessionBeanLocal.createNewSSD(testSSD3);

        // create CPUAirCoolers and CPUWaterCoolers
        List<String> compatibleCPUChips1 = new ArrayList<>();
        compatibleCPUChips1.add("compatibleCPUChip1");
        compatibleCPUChips1.add("compatibleCPUChip2");
        List<String> compatibleCPUChips2 = new ArrayList<>();
        compatibleCPUChips2.add("compatibleCPUChip1");
        compatibleCPUChips2.add("compatibleCPUChip3");
        List<String> compatibleCPUChips3 = new ArrayList<>();
        compatibleCPUChips2.add("compatibleCPUChip2");
        compatibleCPUChips2.add("compatibleCPUChip3");

        CPUAirCooler testCPUAirCooler1 = new CPUAirCooler("Manufacturer1", "Red", 1, 1.0, compatibleCPUChips1, "CPUAirCooler1", 1.0, 1, "image1");
        CPUAirCooler testCPUAirCooler2 = new CPUAirCooler("Manufacturer2", "Yellow", 2, 2.0, compatibleCPUChips2, "CPUAirCooler2", 2.0, 2, "image2");
        CPUAirCooler testCPUAirCooler3 = new CPUAirCooler("Manufacturer3", "Green", 3, 3.0, compatibleCPUChips3, "CPUAirCooler3", 3.0, 3, "image3");
        computerPartSessionBeanLocal.createNewCPUAirCooler(testCPUAirCooler1);
        computerPartSessionBeanLocal.createNewCPUAirCooler(testCPUAirCooler2);
        computerPartSessionBeanLocal.createNewCPUAirCooler(testCPUAirCooler3);

        CPUWaterCooler testCPUWaterCooler1 = new CPUWaterCooler("Manufacturer1", "Red", 1, compatibleCPUChips1, 1.0, "CPUWaterCooler1", 1.0, 1, "image1");
        CPUWaterCooler testCPUWaterCooler2 = new CPUWaterCooler("Manufacturer1", "Yellow", 2, compatibleCPUChips2, 2.0, "CPUWaterCooler2", 2.0, 2, "image2");
        CPUWaterCooler testCPUWaterCooler3 = new CPUWaterCooler("Manufacturer1", "Green", 3, compatibleCPUChips3, 3.0, "CPUWaterCooler3", 3.0, 3, "image3");
        computerPartSessionBeanLocal.createNewCPUWaterCooler(testCPUWaterCooler1);
        computerPartSessionBeanLocal.createNewCPUWaterCooler(testCPUWaterCooler2);
        computerPartSessionBeanLocal.createNewCPUWaterCooler(testCPUWaterCooler3);

        // create computer cases
        List<String> colours = new ArrayList<>();
        colours.add("white");
        colours.add("black");
        List<String> motherBoardFormFactors = new ArrayList<>();
        motherBoardFormFactors.add("motherBoardFormFactor1");
        motherBoardFormFactors.add("motherBoardFormFactor2");

        ComputerCase testComputerCase1 = new ComputerCase("Manufacturer1", "type1", "white", "sidePanelView", motherBoardFormFactors, 1, 1.0, 1.0, 1.0, 1.0, "Computer Case 1", 1.0, 1, "image1");
        ComputerCase testComputerCase2 = new ComputerCase("Manufacturer2", "type2", "white", "sidePanelView", motherBoardFormFactors, 2, 2.0, 2.0, 2.0, 2.0, "Computer Case 2", 2.0, 2, "image2");
        ComputerCase testComputerCase3 = new ComputerCase("Manufacturer3", "type3", "black", "sidePanelView", motherBoardFormFactors, 3, 3.0, 3.0, 3.0, 3.0, "Computer Case 3", 3.0, 3, "image3");
        ComputerCase testComputerCase4 = new ComputerCase("Manufacturer4", "type4", "black", "sidePanelView", motherBoardFormFactors, 4, 4.0, 4.0, 4.0, 4.0, "Computer Case 4", 4.0, 4, "image4");
        computerPartSessionBeanLocal.createNewComCase(testComputerCase1);
        computerPartSessionBeanLocal.createNewComCase(testComputerCase2);
        computerPartSessionBeanLocal.createNewComCase(testComputerCase3);
        computerPartSessionBeanLocal.createNewComCase(testComputerCase4);

        // create PreBuiltComputerSetModels and ComputerSet
        List<RAM> rams = new ArrayList();
        List<GPU> gpus = new ArrayList();
        List<SSD> ssds = new ArrayList();
        List<HDD> hdds = new ArrayList();
        rams.add(testRam);
        rams.add(testRam1);
        gpus.add(testGpu);
        gpus.add(testGpu2);
        ssds.add(testSSD1);
        ssds.add(testSSD2);
        hdds.add(testHDD1);
        hdds.add(testHDD2);

        // create PreBuiltComputerSetModels
        try {
            PreBuiltComputerSetModel p1 = new PreBuiltComputerSetModel(PreBuiltComputerSetTierEnum.PREMIUM);
//            p3.setCpu(testCpu);
//            p3.setWaterCooler(testCPUWaterCooler1);
//            p3.setCompCase(testComputerCase1);
//            p3.getGpus().add(testGpu);
//            p3.getHdds().add(testHDD1);
//            p3.getSsds().add(testSSD1);
//            p3.setMotherboard(testmb1);
//            p3.setPsu(testPsu);
//            p3.getRams().add(testRam);
//            p3.getRams().add(testRam1);
//            p3.setPrice(3722.0);
//            p3.setIsEnabled(true);
            preBuiltComputerSetModelSessionBeanLocal.createNewPreBuiltComputerSetModel(p1);
            
            PreBuiltComputerSetModel p2 = new PreBuiltComputerSetModel(PreBuiltComputerSetTierEnum.REGULAR);
//            p2.setCpu(testCpu);
//            p2.setWaterCooler(testCPUWaterCooler1);
//            p2.setCompCase(testComputerCase1);
//            p2.getGpus().add(testGpu);
//            p2.getHdds().add(testHDD1);
//            p2.getSsds().add(testSSD1);
//            p2.setMotherboard(testmb1);
//            p2.setPsu(testPsu);
//            p2.getRams().add(testRam);
//            p2.getRams().add(testRam1);
//            p2.setPrice(1500.0);
//            p2.setIsEnabled(true);
            preBuiltComputerSetModelSessionBeanLocal.createNewPreBuiltComputerSetModel(p2);
            
            PreBuiltComputerSetModel p3 = new PreBuiltComputerSetModel(PreBuiltComputerSetTierEnum.BUDGET);
//            p1.setCpu(testCpu);
//            p1.setWaterCooler(testCPUWaterCooler1);
//            p1.setCompCase(testComputerCase1);
//            p1.getGpus().add(testGpu);
//            p1.getHdds().add(testHDD1);
//            p1.getSsds().add(testSSD1);
//            p1.setMotherboard(testmb1);
//            p1.setPsu(testPsu);
//            p1.getRams().add(testRam);
//            p1.getRams().add(testRam1);
//            p1.setPrice(800.0);
//            p1.setIsEnabled(true);
            preBuiltComputerSetModelSessionBeanLocal.createNewPreBuiltComputerSetModel(p3);
  
        } catch (ComputerSetTierAlreadyExistsException ex) {
            System.err.println("Computer Set Tier Already Exists!");
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        
        // create line items (each tied to a product)
        LineItem lineItem1 = new LineItem(testRam, 1);
        LineItem lineItem2 = new LineItem(testPsu, 2);
        LineItem lineItem3 = new LineItem(testmb, 3);
        lineItemSessionBeanLocal.createNewLineItem(lineItem1);
        lineItemSessionBeanLocal.createNewLineItem(lineItem2);
        lineItemSessionBeanLocal.createNewLineItem(lineItem3);
        
        // ******************************************************** To be checked if parts here are compatible
        // create test computer set 
        LineItem lineItem4 = new LineItem(3);
        ComputerSet computerSet = new ComputerSet();
        computerSet.setCpu(testCpu);
        computerSet.setCompCase(testComputerCase1);
        computerSet.getGpus().add(testGpu);
        computerSet.getGpus().add(testGpu2);
        computerSet.getHdds().add(testHDD1);
        computerSet.getHdds().add(testHDD2);
        computerSet.getSsds().add(testSSD1);
        computerSet.setMotherBoard(testmb1);
        computerSet.setPsu(testPsu);
        computerSet.getRams().add(testRam);
        computerSet.getRams().add(testRam1);
        computerSet.setWarrantyLengthInYears(2);
        lineItem4.setComputerSet(computerSet);
        computerSetSessionBeanLocal.createNewComputerSet(lineItem4);

//        // create customer orders
//        lineItemSessionBeanLocal.createNewLineItem(lineItem4);
//        List<LineItem> lineItems = new ArrayList<>();
//        lineItems.add(lineItem1);
//        lineItems.add(lineItem2);
//        lineItems.add(lineItem4);
//        CustomerOrder customerOrder1 = new CustomerOrder(new Date(), true, "2 Middle Kent Ridge Road 119933", lineItems);
//        try {
//            customerOrderSessionBeanLocal.createNewCustomerOrder(customerOrder1, (long) 1);
//        } catch (Exception ex) {
//            System.err.println(ex.getMessage());
//        }        
             
    }

}
