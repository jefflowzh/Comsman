/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedbean;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 *
 * @author bryan
 */
@Named(value = "generatePdfManagedBean")
@RequestScoped
public class GeneratePdfManagedBean {

    @Resource(name = "computerSetDataSource")
    private DataSource computerSetDataSource;

    /**
     * Creates a new instance of GeneratePdfManagedBean
     */
    public GeneratePdfManagedBean() {
    }

//    @PostConstruct
//    public void postConstruct()
//    {
//        Long computerSetId = Long.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("computerSetId"));
//        
//        System.out.println("******** GeneratePdfManagedBean: " + computerSetId);
//        
//    }
    public void generateReport(ActionEvent event) {
        
        try {
            InputStream reportStream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/jasperreports/combine.jasper");
            OutputStream outputStream = FacesContext.getCurrentInstance().getExternalContext().getResponseOutputStream();

            Map parameters = new HashMap();
            
            parameters.put("input", 1);
            
            JasperRunManager.runReportToPdfStream(reportStream, outputStream, parameters, computerSetDataSource.getConnection());
        } catch (IOException | JRException | SQLException ex) {
            Logger.getLogger(GeneratePdfManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
