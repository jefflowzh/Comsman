/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.restful.model;

import entity.Customer;

/**
 *
 * @author jeffl
 */
public class UpdateCustomerCartReq {
    
    Long customerId;
    Customer updatedCart;

    public UpdateCustomerCartReq() {
    }

    public UpdateCustomerCartReq(Long customerId, Customer updatedCart) {
        this.customerId = customerId;
        this.updatedCart = updatedCart;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Customer getUpdatedCart() {
        return updatedCart;
    }

    public void setUpdatedCart(Customer updatedCart) {
        this.updatedCart = updatedCart;
    }
    
}
