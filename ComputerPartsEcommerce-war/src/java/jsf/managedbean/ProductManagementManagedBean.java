package jsf.managedbean;

import datamodel.StringValue;
import ejb.session.stateless.ComputerPartSessionBeanLocal;
import ejb.session.stateless.PeripheralSessionBeanLocal;
import entity.CPU;
import entity.CPUAirCooler;
import entity.CPUWaterCooler;
import entity.ComputerCase;
import entity.GPU;
import entity.HDD;
import entity.MotherBoard;
import entity.Peripheral;
import entity.PowerSupply;
import entity.Product;
import entity.RAM;
import entity.SSD;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.model.UploadedFile;
import util.exception.ComputerPartNotFoundException;

@Named(value = "productManagementManagedBean")
@ViewScoped
public class ProductManagementManagedBean implements Serializable {

    @EJB(name = "PeripheralSessionBeanLocal")
    private PeripheralSessionBeanLocal peripheralSessionBeanLocal;

    @EJB(name = "ComputerPartSessionBeanLocal")
    private ComputerPartSessionBeanLocal computerPartSessionBeanLocal;

    private String selectedProduct;
    private String selectedProductToCreate;

    private List<? extends Product> products;
    private List<? extends Product> filteredProducts;

    private Product newProduct;

    private Product selectedProductToUpdate;
    private Product selectedProductImage;
    private Product selectedProductToDelete;

    private List<StringValue> stringValues;
    private List<StringValue> stringValues2;
    private Product previousProduct;

    private String stringEdit;
    private String stringEdit2;

    private CPU newCPU;
    private MotherBoard newMotherboard;
    private RAM newRam;
    private PowerSupply newPowerSupply;
    private ComputerCase newComputerCase;
    private GPU newGpu;
    private HDD newHdd;
    private SSD newSsd;
    private CPUWaterCooler newCPUWaterCooler;
    private CPUAirCooler newCPUAirCooler;
    private Peripheral newPeripheral;

    // Product
    private String name;
    private Double price;
    private Integer quantity;
    private String manufacturer;

    // CPU
    private Integer coreCount;
    private Integer TDP;
    private String socket;
    private Boolean hasIntergratedGraphics;
    private Boolean includesCpuCooler;

    // Motherboard
    private String formFactor;
    private String chipset;
    private Integer memorySlot;
    private String colour;
    private Boolean SLICrossFire;
    private Integer PCIEx16;
    private Integer m2Slot;
    private Boolean wiFi;

    // RAM
    private String speed;
    private String type;
    private Integer sticks;
    private Integer perStickGB;
    private Integer CasLatency;

    // PowerSupply
    private String efficiency;
    private Integer wattage;
    private String modularity;
    private Integer SATAConnectors;
    private Integer PCIe6plus2;

    // Computer Case
    private String sidePanelView; //tintered tempered glass
    private Integer fullHeightExpansionSlot; //7
    private Double MaxVideoCardLength; //in mm
    private Double topFanSupport;
    private Double frontFanSupport;
    private Double rearFanSupport;

    // GPU
    private String Interface;
    private Double length;
    private Integer ExpansionSlotWidth;
    private String externalPower;
    private Integer Memory;
    private String MemoryType;

    // HDD
    private Integer Capacity; //in GB

    // SSD
    private Boolean NVME;

    // WaterCooler
    private Integer noiseLevel;
    private Double RadiatorSize;

    // AirCooler
    private Double Height;

    // Peripheral
    private String description;

    private UploadedFile uploadedFile;
    // private String destination = "C:\\IS3106_Project_Images_Src\\"; // windows
    private String destination = "/Users/weidonglim/Documents/IS3106_Project_Images_Src/"; // mac

    public ProductManagementManagedBean() {
        stringValues = new ArrayList<>();
    }

    @PostConstruct
    public void postConstruct() {
        // products = computerPartSessionBeanLocal.retrieveAllCPU();
//        stringValues.add(new StringValue("String One"));
//        stringValues.add(new StringValue("String Two"));
//        stringValues.add(new StringValue("String Three"));
    }

    public void subjectSelectionChanged(AjaxBehaviorEvent event) {
        
        if (selectedProduct.equals("CPU")) {
            products = computerPartSessionBeanLocal.retrieveAllCPU();
        } else if (selectedProduct.equals("MotherBoard")) {
            products = computerPartSessionBeanLocal.retrieveAllMotherBoard();
        } else if (selectedProduct.equals("RAM")) {
            products = computerPartSessionBeanLocal.retrieveAllRAM();
        } else if (selectedProduct.equals("PowerSupply")) {
            products = computerPartSessionBeanLocal.retrieveAllPowerSupply();
        } else if (selectedProduct.equals("ComputerCase")) {
            products = computerPartSessionBeanLocal.retrieveAllComCase();
        } else if (selectedProduct.equals("GPU")) {
            products = computerPartSessionBeanLocal.retrieveAllGPU();
        } else if (selectedProduct.equals("HDD")) {
            products = computerPartSessionBeanLocal.retrieveAllHDD();
        } else if (selectedProduct.equals("SSD")) {
            products = computerPartSessionBeanLocal.retrieveAllSSD();
        } else if (selectedProduct.equals("CPUWaterCooler")) {
            products = computerPartSessionBeanLocal.retrieveAllCPUWaterCooler();
        } else if (selectedProduct.equals("CPUAirCooler")) {
            products = computerPartSessionBeanLocal.retrieveAllCPUAirCooler();
        } else if (selectedProduct.equals("Peripheral")) {
            products = peripheralSessionBeanLocal.retrieveAllPeripherals();
        }
    }

    public void doUpdateProduct(ActionEvent event) {
        setSelectedProductToUpdate((Product) event.getComponent().getAttributes().get("productToUpdate"));

        if (previousProduct == null || !(previousProduct.getProductId().equals(selectedProductToUpdate.getProductId()))) {

            if (selectedProductToUpdate instanceof CPUWaterCooler || selectedProductToUpdate instanceof CPUAirCooler || selectedProductToUpdate instanceof MotherBoard || selectedProductToUpdate instanceof ComputerCase) {
                List<String> temp = computerPartSessionBeanLocal.retrieveAllStringValue(selectedProduct, selectedProductToUpdate.getProductId());

                stringValues = new ArrayList<>();

                // when empty you load
                if (stringValues.isEmpty()) {
                    for (String s : temp) {
                        stringValues.add(new StringValue(s));
                    }
                }

                previousProduct = selectedProductToUpdate;
            }

//            if (selectedProductToUpdate instanceof ComputerCase) {
//                // List<String> coloursTemp = computerPartSessionBeanLocal.retrieveAllCCStringValue("colours", selectedProductToUpdate.getProductId());
//                List<String> mbffTemp = computerPartSessionBeanLocal.retrieveAllCCStringValue("motherBoardFormFactor", selectedProductToUpdate.getProductId());
//
//                stringValues = new ArrayList<>();
//
//                // when empty you load
////                if (stringValues.isEmpty()) {
////                    for (String s1 : coloursTemp) {
////                        stringValues.add(new StringValue(s1));
////                    }
////                }
//
//                stringValues2 = new ArrayList<>();
//
//                if (stringValues2.isEmpty()) {
//                    for (String s2 : mbffTemp) {
//                        stringValues2.add(new StringValue(s2));
//                    }
//                }
//                previousProduct = selectedProductToUpdate;
//            }
        }
    }

    public void addToStringValues(ActionEvent event) {
        stringValues.add(new StringValue("stringEdit"));
    }

    public void addString(ActionEvent event) {
        stringValues.add(new StringValue());
    }

    public void addString2(ActionEvent event) {
        stringValues2.add(new StringValue());
    }

    public void updateProduct(ActionEvent event) {
        List<String> temp = new ArrayList<>();
        List<String> coloursTemp = new ArrayList<>();
        List<String> mbffTemp = new ArrayList<>();

        if (selectedProductToUpdate instanceof CPUWaterCooler || selectedProductToUpdate instanceof CPUAirCooler || selectedProductToUpdate instanceof MotherBoard || selectedProductToUpdate instanceof ComputerCase) {
            for (StringValue s : stringValues) {
                temp.add(s.getValue());
            }
        }

//        if (selectedProductToUpdate instanceof ComputerCase) {
////            for (StringValue s : stringValues) {
////                coloursTemp.add(s.getValue());
////            }
//            for (StringValue s : getStringValues2()) {
//                mbffTemp.add(s.getValue());
//            }
//        }

        try {
            if (selectedProductToUpdate instanceof CPU) {
                computerPartSessionBeanLocal.updateCPU((CPU) selectedProductToUpdate);
            } else if (selectedProductToUpdate instanceof MotherBoard) {
                MotherBoard selectedMotherBoardToUpdate = (MotherBoard) selectedProductToUpdate;
                selectedMotherBoardToUpdate.setSuportedMemorySpeed(temp);
                computerPartSessionBeanLocal.updateMotherBoard((MotherBoard) selectedMotherBoardToUpdate);
            } else if (selectedProductToUpdate instanceof RAM) {
                computerPartSessionBeanLocal.updateRAM((RAM) selectedProductToUpdate);
            } else if (selectedProductToUpdate instanceof PowerSupply) {
                computerPartSessionBeanLocal.updatePowerSupply((PowerSupply) selectedProductToUpdate);
            } else if (selectedProductToUpdate instanceof ComputerCase) {
                ComputerCase selectedComputerCaseToUpdate = (ComputerCase) selectedProductToUpdate;
//                selectedComputerCaseToUpdate.setColours(coloursTemp);
                selectedComputerCaseToUpdate.setMotherBoardFormFactor(temp);
                computerPartSessionBeanLocal.updateComCase((ComputerCase) selectedComputerCaseToUpdate);
            } else if (selectedProductToUpdate instanceof GPU) {
                computerPartSessionBeanLocal.updateGPU((GPU) selectedProductToUpdate);
            } else if (selectedProductToUpdate instanceof HDD) {
                computerPartSessionBeanLocal.updateHDD((HDD) selectedProductToUpdate);
            } else if (selectedProductToUpdate instanceof SSD) {
                computerPartSessionBeanLocal.updateSSD((SSD) selectedProductToUpdate);
            } else if (selectedProductToUpdate instanceof CPUWaterCooler) {
                CPUWaterCooler selectedCPUWaterCoolerToUpdate = (CPUWaterCooler) selectedProductToUpdate;
                selectedCPUWaterCoolerToUpdate.setCPUChipCompatibility(temp);
                computerPartSessionBeanLocal.updateCPUWaterCooler((CPUWaterCooler) selectedCPUWaterCoolerToUpdate);
            } else if (selectedProductToUpdate instanceof CPUAirCooler) {
                CPUAirCooler selectedCPUAirCoolerToUpdate = (CPUAirCooler) selectedProductToUpdate;
                selectedCPUAirCoolerToUpdate.setCPUChipCompatibility(temp);
                computerPartSessionBeanLocal.updateCPUAirCooler((CPUAirCooler) selectedCPUAirCoolerToUpdate);
            } else if (selectedProductToUpdate instanceof Peripheral) {
                peripheralSessionBeanLocal.updatePeripheral((Peripheral) selectedProductToUpdate);
            }

            selectedProductToUpdate = null;
            // stringValues = new ArrayList<>();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Product updated successfully", null));
        } catch (ComputerPartNotFoundException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Computer Part not found error occurred: " + ex.getMessage(), null));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occurred: " + ex.getMessage(), null));
        }
    }

    public void doDeleteProduct(ActionEvent event) {
        setSelectedProductToDelete((Product) event.getComponent().getAttributes().get("productToDelete"));
    }

    public void deleteProduct(ActionEvent event) {
        try {
            if (selectedProductToDelete instanceof Peripheral) {
                peripheralSessionBeanLocal.deletePeripheral(selectedProductToDelete.getProductId());
            } else {
                computerPartSessionBeanLocal.deleteProduct(selectedProduct, selectedProductToDelete.getProductId());
            }

            products.remove(selectedProductToDelete);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Product deleted successfully", null));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occurred: " + ex.getMessage(), null));
        }
    }

    public void upload(String imageName) {
        try {
            File directory = new File(destination);
            if (!directory.exists()) {
                directory.mkdir();
            }

            OutputStream outputStream;
            InputStream contents = uploadedFile.getInputstream();
            outputStream = new FileOutputStream(new File(destination + imageName));

            int read = 0;
            byte[] bytes = new byte[contents.available()];
            while ((read = contents.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            outputStream.flush();
            outputStream.close();

        } catch (IOException ex) {
            System.out.println("EXCEPTION FOR fileupload" + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("EXCEPTION FOR fileupload" + ex.getMessage());
        }
    }

    public void doImageManager(ActionEvent event) {
        setSelectedProductImage((Product) event.getComponent().getAttributes().get("productImageManager"));
    }

    public void imageManager(ActionEvent event) {
        String fileName = uploadedFile.getFileName();

        selectedProductImage.setImage(fileName);
        upload(selectedProductImage.getImage());

        try {
            if (selectedProductImage instanceof CPU) {
                computerPartSessionBeanLocal.updateCPU((CPU) selectedProductImage);
            } else if (selectedProductImage instanceof MotherBoard) {
                computerPartSessionBeanLocal.updateMotherBoard((MotherBoard) selectedProductImage);
            } else if (selectedProductImage instanceof RAM) {
                computerPartSessionBeanLocal.updateRAM((RAM) selectedProductImage);
            } else if (selectedProductImage instanceof PowerSupply) {
                computerPartSessionBeanLocal.updatePowerSupply((PowerSupply) selectedProductImage);
            } else if (selectedProductImage instanceof ComputerCase) {
                computerPartSessionBeanLocal.updateComCase((ComputerCase) selectedProductImage);
            } else if (selectedProductImage instanceof GPU) {
                computerPartSessionBeanLocal.updateGPU((GPU) selectedProductImage);
            } else if (selectedProductImage instanceof HDD) {
                computerPartSessionBeanLocal.updateHDD((HDD) selectedProductImage);
            } else if (selectedProductImage instanceof SSD) {
                computerPartSessionBeanLocal.updateSSD((SSD) selectedProductImage);
            } else if (selectedProductImage instanceof CPUWaterCooler) {
                computerPartSessionBeanLocal.updateCPUWaterCooler((CPUWaterCooler) selectedProductImage);
            } else if (selectedProductImage instanceof CPUAirCooler) {
                computerPartSessionBeanLocal.updateCPUAirCooler((CPUAirCooler) selectedProductImage);
            }

            selectedProductImage = null;
            uploadedFile = null;

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Product image updated successfully", null));
        } catch (ComputerPartNotFoundException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Computer Part not found error occurred: " + ex.getMessage(), null));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occurred: " + ex.getMessage(), null));
        }
    }

    public void doCreateNewProduct(ActionEvent event) {
        if (selectedProduct.equals("CPU")) {
            newCPU = new CPU();
        } else if (selectedProduct.equals("MotherBoard")) {
            newMotherboard = new MotherBoard();
        } else if (selectedProduct.equals("RAM")) {
            newRam = new RAM();
        } else if (selectedProduct.equals("PowerSupply")) {
            newPowerSupply = new PowerSupply();
        } else if (selectedProduct.equals("ComputerCase")) {
            newComputerCase = new ComputerCase();
        } else if (selectedProduct.equals("GPU")) {
            newGpu = new GPU();
        } else if (selectedProduct.equals("HDD")) {
            newHdd = new HDD();
        } else if (selectedProduct.equals("SSD")) {
            newSsd = new SSD();
        } else if (selectedProduct.equals("CPUWaterCooler")) {
            newCPUWaterCooler = new CPUWaterCooler();
        } else if (selectedProduct.equals("CPUAirCooler")) {
            newCPUAirCooler = new CPUAirCooler();
        } else if (selectedProduct.equals("Peripheral")) {
            newPeripheral = new Peripheral();
        }
//FacesContext.getCurrentInstance().getViewRoot().getViewMap().clear();
    }

    public void createNewProduct(ActionEvent event) {
        try {
            String fileName = uploadedFile.getFileName();

            if (selectedProduct.equals("CPU")) {
                newCPU.setImage(fileName);
                upload(newCPU.getImage());

                newCPU.setName(name);
                newCPU.setPrice(price);
                newCPU.setInventoryQuantity(quantity);
                newCPU.setManufacturer(manufacturer);

                newCPU.setCoreCount(coreCount);
                newCPU.setTDP(TDP);
                newCPU.setSocket(socket);
                newCPU.setHasIntergratedGraphics(hasIntergratedGraphics);
                newCPU.setIncludesCpuCooler(includesCpuCooler);

                computerPartSessionBeanLocal.createNewCPU(newCPU);

            } else if (selectedProduct.equals("MotherBoard")) {
                newMotherboard.setImage(fileName);
                upload(newMotherboard.getImage());

                newMotherboard.setName(name);
                newMotherboard.setPrice(price);
                newMotherboard.setInventoryQuantity(quantity);
                newMotherboard.setManufacturer(manufacturer);

                newMotherboard.setFormFactor(formFactor);
                newMotherboard.setSocket(socket);
                newMotherboard.setChipset(chipset);
                newMotherboard.setMemorySlot(memorySlot);
                newMotherboard.setColour(colour);
                newMotherboard.setSLICrossFire(SLICrossFire);
                newMotherboard.setPCIEx16(PCIEx16);
                newMotherboard.setM2Slot(m2Slot);
                newMotherboard.setM2Slot(m2Slot);
                newMotherboard.setWiFi(wiFi);

                String[] values = stringEdit.trim().split(",");
                for (String s : values) {
                    newMotherboard.getSuportedMemorySpeed().add(s);
                }
                computerPartSessionBeanLocal.createNewMotherBoard(newMotherboard);

            } else if (selectedProduct.equals("RAM")) {
                newRam.setImage(fileName);
                upload(newRam.getImage());

                newRam.setName(name);
                newRam.setPrice(price);
                newRam.setInventoryQuantity(quantity);
                newRam.setManufacturer(manufacturer);

                newRam.setSpeed(speed);
                newRam.setType(type);
                newRam.setSticks(sticks);
                newRam.setPerStickGB(perStickGB);
                newRam.setCasLatency(CasLatency);

                computerPartSessionBeanLocal.createNewRAM(newRam);

            } else if (selectedProduct.equals("PowerSupply")) {
                newPowerSupply.setImage(fileName);
                upload(newPowerSupply.getImage());

                newPowerSupply.setName(name);
                newPowerSupply.setPrice(price);
                newPowerSupply.setInventoryQuantity(quantity);
                newPowerSupply.setManufacturer(manufacturer);

                newPowerSupply.setFormFactor(formFactor);
                newPowerSupply.setEfficiency(efficiency);
                newPowerSupply.setWattage(wattage);
                newPowerSupply.setModularity(modularity);
                newPowerSupply.setSATAConnectors(SATAConnectors);
                newPowerSupply.setPCIe6plus2(PCIe6plus2);

                computerPartSessionBeanLocal.createNewPowerSupply(newPowerSupply);

            } else if (selectedProduct.equals("ComputerCase")) {
                newComputerCase.setImage(fileName);
                upload(newComputerCase.getImage());

                newComputerCase.setName(name);
                newComputerCase.setPrice(price);
                newComputerCase.setInventoryQuantity(quantity);
                newComputerCase.setManufacturer(manufacturer);

                newComputerCase.setType(type);
                newComputerCase.setSidePanelView(sidePanelView);
                newComputerCase.setFullHeightExpansionSlot(fullHeightExpansionSlot);
                newComputerCase.setMaxVideoCardLength(MaxVideoCardLength);
                newComputerCase.setTopFanSupport(topFanSupport);
                newComputerCase.setFrontFanSupport(frontFanSupport);
                newComputerCase.setRearFanSupport(rearFanSupport);
                newComputerCase.setColour(colour);
                
//                String[] values = stringEdit.trim().split(",");
                String[] values2 = stringEdit2.trim().split(",");

//                for (String s : values) {
//                    newComputerCase.getColours().add(s);
//                }
                for (String s : values2) {
                    newComputerCase.getMotherBoardFormFactor().add(s);
                }

                computerPartSessionBeanLocal.createNewComCase(newComputerCase);

            } else if (selectedProduct.equals("GPU")) {
                newGpu.setImage(fileName);
                upload(newGpu.getImage());

                newGpu.setName(name);
                newGpu.setPrice(price);
                newGpu.setInventoryQuantity(quantity);
                newGpu.setManufacturer(manufacturer);

                newGpu.setChipset(chipset);
                newGpu.setInterface(Interface);
                newGpu.setLength(length);
                newGpu.setTDP(TDP);
                newGpu.setExpansionSlotWidth(ExpansionSlotWidth);
                newGpu.setExternalPower(externalPower);
                newGpu.setMemory(Memory);
                newGpu.setMemoryType(MemoryType);

                computerPartSessionBeanLocal.createNewGPU(newGpu);

            } else if (selectedProduct.equals("HDD")) {
                newHdd.setImage(fileName);
                upload(newHdd.getImage());

                newHdd.setName(name);
                newHdd.setPrice(price);
                newHdd.setInventoryQuantity(quantity);
                newHdd.setManufacturer(manufacturer);

                newHdd.setType(type);
                newHdd.setCapacity(Capacity);
                newHdd.setFormFactor(formFactor);
                newHdd.setInterface(Interface);

                computerPartSessionBeanLocal.createNewHDD(newHdd);

            } else if (selectedProduct.equals("SSD")) {
                newSsd.setImage(fileName);
                upload(newSsd.getImage());

                newSsd.setName(name);
                newSsd.setPrice(price);
                newSsd.setInventoryQuantity(quantity);
                newSsd.setManufacturer(manufacturer);

                newSsd.setType(type);
                newSsd.setCapacity(Capacity);
                newSsd.setFormFactor(formFactor);
                newSsd.setInterface(Interface);
                newSsd.setNVME(NVME);

                computerPartSessionBeanLocal.createNewSSD(newSsd);

            } else if (selectedProduct.equals("CPUWaterCooler")) {
                newCPUWaterCooler.setImage(fileName);
                upload(newCPUWaterCooler.getImage());

                newCPUWaterCooler.setName(name);
                newCPUWaterCooler.setPrice(price);
                newCPUWaterCooler.setInventoryQuantity(quantity);
                newCPUWaterCooler.setManufacturer(manufacturer);

                newCPUWaterCooler.setColour(colour);
                newCPUWaterCooler.setNoiseLevel(noiseLevel);
                newCPUWaterCooler.setRadiatorSize(RadiatorSize);

                String[] values = stringEdit.trim().split(",");

                for (String s : values) {
                    newCPUWaterCooler.getCPUChipCompatibility().add(s);
                }

                computerPartSessionBeanLocal.createNewCPUWaterCooler(newCPUWaterCooler);

            } else if (selectedProduct.equals("CPUAirCooler")) {
                newCPUAirCooler.setImage(fileName);
                upload(newCPUAirCooler.getImage());

                newCPUAirCooler.setName(name);
                newCPUAirCooler.setPrice(price);
                newCPUAirCooler.setInventoryQuantity(quantity);
                newCPUAirCooler.setManufacturer(manufacturer);

                newCPUAirCooler.setColour(colour);
                newCPUAirCooler.setNoiseLevel(noiseLevel);
                newCPUAirCooler.setHeight(Height);

                String[] values = stringEdit.trim().split(",");

                for (String s : values) {
                    newCPUAirCooler.getCPUChipCompatibility().add(s);
                }
                computerPartSessionBeanLocal.createNewCPUAirCooler(newCPUAirCooler);
            } else if (selectedProduct.equals("Peripheral")) {
                newPeripheral.setImage(fileName);
                upload(newPeripheral.getImage());

                newPeripheral.setName(name);
                newPeripheral.setPrice(price);
                newPeripheral.setInventoryQuantity(quantity);
                newPeripheral.setManufacturer(manufacturer);

                peripheralSessionBeanLocal.createNewPeripheral(newPeripheral);
            }

            stringEdit = "";
            selectedProduct = null;
            uploadedFile = null;

            // refresh the page
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Product created successfully", null));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occurred: " + ex.getMessage(), null));
        }
    }

    public void doNull(ActionEvent event) {
        System.out.println("NULLLLLLLLLL");
    }

    public void changeNull(ActionEvent event) {
        System.out.println("------------- here");
        FacesContext.getCurrentInstance().getViewRoot().getViewMap().clear();
    }

    public String getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(String selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public List<? extends Product> getProducts() {
        return products;
    }

    public void setProducts(List<? extends Product> products) {
        this.products = products;
    }

    public List<? extends Product> getFilteredProducts() {
        return filteredProducts;
    }

    public void setFilteredProducts(List<? extends Product> filteredProducts) {
        this.filteredProducts = filteredProducts;
    }

    public Product getSelectedProductToUpdate() {
        return selectedProductToUpdate;
    }

    public void setSelectedProductToUpdate(Product selectedProductToUpdate) {
        this.selectedProductToUpdate = selectedProductToUpdate;
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public Product getSelectedProductImage() {
        return selectedProductImage;
    }

    public void setSelectedProductImage(Product selectedProductImage) {
        this.selectedProductImage = selectedProductImage;
    }

    public Product getSelectedProductToDelete() {
        return selectedProductToDelete;
    }

    public void setSelectedProductToDelete(Product selectedProductToDelete) {
        this.selectedProductToDelete = selectedProductToDelete;
    }

    public List<StringValue> getStringValues() {
        return stringValues;
    }

    public void setStringValues(List<StringValue> stringValues) {
        this.stringValues = stringValues;
    }

    public List<StringValue> getStringValues2() {
        return stringValues2;
    }

    public void setStringValues2(List<StringValue> stringValues2) {
        this.stringValues2 = stringValues2;
    }

    public Product getNewProduct() {
        return newProduct;
    }

    public void setNewProduct(Product newProduct) {
        this.newProduct = newProduct;
    }

    public String getSelectedProductToCreate() {
        return selectedProductToCreate;
    }

    public void setSelectedProductToCreate(String selectedProductToCreate) {
        this.selectedProductToCreate = selectedProductToCreate;
    }

    public String getStringEdit() {
        return stringEdit;
    }

    public void setStringEdit(String stringEdit) {
        this.stringEdit = stringEdit;
    }

    public String getStringEdit2() {
        return stringEdit2;
    }

    public void setStringEdit2(String stringEdit2) {
        this.stringEdit2 = stringEdit2;
    }

    public CPU getNewCPU() {
        return newCPU;
    }

    public void setNewCPU(CPU newCPU) {
        this.newCPU = newCPU;
    }

    public MotherBoard getNewMotherboard() {
        return newMotherboard;
    }

    public void setNewMotherboard(MotherBoard newMotherboard) {
        this.newMotherboard = newMotherboard;
    }

    public RAM getNewRam() {
        return newRam;
    }

    public void setNewRam(RAM newRam) {
        this.newRam = newRam;
    }

    public PowerSupply getNewPowerSupply() {
        return newPowerSupply;
    }

    public void setNewPowerSupply(PowerSupply newPowerSupply) {
        this.newPowerSupply = newPowerSupply;
    }

    public ComputerCase getNewComputerCase() {
        return newComputerCase;
    }

    public void setNewComputerCase(ComputerCase newComputerCase) {
        this.newComputerCase = newComputerCase;
    }

    public GPU getNewGpu() {
        return newGpu;
    }

    public void setNewGpu(GPU newGpu) {
        this.newGpu = newGpu;
    }

    public HDD getNewHdd() {
        return newHdd;
    }

    public void setNewHdd(HDD newHdd) {
        this.newHdd = newHdd;
    }

    public SSD getNewSsd() {
        return newSsd;
    }

    public void setNewSsd(SSD newSsd) {
        this.newSsd = newSsd;
    }

    public CPUWaterCooler getNewCPUWaterCooler() {
        return newCPUWaterCooler;
    }

    public void setNewCPUWaterCooler(CPUWaterCooler newCPUWaterCooler) {
        this.newCPUWaterCooler = newCPUWaterCooler;
    }

    public CPUAirCooler getNewCPUAirCooler() {
        return newCPUAirCooler;
    }

    public void setNewCPUAirCooler(CPUAirCooler newCPUAirCooler) {
        this.newCPUAirCooler = newCPUAirCooler;
    }

    public Peripheral getNewPeripheral() {
        return newPeripheral;
    }

    public void setNewPeripheral(Peripheral newPeripheral) {
        this.newPeripheral = newPeripheral;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Integer getCoreCount() {
        return coreCount;
    }

    public void setCoreCount(Integer coreCount) {
        this.coreCount = coreCount;
    }

    public Integer getTDP() {
        return TDP;
    }

    public void setTDP(Integer TDP) {
        this.TDP = TDP;
    }

    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }

    public Boolean getHasIntergratedGraphics() {
        return hasIntergratedGraphics;
    }

    public void setHasIntergratedGraphics(Boolean hasIntergratedGraphics) {
        this.hasIntergratedGraphics = hasIntergratedGraphics;
    }

    public Boolean getIncludesCpuCooler() {
        return includesCpuCooler;
    }

    public void setIncludesCpuCooler(Boolean includesCpuCooler) {
        this.includesCpuCooler = includesCpuCooler;
    }

    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }

    public String getChipset() {
        return chipset;
    }

    public void setChipset(String chipset) {
        this.chipset = chipset;
    }

    public Integer getMemorySlot() {
        return memorySlot;
    }

    public void setMemorySlot(Integer memorySlot) {
        this.memorySlot = memorySlot;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public Boolean getSLICrossFire() {
        return SLICrossFire;
    }

    public void setSLICrossFire(Boolean SLICrossFire) {
        this.SLICrossFire = SLICrossFire;
    }

    public Integer getPCIEx16() {
        return PCIEx16;
    }

    public void setPCIEx16(Integer PCIEx16) {
        this.PCIEx16 = PCIEx16;
    }

    public Integer getM2Slot() {
        return m2Slot;
    }

    public void setM2Slot(Integer m2Slot) {
        this.m2Slot = m2Slot;
    }

    public Boolean getWiFi() {
        return wiFi;
    }

    public void setWiFi(Boolean wiFi) {
        this.wiFi = wiFi;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSticks() {
        return sticks;
    }

    public void setSticks(Integer sticks) {
        this.sticks = sticks;
    }

    public Integer getPerStickGB() {
        return perStickGB;
    }

    public void setPerStickGB(Integer perStickGB) {
        this.perStickGB = perStickGB;
    }

    public Integer getCasLatency() {
        return CasLatency;
    }

    public void setCasLatency(Integer CasLatency) {
        this.CasLatency = CasLatency;
    }

    public String getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(String efficiency) {
        this.efficiency = efficiency;
    }

    public Integer getWattage() {
        return wattage;
    }

    public void setWattage(Integer wattage) {
        this.wattage = wattage;
    }

    public String getModularity() {
        return modularity;
    }

    public void setModularity(String modularity) {
        this.modularity = modularity;
    }

    public Integer getSATAConnectors() {
        return SATAConnectors;
    }

    public void setSATAConnectors(Integer SATAConnectors) {
        this.SATAConnectors = SATAConnectors;
    }

    public Integer getPCIe6plus2() {
        return PCIe6plus2;
    }

    public void setPCIe6plus2(Integer PCIe6plus2) {
        this.PCIe6plus2 = PCIe6plus2;
    }

    public String getSidePanelView() {
        return sidePanelView;
    }

    public void setSidePanelView(String sidePanelView) {
        this.sidePanelView = sidePanelView;
    }

    public Integer getFullHeightExpansionSlot() {
        return fullHeightExpansionSlot;
    }

    public void setFullHeightExpansionSlot(Integer fullHeightExpansionSlot) {
        this.fullHeightExpansionSlot = fullHeightExpansionSlot;
    }

    public Double getMaxVideoCardLength() {
        return MaxVideoCardLength;
    }

    public void setMaxVideoCardLength(Double MaxVideoCardLength) {
        this.MaxVideoCardLength = MaxVideoCardLength;
    }

    public Double getTopFanSupport() {
        return topFanSupport;
    }

    public void setTopFanSupport(Double topFanSupport) {
        this.topFanSupport = topFanSupport;
    }

    public Double getFrontFanSupport() {
        return frontFanSupport;
    }

    public void setFrontFanSupport(Double frontFanSupport) {
        this.frontFanSupport = frontFanSupport;
    }

    public Double getRearFanSupport() {
        return rearFanSupport;
    }

    public void setRearFanSupport(Double rearFanSupport) {
        this.rearFanSupport = rearFanSupport;
    }

    public String getInterface() {
        return Interface;
    }

    public void setInterface(String Interface) {
        this.Interface = Interface;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Integer getExpansionSlotWidth() {
        return ExpansionSlotWidth;
    }

    public void setExpansionSlotWidth(Integer ExpansionSlotWidth) {
        this.ExpansionSlotWidth = ExpansionSlotWidth;
    }

    public String getExternalPower() {
        return externalPower;
    }

    public void setExternalPower(String externalPower) {
        this.externalPower = externalPower;
    }

    public Integer getMemory() {
        return Memory;
    }

    public void setMemory(Integer Memory) {
        this.Memory = Memory;
    }

    public String getMemoryType() {
        return MemoryType;
    }

    public void setMemoryType(String MemoryType) {
        this.MemoryType = MemoryType;
    }

    public Integer getCapacity() {
        return Capacity;
    }

    public void setCapacity(Integer Capacity) {
        this.Capacity = Capacity;
    }

    public Boolean getNVME() {
        return NVME;
    }

    public void setNVME(Boolean NVME) {
        this.NVME = NVME;
    }

    public Integer getNoiseLevel() {
        return noiseLevel;
    }

    public void setNoiseLevel(Integer noiseLevel) {
        this.noiseLevel = noiseLevel;
    }

    public Double getRadiatorSize() {
        return RadiatorSize;
    }

    public void setRadiatorSize(Double RadiatorSize) {
        this.RadiatorSize = RadiatorSize;
    }

    public Double getHeight() {
        return Height;
    }

    public void setHeight(Double Height) {
        this.Height = Height;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
