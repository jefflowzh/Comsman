package ws.restful.model;

import entity.ComputerSet;
import entity.Customer;

public class CustomerLoginRsp {
    
    Customer customer;
    //ComputerSet currComputerBuild;

    public CustomerLoginRsp() {
    }

    public CustomerLoginRsp(Customer customer) //ComputerSet currComputerBuild)
    {
        this.customer = customer;
        //this.currComputerBuild = currComputerBuild;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

//    public ComputerSet getCurrComputerBuild() {
//        return currComputerBuild;
//    }

//    public void setCurrComputerBuild(ComputerSet currComputerBuild) {
//        this.currComputerBuild = currComputerBuild;
//    }

    
    
}
