package jsf.managedbean;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named(value = "dummyJsfManagedBean")
@RequestScoped
public class DummyJsfManagedBean {

    public DummyJsfManagedBean() {
    }
    
}
