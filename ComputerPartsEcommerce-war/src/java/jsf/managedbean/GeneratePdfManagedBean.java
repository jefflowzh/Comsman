/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedbean;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author bryan
 */
@Named(value = "generatePdfManagedBean")
@RequestScoped
public class GeneratePdfManagedBean {

    /**
     * Creates a new instance of GeneratePdfManagedBean
     */
    public GeneratePdfManagedBean() {
    }
    
    
    
    @PostConstruct
    public void postConstruct()
    {
        Long computerSetId = Long.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("computerSetId"));
        
        System.out.println("******** GeneratePdfManagedBean: " + computerSetId);
        
    }
    
    
    public void dummy(ActionEvent event)
    {
        
    }
}
