/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedbean;

import datamodel.StringValue;
import ejb.session.stateless.ComputerPartSessionBeanLocal;
import entity.CPU;
import entity.CPUAirCooler;
import entity.CPUWaterCooler;
import entity.ComputerCase;
import entity.GPU;
import entity.HDD;
import entity.MotherBoard;
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

    public void subjectSelectionChanged(final AjaxBehaviorEvent event) {
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
        }
    }

    public void doUpdateProduct(ActionEvent event) {
        setSelectedProductToUpdate((Product) event.getComponent().getAttributes().get("productToUpdate"));

        if (previousProduct == null || !(previousProduct.getProductId().equals(selectedProductToUpdate.getProductId()))) {

            if (selectedProductToUpdate instanceof CPUWaterCooler || selectedProductToUpdate instanceof CPUAirCooler || selectedProductToUpdate instanceof MotherBoard) {
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

            if (selectedProductToUpdate instanceof ComputerCase) {
                List<String> coloursTemp = computerPartSessionBeanLocal.retrieveAllCCStringValue("colours", selectedProductToUpdate.getProductId());
                List<String> mbffTemp = computerPartSessionBeanLocal.retrieveAllCCStringValue("motherBoardFormFactor", selectedProductToUpdate.getProductId());

                stringValues = new ArrayList<>();

                // when empty you load
                if (stringValues.isEmpty()) {
                    for (String s1 : coloursTemp) {
                        stringValues.add(new StringValue(s1));
                    }
                }

                stringValues2 = new ArrayList<>();

                if (stringValues2.isEmpty()) {
                    for (String s2 : mbffTemp) {
                        stringValues2.add(new StringValue(s2));
                    }
                }
                previousProduct = selectedProductToUpdate;
            }
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

        if (selectedProductToUpdate instanceof CPUWaterCooler || selectedProductToUpdate instanceof CPUAirCooler || selectedProductToUpdate instanceof MotherBoard) {
            for (StringValue s : stringValues) {
                temp.add(s.getValue());
            }
        }

        if (selectedProductToUpdate instanceof ComputerCase) {
            for (StringValue s : stringValues) {
                coloursTemp.add(s.getValue());
            }
            for (StringValue s : getStringValues2()) {
                mbffTemp.add(s.getValue());
            }
        }

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
                selectedComputerCaseToUpdate.setColours(coloursTemp);
                selectedComputerCaseToUpdate.setMotherBoardFormFactor(mbffTemp);
                computerPartSessionBeanLocal.updateComCase((ComputerCase) selectedComputerCaseToUpdate);
                //computerPartSessionBeanLocal.updateComCase((ComputerCase) selectedProductToUpdate);
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
            computerPartSessionBeanLocal.deleteProduct(selectedProduct, selectedProductToDelete.getProductId());

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
            newProduct = new CPU();
        } else if (selectedProduct.equals("MotherBoard")) {
            newProduct = new MotherBoard();
        } else if (selectedProduct.equals("RAM")) {
            newProduct = new RAM();
        } else if (selectedProduct.equals("PowerSupply")) {
            newProduct = new PowerSupply();
        } else if (selectedProduct.equals("ComputerCase")) {
            newProduct = new ComputerCase();
        } else if (selectedProduct.equals("GPU")) {
            newProduct = new GPU();
        } else if (selectedProduct.equals("HDD")) {
            newProduct = new HDD();
        } else if (selectedProduct.equals("SSD")) {
            newProduct = new SSD();
        } else if (selectedProduct.equals("CPUWaterCooler")) {
            newProduct = new CPUWaterCooler();
        } else if (selectedProduct.equals("CPUAirCooler")) {
            newProduct = new CPUAirCooler();
        }
    }

    public void createNewProduct(ActionEvent event) {
        try {
            String fileName = uploadedFile.getFileName();

            newProduct.setImage(fileName);
            upload(newProduct.getImage());

            if (selectedProduct.equals("CPU")) {
                CPU newCPU = (CPU) newProduct;
                computerPartSessionBeanLocal.createNewCPU(newCPU);
            } else if (selectedProduct.equals("MotherBoard")) {
                String[] values = stringEdit.trim().split(",");
                MotherBoard newMotherBoard = (MotherBoard) newProduct;
                for (String s : values) {
                    newMotherBoard.getSuportedMemorySpeed().add(s);
                }
                computerPartSessionBeanLocal.createNewMotherBoard(newMotherBoard);
            } else if (selectedProduct.equals("RAM")) {
                RAM newRAM = (RAM) newProduct;
                computerPartSessionBeanLocal.createNewRAM(newRAM);
            } else if (selectedProduct.equals("PowerSupply")) {
                PowerSupply newPowerSupply = (PowerSupply) newProduct;
                computerPartSessionBeanLocal.createNewPowerSupply(newPowerSupply);
            } else if (selectedProduct.equals("ComputerCase")) {
                String[] values = stringEdit.trim().split(",");
                String[] values2 = stringEdit2.trim().split(",");

                ComputerCase newComputerCase = (ComputerCase) newProduct;

                for (String s : values) {
                    newComputerCase.getColours().add(s);
                }
                for (String s : values2) {
                    newComputerCase.getMotherBoardFormFactor().add(s);
                }

                computerPartSessionBeanLocal.createNewComCase(newComputerCase);
            } else if (selectedProduct.equals("GPU")) {
                GPU newGPU = (GPU) newProduct;
                computerPartSessionBeanLocal.createNewGPU(newGPU);
            } else if (selectedProduct.equals("HDD")) {
                HDD newHDD = (HDD) newProduct;
                computerPartSessionBeanLocal.createNewHDD(newHDD);
            } else if (selectedProduct.equals("SSD")) {
                SSD newSSD = (SSD) newProduct;
                computerPartSessionBeanLocal.createNewSSD(newSSD);
            } else if (selectedProduct.equals("CPUWaterCooler")) {
                String[] values = stringEdit.trim().split(",");
                CPUWaterCooler newCPUWaterCooler = (CPUWaterCooler) newProduct;
                for (String s : values) {
                    newCPUWaterCooler.getCPUChipCompatibility().add(s);
                }
                computerPartSessionBeanLocal.createNewCPUWaterCooler(newCPUWaterCooler);
            } else if (selectedProduct.equals("CPUAirCooler")) {
                String[] values = stringEdit.trim().split(",");
                CPUAirCooler newCPUAirCooler = (CPUAirCooler) newProduct;
                for (String s : values) {
                    newCPUAirCooler.getCPUChipCompatibility().add(s);
                }
                computerPartSessionBeanLocal.createNewCPUAirCooler(newCPUAirCooler);
            }

            stringEdit = "";
            newProduct = null;
            uploadedFile = null;

            // refresh the page
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Product created successfully", null));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occurred: " + ex.getMessage(), null));
        }
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

}
