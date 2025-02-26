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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import util.exception.ComputerSetNotFoundException;
import util.exception.CustomerOrderNotFoundException;
import util.exception.StaffNotFoundException;

@Named(value = "computerBuildingTasksManagedBean")
@ViewScoped
public class ComputerBuildingTasksManagedBean implements Serializable {

    @Resource(name = "computerSetDS")
    private DataSource computerSetDS;

    @EJB(name = "CustomerOrderSessionBeanLocal")
    private CustomerOrderSessionBeanLocal customerOrderSessionBeanLocal;

    @EJB(name = "ComputerSetSessionBeanLocal")
    private ComputerSetSessionBeanLocal computerSetSessionBeanLocal;

//    @Resource(name = "computerPartsEcommerceDataSource")
//    private DataSource computerPartsEcommerceDataSource;
//    @Resource(name = "computerSetDataSource")
//    private DataSource computerSetDataSource;
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
        } catch (StaffNotFoundException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Staff not found!", null));
        } catch (ComputerSetNotFoundException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Computer set not found!", null));
        } catch (CustomerOrderNotFoundException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Computer order not found!", null));
        }
    }

    public void generateReport(ActionEvent event) {
        Long selectedComputerSetId = (Long) event.getComponent().getAttributes().get("selectedComputerSetId");

        try {

//            FacesContext.getCurrentInstance().getExternalContext().responseReset();
//            FacesContext.getCurrentInstance().getExternalContext().setResponseHeader("Content-Disposition", "filename=\"test.pdf\"");
//            InputStream reportStream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/jasperreports/main.jasper");
//            OutputStream outputStream = FacesContext.getCurrentInstance().getExternalContext().getResponseOutputStream();
            Map parameters = new HashMap();

            parameters.put("input", selectedComputerSetId);

            Date date = java.util.Calendar.getInstance().getTime();
            //String path = "/Users/bryan/JaspersoftWorkspace/MyReports/main.jasper";
            //String path = "C:\\Users\\bryan\\Desktop\\3106temp\\GP09\\data\\test\\main.jasper";
            String path = "C:\\test\\main.jasper";
            //String path = "C:/test/main.jasper";
            //String path = "C:/Users/bryan/Desktop/3106temp/GP09/data/test/main.jasper";
            //String path = "/Users/bryan/Documents/NetBeansProjects/Comsman/ComputerPartsEcommerce-war/web/jasperreports/main.jasper";
            //String exportedTo = "C:\\Users\\bryan\\Desktop\\3106temp\\";
            //String exportedTo = "C:\Users\bryan\Desktop\3106temp\";
            //String exportedTo = "C:/Users/bryan/Desktop/3106temp";
            String exportedTo = "C:\\test\\testDestination\\" + date.toString().replace(' ', '_').replace(':', '-') + ".pdf";
            System.out.println("************" + exportedTo);
            JasperRunManager.runReportToPdfFile(path, exportedTo, parameters, computerSetDS.getConnection());
            System.out.println("PASSED RUN");
//            JasperRunManager.runReportToPdfStream(reportStream, outputStream, parameters, computerSetDS.getConnection());

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "PDF Exported to: " + exportedTo + " ", null));
        } catch (JRException | SQLException ex) {
            Logger.getLogger(GeneratePdfManagedBean.class.getName()).log(Level.SEVERE, null, ex);
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
