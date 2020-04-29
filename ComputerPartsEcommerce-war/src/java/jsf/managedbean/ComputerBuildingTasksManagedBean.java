package jsf.managedbean;

import ejb.session.stateless.ComputerSetSessionBeanLocal;
import ejb.session.stateless.CustomerOrderSessionBeanLocal;
import entity.ComputerSet;
import entity.Staff;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import util.exception.ComputerSetNotFoundException;
import util.exception.CustomerOrderNotFoundException;
import util.exception.StaffNotFoundException;

@Named(value = "computerBuildingTasksManagedBean")
@ViewScoped
public class ComputerBuildingTasksManagedBean implements Serializable {

    @EJB(name = "CustomerOrderSessionBeanLocal")
    private CustomerOrderSessionBeanLocal customerOrderSessionBeanLocal;

    @EJB(name = "ComputerSetSessionBeanLocal")
    private ComputerSetSessionBeanLocal computerSetSessionBeanLocal;

    @Resource(name = "computerPartsEcommerceDataSource")
    private DataSource computerPartsEcommerceDataSource;
    
    private List<ComputerSet> computerSets;
    private List<ComputerSet> filteredSets;
    
    public ComputerBuildingTasksManagedBean() {
    }
    
    @PostConstruct
    public void postConstruct() {
        System.out.println("********* postConstruct");
        Staff staff = (Staff) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentStaffEntity");
        setComputerSets(computerSetSessionBeanLocal.retrieveComputerSetsByStaffAssignedTo(staff.getUserId(), Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.FALSE));
        System.out.println("********* computerSets: " + computerSets.size());
    }
    
    public void markAsComplete(ActionEvent event) {
        Long selectedComputerSetId = (Long) event.getComponent().getAttributes().get("selectedComputerSetId");
        try {
            ComputerSet selectedComputerSet = computerSetSessionBeanLocal.retrieveComputerSetById(selectedComputerSetId);
            selectedComputerSet.setAssemblyComplete(Boolean.TRUE);
            computerSetSessionBeanLocal.updateComputerSet(selectedComputerSet, null);
            postConstruct();
            customerOrderSessionBeanLocal.updateOrderStatus(selectedComputerSet.getLineItem().getCustomerOrder().getCustomerOrderId());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Computer Set " + selectedComputerSetId + " completed!", null));
        } catch(StaffNotFoundException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Staff not found!", null));
        } catch (ComputerSetNotFoundException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Computer set not found!", null));
        } catch (CustomerOrderNotFoundException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Computer order not found!", null));
        }
    }
    
    public void generateReport(ActionEvent event) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().responseReset();
            InputStream reportStream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/jasperreports/Invoice_Table_Based.jrxml");
            OutputStream outputStream = FacesContext.getCurrentInstance().getExternalContext().getResponseOutputStream();
            
            JasperRunManager.runReportToPdfStream(reportStream, outputStream, new HashMap<>(), computerPartsEcommerceDataSource.getConnection());
        } catch (IOException | JRException | SQLException ex) {
            Logger.getLogger(ComputerBuildingTasksManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<ComputerSet> getComputerSets() {
        return computerSets;
    }

    public void setComputerSets(List<ComputerSet> computerSets) {
        this.computerSets = computerSets;
    }

    public List<ComputerSet> getFilteredSets() {
        return filteredSets;
    }

    public void setFilteredSets(List<ComputerSet> filteredSets) {
        this.filteredSets = filteredSets;
    }
}
