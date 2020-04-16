/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedbean;

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
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import org.primefaces.model.UploadedFile;
import util.exception.ComputerPartNotFoundException;

@Named(value = "productManagementManagedBean")
@ViewScoped
public class ProductManagementManagedBean implements Serializable {

    @EJB(name = "ComputerPartSessionBeanLocal")
    private ComputerPartSessionBeanLocal computerPartSessionBeanLocal;

    private String selectedProduct;

    private List<? extends Product> products;
    private List<? extends Product> filteredProducts;

    private Product selectedProductToUpdate;
    private Product selectedProductImage;

    private UploadedFile uploadedFile;
    // private String destination = "C:\\IS3106_Project_Images_Src\\"; // windows
    private String destination = "/Users/weidonglim/Documents/IS3106_Project_Images_Src"; // mac

    public ProductManagementManagedBean() {

    }

    @PostConstruct
    public void postConstruct() {
        // products = computerPartSessionBeanLocal.retrieveAllCPU();
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
    }

    public void updateProduct(ActionEvent event) {
        try {
            if (selectedProductToUpdate instanceof CPU) {
                computerPartSessionBeanLocal.updateCPU((CPU) selectedProductToUpdate);
            } else if (selectedProductToUpdate instanceof MotherBoard) {
                computerPartSessionBeanLocal.updateMotherBoard((MotherBoard) selectedProductToUpdate);
            } else if (selectedProductToUpdate instanceof RAM) {
                computerPartSessionBeanLocal.updateRAM((RAM) selectedProductToUpdate);
            } else if (selectedProductToUpdate instanceof PowerSupply) {
                computerPartSessionBeanLocal.updatePowerSupply((PowerSupply) selectedProductToUpdate);
            } else if (selectedProductToUpdate instanceof ComputerCase) {
                computerPartSessionBeanLocal.updateComCase((ComputerCase) selectedProductToUpdate);
            } else if (selectedProductToUpdate instanceof GPU) {
                computerPartSessionBeanLocal.updateGPU((GPU) selectedProductToUpdate);
            } else if (selectedProductToUpdate instanceof HDD) {
                computerPartSessionBeanLocal.updateHDD((HDD) selectedProductToUpdate);
            } else if (selectedProductToUpdate instanceof SSD) {
                computerPartSessionBeanLocal.updateSSD((SSD) selectedProductToUpdate);
            } else if (selectedProductToUpdate instanceof CPUWaterCooler) {
                computerPartSessionBeanLocal.updateCPUWaterCooler((CPUWaterCooler) selectedProductToUpdate);
            } else if (selectedProductToUpdate instanceof CPUAirCooler) {
                computerPartSessionBeanLocal.updateCPUAirCooler((CPUAirCooler) selectedProductToUpdate);
            }

            selectedProductToUpdate = null;

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Product updated successfully", null));
        } catch (ComputerPartNotFoundException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Computer Part not found error occurred: " + ex.getMessage(), null));
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

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Product image updated successfully", null));
        } catch (ComputerPartNotFoundException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Computer Part not found error occurred: " + ex.getMessage(), null));
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

}
