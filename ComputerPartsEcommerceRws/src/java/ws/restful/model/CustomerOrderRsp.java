/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.restful.model;

import entity.LineItem;
import java.util.List;

/**
 *
 * @author zeplh
 */
public class CustomerOrderRsp {
    
    List<LineItem> order;

    public CustomerOrderRsp() {
    }

    public CustomerOrderRsp(List<LineItem> order) {
        this.order = order;
    }

    public List<LineItem> getOrder() {
        return order;
    }

    public void setOrder(List<LineItem> order) {
        this.order = order;
    }

    
    
    
}
