package ws.restful.model;

public class CustomerRegistrationRsp {
    
    Long newCustomerId;

    public CustomerRegistrationRsp() {
    }

    public CustomerRegistrationRsp(Long newCustomerId) {
        this.newCustomerId = newCustomerId;
    }

    public Long getNewCustomerId() {
        return newCustomerId;
    }

    public void setNewCustomerId(Long newCustomerId) {
        this.newCustomerId = newCustomerId;
    }
    
}
