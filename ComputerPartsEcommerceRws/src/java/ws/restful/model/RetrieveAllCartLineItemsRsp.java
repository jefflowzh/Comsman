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
 * @author jeffl
 */
public class RetrieveAllCartLineItemsRsp {
    private List<LineItem> cartLineItems;

    public RetrieveAllCartLineItemsRsp() {
    }

    public RetrieveAllCartLineItemsRsp(List<LineItem> cartLineItems) {
        this.cartLineItems = cartLineItems;
    }

    public List<LineItem> getCartLineItems() {
        return cartLineItems;
    }

    public void setCartLineItems(List<LineItem> cartLineItems) {
        this.cartLineItems = cartLineItems;
    }
    

}
