/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.restful.model;

import entity.CustomerOrder;
import java.util.List;

/**
 *
 * @author zeplh
 */
public class CustomerOrdersRsp {
    private List<CustomerOrder> orders;

    public CustomerOrdersRsp(List<CustomerOrder> orders) {
        this.orders = orders;
    }

    public List<CustomerOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<CustomerOrder> orders) {
        this.orders = orders;
    }

    public CustomerOrdersRsp() {
    }
    
    
}
