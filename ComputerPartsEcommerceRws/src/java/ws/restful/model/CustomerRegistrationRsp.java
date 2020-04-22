package ws.restful.model;

public class CustomerRegistrationRsp {
    
    Long customerId;

    public CustomerRegistrationRsp() {
    }

    public CustomerRegistrationRsp(Long customerId) {
        this.customerId = customerId;
    }

    public Long getCustomer() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    
}
