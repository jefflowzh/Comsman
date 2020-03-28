/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedbean;

import ejb.session.stateless.StaffSessionBeanLocal;
import entity.Staff;
import javax.faces.event.ActionEvent;
import java.io.IOException;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import util.exception.InvalidLoginCredentialException;

/**
 *
 * @author weidonglim
 */
@Named(value = "loginManagedBean")
@RequestScoped
public class LoginManagedBean {

    @EJB
    private StaffSessionBeanLocal staffSessionBeanLocal;
    
    private String email;
    private String password;

    public LoginManagedBean() {
    }

    public void login(ActionEvent event) throws IOException {
        try {
            Staff currentStaffEntity = staffSessionBeanLocal.staffLogin(email, password);
            FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("isLogin", true);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("currentStaffEntity", currentStaffEntity);
            FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/index.xhtml");
        } catch (InvalidLoginCredentialException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid login credential: " + ex.getMessage(), null));
        }
    }

    public void logout(ActionEvent event) throws IOException {
        ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).invalidate();
        FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/login.xhtml");
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
